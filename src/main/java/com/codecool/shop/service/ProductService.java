package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }


    /*public Supplier getSupplier(int supplierId){
        return supplierDao.find(supplierId);
    }*/


    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }


    public List<Product> getFilteredProducts(int categoryId, int supplierId){
        List<Product> filteredProducts = getProductsForCategory(categoryId);
        if (supplierId != 0) {
           filteredProducts = filteredProducts.stream()
                   .filter(element -> element.getSupplier().getId() == supplierId).collect(toList());
        }
        return filteredProducts;
    }

    public List<Product> getProductsForCategory(int categoryId){
        if(categoryId == 0){
            return productDao.getAll();
        }
        else{
            ProductCategory category = getProductCategory(categoryId);
            return productDao.getBy(category);
        }
    }

    /*public List<Product> getProductsForSupplier(int supplierId){
        if(supplierId == 0){
            return productDao.getAll();
        }
        else {
            Supplier supplier = getSupplier(supplierId);
            return productDao.getBy(supplier);
        }
    }*/

    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public List<ProductCategory> getAllCategories(){
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    /*public List<Product> getFilteredProductsById(String categoryId, String supplierId) {
        return ProductDaoMem.getInstance().getAll().stream().filter(product ->
                        (product.getProductCategory().getId()==Integer.parseInt(categoryId)||categoryId.equals("0"))&&
                        (product.getSupplier().getId()==Integer.parseInt(supplierId)||supplierId.equals("0"))).collect(Collectors.toList());
    }*/
}
