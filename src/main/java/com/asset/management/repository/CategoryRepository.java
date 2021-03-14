package com.asset.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asset.management.bean.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {

	public Category findByCategoryName(String categoryName);
	
}
