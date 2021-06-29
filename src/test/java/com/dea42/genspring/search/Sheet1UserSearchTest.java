package com.dea42.genspring.search;


import com.dea42.genspring.UnitBase;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.search.Sheet1UserSearchForm;
import com.dea42.genspring.service.Sheet1UserServices;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Title: sheet1userSearch Test <br>
 * Description: Does regression tests of sheet1user search from service to DB <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sheet1UserSearchTest extends UnitBase {

	@Autowired
	private Sheet1UserServices sheet1UserServices;

	private Page<Sheet1User> confirmGotResult(Sheet1UserSearchForm form, Integer expectedID) {
		log.info("form:"+form);
		Page<Sheet1User> list = sheet1UserServices.listAll(form);
		assertNotNull("Checking return not null", list);
		assertTrue("Checking at least 1 return", list.toList().size() > 0);
		if (expectedID > 0) {
			boolean found = false;
			for (Sheet1User s2 : list) {
				if (s2.getId().equals(expectedID))
					found = true;
				log.info(s2.toString());
			}

			assertTrue("Looking for record ID " + expectedID + " in results", found);
		}
		return list;
	}

	private Sheet1User getMidRecord(Sheet1UserSearchForm form, Integer expectedID) {
		Page<Sheet1User> list = confirmGotResult(form, expectedID);
		assertNotNull("Checking return not null", list);
		int size = list.toList().size();
		assertTrue("Checking at least 1 return", size > 0);
		int record = 0;
		if (size > 2)
			record = size / 2;
		return list.toList().get(record);


	}

	@Test
	public void testSheet1() {
		// sheet1 Sheet1 4
		Sheet1User rec = null;
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		rec = getMidRecord(form, 0);
// TODO: skip further tests now
	}

	@Test
	public void testAccount() {
		// account Account 4
		Sheet1User rec = null;
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		rec = getMidRecord(form, 0);
// TODO: skip further tests now
	}

	@Test
	public void testUseryn() {
		// useryn String 12
		Sheet1User rec = null;
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		rec = getMidRecord(form, 0);
		form.setUseryn("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with useryn of " + rec.getUseryn());

		form = new Sheet1UserSearchForm();
		String text = rec.getUseryn();
		if (text.length() < 2) {
			form.setUseryn(text + "%");
			confirmGotResult(form, rec.getId());

			form.setUseryn("%" + text);
			confirmGotResult(form, rec.getId());
			form.setUseryn("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setUseryn(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setUseryn("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setUseryn("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}
}
