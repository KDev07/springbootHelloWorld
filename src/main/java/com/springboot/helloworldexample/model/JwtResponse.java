package com.springboot.helloworldexample.model;

import java.io.Serializable;

public class JwtResponse implements Serializable{
	
	private static final long serialVersionUID = -731941909731840578L;
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
