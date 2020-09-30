package com.dea42.genspring.form;

import java.io.Serializable;


import com.dea42.genspring.utils.MessageHelper;
import com.dea42.genspring.entity.Sheet1;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Title: sheet1 Form <br>
 * Description: Class for holding data from the sheet1 table for editing. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */

public class Sheet1Form implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer date;
	private BigDecimal decimal;
	private Integer id;
	private Integer intfield;
    @Length(max=7)
	private String text;

	/**
	 * Basic constructor
	 */
	public Sheet1Form() {
	}

	/**
	 * Clones Sheet1 obj into form
	 *
	 * @param obj
	 */
	public static Sheet1Form getInstance(Sheet1 obj) {
		Sheet1Form form = new Sheet1Form();
		form.setDate(obj.getDate());
		form.setDecimal(obj.getDecimal());
		form.setId(obj.getId());
		form.setIntfield(obj.getIntfield());
		form.setText(obj.getText());
		return form;
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
