package com.dea42.genspring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.dea42.genspring.UnitBase;
import com.dea42.genspring.form.ValidatorTestForm;
import com.dea42.genspring.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Based on
 * https://memorynotfound.com/field-matching-bean-validation-annotation-example/
 * 
 * @author avata
 *
 */
@Slf4j
public class FieldMatchConstraintValidatorTest extends UnitBase {

	private static Validator validator;

	@BeforeAll
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Get good form to mod for specific errors
	 * 
	 * @param account
	 * @return
	 */
	private ValidatorTestForm getInstance() {
		ValidatorTestForm accountForm = new ValidatorTestForm();
		accountForm.setEmail(TEST_EMAIL);
		accountForm.setPassword(TEST_PASS);
		accountForm.setPasswordConfirm(TEST_PASS);
		accountForm.setUserrole(TEST_ROLE);
		return accountForm;
	}

	private void validate(Set<ConstraintViolation<ValidatorTestForm>> constraintViolations, int expectErrorCnt,
			String... keys) {
		// check expected errors are found
		List<String> errs = new ArrayList<String>();
		for (ConstraintViolation<ValidatorTestForm> c : constraintViolations) {
			log.debug(c.getMessageTemplate());
			errs.add(c.getMessageTemplate());
		}
		for (String err : keys) {
			assertTrue(errs.contains(err), "Looking for '" + err + "' in errors");
		}

		if (expectErrorCnt != constraintViolations.size()) {
			if (keys != null) {
				List<String> errKeys = Arrays.asList(keys);
				for (String err : errs) {
					if (!errKeys.contains(err))
						log.error("Unexpected error '" + err + "'");
				}
			} else {
				for (String err : errs) {
					log.error("Unexpected error '" + err + "'");
				}

			}

			assertEquals(expectErrorCnt, constraintViolations.size(), "Error count:");
		}

	}

	@Test
	public void testValidPasswords() {
		ValidatorTestForm accountForm = getInstance();
		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 0);
	}

	@Test
	public void testInvalidEmail() {
		ValidatorTestForm accountForm = getInstance();
		accountForm.setEmail("admin");
		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 1, "{email.message}");
	}

	@Test
	public void testBlankEmail() {
		ValidatorTestForm accountForm = getInstance();
		accountForm.setEmail("");
		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 1, "{javax.validation.constraints.NotBlank.message}");
	}

	@Test
	public void testShortPassword() {
		ValidatorTestForm accountForm = getInstance();
		accountForm.setPassword("P@$$w0r");
		accountForm.setPasswordConfirm("P@$$w0r");

		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 2, Utils.getProp(getMsgBundle(), "TOO_SHORT", null, "8", "30"));
	}

	@Test
	public void testLongPassword() {
		ValidatorTestForm accountForm = getInstance();
		accountForm.setPassword(getTestPasswordString(33));
		accountForm.setPasswordConfirm(getTestPasswordString(33));

		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 2, Utils.getProp(getMsgBundle(), "TOO_LONG", null, "8", "30"));
	}

	@Test
	public void testInvalidPassword() {
		ValidatorTestForm accountForm = getInstance();
		accountForm.setPassword("password");
		accountForm.setPasswordConfirm("invalid-password");

		Set<ConstraintViolation<ValidatorTestForm>> constraintViolations = validator.validate(accountForm);

		validate(constraintViolations, 3, Utils.getProp(getMsgBundle(), "password.mismatch"),
				Utils.getProp(getMsgBundle(), "INSUFFICIENT_UPPERCASE", null, "1") + ";"
						+ Utils.getProp(getMsgBundle(), "INSUFFICIENT_DIGIT", null, "1") + ";"
						+ Utils.getProp(getMsgBundle(), "INSUFFICIENT_SPECIAL", null, "1"),
				Utils.getProp(getMsgBundle(), "INSUFFICIENT_UPPERCASE", null, "1") + ";"
						+ Utils.getProp(getMsgBundle(), "INSUFFICIENT_DIGIT", null, "1"));
	}

}