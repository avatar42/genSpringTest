package com.dea42.genspring.form;

import java.io.Serializable;
import lombok.Data;

import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.entity.Sheet1;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Title: sheet1 Form <br>
 * Description: Class for holding data from the sheet1 table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.1<br>
 * @version 1.0.0<br>
 */

@Data
public class Sheet1Form implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer date;
	private BigDecimal decimal;
	private Integer id;
	private Integer intfield;
    @Length(max=7)
	private String text;

	/**
	 * Clones Sheet1 obj into form
	 *
	 * @param obj
	 */
	public static Sheet1Form getInstance(Sheet1 obj) {
		Sheet1Form form = new Sheet1Form();
		form.setDate(obj.getDate());
		form.setDecimal(obj.getDecimal());
		form.setId(obj.getId());
		form.setIntfield(obj.getIntfield());
		form.setText(obj.getText());
		return form;
	}
}
