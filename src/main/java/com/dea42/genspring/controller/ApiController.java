package com.dea42.genspring.controller;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.entity.Sheet1;
import com.dea42.genspring.entity.Sheet1User;
import com.dea42.genspring.entity.Sheet2;
import com.dea42.genspring.service.AccountServices;
import com.dea42.genspring.service.Sheet1Services;
import com.dea42.genspring.service.Sheet1UserServices;
import com.dea42.genspring.service.Sheet2Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Title: ApiController <br>
 * Description: Api REST Controller. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * @author Gened by com.dea42.build.GenSpring version 0.6.3<br>
 * @version 0.6.3<br>
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AccountServices accountServices;
    @Autowired
    private Sheet1UserServices sheet1UserServices;
    @Autowired
    private Sheet2Services sheet2Services;
    @Autowired
    private Sheet1Services sheet1Services;

    public ApiController(){
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        return this.accountServices.listAll(null).toList();
    }

    @GetMapping("/sheet1Users")
    public List<Sheet1User> getAllSheet1Users(){
        return this.sheet1UserServices.listAll(null).toList();
    }

    @GetMapping("/sheet2s")
    public List<Sheet2> getAllSheet2s(){
        return this.sheet2Services.listAll(null).toList();
    }

    @GetMapping("/sheet1s")
    public List<Sheet1> getAllSheet1s(){
        return this.sheet1Services.listAll(null).toList();
    }
}
