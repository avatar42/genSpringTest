package com.dea42.genspring.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handler for status errors like 404
 * 
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 *
 */
@Controller
public class BasicErrorController implements ErrorController {
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
		// retrieve some useful information from the request
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		String message = MessageFormat.format("{0} returned for {1} with message {2}", statusCode, requestUri, "");

		model.addAttribute("errorMessage", message);
		return "error/general";
	}

	@Override
	public String getErrorPath() {
		return null;
	}
}
