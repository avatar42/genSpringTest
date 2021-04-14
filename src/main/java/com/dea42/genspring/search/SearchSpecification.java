package com.dea42.genspring.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

/**
 * Title: SearchSpecification <br>
 * Description: Used to generate SearchCriteria list for queries. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 * @author Gened by GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
 */
@Data
public class SearchSpecification<T> implements Specification<T> {
	private static final long serialVersionUID = 1L;
	private List<SearchCriteria<?>> list;
	private SearchType doOr = SearchType.ADD;

	public SearchSpecification() {
		this.list = new ArrayList<>();
	}

	public SearchSpecification(SearchType doOr) {
		this.list = new ArrayList<>();
		this.doOr = doOr;
	}

	public void add(SearchCriteria<?> criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		// create a new predicate list
		List<Predicate> predicates = new ArrayList<>();

		// add add criteria to predicates
		for (SearchCriteria<?> criteria : list) {
			switch (criteria.getOperation()) {
			case GREATER_THAN_EQUAL:
				if (criteria.getValue() instanceof Date) {
					Path<Date> key;
					if (criteria.getJoin() == null)
						key = root.<Date>get(criteria.getKey());
					else
						key = root.join(criteria.getJoin()).<Date>get(criteria.getKey());
					predicates.add(builder.greaterThanOrEqualTo(key, (Date) criteria.getValue()));
				} else {
					Path<String> key;
					if (criteria.getJoin() == null)
						key = root.get(criteria.getKey());
					else
						key = root.join(criteria.getJoin()).get(criteria.getKey());
					String val = criteria.getValue().toString();
					predicates.add(builder.greaterThanOrEqualTo(key, val));
				}
				break;
			case LESS_THAN_EQUAL:
				if (criteria.getValue() instanceof Date) {
					Path<Date> key;
					if (criteria.getJoin() == null)
						key = root.<Date>get(criteria.getKey());
					else
						key = root.join(criteria.getJoin()).<Date>get(criteria.getKey());
					predicates.add(builder.lessThanOrEqualTo(key, (Date) criteria.getValue()));
				} else {
					Path<String> key;
					if (criteria.getJoin() == null)
						key = root.get(criteria.getKey());
					else
						key = root.join(criteria.getJoin()).get(criteria.getKey());
					String val = criteria.getValue().toString();
					predicates.add(builder.lessThanOrEqualTo(key, val));
				}
				break;
			case LIKE:
				Path<String> key;
				if (criteria.getJoin() == null)
					key = root.get(criteria.getKey());
				else
					key = root.join(criteria.getJoin()).get(criteria.getKey());
				String val = criteria.getValue().toString();
				predicates.add(builder.like(key, val.toLowerCase()));
				break;
			}
		}

		if (doOr == SearchType.OR)
			return builder.or(predicates.toArray(new Predicate[0]));
		else
			return builder.and(predicates.toArray(new Predicate[0]));
	}
}