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
import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.form.Sheet2Form;

/**
 * Title: Sheet2ControllerTest <br>
 * Description: Sheet2Controller. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 */
@Slf4j
@WebMvcTest(Sheet2Controller.class)
public class Sheet2ControllerTest extends MockBase {
	private Sheet2 getSheet2(Integer id) {
		Sheet2 o = new Sheet2();
		o.setId(id);
        o.setText(getTestString(21));
		return o;
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#getAllSheet2s(org.springframework.ui.Model)}.
	 */
	@Test
	public void testGetAllSheet2s() throws Exception {
		List<Sheet2> list = new ArrayList<>();
		Sheet2 o = getSheet2(1);
		list.add(o);

		given(sheet2Services.listAll()).willReturn(list);

		ResultActions ra = getAsAdmin("/sheet2s");
		contentContainsMarkup(ra,"<h1>" + getMsg("class.Sheet2") + " " + getMsg("edit.list") + "</h1>");
		contentContainsMarkup(ra,getMsg("Sheet2.date"));
		contentContainsMarkup(ra,getMsg("Sheet2.decimal"));
		contentContainsMarkup(ra,getMsg("Sheet2.integer"));
		contentContainsMarkup(ra,getTestString(21));
		contentContainsMarkup(ra,getMsg("Sheet2.text"));
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#showNewSheet2Page(org.springframework.ui.Model)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowNewSheet2Page() throws Exception {
		ResultActions ra = getAsAdmin("/sheet2s/new");
		contentContainsMarkup(ra,"<legend>" + getMsg("edit.new") + " " + getMsg("class.Sheet2") + "</legend>");
		contentContainsMarkup(ra,"Date");
		contentContainsMarkup(ra,"Decimal");
		// TODO: confirm ignoring Sheet2.id
		contentContainsMarkup(ra,"Integer");
		contentContainsMarkup(ra,"Text");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#saveSheet2(com.dea42.genspring.entity.Sheet2, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet2Cancel() throws Exception {
		Sheet2 o = getSheet2(1);

		send(SEND_POST, "/sheet2s/save", "sheet2", o, ImmutableMap.of("action", "cancel"), ADMIN_USER,
				"/sheet2s");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#saveSheet2(com.dea42.genspring.entity.Sheet2, java.lang.String)}.
	 */
	@Test
	public void testSaveSheet2Save() throws Exception {
		Sheet2 o = getSheet2(0);
		Sheet2Form form = Sheet2Form.getInstance(o);
		log.debug(form.toString());

		send(SEND_POST, "/sheet2s/save", "sheet2Form", form, ImmutableMap.of("action", "save"), ADMIN_USER,
				"/sheet2s");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#showEditSheet2Page(java.lang.Integer)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testShowEditSheet2Page() throws Exception {
		Sheet2 o = getSheet2(1);

		given(sheet2Services.get(1)).willReturn(o);

		ResultActions ra = getAsAdmin("/sheet2s/edit/1");
		contentContainsMarkup(ra,"Date");
		contentContainsMarkup(ra,"Decimal");
		// TODO: confirm ignoring Sheet2.id
		contentContainsMarkup(ra,"Integer");
		contentContainsMarkup(ra,o.getText());
		contentContainsMarkup(ra,"Text");
	}

	/**
	 * Test method for
	 * {@link com.dea42.genspring.controller.Sheet2Controller#deleteSheet2(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteSheet2() throws Exception {
		getAsAdminRedirectExpected("/sheet2s/delete/1","/sheet2s");
	}

}

