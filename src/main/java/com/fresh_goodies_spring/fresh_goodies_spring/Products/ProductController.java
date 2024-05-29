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
        return Response.successfulResponse("All product fetched"); //ini belom
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<Product>>> getProduct(@PathVariable long id){
        //ini belom
        return Response.successfulResponse("Product detail found");
    }

    @PutMapping
    public ResponseEntity<Response<Product>> updateProduct(@RequestBody Product product){
        // ini belom
        return Response.successfulResponse("Update product success");
    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@Validated @RequestBody Product product){
        //ini belom
        return Response.successfulResponse(HttpStatus.CREATED.value(), "New product created", product);
    }

}
