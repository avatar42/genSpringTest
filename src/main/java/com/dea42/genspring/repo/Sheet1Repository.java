package com.dea42.genspring.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dea42.genspring.entity.Sheet1;

/**
 * Title: Sheet1Repository <br>
 * Description: Class for the Sheet1 Repository. <br>
 * Copyright: Copyright (c) 2001-2024<br>
 * Company: RMRR<br>
 *
 * @author Gened by com.dea42.build.GenSpring version 0.7.2<br>
 * @version 0.7.2<br>
 */
@Repository
public interface Sheet1Repository extends CrudRepository<Sheet1, Integer>,
JpaSpecificationExecutor<Sheet1> {
}
