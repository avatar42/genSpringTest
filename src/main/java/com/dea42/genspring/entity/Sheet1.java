package com.dea42.genspring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

/**
 * Title: sheet1 Bean <br>
 * Description: Class for holding data from the sheet1 table. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 * Table name: sheet1<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br> */
@Entity
@Table(name = "`sheet1`")
public class Sheet1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "Date")
	private Integer date;
	@Column(name = "Decimal")
	private BigDecimal decimal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "IntField", nullable = false)
	private Integer intfield;
	@Column(name = "Text", length = 7)
	private String text;

	/**
	 * Basic constructor
	 */
	public Sheet1() {
	}

	/**
	 * Full constructor
	 *
	 */
	public Sheet1(Integer date, BigDecimal decimal, Integer id, Integer intfield, String text) {
		this.date = date;
		this.decimal = decimal;
		this.id = id;
		this.intfield = intfield;
		this.text = text;
	}
	/**
	 * returns value of the Date column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public Integer getDate() {
		if (date == null)
	    	return 0;
		return date.intValue();
	}

	/**
	 * sets value of the Date column of this row of data
	 * default value for this field set by the DB is null
	 */
	public void setDate(Integer newVal) {
		date = newVal;
	}

	/**
	 * returns value of the Decimal column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public BigDecimal getDecimal() {
		if (decimal == null)
	    	return BigDecimal.ZERO;
		return decimal;
	}

	/**
	 * sets value of the Decimal column of this row of data
	 * default value for this field set by the DB is null
	 */
	public void setDecimal(BigDecimal newVal) {
		decimal = newVal;
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
	 * returns value of the IntField column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public Integer getIntfield() {
		if (intfield == null)
	    	return 0;
		return intfield.intValue();
	}

	/**
	 * sets value of the IntField column of this row of data
	 * default value for this field set by the DB is null
	 */
	public void setIntfield(Integer newVal) {
		intfield = newVal;
	}

	/**
	 * returns value of the Text column of this row of data
	 *
	 * @return value of this column in this row
	 */
	public String getText() {
		return text;
	}

	/**
	 * sets value of the Text column of this row of data
	 * default value for this field set by the DB is null
	 * This field has a max length of 7
	 */
	public void setText(String newVal) {
		text = newVal;
	}

	/**
	 * Returns a String showing the values of this bean - mainly for debuging
	 *
	 * @return String
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Sheet1 [");
		builder.append("date=").append(date);
		builder.append(", decimal=").append(decimal);
		builder.append(", id=").append(id);
		builder.append(", intfield=").append(intfield);
		builder.append(", text=").append(text);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((decimal == null) ? 0 : decimal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intfield == null) ? 0 : intfield.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Sheet1 other = (Sheet1) obj;

		if (getDate() == null) {
			if (other.getDate() != null)
				return false;
		} else if (!getDate().equals(other.getDate()))
			return false;

		if (getDecimal() == null) {
			if (other.getDecimal() != null)
				return false;
		} else if (!getDecimal().equals(other.getDecimal()))
			return false;

		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;

		if (getIntfield() == null) {
			if (other.getIntfield() != null)
				return false;
		} else if (!getIntfield().equals(other.getIntfield()))
			return false;

		if (getText() == null) {
			if (other.getText() != null)
				return false;
		} else if (!getText().equals(other.getText()))
			return false;

		return true;
	}

}
