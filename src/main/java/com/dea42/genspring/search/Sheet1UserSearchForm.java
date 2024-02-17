package com.dea42.genspring.search;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

import com.dea42.genspring.entity.Sheet1User;

import lombok.Data;

/**
 * Title: sheet1userSearchForm <br>
 * Description: Class for holding data from the sheet1user table for searching.
 * <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Data
public class Sheet1UserSearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * info=ColInfo(fNum=2, colName=id, msgKey=Sheet1User.id, vName=id,
	 * type=Integer, jtype=null, stype=4, gsName=Id, length=0, pk=true,
	 * defaultVal=null, constraint=null, required=true, list=true, jsonIgnore=false,
	 * unique=false, hidden=false, password=false, email=false, created=false,
	 * lastMod=false, adminOnly=false, foreignTable=null, foreignCol=null,
	 * colScale=0, colPrecision=0, comment= * Table name: sheet1user<br> Column
	 * name: id<br> Catalog name: null<br> Primary key sequence: 1<br> Primary key
	 * name: null<br> <br>)
	 */
	private Integer idMin;
	private Integer idMax;
	/*
	 * info=ColInfo(fNum=4, colName=Sheet1Id, msgKey=Sheet1User.sheet1id,
	 * vName=sheet1id, type=Integer, jtype=null, stype=4, gsName=Sheet1id, length=0,
	 * pk=false, defaultVal=null, constraint=null, required=false, list=true,
	 * jsonIgnore=false, unique=false, hidden=false, password=false, email=false,
	 * created=false, lastMod=false, adminOnly=false, foreignTable=null,
	 * foreignCol=null, colScale=0, colPrecision=0, comment=null)
	 */
	private Integer sheet1idMin;
	private Integer sheet1idMax;
	/*
	 * info=ColInfo(fNum=3, colName=Userid, msgKey=Sheet1User.userid, vName=userid,
	 * type=Integer, jtype=null, stype=4, gsName=Userid, length=0, pk=false,
	 * defaultVal=null, constraint=null, required=false, list=true,
	 * jsonIgnore=false, unique=false, hidden=false, password=false, email=false,
	 * created=false, lastMod=false, adminOnly=false, foreignTable=null,
	 * foreignCol=null, colScale=0, colPrecision=0, comment=null)
	 */
	private Integer useridMin;
	private Integer useridMax;
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
		form.setSheet1idMin(obj.getSheet1id());
		form.setSheet1idMax(obj.getSheet1id());
		form.setUseridMin(obj.getUserid());
		form.setUseridMax(obj.getUserid());
		form.setUseryn(obj.getUseryn());
		return form;
	}

	/**
	 * Generate a Sort from fields
	 * 
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
