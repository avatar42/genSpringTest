package com.dea42.genspring.search;

import com.dea42.genspring.entity.Sheet2;
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
 * Title: sheet2SearchForm <br>
 * Description: Class for holding data from the sheet2 table for searching. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Data
public class Sheet2SearchForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMin;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefieldMax;
	private BigDecimal decimalfieldMin;
	private BigDecimal decimalfieldMax;
	private Integer idMin;
	private Integer idMax;
	private Integer integerfieldMin;
	private Integer integerfieldMax;
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
