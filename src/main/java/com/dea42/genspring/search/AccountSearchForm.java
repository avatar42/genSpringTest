package com.dea42.genspring.search;

import com.dea42.genspring.controller.FieldMatch;
import com.dea42.genspring.controller.UniqueEmail;
import com.dea42.genspring.controller.ValidatePassword;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.utils.MessageHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Title: accountSearchForm <br>
 * Description: Class for holding data from the account table for searching. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Data
public class AccountSearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email = null;
	private Integer idMin;
	private Integer idMax;
	private String name = null;
	private String password = null;
	private String userrole = null;
	private String sortField = "id";
	private int page = 1;
	private int pageSize = 10;
	private boolean sortAsc = true;
	private int totalPages = 0;
	private long totalItems = 0;
	private SearchType doOr = SearchType.ADD;
	private boolean advanced = true;
	/**
	 * Clones Account obj into form
	 *
	 * @param obj
	 */
	public static AccountSearchForm getInstance(Account obj) {
		AccountSearchForm form = new AccountSearchForm();
		form.setEmail(obj.getEmail());
		form.setIdMin(obj.getId());
		form.setIdMax(obj.getId());
		form.setName(obj.getName());
		form.setPassword(obj.getPassword());
		form.setUserrole(obj.getUserrole());
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
