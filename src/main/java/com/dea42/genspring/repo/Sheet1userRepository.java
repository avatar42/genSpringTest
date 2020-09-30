package com.dea42.genspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dea42.genspring.entity.Sheet1user;

/**
 * Title: Sheet1userRepository <br>
 * Description: Class for the Sheet1user Repository. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */
@Repository
public interface Sheet1userRepository extends JpaRepository<Sheet1user, Integer>{
}
