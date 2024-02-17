package com.dea42.genspring.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.form.Sheet2Form;
import com.dea42.genspring.search.Sheet2SearchForm;
import com.dea42.genspring.service.Sheet2Services;
import com.dea42.genspring.utils.Message;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet2Controller <br>
 * Description: Sheet2Controller. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@Controller
@RequestMapping("/sheet2s")
public class Sheet2Controller {

	@Autowired
	private Sheet2Services sheet2Service;

	private Sheet2SearchForm getForm(HttpServletRequest request) {
		Sheet2SearchForm form = (Sheet2SearchForm) request.getSession().getAttribute("sheet2SearchForm");
		if (log.isDebugEnabled())
			log.debug("pulled from session:" + form);
		if (form == null) {
			form = new Sheet2SearchForm();
		}
		return form;
	}

	private void setForm(HttpServletRequest request, Sheet2SearchForm form) {
		request.getSession().setAttribute("sheet2SearchForm", form);
		if (log.isDebugEnabled())
			log.debug("stored:" + form);
	}

	@GetMapping
	public ModelAndView getAll(HttpServletRequest request) {
		return findPaginated(request, 1, "id", "asc");
	}

	@GetMapping("/new")
	public ModelAndView showNewPage() {
		return showEditPage(0);
	}

	@PostMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, @ModelAttribute Sheet2SearchForm form, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		ModelAndView mav;
		if (action.equals("search")) {
			setForm(request, form);
			form.setAdvanced(true);
			mav = new ModelAndView("sheet2s");
//			mav = findPaginated(request, 1, "id", "asc");
//			@SuppressWarnings("unchecked")
//			List<Sheet2> list = (List<Sheet2>) mav.getModelMap().getAttribute("sheet2s");
//			if (list == null || list.isEmpty()) {
//				mav.setViewName("search_sheet2");
//				mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
//						new Message("search.noResult", Message.Type.WARNING));
//			}
		} else {
			form = new Sheet2SearchForm();
			setForm(request, form);
			mav = new ModelAndView("search_sheet2");
			mav.addObject("sheet2SearchForm", form);
			mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
					new Message("search.formReset", Message.Type.WARNING));
		}

		return mav;
	}

	@GetMapping("/search/{pageNo}")
	public ModelAndView findPaginated(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		Sheet2SearchForm form = getForm(request);
		if (pageNo < 1)
			pageNo = 1;

		form.setPage(pageNo);
		form.setSortField(sortField);
		form.setSortAsc("asc".equalsIgnoreCase(sortDir));

		if (log.isDebugEnabled())
			log.debug("Searching with:" + form);

		Page<Sheet2> page = sheet2Service.listAll(form);

		form.setTotalPages(page.getTotalPages());
		form.setTotalItems(page.getTotalElements());
		setForm(request, form);

		ModelAndView mav = new ModelAndView("sheet2s");
		mav.addObject("sheet2s", page.getContent());
		return mav;
	}

	@GetMapping("/search")
	public String showSearchPage(HttpServletRequest request, Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(getForm(request));
		if (Utils.isAjaxRequest(requestedWith)) {
			return "search_sheet2".concat(" :: sheet2SearchForm");
		}

		return "search_sheet2";
	}

	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute Sheet2Form form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet2";
			}

			Sheet2 sheet2 = new Sheet2();
			sheet2.setDatefield(form.getDatefield());
			sheet2.setDecimalfield(form.getDecimalfield());
			sheet2.setId(form.getId());
			sheet2.setIntegerfield(form.getIntegerfield());
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
	public ModelAndView showEditPage(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet2");
		Sheet2 sheet2 = null;
		if (id > 0)
			sheet2 = sheet2Service.get(id);
		mav.addObject("sheet2Form", Sheet2Form.getInstance(sheet2));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		sheet2Service.delete(id);
		return "redirect:/sheet2s";
	}

	@GetMapping("/list")
	String home(Principal principal) {
		return "sheet2s";
	}
}
