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

import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.form.Sheet2Form;
import com.dea42.genspring.service.Sheet2Services;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet2Controller <br>
 * Description: Sheet2Controller. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.1<br>
 * @version 1.0.0<br>
 */
@Slf4j
@Controller
@RequestMapping("/sheet2s")
public class Sheet2Controller {

	@Autowired
	private Sheet2Services sheet2Service;

	@GetMapping
	public String getAllSheet2s(Model model) {
		model.addAttribute("sheet2s", this.sheet2Service.listAll());
		return "sheet2s";
	}

	@GetMapping("/new")
	public String showNewSheet2Page(Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(new Sheet2Form());
		if (Utils.isAjaxRequest(requestedWith)) {
			return "edit_sheet2".concat(" :: sheet2Form");
		}

		return "edit_sheet2";
	}

	@PostMapping(value = "/save")
	public String saveSheet2(@Valid @ModelAttribute Sheet2Form form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet2";
			}

			Sheet2 sheet2 = new Sheet2();
			sheet2.setDate(form.getDate());
			sheet2.setDecimal(form.getDecimal());
			sheet2.setId(form.getId());
			sheet2.setInteger(form.getInteger());
			sheet2.setText(form.getText());
			try {
				sheet2 = sheet2Service.save(sheet2);
			} catch (Exception e) {
				log.error("Failed saving:" + form, e);
			}

			if (sheet2 == null) {
				MessageHelper.addErrorAttribute(ra, "db.failed");
			} else {
				MessageHelper.addSuccessAttribute(ra, "save.success");
			}
		} else {
			MessageHelper.addSuccessAttribute(ra, "save.cancelled");
		}

		return "redirect:/sheet2s";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditSheet2Page(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet2");
		Sheet2 sheet2 = sheet2Service.get(id);
		mav.addObject("sheet2Form", Sheet2Form.getInstance(sheet2));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String deleteSheet2(@PathVariable(name = "id") Integer id) {
		sheet2Service.delete(id);
		return "redirect:/sheet2s";
	}
}

