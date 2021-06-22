package com.airton.psjava.dto;

import com.airton.psjava.enums.EnumShopcartSortAttribute;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotNull(message = "Score is required")
    private short score;
    @NotBlank(message = "Image is required")
    private String image;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, BigDecimal price, short score, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.score = score;
        this.image = image;
    }

    public BigDecimal calcTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    
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
