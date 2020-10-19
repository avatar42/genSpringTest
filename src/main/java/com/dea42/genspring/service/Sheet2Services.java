package com.dea42.genspring.service;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.repo.Sheet2Repository;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet2Services <br>
 * Description: Sheet2Services. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.4<br>
 * @version 0.5.4<br>
 */
@Slf4j
@Service
public class Sheet2Services {
    @Autowired
    private Sheet2Repository sheet2Repository;

	public List<Sheet2> listAll() {
		return (List<Sheet2>) sheet2Repository.findAll();
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

}

