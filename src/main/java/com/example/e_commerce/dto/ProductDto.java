package com.example.e_commerce.dto;

import com.example.e_commerce.document.Product;
import com.example.e_commerce.model.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    public ProductDto(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.color = entity.getColor();
        this.size = entity.getSize();
        this.manifacture = entity.getManifacture();
        this.merchent = entity.getMerchent();
        this.stock = entity.getStock();
        this.isOffered = entity.getIsOffered();
        this.offer = entity.getOffer();

    }


    private String id;

    @NotEmpty
    private String manifacture;
    @NotEmpty
    private String merchent;
    @NotEmpty(message = "name can not be empty")
    private String name;
    @NotEmpty
    private String description;
    @Min(value = 1,message = "price should be greater than 0")
    private Float price;
    @Min(1)
    private Integer stock;



    private String color;
    private Size size;
    private Boolean isOffered;
    private Float offer;


    private String manifactureName;

    private String merchantName;



}
