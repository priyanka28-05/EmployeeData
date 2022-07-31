package com.assignment2.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment2.dataModel.Assignment2Model;

@Repository
public interface Assignment2Repo   extends MongoRepository<Assignment2Model, String> {

}
