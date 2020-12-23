package com.dea42.genspring.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dea42.genspring.entity.Sheet1User;

/**
 * Title: Sheet1UserRepository <br>
 * Description: Class for the Sheet1User Repository. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */
@Repository
public interface Sheet1UserRepository extends CrudRepository<Sheet1User, Integer>,
JpaSpecificationExecutor<Sheet1User> {
}
