package com.globalsolution2.fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.globalsolution2.fiap.repository.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByNmLogin(username);
	}

}
