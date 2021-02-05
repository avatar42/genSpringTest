package com.dea42.genspring.paging;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Search paging support class
 * 
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 *
 */
@Data
@NoArgsConstructor
public class Column {

	private String data;
	private String name;
	private Boolean searchable;
	private Boolean orderable;
	private Search search;
}
