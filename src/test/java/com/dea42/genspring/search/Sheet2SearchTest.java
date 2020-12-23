package com.dea42.genspring.search;


import com.dea42.genspring.UnitBase;
import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.search.Sheet2SearchForm;
import com.dea42.genspring.service.Sheet2Services;
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
 * Title: sheet2Search Test <br>
 * Description: Does regression tests of sheet2 search from service to DB <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sheet2SearchTest extends UnitBase {

	@Autowired
	private Sheet2Services sheet2Services;

	private Page<Sheet2> confirmGotResult(Sheet2SearchForm form, Integer expectedID) {
		log.info("form:"+form);
		Page<Sheet2> list = sheet2Services.listAll(form);
		assertNotNull("Checking return not null", list);
		assertTrue("Checking at least 1 return", list.toList().size() > 0);
		if (expectedID > 0) {
			boolean found = false;
			for (Sheet2 s2 : list) {
				if (s2.getId().equals(expectedID))
					found = true;
				log.info(s2.toString());
			}

			assertTrue("Looking for record ID " + expectedID + " in results", found);
		}
		return list;
	}

	private Sheet2 getMidRecord(Sheet2SearchForm form, Integer expectedID) {
		Page<Sheet2> list = confirmGotResult(form, expectedID);
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
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setDateMin(new Date(0));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with date of " + rec.getDate());

		form = new Sheet2SearchForm();
		form.setDateMin(rec.getDate());
		form.setDateMax(new Date(rec.getDate().getTime() + DAY));
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDateMin(new Date(rec.getDate().getTime() - DAY));
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDateMin(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDateMin(rec.getDate());
		form.setDateMax(rec.getDate());
		confirmGotResult(form, rec.getId());

	}

	@Test
	public void testDecimal() {
		// decimal BigDecimal 6
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setDecimalMin(new BigDecimal(Integer.MIN_VALUE));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with decimal of " + rec.getDecimal());

		form = new Sheet2SearchForm();
		form.setDecimalMin(rec.getDecimal());
		form.setDecimalMax(rec.getDecimal().add(new BigDecimal(100)));
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalMin(rec.getDecimal().subtract(new BigDecimal(100)));
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalMin(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalMin(rec.getDecimal());
		form.setDecimalMax(rec.getDecimal());
		confirmGotResult(form, rec.getId());
	}

	@Test
	public void testInteger() {
		// integer Integer 4
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setIntegerMin(Integer.MIN_VALUE);
		rec = getMidRecord(form, 0);
		log.info("Searching for records with integer of " + rec.getInteger());

		form = new Sheet2SearchForm();
		form.setIntegerMin(rec.getInteger());
		form.setIntegerMax(rec.getInteger() + 1);
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerMin(rec.getInteger() - 1);
		form.setIntegerMax(rec.getInteger());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerMin(rec.getInteger());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerMax(rec.getInteger());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerMin(rec.getInteger());
		form.setIntegerMax(rec.getInteger());
		confirmGotResult(form, rec.getId());
	}

	@Test
	public void testText() {
		// text String 12
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setText("%");
		rec = getMidRecord(form, 0);
		log.info("Searching for records with text of " + rec.getText());

		form = new Sheet2SearchForm();
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
