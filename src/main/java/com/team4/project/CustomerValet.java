package com.team4.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerValet implements CustomerService {

	@Autowired
	CustomerRepository repo;
	
	@Override
	public Iterable<Customer> getCustomers() {
		return repo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(long id) {
		return repo.findById(id);
	}

	
	@Override
	public void deleteCustomerById(long id) {
		repo.deleteById(id);
		
	}

	@Override
	public Customer addCustomer(Customer c) {
		return repo.save(c);
	}

	@Override
	public Customer getCustomerByName(String name) {
		List<Customer> customer = repo.findByName(name);
		if (customer.isEmpty()) {
			return null;
		}
		return customer.get(0);
	   
	}

	@Override
	public void updateCustomer(Customer c) {
		repo.save(c);
	}
}
