package com.dea42.genspring.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Title: sheet1user Bean <br>
 * Description: Class for holding data from the sheet1user table. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
 * Table name: sheet1user<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br> * Sheet1Id => foreign key column name<br>
 * null => primary key table catalog being imported (may be null)<br>
 * null => primary key table schema being imported (may be null) <br>
 * Sheet1 => primary key table name being imported <br>
 * Id => primary key column name being imported<br>
 * null => foreign key table catalog (may be null)<br>
 * null => foreign key table schema (may be null)<br>
 * sheet1user => foreign key table name <br>
 * 1 => sequence number within a foreign key( a valueof 1 represents the first column of the foreign key, a value of 2 would represent the second column within the foreign key).<br>
 * 3 => What happens to a foreign key when the primary key is updated:<br>
 * 3 => What happens to the foreign key when primary is deleted.<br>
 *  => foreign key name (may be null) <br>
 *  => primary key name (may be null) <br>
 * 5 DEFERRABILITY<br>
 *  <br> * Userid => foreign key column name<br>
 * null => primary key table catalog being imported (may be null)<br>
 * null => primary key table schema being imported (may be null) <br>
 * Account => primary key table name being imported <br>
 * Id => primary key column name being imported<br>
 * null => foreign key table catalog (may be null)<br>
 * null => foreign key table schema (may be null)<br>
 * sheet1user => foreign key table name <br>
 * 1 => sequence number within a foreign key( a valueof 1 represents the first column of the foreign key, a value of 2 would represent the second column within the foreign key).<br>
 * 3 => What happens to a foreign key when the primary key is updated:<br>
 * 3 => What happens to the foreign key when primary is deleted.<br>
 *  => foreign key name (may be null) <br>
 *  => primary key name (may be null) <br>
 * 5 DEFERRABILITY<br>
 *  <br> */
@Data
@Entity
@Table(name = "`sheet1user`")
public class Sheet1User implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "Sheet1Id", referencedColumnName = "Id")
	private Sheet1 sheet1;
	@ManyToOne
	@JoinColumn(name = "Userid", referencedColumnName = "Id")
	private Account account;
	@Column(name = "UserYN", length = 1)
	private String useryn;
}
