package com.dea42.genspring.controller;

import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.dea42.genspring.utils.Utils;
import com.dea42.genspring.utils.MessageHelper;
import lombok.extern.slf4j.Slf4j;
/**
 * Compares two fields to confirm that match mainly for password confirm hence
 * the field name defaults.
 * 
 * @author avata
 *
 */
@Slf4j
public class FieldMatchValidator extends BaseConstraintValidator<FieldMatch, Object> {

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		initialize(constraintAnnotation.fieldName(), constraintAnnotation.secondFieldName(),
				constraintAnnotation.idFieldName(), constraintAnnotation.message());
	}

	@Override
	public boolean isValid(final Object formBean, final ConstraintValidatorContext context) {
		String messageTemplate;
		try {
			final Object firstObj = BeanUtils.getProperty(formBean, fieldName);
			final Object secondObj = BeanUtils.getProperty(formBean, secondFieldName);
			if (isNew(formBean, context)) {
				if (firstObj instanceof String) {
					if (!StringUtils.isBlank((String) firstObj) && firstObj.equals(secondObj))
						return true;
				} else {
					if (firstObj != null && firstObj.equals(secondObj))
						return true;
				}
				
				if (StringUtils.isBlank((String) firstObj) && StringUtils.isBlank((String) secondObj)) {
					messageTemplate = Utils.getProp(bundle, MessageHelper.notBlank_message, MessageHelper.notBlank_message);
					storeErrors(context, messageTemplate);

					return false;
				}
			} else {
				if (StringUtils.isBlank((String) firstObj) && StringUtils.isBlank((String) secondObj))
					return true;
				if (firstObj != null && firstObj.equals(secondObj))
					return true;
			}
			messageTemplate = Utils.getProp(bundle, msgKey, msgKey);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			messageTemplate = e.getLocalizedMessage();
		}

		storeErrors(context, messageTemplate);

		return false;
	}

}