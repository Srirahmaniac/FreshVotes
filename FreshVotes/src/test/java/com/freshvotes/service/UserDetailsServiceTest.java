package com.freshvotes.service;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceTest {

	@Test
	public void generate_EncryptrdPassword() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "root";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		assertThat(rawPassword, not(encodedPassword));
		
	}

}
