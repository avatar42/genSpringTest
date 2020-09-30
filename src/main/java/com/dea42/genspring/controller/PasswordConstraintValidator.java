package com.dea42.genspring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.ResourceBundleMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;

/**
 * Based on
 * https://github.com/StackAbuse/learning-spring/blob/master/password-strength/src/main/java/com/mynotes/spring/mvc/passwordstrength/constraint/PasswordConstraintValidator.java
 *
 */
public class PasswordConstraintValidator extends BaseConstraintValidator<ValidatePassword, Object> {
	public static final int MIN_PASS_LEN = 8;
	public static final int MAX_PASS_LEN = 30;

	@Override
	public void initialize(ValidatePassword constraintAnnotation) {
		initialize(constraintAnnotation.fieldName(), null, constraintAnnotation.idFieldName(),
				constraintAnnotation.message());

	}

	@Override
	public boolean isValid(final Object data, final ConstraintValidatorContext context) {
		String password = (String) getStringField(data, fieldName);
		// if nothing in field and is edit then do not check
		if (StringUtils.isBlank(password))
			return true;

		PasswordValidator validator = new PasswordValidator(new ResourceBundleMessageResolver(bundle), Arrays.asList(
				// at least 8 characters
				new LengthRule(MIN_PASS_LEN, MAX_PASS_LEN),

				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				// at least one lower-case character
				new CharacterRule(EnglishCharacterData.LowerCase, 1),

				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 1),

				// at least one symbol (special character)
				new CharacterRule(EnglishCharacterData.Special, 1),

				// no whitespace
				new WhitespaceRule()

		));
		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}

		List<String> messages = validator.getMessages(result);
		String messageTemplate = messages.stream().collect(Collectors.joining(";"));
		storeErrors(context, messageTemplate);

		return false;
	}
}