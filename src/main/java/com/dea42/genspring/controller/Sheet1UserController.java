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

import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.form.Sheet1UserForm;
import com.dea42.genspring.paging.PageInfo;
import com.dea42.genspring.paging.PagingRequest;
import com.dea42.genspring.search.Sheet1UserSearchForm;
import com.dea42.genspring.service.Sheet1UserServices;
import com.dea42.genspring.utils.Message;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.utils.Utils;
import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet1UserController <br>
 * Description: Sheet1UserController. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Slf4j
@Controller
@RequestMapping("/sheet1Users")
public class Sheet1UserController {

	@Autowired
	private Sheet1UserServices sheet1UserService;

	private Sheet1UserSearchForm getForm(HttpServletRequest request) {
		Sheet1UserSearchForm form = (Sheet1UserSearchForm) request.getSession().getAttribute("sheet1UserSearchForm");
		if (log.isDebugEnabled())
			log.debug("pulled from session:" + form);
		if (form == null) {
			form = new Sheet1UserSearchForm();
		}
		return form;
	}

	private void setForm(HttpServletRequest request, Sheet1UserSearchForm form) {
		request.getSession().setAttribute("sheet1UserSearchForm", form);
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
	public ModelAndView search(HttpServletRequest request, @ModelAttribute Sheet1UserSearchForm form, 
			RedirectAttributes ra, @RequestParam(value = "action", required = true) String action) {
		ModelAndView mav;
		if (action.equals("search")) {
			setForm(request, form);
			form.setAdvanced(true);
			mav = new ModelAndView("sheet1Users");
//			mav = findPaginated(request, 1, "id", "asc");
//			@SuppressWarnings("unchecked")
//			List<Sheet1User> list = (List<Sheet1User>) mav.getModelMap().getAttribute("sheet1Users");
//			if (list == null || list.isEmpty()) {
//				mav.setViewName("search_sheet1User");
//				mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
//						new Message("search.noResult", Message.Type.WARNING));
//			}
		} else {
			form = new Sheet1UserSearchForm();
			setForm(request, form);
			mav = new ModelAndView("search_sheet1User");
			mav.addObject("sheet1UserSearchForm", form);
			mav.getModelMap().addAttribute(Message.MESSAGE_ATTRIBUTE,
					new Message("search.formReset", Message.Type.WARNING));
		}

		return mav;
	}

	@GetMapping("/search/{pageNo}")
	public ModelAndView findPaginated(HttpServletRequest request, @PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		Sheet1UserSearchForm form = getForm(request);
		if (pageNo < 1)
			pageNo = 1;

		form.setPage(pageNo);
		form.setSortField(sortField);
		form.setSortAsc("asc".equalsIgnoreCase(sortDir));

		if (log.isDebugEnabled())
			log.debug("Searching with:" + form);

		Page<Sheet1User> page = sheet1UserService.listAll(form);

		form.setTotalPages(page.getTotalPages());
		form.setTotalItems(page.getTotalElements());
		setForm(request, form);

		ModelAndView mav = new ModelAndView("sheet1Users");
		mav.addObject("sheet1Users", page.getContent());
		return mav;
	}

	@GetMapping("/search")
	public String showSearchPage(HttpServletRequest request, Model model,
			@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		model.addAttribute(getForm(request));
		if (Utils.isAjaxRequest(requestedWith)) {
			return "search_sheet1User".concat(" :: sheet1UserSearchForm");
		}

		return "search_sheet1User";
	}

	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute Sheet1UserForm form, Errors errors, RedirectAttributes ra,
			@RequestParam(value = "action", required = true) String action) {
		if (action.equals("save")) {
			if (errors.hasErrors()) {
				return "edit_sheet1User";
			}

			Sheet1User sheet1User = new Sheet1User();
			sheet1User.setId(form.getId());
			sheet1User.setSheet1(form.getSheet1());
			sheet1User.setAccount(form.getAccount());
			sheet1User.setUseryn(form.getUseryn());
			try {
				sheet1User = sheet1UserService.save(sheet1User);
			} catch (Exception e) {
				log.error("Failed saving:" + form, e);
			}

			if (sheet1User == null) {
				MessageHelper.addErrorAttribute(ra, "db.failed");
			} else {
				MessageHelper.addSuccessAttribute(ra, "save.success");
			}
		} else {
			MessageHelper.addSuccessAttribute(ra, "save.cancelled");
		}

		return "redirect:/sheet1Users";
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditPage(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_sheet1User");
		Sheet1User sheet1User = null;
		if (id > 0)
			sheet1User = sheet1UserService.get(id);
		mav.addObject("sheet1UserForm", Sheet1UserForm.getInstance(sheet1User));

		return mav;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		sheet1UserService.delete(id);
		return "redirect:/sheet1Users";
	}

	@GetMapping("/list")
	String home(Principal principal) {
		return "sheet1Users";
	}
}
