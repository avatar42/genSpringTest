package com.dea42.genspring.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dea42.genspring.entity.Account;

/**
 * Title: AccountRepository <br>
 * Description: Class for the Account Repository. <br>
 * Copyright: Copyright (c) 2001-2021<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.1<br>
 * @version 0.7.1<br>
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>,
JpaSpecificationExecutor<Account> {
}
