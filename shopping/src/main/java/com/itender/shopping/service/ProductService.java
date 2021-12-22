package com.itender.shopping.service;


import com.itender.shopping.entity.ProductInfo;

import java.util.List;

/**
 * @author itender
 */
public interface ProductService {
    /**
     * 添加商品
     *
     * @param product
     */
    void createProduct(ProductInfo product);

    /**
     * 查询商品
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<ProductInfo> queryProduct(int page, int pageSize);
}
