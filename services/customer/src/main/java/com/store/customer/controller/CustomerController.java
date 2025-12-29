package com.store.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.customer.dto.CustomerRequest;
import com.store.customer.dto.CustomerResponse;
import com.store.customer.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/createCustomer")
	public ResponseEntity<String> createCustomer(
			@RequestBody @Valid CustomerRequest customerRequest){
		return ResponseEntity.ok(customerService.createCustomer(customerRequest));
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody @Valid CustomerRequest customerRequest) {
		customerService.updateCustomer(id, customerRequest);
		
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/findAllCustomer")
	public ResponseEntity<List<CustomerResponse>> findAllCustomers(){
		return ResponseEntity.ok(customerService.findAllCustomers());
	}
	
	@GetMapping("/customerExists/{id}")
	public ResponseEntity<Boolean> customerExists(@PathVariable String id){
		return ResponseEntity.ok(customerService.customerExists(id));
	}
	
	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.accepted().build();
	}
	
}
