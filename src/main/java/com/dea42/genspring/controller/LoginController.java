package com.dea42.genspring.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.repo.UserRepository;

/**
 * REST interface for basic login actions
 * 
 * @author avata
 *
 */
@RestController
public class LoginController {

	private final UserRepository userRepository;

	public LoginController(UserRepository accountRepository) {
		this.userRepository = accountRepository;
	}

	@GetMapping("account/current")
	@ResponseStatus(value = HttpStatus.OK)
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public Account currentAccount(Principal principal) {
		Assert.notNull(principal, "Check principal null");
		return userRepository.findOneByEmail(principal.getName());
	}

	@GetMapping("account/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public Account account(@PathVariable("id") Integer id) {
		return userRepository.getOne(id);
	}
}
