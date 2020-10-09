package com.dea42.genspring.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;

/**
 * General error handler for the application.
 */
@Slf4j
@ControllerAdvice
class ExceptionHandler {
	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
		Throwable rootCause = Throwables.getRootCause(exception);
		modelAndView.addObject("errorMessage", rootCause);
		log.error(rootCause.toString(), exception);
		return modelAndView;
	}
}