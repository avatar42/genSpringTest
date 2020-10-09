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

import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.repo.Sheet1Repository;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet1Services <br>
 * Description: Sheet1Services. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.5.1<br>
 * @version 1.0.0<br>
 */
@Slf4j
@Service
public class Sheet1Services {
    @Autowired
    private Sheet1Repository sheet1Repository;

	public List<Sheet1> listAll() {
		return (List<Sheet1>) sheet1Repository.findAll();
	}
	
	public Sheet1 save(Sheet1 sheet1) {
		return sheet1Repository.save(sheet1);
	}
	
	public Sheet1 get(Integer id) {
		return sheet1Repository.findById(id).get();
	}
	
	public void delete(Integer id) {
		sheet1Repository.deleteById(id);
	}

}

