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
    public ResponseEntity<Response<List<Product>>> getProducts(){
        return Response.successfulResponse("All product fetched", productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<Product>>> getProduct(@PathVariable long id){
        var productFound = productService.getProduct(id);
        if (productFound.isEmpty()){
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Product not found");
        }
        return Response.successfulResponse("Product detail found");
    }

    @PutMapping
    public ResponseEntity<Response<Product>> updateProduct(@RequestBody Product product){
        return Response.successfulResponse("Update product success", productService.updateProduct(product));
    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@Validated @RequestBody Product product){
        var createdProduct = productService.addProduct(product);
        return Response.successfulResponse(HttpStatus.CREATED.value(), "New product created", product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Product>> deleteProduct(@PathVariable long id){
        Product deletedProduct = productService.deleteProduct(id);
        if (deletedProduct != null) {
            return Response.successfulResponse("Product deleted successfully", deletedProduct);
        } else {
            return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Product not found");
        }
    }

}
