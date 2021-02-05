package com.dea42.genspring.controller;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.ResultActions;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import com.dea42.genspring.MockBase;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.form.Sheet1UserForm;
import com.dea42.genspring.search.Sheet1UserSearchForm;

/**
 * Title: Sheet1UserControllerTest <br>
 * Description: Sheet1UserController. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Slf4j
@WebMvcTest(Sheet1UserController.class)
public class Sheet1UserControllerTest extends MockBase {
	private Sheet1User getSheet1User(Integer id) {
		Sheet1User o = new Sheet1User();
		o.setId(id);
        o.setUseryn(getTestString(1));
		return o;
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#getAllSheet1Users(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet1Users() throws Exception {
		List<Sheet1User> list = new ArrayList<>();
		Sheet1User o = getSheet1User(1);
		list.add(o);

		Page<Sheet1User> p = getPage(list);
		given(sheet1UserServices.listAll(new Sheet1UserSearchForm())).willReturn(p);

		ResultActions ra = getAsAdmin("/sheet1Users");
		contentContainsMarkup(ra,"<h1>" + getMsg("class.Sheet1User") + " " + getMsg("edit.list") + "</h1>");
//		contentContainsMarkup(ra,getMsg("Sheet1User.sheet1id"));
//		contentContainsMarkup(ra,getMsg("Sheet1User.userid"));
//		contentContainsMarkup(ra,getTestString(1));
//		contentContainsMarkup(ra,getMsg("Sheet1User.useryn"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#showNewSheet1UserPage(org.springframework.ui.Model)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowNewSheet1UserPage() throws Exception {
		ResultActions ra = getAsAdmin("/sheet1Users/new");
		contentContainsMarkup(ra,"<legend>" + getMsg("edit.new") + " " + getMsg("class.Sheet1User") + "</legend>");
		// TODO: confirm ignoring Sheet1User.id
		contentContainsMarkup(ra,getMsg("Sheet1User.sheet1id"));
		contentContainsMarkup(ra,getMsg("Sheet1User.userid"));
		contentContainsMarkup(ra,getMsg("Sheet1User.useryn"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#saveSheet1User(com.dea42.genspring.entity.Sheet1User, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1UserCancel() throws Exception {
		Sheet1User o = getSheet1User(1);

		send(SEND_POST, "/sheet1Users/save", "sheet1User", o, ImmutableMap.of("action", "cancel"), ADMIN_EMAIL,
				"/sheet1Users");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#saveSheet1User(com.dea42.genspring.entity.Sheet1User, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1UserSave() throws Exception {
		Sheet1User o = getSheet1User(0);
		Sheet1UserForm form = Sheet1UserForm.getInstance(o);
		log.debug(form.toString());

		send(SEND_POST, "/sheet1Users/save", "sheet1UserForm", form, ImmutableMap.of("action", "save"), ADMIN_EMAIL,
				"/sheet1Users");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#showEditSheet1UserPage(java.lang.Integer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowEditSheet1UserPage() throws Exception {
		Sheet1User o = getSheet1User(1);

		given(sheet1UserServices.get(1)).willReturn(o);

		ResultActions ra = getAsAdmin("/sheet1Users/edit/1");
		// TODO: confirm ignoring Sheet1User.id
		contentContainsMarkup(ra,"Sheet1");
		contentContainsMarkup(ra,"Account");
		contentContainsMarkup(ra,o.getUseryn());
		contentContainsMarkup(ra,"Useryn");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1UserController#deleteSheet1User(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSheet1User() throws Exception {
		getAsAdminRedirectExpected("/sheet1Users/delete/1","/sheet1Users");
	}

}

