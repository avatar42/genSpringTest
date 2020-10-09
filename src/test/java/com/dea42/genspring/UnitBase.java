package com.dea42.genspring;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dea42.genspring.controller.AppController;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public abstract class UnitBase extends TestCase {
	private static final String strVal = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890abcdefghijklmnopqrstuvwzyz";
	protected static final Logger LOGGER = LoggerFactory.getLogger(UnitBase.class.getName());

	public static final String SEND_GET = "GET";
	public static final String SEND_POST = "POST";

	protected ResourceBundle appBundle;
	protected ResourceBundle msgEnBundle;

	protected String lang = "en";

	protected MockMvc mockMvc;

	protected Set<String> headless = new HashSet<String>();

	protected static int TEST_USER_ID;
	protected static String TEST_USER;
	protected static String TEST_PASS;
	protected static String TEST_ROLE;

	protected static int ADMIN_USER_ID;
	protected static String ADMIN_USER;
	protected static String ADMIN_PASS;
	protected static String ADMIN_ROLE;

	public UnitBase() {
		appBundle = ResourceBundle.getBundle("app");
		msgEnBundle = ResourceBundle.getBundle("messages");

		if (TEST_USER == null) {
			TEST_USER_ID = Utils.getProp(appBundle, "default.userid", 1);
			TEST_USER = Utils.getProp(appBundle, "default.user", null);
			TEST_PASS = Utils.getProp(appBundle, "default.userpass", null);
			TEST_ROLE = Utils.getProp(appBundle, "default.userrole", null);
		}
		if (ADMIN_USER == null) {
			ADMIN_USER_ID = Utils.getProp(appBundle, "default.adminid", 2);
			ADMIN_USER = Utils.getProp(appBundle, "default.admin", null);
			ADMIN_PASS = Utils.getProp(appBundle, "default.adminpass", null);
			ADMIN_ROLE = Utils.getProp(appBundle, "default.adminrole", null);
		}

		headless.add("/login");
		headless.add("/signup");
		headless.add("/authenticate");
		headless.add(AppController.SIGNUP_VIEW_NAME);
		headless.add(AppController.SIGNIN_VIEW_NAME);
		headless.add(AppController.HOME_SIGNED_VIEW_NAME);
		headless.add(AppController.HOME_NOT_SIGNED_VIEW_NAME);

	}

	protected Account getDefaultAdminAccount() {
		Account a = new Account();
		a.setEmail(ADMIN_USER);
		a.setId(ADMIN_USER_ID);
		a.setPassword(ADMIN_PASS);
		a.setRole(ADMIN_ROLE);
		return a;
	}

	protected Account getDefaultUserAccount() {
		Account a = new Account();
		a.setEmail(TEST_USER);
		a.setId(TEST_USER_ID);
		a.setPassword(TEST_PASS);
		a.setRole(TEST_ROLE);
		return a;
	}

	/**
	 * Create a max length email string for testing
	 * 
	 * @param length
	 * @return
	 */
	protected String getTestPasswordString(int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("P@$$w0rd");
		if (length > 8)
			sb.append(getTestString(length - 8));

		return sb.toString();
	}

	/**
	 * Create a max length email string for testing
	 * 
	 * @param length
	 * @return
	 */
	protected String getTestEmailString(int length) {
		int partLen = length / 3;
		// user must be 60 or less to pass built in Email validator though RFC allows 64
		if (partLen > 60)
			partLen = 60;
		int diff = partLen - 2;
		StringBuilder sb = new StringBuilder();
		sb.append(getTestString(partLen));
		sb.append("@");
		sb.append(getTestString(diff));
		sb.append(".");
		sb.append(getTestString(partLen));

		return sb.toString();
	}

	/**
	 * Create a max length string for testing
	 * 
	 * @param length
	 * @return
	 */
	protected String getTestString(int length) {
		if (length < strVal.length()) {
			return strVal.substring(0, length);
		}

		StringBuilder sb = new StringBuilder(strVal);
		while (sb.length() < length) {
			sb.append(strVal);
		}

		return sb.toString().substring(0, length);
	}

	/**
	 * get the bundle for the current lang
	 * 
	 * @return
	 */
	protected ResourceBundle getMsgBundle() {
		if ("en".equals(lang))
			return msgEnBundle;

		try {
			return ResourceBundle.getBundle("messages_" + lang);
		} catch (Exception e) {
			LOGGER.warn("messages_" + lang + " not found");
			// return en bundle
		}

		return msgEnBundle;
	}

	/**
	 * convenience wrapper for Utils.getProp(getMsgBundle(), key, key)
	 * 
	 * @param key
	 * @return String from props file or key is not found
	 */
	protected String getMsg(String key) {
		return Utils.getProp(getMsgBundle(), key, key);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
