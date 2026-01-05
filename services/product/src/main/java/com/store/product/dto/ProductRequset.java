package com.store.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequset(
		Integer id,

		@NotNull(message = "Product Name is Required")
		String name,
		
		@NotNull(message = "Product Description is Required")
		String description,

		@Positive(message = "Available Quantity should be Positive")
		double availableQuantity,

		@Positive(message = "Price should be Positive")
		BigDecimal price,
		
		@NotNull(message = "Product Category is Required")
		Integer categoryId

) {

}
