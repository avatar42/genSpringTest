package com.dea42.genspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dea42.genspring.entity.Account;
import com.dea42.genspring.service.AccountServices;
import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.service.Sheet2Services;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.service.Sheet1Services;
import com.dea42.genspring.entity.Sheet1user;
import com.dea42.genspring.service.Sheet1userServices;

import java.util.List;
/**
 * Title: ApiController <br>
 * Description: Api REST Controller. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.4.0<br>
 * @version 1.0.0<br>
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AccountServices accountServices;
    @Autowired
    private Sheet2Services sheet2Services;
    @Autowired
    private Sheet1Services sheet1Services;
    @Autowired
    private Sheet1userServices sheet1userServices;

    public ApiController(){
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return this.accountServices.listAll();
    }

    @GetMapping("/sheet2s")
    public List<Sheet2> getAllSheet2s(){
        return this.sheet2Services.listAll();
    }

    @GetMapping("/sheet1s")
    public List<Sheet1> getAllSheet1s(){
        return this.sheet1Services.listAll();
    }

    @GetMapping("/sheet1users")
    public List<Sheet1user> getAllSheet1users(){
        return this.sheet1userServices.listAll();
    }
}
