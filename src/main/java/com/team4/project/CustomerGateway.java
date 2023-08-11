package com.team4.project;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@CrossOrigin
@RestController
@RequestMapping("/gateway/customers")
public class CustomerGateway {

	static final String JSON = "application/json";

	@Autowired
	@Qualifier("mockCustomerService")
	CustomerService customerService;
	
	// GET all customers in the list
	@GetMapping
	public Iterable<Customer> getAllCustomers(HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomers();
	}
	
	
	// GET a customer by their place in the list
	@GetMapping("/{id}")
	public Optional<Customer> getOneSingleCustomer(@PathVariable long id, HttpServletResponse response) {
		
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomerById(id);
	}
	
	
	// Not passing in any ID at the moment, will not work
	// POST a new customer to the list
	@PostMapping(consumes = JSON, produces = JSON)
	public ResponseEntity<?> addCustomer(@RequestBody Customer c, HttpServletResponse response) {
		Customer postedCustomer = customerService.addCustomer(c);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(postedCustomer, headers, HttpStatus.CREATED);
	}
	
	
	
	// GET a customer by their name
	@GetMapping("/byname/{username}")
	public Customer getCustomerByName(@PathVariable String username, HttpServletResponse response) {
			
		response.setStatus(HttpServletResponse.SC_OK);
		return customerService.getCustomerByName(username);
	}
	
	
	
	@PostMapping(path = "/byname")
	@ResponseBody
	public Customer getCustomerByNamePost(@RequestBody() String customerName, HttpServletResponse response){
	  
		return getCustomerByName(customerName, response);
	}
	
	
	
	@PutMapping("/{customerId}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, @PathVariable long customerId) {
		if (newCustomer.getId() != customerId) {
			return ResponseEntity.badRequest().build();
		}
		customerService.updateCustomer(newCustomer);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(location).build();
	}
	

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable long id){
	    
		customerService.deleteCustomerById(id);
		return ResponseEntity.ok().build();
	}
	
	

}
