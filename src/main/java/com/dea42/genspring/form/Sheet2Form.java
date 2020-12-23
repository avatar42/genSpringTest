package com.dea42.genspring.form;

import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.utils.MessageHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Title: sheet2 Form <br>
 * Description: Class for holding data from the sheet2 table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */

@Data
public class Sheet2Form implements Serializable {
	private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date date;
	private BigDecimal decimal;
	private Integer id;
	private Integer integer;
    @Length(max=21)
    @NotBlank(message = "{"+MessageHelper.notBlank_message+"}")
	private String text;

	/**
	 * Clones Sheet2 obj into form
	 *
	 * @param obj
	 */
	public static Sheet2Form getInstance(Sheet2 obj) {
		Sheet2Form form = new Sheet2Form();
		if (obj != null) {
			form.setDate(obj.getDate());
			form.setDecimal(obj.getDecimal());
			form.setId(obj.getId());
			form.setInteger(obj.getInteger());
			form.setText(obj.getText());
		}
		return form;
	}
}
