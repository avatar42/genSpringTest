package com.dea42.genspring.search;

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.utils.MessageHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * Title: sheet1SearchForm <br>
 * Description: Class for holding data from the sheet1 table for searching. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Data
public class Sheet1SearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMin;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMax;
	private BigDecimal decimalfieldMin;
	private BigDecimal decimalfieldMax;
	private Integer idMin;
	private Integer idMax;
	private Integer intfieldMin;
	private Integer intfieldMax;
	private String text = null;
	private String sortField = "id";
	private int page = 1;
	private int pageSize = 10;
	private boolean sortAsc = true;
	private int totalPages = 0;
	private long totalItems = 0;
	private SearchType doOr = SearchType.ADD;
	private boolean advanced = true;
	/**
	 * Clones Sheet1 obj into form
	 *
	 * @param obj
	 */
	public static Sheet1SearchForm getInstance(Sheet1 obj) {
		Sheet1SearchForm form = new Sheet1SearchForm();
		form.setDatefieldMin(obj.getDatefield());
		form.setDatefieldMax(obj.getDatefield());
		form.setDecimalfieldMin(obj.getDecimalfield());
		form.setDecimalfieldMax(obj.getDecimalfield());
		form.setIdMin(obj.getId());
		form.setIdMax(obj.getId());
		form.setIntfieldMin(obj.getIntfield());
		form.setIntfieldMax(obj.getIntfield());
		form.setText(obj.getText());
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
