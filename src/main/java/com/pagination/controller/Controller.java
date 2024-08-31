package com.pagination.controller;

import com.pagination.apiResponse.APIResponse;
import com.pagination.entity.Product;
import com.pagination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class Controller {
    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    public List<Product> getAllProductsController() {
        return  productService.getAllProducts();
    }

    @PostMapping("/insert")
    public Product insertProductInDb(@RequestBody Product product) {
        return  productService.insertProductIntoDatabase(product);
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable Long id) {
        return  productService.getProductById(id);
    }

    @GetMapping
    private APIResponse<List<Product>> getProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/sort")
    private APIResponse<List<Product>> getProductsWithSort(@RequestParam(defaultValue = "id")String sortBy) {
        List<Product> allProducts = productService.findProductsWithSorting(sortBy);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/pagination")
    private APIResponse<Page<Product>> getProductsWithPagination(@RequestParam(defaultValue = "0") int pageNumber,
                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<Product> productsWithPagination = productService.findProductsWithPagination(pageNumber, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @GetMapping("/paginationAndSort")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Page<Product> productsWithPaginationSorting = productService.findProductsWithPaginationAndSorting(pageNumber, pageSize, sortBy);
        return new APIResponse<>(productsWithPaginationSorting.getSize(), productsWithPaginationSorting);
    }
}
