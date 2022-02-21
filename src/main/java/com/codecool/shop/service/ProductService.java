package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        if(categoryId == 0){
            return productDao.getAll();
        }
        else{
            var category = productCategoryDao.find(categoryId);
            return productDao.getBy(category);
        }
    }

    public List<ProductCategory> getAllCategories(){
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return SupplierDaoMem.getInstance().getAll();
    }

    public List<Product> getFilteredProductsById(String categoryId, String supplierId) {
        return ProductDaoMem.getInstance().getAll().stream().filter(product ->
                        (product.getProductCategory().getId()==Integer.parseInt(categoryId)||categoryId.equals("0"))&&
                        (product.getSupplier().getId()==Integer.parseInt(supplierId)||supplierId.equals("0"))).collect(Collectors.toList());
    }

    public ArrayList<String> convertList(List<Product> filteredProducts){
        ArrayList<String> products = new ArrayList<String>();
        for (Product product:filteredProducts) {
            products.add(product.toString());
        }
        return products;

    }
}
