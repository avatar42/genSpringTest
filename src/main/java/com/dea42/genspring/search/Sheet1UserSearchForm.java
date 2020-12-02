package com.dea42.genspring.search;

import java.io.Serializable;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.entity.Sheet1User;

import lombok.Data;

/**
 * Title: sheet1user Form <br>
 * Description: Class for holding data from the sheet1user table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.1<br>
 * @version 0.6.1<br>
 */
@Data
public class Sheet1UserSearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idMin;
	private Integer idMax;
	private Sheet1 sheet1Min;
	private Sheet1 sheet1Max;
	private Account accountMin;
	private Account accountMax;
	private String useryn = null;
	private String sortField = "id";
	private int page = 1;
	private int pageSize = 10;
	private boolean sortAsc = true;
	private int totalPages = 0;
	private long totalItems = 0;
	/**
	 * Clones Sheet1User obj into form
	 *
	 * @param obj
	 */
	public static Sheet1UserSearchForm getInstance(Sheet1User obj) {
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		form.setIdMin(obj.getId());
		form.setIdMax(obj.getId());
		form.setSheet1Min(obj.getSheet1());
		form.setSheet1Max(obj.getSheet1());
		form.setAccountMin(obj.getAccount());
		form.setAccountMax(obj.getAccount());
		form.setUseryn(obj.getUseryn());
		return form;
	}

	/**
	 * Generate a Sort from fields
	 * @return
	 */
	public Sort getSort() {
		if (sortAsc)
			return Sort.by(sortField).ascending();

		return Sort.by(sortField).descending();
	}

	public String getSortDir() {
		if (sortAsc)
			return "asc";
		else
			return "desc";
	}

	public String getReverseSortDir() {
		if (sortAsc)
			return "desc";
		else
			return "asc";
	}

	boolean getSortAscFlip() {
		return !sortAsc;
	}
}
