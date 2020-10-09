package com.dea42.genspring.service;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
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
 * @author Gened by com.dea42.build.GenSpring version 0.5.1<br>
 * @version 1.0.0<br>
 */
@Slf4j
@Service
public class Sheet1userServices {
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

