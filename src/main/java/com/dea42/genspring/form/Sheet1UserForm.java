package com.dea42.genspring.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.dea42.genspring.entity.Sheet1User;

import lombok.Data;

/**
 * Title: sheet1user Form <br>
 * Description: Class for holding data from the sheet1user table for editing.
 * <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */

@Data
public class Sheet1UserForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id = 0;
	private Integer sheet1id;
	private Integer userid;
	@Length(max = 1)
	private String useryn;

	/**
	 * Clones Sheet1User obj into form
	 *
	 * @param obj
	 */
	public static Sheet1UserForm getInstance(Sheet1User obj) {
		Sheet1UserForm form = new Sheet1UserForm();
		if (obj != null) {
			form.setId(obj.getId());
			form.setSheet1id(obj.getSheet1id());
			form.setUserid(obj.getUserid());
			form.setUseryn(obj.getUseryn());
		}
		return form;
	}
}
