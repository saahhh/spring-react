package com.kh.springchap1.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springchap1.model.Product;

public interface ProductRepository extends JpaRepository <Product, Long>{

}
