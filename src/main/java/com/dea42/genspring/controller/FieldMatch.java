package com.dea42.genspring.controller;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dea42.genspring.controller.FieldMatch.List;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 *
 * Example, compare 1 pair of fields:
 * 
 * @FieldMatch(first = "password", second = "passwordConfirm", message = "The
 *                   password fields must match")
 * 
 *                   Example, compare more than 1 pair of
 *                   fields: @FieldMatch.List({
 * @FieldMatch(first = "password", second = "passwordConfirm", message = "The
 *                   password fields must match"),
 * @FieldMatch(first = "email", second = "confirmEmail", message = "The email
 *                   fields must match")})
 */
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface FieldMatch {
	String message() default "Field mismatch";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName() default "password";

	String secondFieldName() default "passwordConfirm";

	String idFieldName() default "id";

	/**
	 * Defines several <code>@FieldMatch</code> annotations on the same element
	 *
	 * @see FieldMatch
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		FieldMatch[] value();
	}
}
