package com.dea42.genspring.service;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dea42.genspring.entity.Sheet1user;
import com.dea42.genspring.repo.Sheet1userRepository;
import com.dea42.genspring.utils.Utils;

/**
 * Title: Sheet1userServices <br>
 * Description: Sheet1userServices. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */
@Service
public class Sheet1userServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sheet1userServices.class.getName());
    @Autowired
    private Sheet1userRepository sheet1userRepository;

	public List<Sheet1user> listAll() {
		return (List<Sheet1user>) sheet1userRepository.findAll();
	}
	
	public Sheet1user save(Sheet1user sheet1user) {
		return sheet1userRepository.save(sheet1user);
	}
	
	public Sheet1user get(Integer id) {
		return sheet1userRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		sheet1userRepository.deleteById(id);
	}

}

