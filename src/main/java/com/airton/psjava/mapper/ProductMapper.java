package com.airton.psjava.mapper;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO pdto = new ProductDTO();
        pdto.setId(product.getId());
        pdto.setName(product.getName());
        pdto.setScore(product.getScore());
        pdto.setPrice(product.getPrice());
        pdto.setImage(product.getImage());

        return pdto;
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        List<ProductDTO> listDTO = new ArrayList<>();
        for (Product prod : products) {
            listDTO.add(toDTO(prod));
        }
        return listDTO;
    }

}
