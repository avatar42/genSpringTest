package com.dea42.genspring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Title: account Bean <br>
 * Description: Class for holding data from the account table. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 * Table name: account<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br> */
@Entity
@Table(name = "`account`")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "Email", unique = true, nullable = false, length = 254)
	private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
    @JsonIgnore
	@Column(name = "Password", nullable = false, length = 30)
	private String password;
	@Column(name = "Role", nullable = false, length = 25)
	private String role;

	/**
	 * Basic constructor
	 */
	public Account() {
	}

	/**
	 * Special constructor for sign up
	 * @param email
	 * @param password
	 * @param role
	 */
	public Account(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;

	}

	/**
	 * Full constructor
	 *
	 */
	public Account(String email, Integer id, String password, String role) {
		this.email = email;
		this.id = id;
		this.password = password;
		this.role = role;
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

	/**
	 * sets value of the Password column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 30
	 */
	public void setPassword(String newVal) {
		password = newVal;
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
