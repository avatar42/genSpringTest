package com.dea42.genspring.service;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dea42.genspring.entity.Account;
import com.dea42.genspring.repo.UserRepository;

/**
 * Title: Parent with user login services <br>
 * Description: UserServices. <br>
 * Copyright: Copyright (c) 2001-2020<br>
 * Company: RMRR<br>
 * 
 * @author Gened by com.dea42.build.GenSpring version 0.7.0<br>
 * @version 0.7.0<br>
 */
@Slf4j
public abstract class UserServices<T> implements UserDetailsService {
	@Autowired
	protected UserRepository userRepository;

	public static final String ROLE_PREFIX = "ROLE_";

	@Autowired
	private PasswordEncoder passwordEncoder;

	protected String encrypt(String s) {
		if (StringUtils.isAllBlank(s)) {
			return null;
		}

		String rtn = passwordEncoder.encode(s);

		log.trace("Encrypted:'" + s + "' to '" + rtn + "'");
		return rtn;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = userRepository.findOneByEmail(username);
		if (account == null) {
			return null;
			// throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}

	public boolean isEmailAlreadyInUse(String username) throws UsernameNotFoundException {
		if (username == null)
			return false;

		Account account = userRepository.findOneByEmail(username);
		if (account == null)
			return false;

		return true;
	}

	public boolean login(String email, String password) {
		Account account = userRepository.findOneByEmail(email);
		if (account == null) {
			log.warn("User email " + email + " not found in DB");
			return false;
		}

		if (passwordEncoder.matches(password, account.getPassword())) {
			SecurityContextHolder.getContext().setAuthentication(genAuthToken(account));
			// TODO: add last logged in date/time.
			return true;
		}

		log.warn("User " + email + " password did not match");
		return false;

	}

	/**
	 * Gen token.
	 * 
	 * @param account
	 * @return
	 */
	private Authentication genAuthToken(Account account) {
		log.debug("authing:" + account);
		Authentication a = new UsernamePasswordAuthenticationToken(createUser(account), null,
				Collections.singleton(createAuthority(account)));
		log.debug("result in:" + a);
		return a;
	}

	private User createUser(Account account) {
		return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		log.debug("logged in:" + account);
		return new SimpleGrantedAuthority(account.getUserrole());
	}

	public abstract T save(T obj);
}
