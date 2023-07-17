package com.ecomerceapi.eproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Embedded // Indica que o conteúdo da classe ImgData será armazenado como colunas na tabela Product
    private ImgData img;
    private Double value;

    @Embedded // Indica que o conteúdo da classe DescriptionData será armazenado como colunas na tabela Product
    private DescriptionData description;

    // Método estático para converter um objeto ProductDto em um objeto Product
    public static Product convert(ProductDto productDto) {
        Product product = new Product();

        // Copia os dados da imagem do ProductDto para o objeto ImgData
        ImgData imgData = new ImgData();
        imgData.setImg1(productDto.getImg().getImg1());
        imgData.setImg2(productDto.getImg().getImg2());
        imgData.setImg3(productDto.getImg().getImg3());
        imgData.setImg4(productDto.getImg().getImg4());
        imgData.setImg5(productDto.getImg().getImg5());

        // Copia os dados da descrição do ProductDto para o objeto DescriptionData
        DescriptionData descriptionData = new DescriptionData();
        descriptionData.setType(productDto.getDescription().getType());
        descriptionData.setColor(productDto.getDescription().getColor());
        descriptionData.setMaterial(productDto.getDescription().getMaterial());
        descriptionData.setBrand(productDto.getDescription().getBrand());
        descriptionData.setProductDescription(productDto.getDescription().getProductDescription());

        // Atribui os valores convertidos ao objeto Product
        product.setId(productDto.getId());
        product.setImg(imgData);
        product.setValue(productDto.getValue());
        product.setDescription(descriptionData);
        product.setName(productDto.getName());

        return product;
    }
}
