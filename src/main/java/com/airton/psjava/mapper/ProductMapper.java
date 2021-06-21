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

    public static Product toEntity(ProductDTO pdto) {
        Product product = new Product();
        product.setId(pdto.getId());
        product.setName(pdto.getName());
        product.setScore(pdto.getScore());
        product.setPrice(pdto.getPrice());
        product.setImage(pdto.getImage());

        return product;
    }

    public static void merge(Product entity, ProductDTO dto) {
        entity.setImage(dto.getImage());
        entity.setScore(dto.getScore());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
    }

}
