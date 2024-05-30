package com.fresh_goodies_spring.fresh_goodies_spring.Products;

import com.fresh_goodies_spring.fresh_goodies_spring.Products.Service.ProductService;
import com.fresh_goodies_spring.fresh_goodies_spring.Products.model.Product;
import com.fresh_goodies_spring.fresh_goodies_spring.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<Response<List<Product>>> getProducts(@RequestParam(value = "search", required = false) String search) {
        List<Product> products = productService.searchProducts(search);
        if (products.isEmpty()) {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "No products found matching the criteria");
        }
        return Response.successfulResponse("All products fetched", products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<Product>>> getProduct(@PathVariable long id){
        var productFound = productService.getProduct(id);
        return Response.successfulResponse("Product detail found", productFound);
    }

    @PutMapping
    public ResponseEntity<Response<Product>> updateProduct(@Validated @RequestBody Product product){
        return Response.successfulResponse("Update product success", productService.updateProduct(product));
    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@Validated @RequestBody Product product){
        var createdProduct = productService.addProduct(product);
        return Response.successfulResponse(HttpStatus.CREATED.value(), "New product created", createdProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Product>> deleteProduct(@PathVariable long id){
        Product deletedProduct = productService.deleteProduct(id);
        return Response.successfulResponse("Product deleted successfully", deletedProduct);
    }

}

