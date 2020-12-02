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


import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.repo.Sheet1UserRepository;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.search.Sheet1UserSearchForm;
import com.dea42.genspring.utils.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet1UserServices <br>
 * Description: Sheet1UserServices. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.1<br>
 * @version 0.6.1<br>
 */
@Slf4j
@Service
public class Sheet1UserServices {
    @Autowired
    private Sheet1UserRepository sheet1UserRepository;

	public Page<Sheet1User> listAll(Sheet1UserSearchForm form) {
		SearchSpecification<Sheet1User> searchSpec = new SearchSpecification<Sheet1User>();
		if (form != null) {
			log.debug(form.toString());
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getSheet1Min() != null) {
				searchSpec.add(new SearchCriteria("sheet1", form.getSheet1Min(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getSheet1Max() != null) {
				searchSpec.add(new SearchCriteria("sheet1", form.getSheet1Max(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getAccountMin() != null) {
				searchSpec.add(new SearchCriteria("account", form.getAccountMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getAccountMax() != null) {
				searchSpec.add(new SearchCriteria("account", form.getAccountMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getUseryn())) {
				searchSpec.add(new SearchCriteria("useryn", form.getUseryn().toLowerCase(), SearchOperation.LIKE));
			}

		} else {
			form = new Sheet1UserSearchForm();
		}
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getPageSize(),
				form.getSort());
		return sheet1UserRepository.findAll(searchSpec, pageable);
	}
	
	public Sheet1User save(Sheet1User sheet1User) {
		return sheet1UserRepository.save(sheet1User);
	}
	
	public Sheet1User get(Integer id) {
		return sheet1UserRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		sheet1UserRepository.deleteById(id);
	}

}

