package com.dea42.genspring.search;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dea42.genspring.UnitBase;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.service.Sheet1UserServices;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: sheet1userSearch Test <br>
 * Description: Does regression tests of sheet1user search from service to DB
 * <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class Sheet1UserSearchTest extends UnitBase {

	@Autowired
	private Sheet1UserServices sheet1UserServices;

	private Page<Sheet1User> confirmGotResult(Sheet1UserSearchForm form, Integer expectedID) {
		log.info("form:" + form);
		Page<Sheet1User> list = sheet1UserServices.listAll(form);
		assertNotNull(list, "Checking return not null");
		assertTrue(list.toList().size() > 0, "Checking at least 1 return");
		if (expectedID > 0) {
			boolean found = false;
			for (Sheet1User s2 : list) {
				if (s2.getId().equals(expectedID))
					found = true;
				log.info(s2.toString());
			}

			assertTrue(found, "Looking for record ID " + expectedID + " in results");
		}
		return list;
	}

	private Sheet1User getMidRecord(Sheet1UserSearchForm form, Integer expectedID) {
		Page<Sheet1User> list = confirmGotResult(form, expectedID);
		assertNotNull(list, "Checking return not null");
		int size = list.toList().size();
		assertTrue(size > 0, "Checking at least 1 return");
		int record = 0;
		if (size > 2)
			record = size / 2;
		return list.toList().get(record);

	}

	@Test
	void testSheet1id() {
		// sheet1id Integer 4
		Sheet1User rec = null;
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		rec = getMidRecord(form, 0);
		form.setSheet1idMin(Integer.MIN_VALUE);
		rec = getMidRecord(form, 0);
		log.info("Searching for records with sheet1id of " + rec.getSheet1id());

		form = new Sheet1UserSearchForm();
		form.setSheet1idMin(rec.getSheet1id());
		form.setSheet1idMax(rec.getSheet1id() + 1);
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setSheet1idMin(rec.getSheet1id() - 1);
		form.setSheet1idMax(rec.getSheet1id());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setSheet1idMin(rec.getSheet1id());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setSheet1idMax(rec.getSheet1id());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setSheet1idMin(rec.getSheet1id());
		form.setSheet1idMax(rec.getSheet1id());
		confirmGotResult(form, rec.getId());
	}

	@Test
	void testUserid() {
		// userid Integer 4
		Sheet1User rec = null;
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		rec = getMidRecord(form, 0);
		form.setUseridMin(Integer.MIN_VALUE);
		rec = getMidRecord(form, 0);
		log.info("Searching for records with userid of " + rec.getUserid());

		form = new Sheet1UserSearchForm();
		form.setUseridMin(rec.getUserid());
		form.setUseridMax(rec.getUserid() + 1);
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setUseridMin(rec.getUserid() - 1);
		form.setUseridMax(rec.getUserid());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setUseridMin(rec.getUserid());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setUseridMax(rec.getUserid());
		confirmGotResult(form, rec.getId());

		form = new Sheet1UserSearchForm();
		form.setUseridMin(rec.getUserid());
		form.setUseridMax(rec.getUserid());
		confirmGotResult(form, rec.getId());
	}

	@Test
	void testUseryn() {
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
