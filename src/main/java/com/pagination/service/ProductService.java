package com.pagination.service;

import com.pagination.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product insertProductIntoDatabase(Product product);

    Product getProductById(Long id );

    List<Product> findProductsWithSorting(String sortBy);

    Page<Product> findProductsWithPagination(int pageNumber, int pageSize);

    Page<Product> findProductsWithPaginationAndSorting(int pageNumber,int pageSize, String sortBy);
}
