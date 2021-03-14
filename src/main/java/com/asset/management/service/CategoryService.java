package com.asset.management.service;

import java.util.List;

import com.asset.management.bean.Category;


public interface CategoryService {

	public String saveCategory(Category category);
	public String updateCategory(Category category);
	public List<Category> listCategory();
}
