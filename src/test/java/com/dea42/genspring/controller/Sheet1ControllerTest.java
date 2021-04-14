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
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.form.Sheet1Form;
import com.dea42.genspring.search.Sheet1SearchForm;

/**
 * Title: Sheet1ControllerTest <br>
 * Description: Sheet1Controller. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
 */
@Slf4j
@WebMvcTest(Sheet1Controller.class)
public class Sheet1ControllerTest extends MockBase {
	private Sheet1 getSheet1(Integer id) {
		Sheet1 o = new Sheet1();
		o.setId(id);
        o.setText(getTestString(7));
		return o;
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#getAllSheet1s(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet1s() throws Exception {
		List<Sheet1> list = new ArrayList<>();
		Sheet1 o = getSheet1(1);
		list.add(o);

		Page<Sheet1> p = getPage(list);
		given(sheet1Services.listAll(new Sheet1SearchForm())).willReturn(p);

		ResultActions ra = getAsAdmin("/sheet1s");
		contentContainsMarkup(ra,"<h1>" + getMsg("class.Sheet1") + " " + getMsg("edit.list") + "</h1>");
//		contentContainsMarkup(ra,getMsg("Sheet1.datefield"));
//		contentContainsMarkup(ra,getMsg("Sheet1.decimalfield"));
//		contentContainsMarkup(ra,getMsg("Sheet1.intfield"));
//		contentContainsMarkup(ra,getTestString(7));
//		contentContainsMarkup(ra,getMsg("Sheet1.text"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#showNewSheet1Page(org.springframework.ui.Model)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowNewSheet1Page() throws Exception {
		ResultActions ra = getAsAdmin("/sheet1s/new");
		contentContainsMarkup(ra,"<legend>" + getMsg("edit.new") + " " + getMsg("class.Sheet1") + "</legend>");
		contentContainsMarkup(ra,getMsg("Sheet1.datefield"));
		contentContainsMarkup(ra,getMsg("Sheet1.decimalfield"));
		// TODO: confirm ignoring Sheet1.id
		contentContainsMarkup(ra,getMsg("Sheet1.intfield"));
		contentContainsMarkup(ra,getMsg("Sheet1.text"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#saveSheet1(com.dea42.genspring.entity.Sheet1, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1Cancel() throws Exception {
		Sheet1 o = getSheet1(1);

		send(SEND_POST, "/sheet1s/save", "sheet1", o, ImmutableMap.of("action", "cancel"), ADMIN_EMAIL,
				"/sheet1s");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#saveSheet1(com.dea42.genspring.entity.Sheet1, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet1Save() throws Exception {
		Sheet1 o = getSheet1(0);
		Sheet1Form form = Sheet1Form.getInstance(o);
		log.debug(form.toString());

		send(SEND_POST, "/sheet1s/save", "sheet1Form", form, ImmutableMap.of("action", "save"), ADMIN_EMAIL,
				"/sheet1s");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#showEditSheet1Page(java.lang.Integer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowEditSheet1Page() throws Exception {
		Sheet1 o = getSheet1(1);

		given(sheet1Services.get(1)).willReturn(o);

		ResultActions ra = getAsAdmin("/sheet1s/edit/1");
		contentContainsMarkup(ra,"Datefield");
		contentContainsMarkup(ra,"Decimalfield");
		// TODO: confirm ignoring Sheet1.id
		contentContainsMarkup(ra,"Intfield");
		contentContainsMarkup(ra,o.getText());
		contentContainsMarkup(ra,"Text");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet1Controller#deleteSheet1(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSheet1() throws Exception {
		getAsAdminRedirectExpected("/sheet1s/delete/1","/sheet1s");
	}

}

