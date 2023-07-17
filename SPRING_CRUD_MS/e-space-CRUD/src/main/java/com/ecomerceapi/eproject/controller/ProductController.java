package com.ecomerceapi.eproject.controller;

import com.ecomerceapi.eproject.model.ProductDto;
import com.ecomerceapi.eproject.service.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/2")
@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    // Cria um novo produto
    @PostMapping("/new")
    public String newProduct(@RequestBody ProductDto productDto) {
        productService.newProduct(productDto);
        return ("redirect:/api/2/find");
    }

    // Obtém todos os produtos
    @GetMapping("/find")
    public List<ProductDto> getProduct() {
        return productService.getProduct();
    }

    // Obtém um produto por ID
    @GetMapping("/find/{id}")
    public ProductDto findById(@PathVariable Integer id) {
        return productService.findById(id);
    }
}
