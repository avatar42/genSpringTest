package com.dea42.genspring.search;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import com.dea42.genspring.entity.Sheet2;

import lombok.Data;

/**
 * Title: sheet2SearchForm <br>
 * Description: Class for holding data from the sheet2 table for searching. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Data
public class Sheet2SearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMin;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMax;
	/*
	 * info=ColInfo(fNum=6, colName=DecimalField, msgKey=Sheet2.decimalfield,
	 * vName=decimalfield, type=BigDecimal, jtype=null, stype=6,
	 * gsName=Decimalfield, length=0, pk=false, defaultVal=null, constraint=null,
	 * required=false, list=true, jsonIgnore=false, unique=false, hidden=false,
	 * password=false, email=false, created=false, lastMod=false, adminOnly=false,
	 * foreignTable=null, foreignCol=null, colScale=10, colPrecision=2000000000,
	 * comment=null)
	 */
	private BigDecimal decimalfieldMin;
	private BigDecimal decimalfieldMax;
	/*
	 * info=ColInfo(fNum=2, colName=id, msgKey=Sheet2.id, vName=id, type=Integer,
	 * jtype=null, stype=4, gsName=Id, length=0, pk=true, defaultVal=null,
	 * constraint=null, required=true, list=true, jsonIgnore=false, unique=false,
	 * hidden=false, password=false, email=false, created=false, lastMod=false,
	 * adminOnly=false, foreignTable=null, foreignCol=null, colScale=0,
	 * colPrecision=0, comment= * Table name: sheet2<br> Column name: id<br> Catalog
	 * name: null<br> Primary key sequence: 1<br> Primary key name: null<br> <br>)
	 */
	private Integer idMin;
	private Integer idMax;
	/*
	 * info=ColInfo(fNum=4, colName=IntegerField, msgKey=Sheet2.integerfield,
	 * vName=integerfield, type=Integer, jtype=null, stype=4, gsName=Integerfield,
	 * length=0, pk=false, defaultVal=null, constraint=null, required=true,
	 * list=true, jsonIgnore=false, unique=false, hidden=false, password=false,
	 * email=false, created=false, lastMod=false, adminOnly=false,
	 * foreignTable=null, foreignCol=null, colScale=0, colPrecision=0, comment=null)
	 */
	private Integer integerfieldMin;
	private Integer integerfieldMax;
	private String text = "";
	private String sortField = "id";
	private int page = 1;
	private int pageSize = 10;
	private boolean sortAsc = true;
	private int totalPages = 0;
	private long totalItems = 0;
	private SearchType doOr = SearchType.ADD;
	private boolean advanced = true;

	/**
	 * Clones Sheet2 obj into form
	 *
	 * @param obj
	 */
	public static Sheet2SearchForm getInstance(Sheet2 obj) {
		Sheet2SearchForm form = new Sheet2SearchForm();
		form.setDatefieldMin(obj.getDatefield());
		form.setDatefieldMax(obj.getDatefield());
		form.setDecimalfieldMin(obj.getDecimalfield());
		form.setDecimalfieldMax(obj.getDecimalfield());
		form.setIdMin(obj.getId());
		form.setIdMax(obj.getId());
		form.setIntegerfieldMin(obj.getIntegerfield());
		form.setIntegerfieldMax(obj.getIntegerfield());
		form.setText(obj.getText());
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
