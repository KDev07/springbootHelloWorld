package com.springboot.helloworldexample.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.helloworldexample.model.Users;
import com.springboot.helloworldexample.exception.ResourceAlreadyExistsException;
import com.springboot.helloworldexample.model.UserDTO;
import com.springboot.helloworldexample.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
	public ResponseEntity<Users> save(UserDTO userDTO) throws ResourceAlreadyExistsException {
		Users user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
		if (user != null) {
			throw new ResourceAlreadyExistsException(userDTO.getUsername() + " already exist");
		}
		else {
			Users newUser = new Users();
			newUser.setUsername(userDTO.getUsername());
			newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
			return ResponseEntity.ok(userRepository.save(newUser));
		}
	}
}
