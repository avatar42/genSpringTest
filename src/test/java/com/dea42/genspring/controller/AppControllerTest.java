package com.dea42.genspring.controller;

import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.dea42.genspring.MockBase;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.form.AccountForm;
import com.dea42.genspring.form.LoginForm;
import com.dea42.genspring.utils.MessageHelper;
import com.google.common.collect.ImmutableMap;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTest extends MockBase {

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.AppController#getIndex()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIndex() throws Exception {
		ResultActions result = getAsNoOne("/");
		contentContainsKey(result, MessageHelper.view_index_title);
		contentContainsKey(result, MessageHelper.app_name);
		contentContainsKey(result, MessageHelper.signin_signup);
		result = getAsAdmin("/");
		contentContainsKey(result, MessageHelper.view_index_title);
		contentContainsKey(result, MessageHelper.index_greeting);
	}

	/**
	 * quick check / maps to /home
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHome() throws Exception {
		ResultActions result = getAsNoOne("/home");
		contentContainsKey(result, MessageHelper.view_index_title);
	}

	/**
	 * check text on sign up page
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSignupModelString() throws Exception {
		ResultActions result = getAsNoOne("/signup");
		contentContainsKey(result, MessageHelper.signin_email);
		contentContainsKey(result, MessageHelper.signin_password);
		contentContainsKey(result, MessageHelper.signin_haveAccount);
		contentContainsKey(result, MessageHelper.signin_signin);
		contentContainsKey(result, MessageHelper.signin_signup);
	}

	/**
	 * used only for AppController.login() testing
	 * 
	 * @param email
	 * @param password
	 */
	public LoginForm getLoginInstance(String email, String password) {
		LoginForm a = new LoginForm();
		a.setEmail(email);
		a.setPassword(password);
		return a;
	}

	/**
	 * Get good form to mod for specific errors
	 * 
	 * @param account
	 * @return
	 */
	private AccountForm getInstance(Account account) {
		AccountForm accountForm = AccountForm.getInstance(account);
		accountForm.setPassword(account.getPassword());
		accountForm.setPasswordConfirm(account.getPassword());
		return accountForm;
	}

	/**
	 * Test signup TODO: sort best way to test this
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSignupAccountFormErrorsRedirectAttributes() throws Exception {
		Account account = getDefaultUserAccount();
		account.setId(0);

		given(accountServices.save(account)).willReturn(account);
		given(accountServices.login(account.getEmail(), account.getPassword())).willReturn(true);
		given(accountServices.isEmailAlreadyInUse(ADMIN_USER)).willReturn(true);
		given(accountServices.isEmailAlreadyInUse(TEST_USER)).willReturn(false);

		AccountForm accountForm = getInstance(account);
		ResultActions result = send(SEND_POST, "/signup", "accountForm", accountForm, ImmutableMap.of("action", "save"),
				null, "/home");
		expectSuccessMsg(result, MessageHelper.signup_success,TEST_USER);

//		send(SEND_GET, "/home", null, null, null, ADMIN_USER, null);
//		result.andDo(MockMvcResultHandlers.print());
//		contentContainsKey(result, MessageHelper.app_name);
//		contentContainsKey(result, MessageHelper.app_description);
//		String appName = Utils.getProp(getMsgBundle(), MessageHelper.app_name, "??");
//		contentContainsKey(result, MessageHelper.index_greeting, appName);

		accountForm = getInstance(account);
		accountForm.setPassword(" ");
		accountForm.setPasswordConfirm(" ");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);

		accountForm = getInstance(account);
		accountForm.setEmail(ADMIN_USER);
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.email_unique);

		accountForm = getInstance(account);
		accountForm.setEmail("admin");
		accountForm.setPasswordConfirm("bad password");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.INSUFFICIENT_UPPERCASE, "1");
		contentContainsKey(result, MessageHelper.INSUFFICIENT_DIGIT, "1");
		contentContainsKey(result, MessageHelper.INSUFFICIENT_SPECIAL, "1");

		accountForm.setPasswordConfirm("BAD_PASSWORD");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.INSUFFICIENT_LOWERCASE, "1");
		contentContainsKey(result, MessageHelper.INSUFFICIENT_DIGIT, "1");

		accountForm.setPasswordConfirm("P@$$w1rd");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.password_mismatch);

		accountForm.setPassword("P@$$w1r");
		accountForm.setPasswordConfirm("P@$$w1r");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.TOO_SHORT, "8", "30");

		accountForm.setPassword(getTestPasswordString(31));
		accountForm.setPasswordConfirm(getTestPasswordString(31));
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.TOO_LONG, "8", "30");

		accountForm = getInstance(account);
		accountForm.setEmail("admin");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.email_message);

		accountForm = getInstance(account);
		accountForm.setEmail("");
		result = send(SEND_POST, "/signup", "accountForm", accountForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);
	}

	/**
	 * check text on sign in page
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginHttpServletRequestAccountFormStringString() throws Exception {
		ResultActions result = getAsNoOne("/login");
		contentContainsKey(result, MessageHelper.signin_email);
		contentContainsKey(result, MessageHelper.signin_password);
		contentContainsKey(result, MessageHelper.signin_rememberMe);
		contentContainsKey(result, MessageHelper.signin_signin);
		contentContainsKey(result, MessageHelper.signin_newHere);
		contentContainsKey(result, MessageHelper.signin_signup);
	}

	/**
	 * Test sign in
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginModelAccountFormErrorsRedirectAttributes() throws Exception {
		// set up
		Account account = getDefaultAdminAccount();
		account.setId(0);

		given(accountServices.save(account)).willReturn(account);
		given(accountServices.login(account.getEmail(), account.getPassword())).willReturn(true);

		// happy path test
		LoginForm loginForm = getLoginInstance(ADMIN_USER, ADMIN_PASS);
		ResultActions result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, "/home");
		expectSuccessMsg(result, MessageHelper.signin_success,ADMIN_USER);
		contentNotContainsKey(result, MessageHelper.form_errors);

		// failure tests
		loginForm = getLoginInstance(ADMIN_USER, "bad pass");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.signin_failed);

		loginForm = getLoginInstance(ADMIN_USER, "");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);

		loginForm = getLoginInstance("", "bad pass");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);

		loginForm = getLoginInstance(ADMIN_USER, " ");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);

		loginForm = getLoginInstance("	", "bad pass");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.notBlank_message);

		loginForm = getLoginInstance("admin", "bad pass");
		result = send(SEND_POST, "/authenticate", "loginForm", loginForm, null, null, null);
		contentContainsKey(result, MessageHelper.email_message);
		contentNotContainsKey(result, MessageHelper.notBlank_message);
	}

	/**
	 * quick test lang swap
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetInternationalPage() throws Exception {
		getAsAdminRedirectExpected("/international", "/home");
		getAsNoOneRedirectExpected("/international", "/home");
	}

}
