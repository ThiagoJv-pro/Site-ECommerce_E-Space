package com.ecomerceapi.eproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionData {

    private String productDescription;
    private String type;
    private String color;
    private String material;
    private String brand;

}
