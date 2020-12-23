package com.dea42.genspring.service;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.repo.AccountRepository;
import com.dea42.genspring.search.AccountSearchForm;
import com.dea42.genspring.search.SearchCriteria;
import com.dea42.genspring.search.SearchOperation;
import com.dea42.genspring.search.SearchSpecification;
import com.dea42.genspring.utils.Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Title: AccountServices <br>
 * Description: AccountServices. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */
@Slf4j
@Service
public class AccountServices extends UserServices<Account> {
    @Autowired
    private AccountRepository accountRepository;

	public static final String ROLE_PREFIX = "ROLE_";

	/**
	 * reset default users. Comment out once done testing
	 */
	@PostConstruct
	protected void initialize() {
		ResourceBundle bundle = ResourceBundle.getBundle("app");
		boolean doinit = Utils.getProp(bundle, "init.default.users", true);
		if (doinit) {
			log.warn("Resetting default users");
			String user = Utils.getProp(bundle, "default.user", null);
			if (!StringUtils.isBlank(user)) {
				Integer id = Utils.getProp(bundle, "default.userid", 1);
				String userpass = Utils.getProp(bundle, "default.userpass", null);
				String userrole = ROLE_PREFIX + Utils.getProp(bundle, "default.userrole", null);
				Account a = new Account();
				a.setEmail(user);
				a.setPassword(userpass);
				a.setRole(userrole);
				a.setId(id);
				save(a);
			}

			user = Utils.getProp(bundle, "default.admin", null);
			if (!StringUtils.isBlank(user)) {
				Integer id = Utils.getProp(bundle, "default.adminid", 2);
				String userpass = Utils.getProp(bundle, "default.adminpass", null);
				String userrole = ROLE_PREFIX + Utils.getProp(bundle, "default.adminrole", null);
				Account a = new Account();
				a.setEmail(user);
				a.setPassword(userpass);
				a.setRole(userrole);
				a.setId(id);
				save(a);
			}
		}
	}

	public Page<Account> listAll(AccountSearchForm form) {
		SearchSpecification<Account> searchSpec = new SearchSpecification<Account>();
		if (form != null) {
			log.debug(form.toString());
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria<Integer>("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria<Integer>("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getEmail())) {
				searchSpec.add(new SearchCriteria<String>("email", form.getEmail().toLowerCase(), SearchOperation.LIKE));
			}
			if (form.getIdMin() != null) {
				searchSpec.add(new SearchCriteria<Integer>("id", form.getIdMin(), SearchOperation.GREATER_THAN_EQUAL));
			}
			if (form.getIdMax() != null) {
				searchSpec.add(new SearchCriteria<Integer>("id", form.getIdMax(), SearchOperation.LESS_THAN_EQUAL));
			}
			if (!StringUtils.isBlank(form.getPassword())) {
				searchSpec.add(new SearchCriteria<String>("password", form.getPassword().toLowerCase(), SearchOperation.LIKE));
			}
			if (!StringUtils.isBlank(form.getRole())) {
				searchSpec.add(new SearchCriteria<String>("role", form.getRole().toLowerCase(), SearchOperation.LIKE));
			}

		} else {
			form = new AccountSearchForm();
		}
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getPageSize(),
				form.getSort());
		if (log.isInfoEnabled())
			log.info("searchSpec:" + searchSpec);
		return accountRepository.findAll(searchSpec, pageable);
	}

	public Account save(Account account) {
		Optional<Account> o = null;
		if (account.getId() > 0) {
			o = accountRepository.findById(account.getId());
		}

		if (o != null && StringUtils.isBlank(account.getPassword())) {
			account.setPassword(o.get().getPassword());
		} else {
			account.setPassword(encrypt(account.getPassword()));
		}

		return accountRepository.save(account);
	}
	
	public Account get(Integer id) {
		return accountRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		accountRepository.deleteById(id);
	}

}

