package com.assignment2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment2.dataModel.Assignment2Model;
import com.assignment2.entity.Assignment2Repo;
import com.assignment2.entity.Job;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.result.DeleteResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Spring annotations
@RestController
@RequestMapping("/api")
public class assignment2Controller {

	@Autowired
	private Assignment2Model model;
	
	
	@Autowired
	private Assignment2Repo repository;

	@Autowired
	private MongoOperations mongoOperations;
	
	

	@RequestMapping(value = "/addRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addRecord(@RequestBody Assignment2Model employeeData) throws JSONException {

		model.setId(employeeData.getId());
		model.setName_ar(employeeData.getName_ar());
		model.setName_en(employeeData.getName_en());
		model.setStatus(employeeData.getStatus());
		model.setUser_name(employeeData.getUser_name());
		model.setJobs(employeeData.getJobs());
		model.setOrg(employeeData.getOrg());
		
		repository.save(model);
		
		
		return new ResponseEntity(model, HttpStatus.OK);

	}

	@GetMapping("/Get/{id}/{user_name}/record")
    public ResponseEntity getRecord(@PathVariable String id,@PathVariable String user_name)
    {
		
    	 Assignment2Model employeeRecord = mongoOperations.findOne
    			 (Query.query(new Criteria().orOperator(Criteria.where("_id").is(id),Criteria.where("user_name").is(user_name))),
				Assignment2Model.class, "employee_Records");
    	 
    	 if(employeeRecord == null)
    		 return new ResponseEntity("No Record Found",HttpStatus.NOT_FOUND);
    	 return new ResponseEntity(employeeRecord, HttpStatus.OK);
    }

	 @PutMapping("/{employeId}/updateJob")
	    public ResponseEntity updateJobRecord(@PathVariable String employeId,@RequestBody Assignment2Model employee )
	    {
	    	Assignment2Model employeeRecord  = mongoOperations.findOne(Query.query(Criteria.where("_id").is(employeId)),
					Assignment2Model.class, "employee_Records");
	    	
	    	employeeRecord.setJobs(employee.getJobs());
	    	
	        repository.save(employeeRecord);
	        return new ResponseEntity(employeeRecord,HttpStatus.OK);
	    }
	 
	 @PutMapping("/{employeId}/updateOrganization")
	    public ResponseEntity updateOrganizationRecord(@PathVariable String employeId,@RequestBody Assignment2Model employee )
	    {
	    	Assignment2Model employeeRecord  = mongoOperations.findOne(Query.query(Criteria.where("_id").is(employeId)),
					Assignment2Model.class, "employee_Records");
	    	
	    	employeeRecord.setOrg(employee.getOrg());
	    	
	        repository.save(employeeRecord);
	        return new ResponseEntity(employeeRecord,HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/DeleteJobs/{employeeId}/record")
	    public ResponseEntity deleteJobRecord(@PathVariable String employeeId) 
	    {
		 Assignment2Model data = mongoOperations.findOne(Query.query(Criteria.where("_id").is(employeeId)),
					Assignment2Model.class, "employee_Records");
		 			
		 data.setJobs(null);
		 repository.save(data);
		 	if(data == null)
		 		return new ResponseEntity("Record not Found !!",HttpStatus.NOT_FOUND);
		 	else
		 		return new ResponseEntity("Record  deleted !!",HttpStatus.OK);
		 	
	    }
	 
	 @DeleteMapping("/DeleteOrganizations/{employeeId}/record")
	    public ResponseEntity deleteOrgRecord(@PathVariable String employeeId) 
	    {
		 Assignment2Model data = mongoOperations.findOne(Query.query(Criteria.where("_id").is(employeeId)),
					Assignment2Model.class, "employee_Records");
		 			
		 data.setOrg(null);
		 repository.save(data);
		 	if(data == null)
		 		return new ResponseEntity("Record not Found !!",HttpStatus.NOT_FOUND);
		 	else
		 		return new ResponseEntity("Record  deleted !!",HttpStatus.OK);
		 	
	    }
	 
	 @GetMapping("/getAllRecords/no_of_records")
	 public ResponseEntity getRecords(@RequestParam("no_of_records") String no_of_records)
	 {
		 List<Assignment2Model> List = repository.findAll().stream().limit(Integer.parseInt(no_of_records)).collect(Collectors.toList());;
		 
		 return new ResponseEntity(List,HttpStatus.OK);
	 }
	    
	
	public boolean checkRecord(String sequence) {

		Assignment2Model data = mongoOperations.findOne(Query.query(Criteria.where("_id").is(sequence)),
				Assignment2Model.class, "employee_Records");

		return data != null;
	}
}
