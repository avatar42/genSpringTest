package com.dea42.genspring.form;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

// non common imports start
import com.dea42.genspring.controller.FieldMatch;
import com.dea42.genspring.controller.ValidatePassword;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.utils.MessageHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

// non common imports end
/**
 * Title: account Form <br>
 * Description: Class for holding data from the account table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * 
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@FieldMatch.List({
		@FieldMatch(fieldName = "password", secondFieldName = "passwordConfirm", message = "password.mismatch") })
public class ValidatorTestForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Email(message = "{" + MessageHelper.email_message + "}")
	@Length(max = 254)
	@NotBlank
	private String email;
	private BigDecimal ftype;
	private Integer id;
	@JsonIgnore
	@ValidatePassword(fieldName = "passwordConfirm")
	@Length(max = 254)
	@NotBlank
	private String password;
	@ValidatePassword(fieldName = "password")
	private String passwordConfirm;
	@Length(max = 25)
	@NotBlank
	private String role;

	/**
	 * Basic constructor
	 */
	public ValidatorTestForm() {
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
		form.setUserrole(obj.getUserrole());
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
	 * sets value of the Email column of this row of data default value for this
	 * field set by the DB is null This field has a max length of 254
	 */
	public void setEmail(String newVal) {
		email = newVal;
	}

	/**
	 * returns value of the Ftype column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public BigDecimal getFtype() {
		if (ftype == null)
			return BigDecimal.ZERO;
		return ftype;
	}

	/**
	 * sets value of the Ftype column of this row of data default value for this
	 * field set by the DB is null
	 */
	public void setFtype(BigDecimal newVal) {
		ftype = newVal;
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
	 * sets value of the id column of this row of data default value for this field
	 * set by the DB is null This is the primary key for this table
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
	 * sets value of the Password column of this row of data default value for this
	 * field set by the DB is null This field has a max length of 254
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
	public String getUserrole() {
		return role;
	}

	/**
	 * sets value of the Role column of this row of data default value for this
	 * field set by the DB is null This field has a max length of 25
	 */
	public void setUserrole(String newVal) {
		role = newVal;
	}

	/**
	 * Returns a String showing the values of this bean - mainly for debuging
	 *
	 * @return String
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [");
		builder.append("email=").append(email);
		builder.append(", ftype=").append(ftype);
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
		result = prime * result + ((ftype == null) ? 0 : ftype.hashCode());
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

		if (getUserrole() == null) {
			if (other.getUserrole() != null)
				return false;
		} else if (!getUserrole().equals(other.getUserrole()))
			return false;

		return true;
	}

}
