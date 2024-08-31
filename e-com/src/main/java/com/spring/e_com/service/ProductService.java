package com.spring.e_com.service;


import com.spring.e_com.model.Product;
import com.spring.e_com.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    private ProductRepo repo;
    public ProductService(ProductRepo repo){
        this.repo=repo;
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return repo.save(product);
    }

    public Product getProduct(int id){
        return repo.findById(id).orElse(null);
    }

    public Product updateProduct(int id,MultipartFile imageFile,Product product) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageDate(imageFile.getBytes());
        return repo.save(product);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword){
        return repo.searchProducts(keyword);
    }
}
