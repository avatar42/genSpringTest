package com.dea42.genspring.paging;

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
public class Search {

	private String value;
	private String regexp;
}
