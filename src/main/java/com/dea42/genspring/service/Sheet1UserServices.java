package com.dea42.genspring.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.paging.Column;
import com.dea42.genspring.paging.Direction;
import com.dea42.genspring.paging.Order;
import com.dea42.genspring.paging.PageInfo;
import com.dea42.genspring.paging.PagingRequest;
import com.dea42.genspring.repo.Sheet1UserRepository;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.search.SearchType;
import com.dea42.genspring.search.Sheet1UserSearchForm;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Sheet1UserServices <br>
 * Description: Sheet1UserServices. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
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
			searchSpec.setDoOr(form.getDoOr());
			if (form.getSheet1idMin() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null, "sheet1id", form.getSheet1idMin(),
						SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getSheet1idMax() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null, "sheet1id", form.getSheet1idMax(),
						SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getUseridMin() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null, "userid", form.getUseridMin(),
						SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getUseridMax() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null, "userid", form.getUseridMax(),
						SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getUseryn())) {
				searchSpec.add(new SearchCriteria<String>(null, "useryn", form.getUseryn().toLowerCase(),
						SearchOperation.LIKE));
			}

		} else {
			form = new Sheet1UserSearchForm();
		}

		// OR queries assume at least one SearchCriteria or return nothing
		if (searchSpec.getList().isEmpty()) {
			searchSpec.setDoOr(SearchType.ADD);
		}
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getPageSize(), form.getSort());

		if (log.isInfoEnabled())
			log.info("searchSpec:" + searchSpec);
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

	public PageInfo<Sheet1User> getSheet1Users(HttpServletRequest request, PagingRequest pagingRequest) {

		Sheet1UserSearchForm form = (Sheet1UserSearchForm) request.getSession().getAttribute("sheet1UserSearchForm");

		if (form == null) {
			form = new Sheet1UserSearchForm();
		} else if (StringUtils.isNotBlank(pagingRequest.getSearch().getValue())) {

			String value = pagingRequest.getSearch().getValue();
			log.info("Searching for:" + value);
			form.setUseryn(value);
			form.setDoOr(SearchType.OR);
			form.setAdvanced(false);
		} else if (!form.isAdvanced() && StringUtils.isBlank(pagingRequest.getSearch().getValue())) {
			form = new Sheet1UserSearchForm();

		}
		form.setPage((pagingRequest.getStart() / pagingRequest.getLength()) + 1);
		form.setPageSize(pagingRequest.getLength());
		Order order = pagingRequest.getOrder().get(0);
		int columnIndex = order.getColumn();
		Column column = pagingRequest.getColumns().get(columnIndex);
		form.setSortField(column.getData());
		form.setSortAsc(order.getDir().equals(Direction.asc));

		Page<Sheet1User> filtered = listAll(form);
		int count = (int) filtered.getTotalElements();

		PageInfo<Sheet1User> pageInfo = new PageInfo<Sheet1User>(filtered);
		pageInfo.setRecordsFiltered(count);
		pageInfo.setRecordsTotal(count);
		pageInfo.setDraw(pagingRequest.getDraw());

		request.getSession().setAttribute("sheet1UserSearchForm", form);

		return pageInfo;
	}

}
