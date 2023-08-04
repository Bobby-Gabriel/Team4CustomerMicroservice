package com.team4.project;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

	List<Customer> findByName(@Param("name") String name);
}
