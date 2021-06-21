package com.airton.psjava.mapper;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.entities.Shopcart;
import com.airton.psjava.entities.ShopcartProduct;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public static ShopcartDTO toDTO(Shopcart shopcart) { // overload
        return toDTO(shopcart, null);
    }

    public static List<ShopcartDTO> toDTOList(List<Shopcart> shopcarts, String sortAttribute) {
        List<ShopcartDTO> listDTO = new ArrayList<>();
        for (Shopcart shop : shopcarts) {
            listDTO.add(toDTO(shop, sortAttribute));
        }
        return listDTO;
    }

    public static List<ShopcartDTO> toDTOList(List<Shopcart> shopcarts) { // overload
        return toDTOList(shopcarts, null);
    }


}
