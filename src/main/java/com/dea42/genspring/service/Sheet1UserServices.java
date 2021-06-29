package com.dea42.genspring.service;


import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.paging.Column;
import com.dea42.genspring.paging.Direction;
import com.dea42.genspring.paging.Order;
import com.dea42.genspring.paging.PageInfo;
import com.dea42.genspring.paging.PagingRequest;
import com.dea42.genspring.repo.Sheet1UserRepository;
import com.dea42.genspring.search.AccountSearchForm;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.search.SearchType;
import com.dea42.genspring.search.Sheet1SearchForm;
import com.dea42.genspring.search.Sheet1UserSearchForm;
import com.dea42.genspring.utils.Utils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;


/**
 * Title: Sheet1UserServices <br>
 * Description: Sheet1UserServices. <br>
 * Copyright: Copyright (c) 2001-2021<br>
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
		if (form.getSheet1() != null) {

				if (form.getSheet1().getDatefieldMin() != null) {
// need to subtract a millsec here to get >= same to work reliably.
					searchSpec.add(new SearchCriteria<Date>("sheet1","datefield", new Date(form.getSheet1().getDatefieldMin().getTime() - 1), SearchOperation.GREATER_THAN_EQUAL));
				}
				if (form.getSheet1().getDatefieldMax() != null) {
// need to add a millsec here to get <= same to work reliably.
					searchSpec.add(new SearchCriteria<Date>("sheet1","datefield", new Date(form.getSheet1().getDatefieldMax().getTime() + 1), SearchOperation.LESS_THAN_EQUAL));
				}
				if (form.getSheet1().getDecimalfieldMin() != null) {
					BigDecimal bd = form.getSheet1().getDecimalfieldMin();
// SQLite rounds scales > 10 in select where compare though returns all decimals
					if (bd.scale() > 10) {
						bd = bd.setScale(10, BigDecimal.ROUND_DOWN);
					}
					searchSpec.add(new SearchCriteria<BigDecimal>("sheet1","decimalfield",bd, SearchOperation.GREATER_THAN_EQUAL));
				}
				if (form.getSheet1().getDecimalfieldMax() != null) {
					BigDecimal bd = form.getSheet1().getDecimalfieldMax();
// SQLite rounds scales > 10 in select where compare though returns all decimals
				if (bd.scale() > 10) {
					bd = bd.setScale(10, BigDecimal.ROUND_UP);
				}
					searchSpec.add(new SearchCriteria<BigDecimal>("sheet1","decimalfield",bd, SearchOperation.LESS_THAN_EQUAL));
				}
				if (form.getSheet1().getIntfieldMin() != null) {
					searchSpec.add(new SearchCriteria<Integer>("sheet1","intfield", form.getSheet1().getIntfieldMin(), SearchOperation.GREATER_THAN_EQUAL));
				}
				if (form.getSheet1().getIntfieldMax() != null) {
					searchSpec.add(new SearchCriteria<Integer>("sheet1","intfield", form.getSheet1().getIntfieldMax(), SearchOperation.LESS_THAN_EQUAL));
				}
				if (!StringUtils.isBlank(form.getSheet1().getText())) {
					searchSpec.add(new SearchCriteria<String>("sheet1","text", form.getSheet1().getText().toLowerCase(), SearchOperation.LIKE));
				}
		}

		if (form.getAccount() != null) {
				if (!StringUtils.isBlank(form.getAccount().getEmail())) {
					searchSpec.add(new SearchCriteria<String>("account","email", form.getAccount().getEmail().toLowerCase(), SearchOperation.LIKE));
				}
				if (!StringUtils.isBlank(form.getAccount().getName())) {
					searchSpec.add(new SearchCriteria<String>("account","name", form.getAccount().getName().toLowerCase(), SearchOperation.LIKE));
				}
				if (!StringUtils.isBlank(form.getAccount().getPassword())) {
					searchSpec.add(new SearchCriteria<String>("account","password", form.getAccount().getPassword().toLowerCase(), SearchOperation.LIKE));
				}
				if (!StringUtils.isBlank(form.getAccount().getUserrole())) {
					searchSpec.add(new SearchCriteria<String>("account","userrole", form.getAccount().getUserrole().toLowerCase(), SearchOperation.LIKE));
				}
		}

			if (!StringUtils.isBlank(form.getUseryn())) {
				searchSpec.add(new SearchCriteria<String>(null,"useryn", form.getUseryn().toLowerCase(),
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

		Sheet1UserSearchForm form =  (Sheet1UserSearchForm) request.getSession().getAttribute("sheet1UserSearchForm");

		if (form == null ) {
			form = new Sheet1UserSearchForm();
		} else if (StringUtils.isNotBlank(pagingRequest.getSearch().getValue())) {

			String value = pagingRequest.getSearch().getValue();
			log.info("Searching for:" + value);
		Sheet1SearchForm sheet1Form =  form.getSheet1();
		if (sheet1Form == null) {
			sheet1Form = new Sheet1SearchForm();
		}
			sheet1Form.setText(value);
			form.setSheet1(sheet1Form);
		AccountSearchForm accountForm =  form.getAccount();
		if (accountForm == null) {
			accountForm = new AccountSearchForm();
		}
			accountForm.setEmail(value);
			accountForm.setName(value);
			accountForm.setUserrole(value);
			form.setAccount(accountForm);
			form.setUseryn(value);
			form.setDoOr(SearchType.OR);
			form.setAdvanced(false);
		} else if (!form.isAdvanced() && StringUtils.isBlank(pagingRequest.getSearch().getValue())) {
			form = new Sheet1UserSearchForm();

		}
		form.setPage(pagingRequest.getStart() + 1);
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
