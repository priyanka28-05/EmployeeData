package com.assignment2.dataModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import com.assignment2.entity.Job;
import com.assignment2.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Mongodb annotation
//marks a class for the domain object that we want to persist in the db
@Document(collection = "employee_Records")
//Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
//Spring stereotype annotation
@Component
public class Assignment2Model {

	@Id
	   @Field("id")
	   private String id;
	
	@Field("name-en")
	   private String name_en;
	
	@Field("name-ar")
	   private String name_ar;
	
	@Indexed(unique = true)
	@Field("user-name")
	   private String user_name;
	
	@Field("status")
	   private String status;
	
	private Job jobs;
	
	private Organization org;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_ar() {
		return name_ar;
	}

	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Job getJobs() {
		return jobs;
	}

	public void setJobs(Job jobs) {
		this.jobs = jobs;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
}
