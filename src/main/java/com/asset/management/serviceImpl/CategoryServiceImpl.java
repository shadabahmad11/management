package com.asset.management.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.asset.management.bean.Category;
import com.asset.management.repository.CategoryRepository;
import com.asset.management.service.CategoryService;
import com.asset.management.utility.Constant;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	private Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	@Override
	public String saveCategory(Category category) {
		String result=null;
		Category temp=categoryRepository.findByCategoryName(category.getCategoryName());
		if(temp!=null) {
			result=Constant.DUPLICATE_CATEGORY;
		}else {
			category= save(category);
			result=Constant.ADDED_SUCCESSFULLY;
		}
		return result;
	}
	
	@Override
	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public String updateCategory(Category category) {
		String result=null;
		if(category.getCategoryId()==null) {
			result=Constant.ID_MISSING;
		}else if(!StringUtils.hasText(category.getCategoryName())) {
			result=Constant.CATEGORY_NAME_MISSING;
		}else {
			Category temp=categoryRepository.findByCategoryName(category.getCategoryName());
			if(temp==null || temp.getCategoryId()==category.getCategoryId()) {
				save(category);
				result=Constant.ADDED_SUCCESSFULLY;
			}else {
				result=Constant.DUPLICATE_CATEGORY;
			}
		}
		return result;
	}

}
