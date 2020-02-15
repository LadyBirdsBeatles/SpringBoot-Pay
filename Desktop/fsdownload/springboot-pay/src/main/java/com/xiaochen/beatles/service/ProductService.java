package com.xiaochen.beatles.service;

import com.xiaochen.beatles.pojo.Product;

import java.util.List;

public interface ProductService {

	/**
	 * 获取所有产品列表
	 * @return
	 */
	public List<Product> getProducts();
	
	/**
	 * 根据产品ID查询产品详情
	 * @param productId
	 * @return
	 */
	public Product getProductById(String productId);
}
