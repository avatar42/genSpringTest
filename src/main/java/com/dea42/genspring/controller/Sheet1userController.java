package com.dea42.genspring.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;

import com.dea42.genspring.entity.Sheet1user;
import com.dea42.genspring.form.Sheet1userForm;
import com.dea42.genspring.service.Sheet1userServices;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet1userController <br>
 * Description: Sheet1userController. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */
@Controller
@RequestMapping("/sheet1users")
public class Sheet1userController {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sheet1userController.class.getName());

	@Autowired
	private Sheet1userServices sheet1userService;

	@GetMapping
	public String getAllSheet1users(Model model) {
		model.addAttribute("sheet1users", this.sheet1userService.listAll());
		return "sheet1users";
	}

	@GetMapping("/new")
	public String showNewSheet1userPage(Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new Sheet1userForm());
		if (Utils.isAjaxRequest(requestedWith)) {
			return "edit_sheet1user".concat(" :: sheet1userForm");
		}

		return "edit_sheet1user";
	}

	@PostMapping(value = "/save")
	public String saveSheet1user(@Valid @ModelAttribute Sheet1userForm form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet1user";
			}

			Sheet1user sheet1user = new Sheet1user();
			sheet1user.setId(form.getId());
			sheet1user.setSheet1(form.getSheet1());
			sheet1user.setAccount(form.getAccount());
			sheet1user.setUseryn(form.getUseryn());
			try {
				sheet1user = sheet1userService.save(sheet1user);
			} catch (Exception e) {
				LOGGER.error("Failed saving:" + form, e);
			}

			if (sheet1user == null) {
				MessageHelper.addErrorAttribute(ra, "db.failed");
			} else {
				MessageHelper.addSuccessAttribute(ra, "save.success");
			}
		} else {
			MessageHelper.addSuccessAttribute(ra, "save.cancelled");
		}

		return "redirect:/sheet1users";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditSheet1userPage(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet1user");
		Sheet1user sheet1user = sheet1userService.get(id);
		mav.addObject("sheet1userForm", Sheet1userForm.getInstance(sheet1user));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String deleteSheet1user(@PathVariable(name = "id") Integer id) {
		sheet1userService.delete(id);
		return "redirect:/sheet1users";
	}
}

