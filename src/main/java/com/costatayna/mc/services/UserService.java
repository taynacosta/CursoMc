package com.costatayna.mc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.costatayna.mc.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
		//retorna o usuario q estiver logado no sistema
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
