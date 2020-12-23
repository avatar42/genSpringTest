package com.dea42.genspring.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Title: sheet2 Bean <br>
 * Description: Class for holding data from the sheet2 table. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 * Table name: sheet2<br>
 * Column name: id<br>
 * Catalog name: null<br>
 * Primary key sequence: 0<br>
 * Primary key name: null<br>
 *  <br> */
@Data
@Entity
@Table(name = "`sheet2`")
public class Sheet2 implements Serializable {
	private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name = "Date", nullable = false)
	private Date date;
	@Column(name = "Decimal")
	private BigDecimal decimal;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "Integer", nullable = false)
	private Integer integer;
	@Column(name = "Text", nullable = false, length = 21)
	private String text;
}
