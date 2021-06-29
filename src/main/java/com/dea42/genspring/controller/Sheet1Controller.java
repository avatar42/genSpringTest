package com.dea42.genspring.controller;

import java.util.Date;
import java.security.Principal;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.form.Sheet1Form;
import com.dea42.genspring.paging.PageInfo;
import com.dea42.genspring.paging.PagingRequest;
import com.dea42.genspring.search.Sheet1SearchForm;
import com.dea42.genspring.service.Sheet1Services;
import com.dea42.genspring.utils.Message;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet1Controller <br>
 * Description: Sheet1Controller. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@Controller
@RequestMapping("/sheet1s")
public class Sheet1Controller {

	@Autowired
	private Sheet1Services sheet1Service;

	private Sheet1SearchForm getForm(HttpServletRequest request) {
		Sheet1SearchForm form = (Sheet1SearchForm) request.getSession().getAttribute("sheet1SearchForm");
		if (log.isDebugEnabled())
			log.debug("pulled from session:" + form);
		if (form == null) {
			form = new Sheet1SearchForm();
		}
		return form;
	}

	private void setForm(HttpServletRequest request, Sheet1SearchForm form) {
		request.getSession().setAttribute("sheet1SearchForm", form);
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
	public ModelAndView search(HttpServletRequest request, @ModelAttribute Sheet1SearchForm form, 
			RedirectAttributes ra, @RequestParam(value = "action", required = true) String action) {
		ModelAndView mav;
		if (action.equals("search")) {
			setForm(request, form);
			form.setAdvanced(true);
			mav = new ModelAndView("sheet1s");
//			mav = findPaginated(request, 1, "id", "asc");
//			@SuppressWarnings("unchecked")
//			List<Sheet1> list = (List<Sheet1>) mav.getModelMap().getAttribute("sheet1s");
//			if (list == null || list.isEmpty()) {
//				mav.setViewName("search_sheet1");
//				mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
//						new Message("search.noResult", Message.Type.WARNING));
//			}
		} else {
			form = new Sheet1SearchForm();
			setForm(request, form);
			mav = new ModelAndView("search_sheet1");
			mav.addObject("sheet1SearchForm", form);
			mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
					new Message("search.formReset", Message.Type.WARNING));
		}

		return mav;
	}

	@GetMapping("/search/{pageNo}")
	public ModelAndView findPaginated(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		Sheet1SearchForm form = getForm(request);
		if (pageNo < 1)
			pageNo = 1;

		form.setPage(pageNo);
		form.setSortField(sortField);
		form.setSortAsc("asc".equalsIgnoreCase(sortDir));

		if (log.isDebugEnabled())
			log.debug("Searching with:" + form);

		Page<Sheet1> page = sheet1Service.listAll(form);

		form.setTotalPages(page.getTotalPages());
		form.setTotalItems(page.getTotalElements());
		setForm(request, form);

		ModelAndView mav = new ModelAndView("sheet1s");
		mav.addObject("sheet1s", page.getContent());
		return mav;
	}

	@GetMapping("/search")
	public String showSearchPage(HttpServletRequest request, Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(getForm(request));
		if (Utils.isAjaxRequest(requestedWith)) {
			return "search_sheet1".concat(" :: sheet1SearchForm");
		}

		return "search_sheet1";
	}

	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute Sheet1Form form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet1";
			}

			Sheet1 sheet1 = new Sheet1();
			sheet1.setDatefield(form.getDatefield());
			sheet1.setDecimalfield(form.getDecimalfield());
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
	public ModelAndView showEditPage(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet1");
		Sheet1 sheet1 = null;
		if (id > 0)
			sheet1 = sheet1Service.get(id);
		mav.addObject("sheet1Form", Sheet1Form.getInstance(sheet1));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		sheet1Service.delete(id);
		return "redirect:/sheet1s";
	}

	@GetMapping("/list")
	String home(Principal principal) {
		return "sheet1s";
	}
}
