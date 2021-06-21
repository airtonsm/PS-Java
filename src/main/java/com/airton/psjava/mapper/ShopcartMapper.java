package com.airton.psjava.mapper;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.entities.Shopcart;
import com.airton.psjava.entities.ShopcartProduct;
import org.apache.commons.lang3.StringUtils;

public class ShopcartMapper {

    public static ShopcartDTO toDTO(Shopcart shopcart, String sortAttribute) {
        ShopcartDTO dto = new ShopcartDTO();

        dto.setId(shopcart.getId());
        dto.setMoment(shopcart.getMoment());

        for (ShopcartProduct sp : shopcart.getProducts()) {
            ProductDTO pdto = ProductMapper.toDTO(sp.getProduct());
            pdto.setQuantity(sp.getQuantity());
            dto.setProductQuantity(dto.getProductQuantity() + sp.getQuantity());
            dto.getProducts().add(pdto);
        }

        if (StringUtils.isNotBlank(sortAttribute)) {
            dto.sortProducts(sortAttribute);
        }
        return dto;
    }



}
