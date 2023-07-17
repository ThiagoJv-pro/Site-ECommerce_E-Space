package com.ecomerceapi.eproject.service;

import com.ecomerceapi.eproject.model.Product;
import com.ecomerceapi.eproject.model.ProductDto;
import com.ecomerceapi.eproject.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Cria um novo produto com base no objeto ProductDto fornecido e retorna o objeto ProductDto criado
    public ProductDto newProduct(ProductDto productDto) {
        Product product = productRepository.save(Product.convert(productDto));
        return ProductDto.convert(product);
    }

    // Obtém todos os produtos do repositório e retorna uma lista de objetos ProductDto
    public List<ProductDto> getProduct() {
        List<Product> product = productRepository.findAll();
        return product.stream().map(ProductDto::convert).collect(Collectors.toList());
    }

    // Encontra um produto pelo ID no repositório e retorna um objeto ProductDto correspondente
    public ProductDto findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ProductDto.convert(product.get());
        }
        return null;
    }
}
