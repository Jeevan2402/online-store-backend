package com.store.product.reposiotry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	List<Product> findAllByIdInOrderById(List<Integer> productIds);

}
