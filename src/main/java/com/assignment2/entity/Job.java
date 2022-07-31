package com.assignment2.entity;

import org.springframework.stereotype.Component;

@Component
public class Job {

	private String code;
	private String role;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
