package com.spring.e_com.controller;


import com.spring.e_com.model.Product;
import com.spring.e_com.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {


    private ProductService service;
    public ProductController(ProductService service){
        this.service=service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> list=service.getAllProducts();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=service.getProduct(id);
        if(product!=null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try{
            Product product1=service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/products/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product=service.getProduct(productId);
        byte[] image=product.getImageDate();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(image);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId,@RequestPart MultipartFile imageFile,@RequestPart Product product){
        System.out.println("hello");
        Product p=null;
        System.out.println("hello");
        try{
            System.out.println("hello");
            p=service.updateProduct(productId,imageFile,product);
            System.out.println("hello");
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        if(p!=null){
            return new ResponseEntity<>("updated Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        Product p=service.getProduct(productId);
        if(p!=null){
            service.deleteProduct(productId);
            return new ResponseEntity<>("Deleted successfully ",HttpStatus.OK);
        }
        return new ResponseEntity<>("Prodduct not found",HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products=service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
