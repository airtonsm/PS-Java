package com.airton.psjava.dto;

import javax.validation.constraints.NotNull;

public class ProductQuantityDTO {

    @NotNull(message = "Product id is required")
    private Long id;
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    public ProductQuantityDTO(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
