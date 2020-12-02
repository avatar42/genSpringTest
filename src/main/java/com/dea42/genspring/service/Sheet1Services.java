package com.dea42.genspring.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.repo.Sheet1Repository;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.search.Sheet1SearchForm;
import com.dea42.genspring.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet1Services <br>
 * Description: Sheet1Services. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.1<br>
 * @version 0.6.1<br>
 */
@Slf4j
@Service
public class Sheet1Services {
    @Autowired
    private Sheet1Repository sheet1Repository;

	public Page<Sheet1> listAll(Sheet1SearchForm form) {
		SearchSpecification<Sheet1> searchSpec = new SearchSpecification<Sheet1>();
		if (form != null) {
			log.debug(form.toString());
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getDateMin() != null) {
				searchSpec.add(new SearchCriteria("date", form.getDateMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getDateMax() != null) {
				searchSpec.add(new SearchCriteria("date", form.getDateMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getDecimalMin() != null) {
				BigDecimal bd = form.getDecimalMin();
				searchSpec.add(new SearchCriteria("decimal",
						form.getDecimalMin().setScale(bd.scale() - 1, BigDecimal.ROUND_DOWN),
						SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getDecimalMax() != null) {
				BigDecimal bd = form.getDecimalMin();
				searchSpec.add(new SearchCriteria("decimal",
						form.getDecimalMin().setScale(bd.scale() - 1, BigDecimal.ROUND_UP),
						SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getIntfieldMin() != null) {
				searchSpec.add(new SearchCriteria("intfield", form.getIntfieldMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIntfieldMax() != null) {
				searchSpec.add(new SearchCriteria("intfield", form.getIntfieldMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getText())) {
				searchSpec.add(new SearchCriteria("text", form.getText().toLowerCase(), SearchOperation.LIKE));
			}

		} else {
			form = new Sheet1SearchForm();
		}
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getPageSize(),
				form.getSort());
		return sheet1Repository.findAll(searchSpec, pageable);
	}
	
	public Sheet1 save(Sheet1 sheet1) {
		return sheet1Repository.save(sheet1);
	}
	
	public Sheet1 get(Integer id) {
		return sheet1Repository.findById(id).get();
	}
	
	public void delete(Integer id) {
		sheet1Repository.deleteById(id);
	}

}

