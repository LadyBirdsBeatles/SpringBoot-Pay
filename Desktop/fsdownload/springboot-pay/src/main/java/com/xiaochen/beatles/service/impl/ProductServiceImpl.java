package com.xiaochen.beatles.service.impl;

import com.xiaochen.beatles.mapper.ProductMapper;
import com.xiaochen.beatles.pojo.Product;
import com.xiaochen.beatles.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<Product> getProducts() {
		List<Product> list = productMapper.selectByExample();
		
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		
		return productMapper.selectByPrimaryKey(productId);
	}

}
