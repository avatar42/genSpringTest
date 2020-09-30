package com.dea42.genspring.form;

import java.io.Serializable;


import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.entity.Account;

import com.dea42.genspring.controller.FieldMatch;
import com.dea42.genspring.controller.UniqueEmail;
import com.dea42.genspring.controller.ValidatePassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Title: account Form <br>
 * Description: Class for holding data from the account table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */
@UniqueEmail.List({ @UniqueEmail(fieldName = "email", message = "email.unique") })
@FieldMatch.List({
		@FieldMatch(fieldName = "password", secondFieldName = "passwordConfirm", message = "password.mismatch")
		})
public class AccountForm implements Serializable {
	private static final long serialVersionUID = 1L;

    @Email(message = "{"+MessageHelper.email_message+"}")
    @Length(max=254)
    @NotBlank(message = "{"+MessageHelper.notBlank_message+"}")
	private String email;
	private Integer id;
    @JsonIgnore
    @ValidatePassword(fieldName = "passwordConfirm")
    @Length(max=30)
    @NotBlank(message = "{"+MessageHelper.notBlank_message+"}")
	private String password;
    @ValidatePassword(fieldName = "password")
	private String passwordConfirm;
    @Length(max=25)
    @NotBlank(message = "{"+MessageHelper.notBlank_message+"}")
	private String role;

	/**
	 * Basic constructor
	 */
	public AccountForm() {
	}

	/**
	 * Clones Account obj into form
	 *
	 * @param obj
	 */
	public static AccountForm getInstance(Account obj) {
		AccountForm form = new AccountForm();
		form.setEmail(obj.getEmail());
		form.setId(obj.getId());
//		form.setPassword(obj.getPassword());
//		form.setPasswordConfirm(obj.getPassword());
		form.setRole(obj.getRole());
		return form;
	}
	/**
	 * returns value of the Email column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets value of the Email column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 254
	 */
	public void setEmail(String newVal) {
		email = newVal;
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
	 * returns value of the Password column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * sets value of the Password column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 30
	 */
	public void setPassword(String newVal) {
		password = newVal;
	}

	public void setPasswordConfirm(String newVal) {
		passwordConfirm = newVal;
	}

	/**
	 * returns value of the Role column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public String getRole() {
		return role;
	}

	/**
	 * sets value of the Role column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 25
	 */
	public void setRole(String newVal) {
		role = newVal;
	}

	/**
	 * Returns a String showing the values of this bean - mainly for debuging
	 *
	 * @return String
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Account [");
		builder.append("email=").append(email);
		builder.append(", id=").append(id);
		builder.append(", password=").append(password);
		builder.append(", passwordConfirm=").append(passwordConfirm);
		builder.append(", role=").append(role);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Account other = (Account) obj;

		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;

		if (getPassword() == null) {
			if (other.getPassword() != null)
				return false;
		} else if (!getPassword().equals(other.getPassword()))
			return false;

		if (getRole() == null) {
			if (other.getRole() != null)
				return false;
		} else if (!getRole().equals(other.getRole()))
			return false;

		return true;
	}

}
