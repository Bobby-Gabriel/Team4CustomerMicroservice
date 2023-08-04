package com.team4.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerMongoService implements CustomerService {

	@Autowired
	CustomerMongoRepository repo;
	
	@Override
	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(String id) {
		return repo.findById(id);
	}

	
	@Override
	public void deleteCustomerById(String id) {
		repo.deleteById(id);
		
	}

	@Override
	public void addCustomer(Customer c) {
		repo.save(c);
	}

	@Override
	public Customer getCustomerByName(String name) {
		return repo.findByName(name).get(0);
	   
	}

	@Override
	public void updateCustomer(Customer c) {
		repo.save(c);
	}
}
