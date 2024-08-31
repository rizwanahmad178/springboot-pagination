package com.pagination.service;

import com.pagination.entity.Product;
import com.pagination.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product insertProductIntoDatabase(Product product) {
        return productRepository.save(product);
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }
    @Override
    public List<Product> findProductsWithSorting(String sortBy){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,sortBy));
    }
    @Override
    public Page<Product> findProductsWithPagination(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        return  products;
    }
    @Override
    public Page<Product> findProductsWithPaginationAndSorting(int pageNumber,int pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Product> products = productRepository.findAll(pageable);
        return  products;
    }

    @PostConstruct
    public void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 300)
                .mapToObj(i -> new Product("product" + i, 1000.0, new Random().nextInt(50000)))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }

}
