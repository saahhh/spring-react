package com.kh.springchap2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springchap2.model.Product;

//JPARepository
@Mapper
public interface ProductMapper {
	// src/main/resources/mapper/ProductMapper.xml 밑에 작성해준 sql id만 작성할 것
	// ex ) <select id="getAllProduct"
	// JPA처럼 mapper.xml 파일에는 작성하지 않은 sql문과 id에 관련된 메서드를 모두 작성해주면 mapper.xml 밑에는 사용할 수 없는 id가 바라보기 때문에 에러남
	List<Product> getAllProduct();
}
