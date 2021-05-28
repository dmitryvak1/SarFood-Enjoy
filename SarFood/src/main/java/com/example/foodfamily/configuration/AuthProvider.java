package com.example.foodfamily.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.foodfamily.entity.User;
import com.example.foodfamily.service.UserService;

@Component
public class AuthProvider implements AuthenticationProvider {
	
	@Autowired
	UserService userService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		User user = (User) userService.loadUserByUsername(email);
		if(user != null && user.getEmail().equals(email))
		{
			if(!passwordEncoder.matches(password, user.getPassword()))
			{
				throw new BadCredentialsException("Wrong password");
			}

			Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

			return new UsernamePasswordAuthenticationToken(user, password, authorities);
		}
		else
			throw new BadCredentialsException("Username not found");
	}

	public boolean supports(Class<?> arg)
	{
		return true;
	}
}
