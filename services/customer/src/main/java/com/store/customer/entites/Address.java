package com.store.customer.entites;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

	private String street;
	private String houseNumber;
	private String zipCode;
	
}
