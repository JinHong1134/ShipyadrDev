package com.hwy.shipyard.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwy.shipyard.dataobject.Product;
import com.hwy.shipyard.mapper.ProductMapper;
import com.hwy.shipyard.service.ProductService;
import com.hwy.shipyard.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;

    @Override
    public Object add(Product product) {
        try {
            mapper.add(product);
            return JsonData.buildSuccess(null,"增加成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("增加失败");
        }
    }

    @Override
    public Object update(Product product) {
        try {
            mapper.update(product);
            return JsonData.buildSuccess(null, "更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("更新失败");
        }
    }

    @Override
    public Object delete(String productId) {
        try {
            mapper.delete(productId);
            return JsonData.buildSuccess(null,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("删除失败");
        }
    }

    @Override
    public Object getAll(Integer pageNum, Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<Product> allProduct = mapper.getAll();
            PageInfo<Product> productPageInfo = new PageInfo<>(allProduct);
            return JsonData.buildSuccess(productPageInfo, "查找全部成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("查找全部失败");
        }
    }

    @Override
    public Object getById(String productId) {
        try{
            Product product = mapper.findById(productId);
            List<Product> list = new ArrayList<>();
            list.add(product);
            return JsonData.buildSuccess(list,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }

    @Override
    public Object getByName(String productName) {
        try {
            Product product = mapper.findByName(productName);
            List<Product> list = new ArrayList<>();
            list.add(product);
            return JsonData.buildSuccess(list,"查询成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("查询失败");
        }
    }


}
