package com.dea42.genspring.controller;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import com.dea42.genspring.MockBase;
import com.dea42.genspring.entity.Sheet1user;
import com.dea42.genspring.form.Sheet1userForm;

/**
 * Title: Sheet1userControllerTest <br>
 * Description: Sheet1userController. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 */
@Slf4j
@WebMvcTest(Sheet1userController.class)
public class Sheet1userControllerTest extends MockBase {
	private Sheet1user getSheet1user(Integer id) {
		Sheet1user o = new Sheet1user();
		o.setId(id);
        o.setUseryn(getTestString(1));
		return o;
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#getAllSheet1users(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet1users() throws Exception {
		List<Sheet1user> list = new ArrayList<>();
		Sheet1user o = getSheet1user(1);
		list.add(o);

		given(sheet1userServices.listAll()).willReturn(list);

		ResultActions ra = getAsAdmin("/sheet1users");
		contentContainsMarkup(ra,"<h1>" + getMsg("class.Sheet1user") + " " + getMsg("edit.list") + "</h1>");
		contentContainsMarkup(ra,getMsg("Sheet1user.sheet1id"));
		contentContainsMarkup(ra,getMsg("Sheet1user.userid"));
		contentContainsMarkup(ra,getTestString(1));
		contentContainsMarkup(ra,getMsg("Sheet1user.useryn"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#showNewSheet1userPage(org.springframework.ui.Model)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowNewSheet1userPage() throws Exception {
		ResultActions ra = getAsAdmin("/sheet1users/new");
		contentContainsMarkup(ra,"<legend>" + getMsg("edit.new") + " " + getMsg("class.Sheet1user") + "</legend>");
		// TODO: confirm ignoring Sheet1user.id
		contentContainsMarkup(ra,"Sheet1");
		contentContainsMarkup(ra,"Account");
		contentContainsMarkup(ra,"Useryn");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#saveSheet1user(com.dea42.genspring.entity.Sheet1user, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1userCancel() throws Exception {
		Sheet1user o = getSheet1user(1);

		send(SEND_POST, "/sheet1users/save", "sheet1user", o, ImmutableMap.of("action", "cancel"), ADMIN_USER,
				"/sheet1users");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#saveSheet1user(com.dea42.genspring.entity.Sheet1user, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1userSave() throws Exception {
		Sheet1user o = getSheet1user(0);
		Sheet1userForm form = Sheet1userForm.getInstance(o);
		log.debug(form.toString());

		send(SEND_POST, "/sheet1users/save", "sheet1userForm", form, ImmutableMap.of("action", "save"), ADMIN_USER,
				"/sheet1users");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#showEditSheet1userPage(java.lang.Integer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowEditSheet1userPage() throws Exception {
		Sheet1user o = getSheet1user(1);

		given(sheet1userServices.get(1)).willReturn(o);

		ResultActions ra = getAsAdmin("/sheet1users/edit/1");
		// TODO: confirm ignoring Sheet1user.id
		contentContainsMarkup(ra,"Sheet1");
		contentContainsMarkup(ra,"Account");
		contentContainsMarkup(ra,o.getUseryn());
		contentContainsMarkup(ra,"Useryn");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1userController#deleteSheet1user(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSheet1user() throws Exception {
		getAsAdminRedirectExpected("/sheet1users/delete/1","/sheet1users");
	}

}

