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
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.service.AccountServices;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: accountSearch Test <br>
 * Description: Does regression tests of account search from service to DB <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountSearchTest extends UnitBase {

	@Autowired
	private AccountServices accountServices;

	private Page<Account> confirmGotResult(AccountSearchForm form, Integer expectedID) {
		log.info("form:" + form);
		Page<Account> list = accountServices.listAll(form);
		assertNotNull(list, "Checking return not null");
		assertTrue(list.toList().size() > 0, "Checking at least 1 return");
		if (expectedID > 0) {
			boolean found = false;
			for (Account s2 : list) {
				if (s2.getId().equals(expectedID))
					found = true;
				log.info(s2.toString());
			}

			assertTrue(found, "Looking for record ID " + expectedID + " in results");
		}
		return list;
	}

	private Account getMidRecord(AccountSearchForm form, Integer expectedID) {
		Page<Account> list = confirmGotResult(form, expectedID);
		assertNotNull(list, "Checking return not null");
		int size = list.toList().size();
		assertTrue(size > 0, "Checking at least 1 return");
		int record = 0;
		if (size > 2)
			record = size / 2;
		return list.toList().get(record);

	}

	@Test
	void testEmail() {
		// email String 12
		Account rec = null;
		AccountSearchForm form = new AccountSearchForm();
		rec = getMidRecord(form, 0);
		form.setEmail("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with email of " + rec.getEmail());

		form = new AccountSearchForm();
		String text = rec.getEmail();
		if (text.length() < 2) {
			form.setEmail(text + "%");
			confirmGotResult(form, rec.getId());

			form.setEmail("%" + text);
			confirmGotResult(form, rec.getId());
			form.setEmail("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setEmail(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setEmail("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setEmail("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}

	@Test
	void testName() {
		// name String 12
		Account rec = null;
		AccountSearchForm form = new AccountSearchForm();
		rec = getMidRecord(form, 0);
		form.setName("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with name of " + rec.getName());

		form = new AccountSearchForm();
		String text = rec.getName();
		if (text.length() < 2) {
			form.setName(text + "%");
			confirmGotResult(form, rec.getId());

			form.setName("%" + text);
			confirmGotResult(form, rec.getId());
			form.setName("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setName(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setName("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setName("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}

	@Test
	void testPassword() {
		// password String 12
		Account rec = null;
		AccountSearchForm form = new AccountSearchForm();
		rec = getMidRecord(form, 0);
		form.setPassword("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with password of " + rec.getPassword());

		form = new AccountSearchForm();
		String text = rec.getPassword();
		if (text.length() < 2) {
			form.setPassword(text + "%");
			confirmGotResult(form, rec.getId());

			form.setPassword("%" + text);
			confirmGotResult(form, rec.getId());
			form.setPassword("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setPassword(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setPassword("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setPassword("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}

	@Test
	void testUserrole() {
		// userrole String 12
		Account rec = null;
		AccountSearchForm form = new AccountSearchForm();
		rec = getMidRecord(form, 0);
		form.setUserrole("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with userrole of " + rec.getUserrole());

		form = new AccountSearchForm();
		String text = rec.getUserrole();
		if (text.length() < 2) {
			form.setUserrole(text + "%");
			confirmGotResult(form, rec.getId());

			form.setUserrole("%" + text);
			confirmGotResult(form, rec.getId());
			form.setUserrole("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setUserrole(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setUserrole("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setUserrole("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}
}
