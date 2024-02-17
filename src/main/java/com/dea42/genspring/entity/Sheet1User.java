package com.dea42.genspring.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Title: sheet1user Bean <br>
 * Description: Class for holding data from the sheet1user table. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 * Table name: sheet1user<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 1<br>
 * Primary key name: null<br>
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
	@Column(name = "Sheet1Id")
	private Integer sheet1id;
	@Column(name = "Userid")
	private Integer userid;
	@Column(name = "UserYN", length = 1)
	private String useryn;
}
