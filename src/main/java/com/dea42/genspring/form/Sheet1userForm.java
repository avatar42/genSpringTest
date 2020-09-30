package com.dea42.genspring.form;

import java.io.Serializable;


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
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */

public class Sheet1userForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Sheet1 sheet1;
	private Account account;
    @Length(max=1)
	private String useryn;

	/**
	 * Basic constructor
	 */
	public Sheet1userForm() {
	}

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
	/**
	 * returns value of the id column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public Integer getId() {
		if (id == null)
	    	return 0;
		return id.intValue();
	}

	/**
	 * sets value of the id column of this row of data
	 * default value for this field set by the DB is null
	 * This is the primary key for this table
	 */
	public void setId(Integer newVal) {
		id = newVal;
	}

	/**
	 * returns value of the Sheet1Id column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public Sheet1 getSheet1() {
		return sheet1;
	}

	/**
	 * sets value of the Sheet1Id column of this row of data
	 * default value for this field set by the DB is null
	 */
	public void setSheet1(Sheet1 newVal) {
		sheet1 = newVal;
	}

	/**
	 * returns value of the Userid column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * sets value of the Userid column of this row of data
	 * default value for this field set by the DB is null
	 */
	public void setAccount(Account newVal) {
		account = newVal;
	}

	/**
	 * returns value of the UserYN column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public String getUseryn() {
		return useryn;
	}

	/**
	 * sets value of the UserYN column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 1
	 */
	public void setUseryn(String newVal) {
		useryn = newVal;
	}

	/**
	 * Returns a String showing the values of this bean - mainly for debuging
	 *
	 * @return String
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Sheet1user [");
		builder.append("id=").append(id);
		builder.append(", sheet1=").append(sheet1);
		builder.append(", account=").append(account);
		builder.append(", useryn=").append(useryn);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sheet1 == null) ? 0 : sheet1.hashCode());
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((useryn == null) ? 0 : useryn.hashCode());
		return result;
	}

	/**
	 * Mainly for mock testing
	 *
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sheet1user other = (Sheet1user) obj;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;

		if (getSheet1() == null) {
			if (other.getSheet1() != null)
				return false;
		} else if (!getSheet1().equals(other.getSheet1()))
			return false;

		if (getAccount() == null) {
			if (other.getAccount() != null)
				return false;
		} else if (!getAccount().equals(other.getAccount()))
			return false;

		if (getUseryn() == null) {
			if (other.getUseryn() != null)
				return false;
		} else if (!getUseryn().equals(other.getUseryn()))
			return false;

		return true;
	}

}
