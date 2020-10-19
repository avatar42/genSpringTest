package com.dea42.genspring.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.form.Sheet1Form;
import com.dea42.genspring.service.Sheet1Services;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet1Controller <br>
 * Description: Sheet1Controller. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 */
@Slf4j
@Controller
@RequestMapping("/sheet1s")
public class Sheet1Controller {

	@Autowired
	private Sheet1Services sheet1Service;

	@GetMapping
	public String getAllSheet1s(Model model) {
		model.addAttribute("sheet1s", this.sheet1Service.listAll());
		return "sheet1s";
	}

	@GetMapping("/new")
	public String showNewSheet1Page(Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new Sheet1Form());
		if (Utils.isAjaxRequest(requestedWith)) {
			return "edit_sheet1".concat(" :: sheet1Form");
		}

		return "edit_sheet1";
	}

	@PostMapping(value = "/save")
	public String saveSheet1(@Valid @ModelAttribute Sheet1Form form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet1";
			}

			Sheet1 sheet1 = new Sheet1();
			sheet1.setDate(form.getDate());
			sheet1.setDecimal(form.getDecimal());
			sheet1.setId(form.getId());
			sheet1.setIntfield(form.getIntfield());
			sheet1.setText(form.getText());
			try {
				sheet1 = sheet1Service.save(sheet1);
			} catch (Exception e) {
				log.error("Failed saving:" + form, e);
			}

			if (sheet1 == null) {
				MessageHelper.addErrorAttribute(ra, "db.failed");
			} else {
				MessageHelper.addSuccessAttribute(ra, "save.success");
			}
		} else {
			MessageHelper.addSuccessAttribute(ra, "save.cancelled");
		}

		return "redirect:/sheet1s";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditSheet1Page(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet1");
		Sheet1 sheet1 = sheet1Service.get(id);
		mav.addObject("sheet1Form", Sheet1Form.getInstance(sheet1));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String deleteSheet1(@PathVariable(name = "id") Integer id) {
		sheet1Service.delete(id);
		return "redirect:/sheet1s";
	}
}

