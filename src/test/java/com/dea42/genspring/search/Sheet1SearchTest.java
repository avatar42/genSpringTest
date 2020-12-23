package com.dea42.genspring.search;


import com.dea42.genspring.UnitBase;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.search.Sheet1SearchForm;
import com.dea42.genspring.service.Sheet1Services;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Title: sheet1Search Test <br>
 * Description: Does regression tests of sheet1 search from service to DB <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sheet1SearchTest extends UnitBase {

	@Autowired
	private Sheet1Services sheet1Services;

	private Page<Sheet1> confirmGotResult(Sheet1SearchForm form, Integer expectedID) {
		log.info("form:"+form);
		Page<Sheet1> list = sheet1Services.listAll(form);
		assertNotNull("Checking return not null", list);
		assertTrue("Checking at least 1 return", list.toList().size() > 0);
		if (expectedID > 0) {
			boolean found = false;
			for (Sheet1 s2 : list) {
				if (s2.getId().equals(expectedID))
					found = true;
				log.info(s2.toString());
			}

			assertTrue("Looking for record ID " + expectedID + " in results", found);
		}
		return list;
	}

	private Sheet1 getMidRecord(Sheet1SearchForm form, Integer expectedID) {
		Page<Sheet1> list = confirmGotResult(form, expectedID);
		assertNotNull("Checking return not null", list);
		int size = list.toList().size();
		assertTrue("Checking at least 1 return", size > 0);
		int record = 0;
		if (size > 2)
			record = size / 2;
		return list.toList().get(record);


	}

	@Test
	public void testDate() {
		// date Date 93
		Sheet1 rec = null;
		Sheet1SearchForm form = new Sheet1SearchForm();
		rec = getMidRecord(form, 0);
		form.setDateMin(new Date(0));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with date of " + rec.getDate());

		form = new Sheet1SearchForm();
		form.setDateMin(rec.getDate());
		form.setDateMax(new Date(rec.getDate().getTime() + DAY));
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDateMin(new Date(rec.getDate().getTime() - DAY));
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDateMin(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDateMin(rec.getDate());
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

	}

	@Test
	public void testDecimal() {
		// decimal BigDecimal 6
		Sheet1 rec = null;
		Sheet1SearchForm form = new Sheet1SearchForm();
		rec = getMidRecord(form, 0);
		form.setDecimalMin(new BigDecimal(Integer.MIN_VALUE));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with decimal of " + rec.getDecimal());

		form = new Sheet1SearchForm();
		form.setDecimalMin(rec.getDecimal());
		form.setDecimalMax(rec.getDecimal().add(new BigDecimal(100)));
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDecimalMin(rec.getDecimal().subtract(new BigDecimal(100)));
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDecimalMin(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setDecimalMin(rec.getDecimal());
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());
	}

	@Test
	public void testIntfield() {
		// intfield Integer 4
		Sheet1 rec = null;
		Sheet1SearchForm form = new Sheet1SearchForm();
		rec = getMidRecord(form, 0);
		form.setIntfieldMin(Integer.MIN_VALUE);
		rec = getMidRecord(form, 0);
		log.info("Searching for records with intfield of " + rec.getIntfield());

		form = new Sheet1SearchForm();
		form.setIntfieldMin(rec.getIntfield());
		form.setIntfieldMax(rec.getIntfield() + 1);
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setIntfieldMin(rec.getIntfield() - 1);
		form.setIntfieldMax(rec.getIntfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setIntfieldMin(rec.getIntfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setIntfieldMax(rec.getIntfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet1SearchForm();
		form.setIntfieldMin(rec.getIntfield());
		form.setIntfieldMax(rec.getIntfield());
		confirmGotResult(form, rec.getId());
	}

	@Test
	public void testText() {
		// text String 12
		Sheet1 rec = null;
		Sheet1SearchForm form = new Sheet1SearchForm();
		rec = getMidRecord(form, 0);
		form.setText("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with text of " + rec.getText());

		form = new Sheet1SearchForm();
		String text = rec.getText();
		if (text.length() < 2) {
			form.setText(text + "%");
			confirmGotResult(form, rec.getId());

			form.setText("%" + text);
			confirmGotResult(form, rec.getId());
			form.setText("%" + text + "%");
			confirmGotResult(form, rec.getId());
		} else {
			int mid = text.length() / 2;
			form.setText(text.substring(0, mid) + "%");
			confirmGotResult(form, rec.getId());

			form.setText("%" + text.substring(mid - 1, mid) + "%");
			confirmGotResult(form, rec.getId());
			form.setText("%" + text.substring(mid, text.length()));
			confirmGotResult(form, rec.getId());
		}
	}
}
