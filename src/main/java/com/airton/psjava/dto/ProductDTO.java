package com.airton.psjava.dto;

import com.airton.psjava.enums.EnumShopcartSortAttribute;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private short score;
    private String image;
    private Integer quantity;
    
    public static String sortProduct(String sortAttribute) {
        EnumShopcartSortAttribute attribute = EnumShopcartSortAttribute.valueOfString(sortAttribute);

        if (Objects.isNull(attribute)) {
            return "id";
        } else {
            return attribute.getSortAttribute().toLowerCase();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
