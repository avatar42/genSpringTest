package com.dea42.genspring.form;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.utils.MessageHelper;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Title: sheet1user Form <br>
 * Description: Class for holding data from the sheet1user table for editing. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */

@Data
public class Sheet1UserForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id = 0;
	private Sheet1 sheet1;
	private Account account;
    @Length(max=1)
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
			form.setSheet1(obj.getSheet1());
			form.setAccount(obj.getAccount());
			form.setUseryn(obj.getUseryn());
		}
		return form;
	}
}
