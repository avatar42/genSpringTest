package com.dea42.genspring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Title: account Bean <br>
 * Description: Class for holding data from the account table. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 * Table name: account<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br> */
@Data
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
}