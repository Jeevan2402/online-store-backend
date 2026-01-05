package com.store.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.product.dto.ProductPurchaseRequest;
import com.store.product.dto.ProductPurchaseResponse;
import com.store.product.dto.ProductRequset;
import com.store.product.dto.ProductResponse;
import com.store.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequset producrReq){
		return ResponseEntity.ok(productService.createProduct(producrReq));
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct( @RequestBody List<ProductPurchaseRequest> purchaseReq) {
		return ResponseEntity.ok(productService.purchaseProduct(purchaseReq));
	}
	
	@GetMapping("/{product-id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId) {
		return ResponseEntity.ok(productService.findById(productId));
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductResponse>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	
}