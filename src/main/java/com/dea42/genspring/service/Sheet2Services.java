package com.dea42.genspring.service;


import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.paging.Column;
import com.dea42.genspring.paging.Direction;
import com.dea42.genspring.paging.Order;
import com.dea42.genspring.paging.PageInfo;
import com.dea42.genspring.paging.PagingRequest;
import com.dea42.genspring.repo.Sheet2Repository;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.search.SearchType;
import com.dea42.genspring.search.Sheet2SearchForm;
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
 * Title: Sheet2Services <br>
 * Description: Sheet2Services. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Slf4j
@Service
public class Sheet2Services {
    @Autowired
    private Sheet2Repository sheet2Repository;

	public Page<Sheet2> listAll(Sheet2SearchForm form) {
		SearchSpecification<Sheet2> searchSpec = new SearchSpecification<Sheet2>();
		if (form != null) {
			log.debug(form.toString());
			searchSpec.setDoOr(form.getDoOr());

			if (form.getDatefieldMin() != null) {
// need to subtract a millsec here to get >= same to work reliably.
				searchSpec.add(new SearchCriteria<Date>(null,"datefield",
					new Date(form.getDatefieldMin().getTime() - 1),
					SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getDatefieldMax() != null) {
// need to add a millsec here to get <= same to work reliably.
				searchSpec.add(new SearchCriteria<Date>(null,"datefield",
					new Date(form.getDatefieldMax().getTime() + 1),
					SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getDecimalfieldMin() != null) {
				BigDecimal bd = form.getDecimalfieldMin();
// SQLite rounds scales > 10 in select where compare though returns all decimals
				if (bd.scale() > 10) {
					bd = bd.setScale(10, BigDecimal.ROUND_DOWN);
				}
				searchSpec.add(new SearchCriteria<BigDecimal>(null,"decimalfield",bd,
					SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getDecimalfieldMax() != null) {
				BigDecimal bd = form.getDecimalfieldMax();
// SQLite rounds scales > 10 in select where compare though returns all decimals
				if (bd.scale() > 10) {
					bd = bd.setScale(10, BigDecimal.ROUND_UP);
				}
				searchSpec.add(new SearchCriteria<BigDecimal>(null,"decimalfield",bd,
					SearchOperation.LESS_THAN_EQUAL));
			}
			if (form.getIntegerfieldMin() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null,"integerfield", form.getIntegerfieldMin(),
					SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIntegerfieldMax() != null) {
				searchSpec.add(new SearchCriteria<Integer>(null,"integerfield", form.getIntegerfieldMax(),
					SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getText())) {
				searchSpec.add(new SearchCriteria<String>(null,"text", form.getText().toLowerCase(),
					SearchOperation.LIKE));
			}

		} else {
			form = new Sheet2SearchForm();
		}

		// OR queries assume at least one SearchCriteria or return nothing
		if (searchSpec.getList().isEmpty()) {
			searchSpec.setDoOr(SearchType.ADD);
		}
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getPageSize(), form.getSort());

		if (log.isInfoEnabled())
			log.info("searchSpec:" + searchSpec);
		return sheet2Repository.findAll(searchSpec, pageable);
	}

	public Sheet2 save(Sheet2 sheet2) {
		return sheet2Repository.save(sheet2);
	}
	
	public Sheet2 get(Integer id) {
		return sheet2Repository.findById(id).get();
	}
	
	public void delete(Integer id) {
		sheet2Repository.deleteById(id);
	}

	public PageInfo<Sheet2> getSheet2s(HttpServletRequest request, PagingRequest pagingRequest) {

		Sheet2SearchForm form =  (Sheet2SearchForm) request.getSession().getAttribute("sheet2SearchForm");

		if (form == null ) {
			form = new Sheet2SearchForm();
		} else if (StringUtils.isNotBlank(pagingRequest.getSearch().getValue())) {

			String value = pagingRequest.getSearch().getValue();
			log.info("Searching for:" + value);
			form.setText(value);
			form.setDoOr(SearchType.OR);
			form.setAdvanced(false);
		} else if (!form.isAdvanced() && StringUtils.isBlank(pagingRequest.getSearch().getValue())) {
			form = new Sheet2SearchForm();

		}
		form.setPage(pagingRequest.getStart() + 1);
		form.setPageSize(pagingRequest.getLength());
		Order order = pagingRequest.getOrder().get(0);
		int columnIndex = order.getColumn();
		Column column = pagingRequest.getColumns().get(columnIndex);
		form.setSortField(column.getData());
		form.setSortAsc(order.getDir().equals(Direction.asc));

		Page<Sheet2> filtered = listAll(form);
		int count = (int) filtered.getTotalElements();

		PageInfo<Sheet2> pageInfo = new PageInfo<Sheet2>(filtered);
		pageInfo.setRecordsFiltered(count);
		pageInfo.setRecordsTotal(count);
		pageInfo.setDraw(pagingRequest.getDraw());

		request.getSession().setAttribute("sheet2SearchForm", form);


		return pageInfo;
	}


}
