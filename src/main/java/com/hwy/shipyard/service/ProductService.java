package com.hwy.shipyard.service;

import com.hwy.shipyard.dataobject.Product;

public interface ProductService {
    Object add(Product product);

    Object update(Product product);

    Object delete(String productId);

    Object getAll(Integer pageNum, Integer pageSize);

    Object getById(String productId);

    Object getByName(String productName);
}
