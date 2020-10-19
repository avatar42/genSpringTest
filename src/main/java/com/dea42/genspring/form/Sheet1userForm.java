package com.dea42.genspring.form;

import java.io.Serializable;
import lombok.Data;

import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.entity.Sheet1user;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Title: sheet1user Form <br>
 * Description: Class for holding data from the sheet1user table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 */

@Data
public class Sheet1userForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Sheet1 sheet1;
	private Account account;
    @Length(max=1)
	private String useryn;

	/**
	 * Clones Sheet1user obj into form
	 *
	 * @param obj
	 */
	public static Sheet1userForm getInstance(Sheet1user obj) {
		Sheet1userForm form = new Sheet1userForm();
		form.setId(obj.getId());
		form.setSheet1(obj.getSheet1());
		form.setAccount(obj.getAccount());
		form.setUseryn(obj.getUseryn());
		return form;
	}
}
