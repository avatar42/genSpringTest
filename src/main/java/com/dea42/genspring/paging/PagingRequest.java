package com.dea42.genspring.paging;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Search paging support class
 * 
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class PagingRequest {

	private int start;
	private int length;
	private int draw;
	private List<Order> order;
	private List<Column> columns;
	private Search search;

}
