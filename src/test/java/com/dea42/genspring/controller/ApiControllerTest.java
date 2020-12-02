package com.dea42.genspring.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dea42.genspring.MockBase;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.entity.Sheet1;

/**
 * Title: ApiControllerTest <br>
 * Description: REST Api Controller Test. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.1<br>
 * @version 0.6.1<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest extends MockBase {


	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.AccountController#getAllAccounts(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllAccounts() throws Exception {
		List<Account> list = new ArrayList<>();
		Account o = new Account();
        o.setEmail(getTestEmailString(254));
		o.setId(1);
        o.setPassword(getTestPasswordString(30));
        o.setRole(getTestString(25));
		list.add(o);
		Page<Account> p = getPage(list);
		given(accountServices.listAll(null)).willReturn(p);

		this.mockMvc.perform(get("/api/accounts").with(user("user").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(content().string(containsString(o.getEmail())))
				.andExpect(content().string(containsString("email")))				.andExpect(content().string(containsString("id")))				.andExpect(content().string(containsString(o.getRole())))
				.andExpect(content().string(containsString("role")));
	}


	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#getAllSheet1Users(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet1Users() throws Exception {
		List<Sheet1User> list = new ArrayList<>();
		Sheet1User o = new Sheet1User();
		o.setId(1);
        o.setUseryn(getTestString(1));
		list.add(o);
		Page<Sheet1User> p = getPage(list);
		given(sheet1UserServices.listAll(null)).willReturn(p);

		this.mockMvc.perform(get("/api/sheet1Users").with(user("user").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(content().string(containsString("id")))				.andExpect(content().string(containsString("sheet1")))				.andExpect(content().string(containsString("account")))				.andExpect(content().string(containsString(o.getUseryn())))
				.andExpect(content().string(containsString("useryn")));
	}


	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#getAllSheet2s(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet2s() throws Exception {
		List<Sheet2> list = new ArrayList<>();
		Sheet2 o = new Sheet2();
		o.setId(1);
        o.setText(getTestString(21));
		list.add(o);
		Page<Sheet2> p = getPage(list);
		given(sheet2Services.listAll(null)).willReturn(p);

		this.mockMvc.perform(get("/api/sheet2s").with(user("user").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(content().string(containsString("date")))				.andExpect(content().string(containsString("decimal")))				.andExpect(content().string(containsString("id")))				.andExpect(content().string(containsString("integer")))				.andExpect(content().string(containsString(o.getText())))
				.andExpect(content().string(containsString("text")));
	}


	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#getAllSheet1s(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet1s() throws Exception {
		List<Sheet1> list = new ArrayList<>();
		Sheet1 o = new Sheet1();
		o.setId(1);
        o.setText(getTestString(7));
		list.add(o);
		Page<Sheet1> p = getPage(list);
		given(sheet1Services.listAll(null)).willReturn(p);

		this.mockMvc.perform(get("/api/sheet1s").with(user("user").roles("ADMIN"))).andExpect(status().isOk())
				.andExpect(content().string(containsString("date")))				.andExpect(content().string(containsString("decimal")))				.andExpect(content().string(containsString("id")))				.andExpect(content().string(containsString("intfield")))				.andExpect(content().string(containsString(o.getText())))
				.andExpect(content().string(containsString("text")));
	}

}
