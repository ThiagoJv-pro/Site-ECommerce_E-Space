package com.ecomerceapi.eproject.model;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Integer id;
    private String name;

    @Embedded // Indica que o conteúdo da classe ImgData será embutido no objeto ProductDto
    private ImgData img;
    private Double value;

    @Embedded // Indica que o conteúdo da classe DescriptionData será embutido no objeto ProductDto
    private DescriptionData description;

    // Método estático para converter um objeto Product em um objeto ProductDto
    public static ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();

        // Copia os dados da imagem do Product para o objeto ImgData do ProductDto
        ImgData imgDataDto = new ImgData();
        imgDataDto.setImg1(product.getImg().getImg1());
        imgDataDto.setImg2(product.getImg().getImg2());
        imgDataDto.setImg3(product.getImg().getImg3());
        imgDataDto.setImg4(product.getImg().getImg4());
        imgDataDto.setImg5(product.getImg().getImg5());

        // Copia os dados da descrição do Product para o objeto DescriptionData do ProductDto
        DescriptionData descriptionDataDto = new DescriptionData();
        descriptionDataDto.setType(product.getDescription().getType());
        descriptionDataDto.setColor(product.getDescription().getColor());
        descriptionDataDto.setMaterial(product.getDescription().getMaterial());
        descriptionDataDto.setBrand(product.getDescription().getBrand());
        descriptionDataDto.setProductDescription(product.getDescription().getProductDescription());

        // Atribui os valores convertidos ao objeto ProductDto
        productDto.setId(product.getId());
        productDto.setImg(imgDataDto);
        productDto.setValue(product.getValue());
        productDto.setDescription(descriptionDataDto);
        productDto.setName(product.getName());

        return productDto;
    }

}
