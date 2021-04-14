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
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
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
	public void testDatefield() {
		// datefield Date 93
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setDatefieldMin(new Date(0));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with datefield of " + rec.getDatefield());

		form = new Sheet2SearchForm();
		form.setDatefieldMin(rec.getDatefield());
		form.setDatefieldMax(new Date(rec.getDatefield().getTime() + DAY));
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDatefieldMin(new Date(rec.getDatefield().getTime() - DAY));
		form.setDatefieldMax(rec.getDatefield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDatefieldMin(rec.getDatefield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDatefieldMax(rec.getDatefield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDatefieldMin(rec.getDatefield());
		form.setDatefieldMax(rec.getDatefield());
		confirmGotResult(form, rec.getId());

	}

	@Test
	public void testDecimalfield() {
		// decimalfield BigDecimal 6
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setDecimalfieldMin(new BigDecimal(Integer.MIN_VALUE));
		rec = getMidRecord(form, 0);
		log.info("Searching for records with decimalfield of " + rec.getDecimalfield());

		form = new Sheet2SearchForm();
		form.setDecimalfieldMin(rec.getDecimalfield());
		form.setDecimalfieldMax(rec.getDecimalfield().add(new BigDecimal(100)));
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalfieldMin(rec.getDecimalfield().subtract(new BigDecimal(100)));
		form.setDecimalfieldMax(rec.getDecimalfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalfieldMin(rec.getDecimalfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalfieldMax(rec.getDecimalfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setDecimalfieldMin(rec.getDecimalfield());
		form.setDecimalfieldMax(rec.getDecimalfield());
		confirmGotResult(form, rec.getId());
	}

	@Test
	public void testIntegerfield() {
		// integerfield Integer 4
		Sheet2 rec = null;
		Sheet2SearchForm form = new Sheet2SearchForm();
		rec = getMidRecord(form, 0);
		form.setIntegerfieldMin(Integer.MIN_VALUE);
		rec = getMidRecord(form, 0);
		log.info("Searching for records with integerfield of " + rec.getIntegerfield());

		form = new Sheet2SearchForm();
		form.setIntegerfieldMin(rec.getIntegerfield());
		form.setIntegerfieldMax(rec.getIntegerfield() + 1);
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerfieldMin(rec.getIntegerfield() - 1);
		form.setIntegerfieldMax(rec.getIntegerfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerfieldMin(rec.getIntegerfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerfieldMax(rec.getIntegerfield());
		confirmGotResult(form, rec.getId());

		form = new Sheet2SearchForm();
		form.setIntegerfieldMin(rec.getIntegerfield());
		form.setIntegerfieldMax(rec.getIntegerfield());
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
