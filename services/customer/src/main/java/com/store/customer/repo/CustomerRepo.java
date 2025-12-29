package com.store.customer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.store.customer.entites.Customer;

@Repository
public interface CustomerRepo extends MongoRepository<Customer, String>{

}
