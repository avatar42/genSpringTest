package com.dea42.genspring.form;

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.utils.MessageHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Title: sheet1 Form <br>
 * Description: Class for holding data from the sheet1 table for editing. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */

@Data
public class Sheet1Form implements Serializable {
	private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datefield;
	private BigDecimal decimalfield;
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
		if (obj != null) {
			form.setDatefield(obj.getDatefield());
			form.setDecimalfield(obj.getDecimalfield());
			form.setId(obj.getId());
			form.setIntfield(obj.getIntfield());
			form.setText(obj.getText());
		}
		return form;
	}
}
