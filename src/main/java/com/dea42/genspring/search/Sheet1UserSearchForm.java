package com.dea42.genspring.search;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.utils.MessageHelper;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Title: sheet1userSearchForm <br>
 * Description: Class for holding data from the sheet1user table for searching. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Data
public class Sheet1UserSearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

/* info=ColInfo(fNum=1, colName=id, msgKey=Sheet1User.id, vName=id, type=Integer, jtype=null, stype=4, gsName=Id, length=0, pk=true, defaultVal=null, constraint=null, required=true, list=true, jsonIgnore=false, unique=false, hidden=false, password=false, email=false, created=false, lastMod=false, adminOnly=false, foreignTable=null, foreignCol=null, colScale=0, colPrecision=0, comment= * Table name: sheet1user<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br>) */
	private Integer idMin;
	private Integer idMax;
	private Sheet1SearchForm sheet1;
	private AccountSearchForm account;
	private String useryn = "";
	private String sortField = "id";
	private int page = 1;
	private int pageSize = 10;
	private boolean sortAsc = true;
	private int totalPages = 0;
	private long totalItems = 0;
	private SearchType doOr = SearchType.ADD;
	private boolean advanced = true;
	/**
	 * Clones Sheet1User obj into form
	 *
	 * @param obj
	 */
	public static Sheet1UserSearchForm getInstance(Sheet1User obj) {
		Sheet1UserSearchForm form = new Sheet1UserSearchForm();
		form.setIdMin(obj.getId());
		form.setIdMax(obj.getId());
		form.setSheet1(Sheet1SearchForm.getInstance(obj.getSheet1()));
		form.setAccount(AccountSearchForm.getInstance(obj.getAccount()));
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
