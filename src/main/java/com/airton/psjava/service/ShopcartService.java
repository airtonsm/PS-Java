package com.airton.psjava.service;

import com.airton.psjava.dto.ProductQuantityDTO;
import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.entities.Shopcart;
import com.airton.psjava.entities.ShopcartProduct;
import com.airton.psjava.exception.ResourcesNotFoundException;
import com.airton.psjava.mapper.ShopcartMapper;
import com.airton.psjava.repository.ProductRepository;
import com.airton.psjava.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartService {



    @Autowired
    private ShopcartRepository shopcartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ShopcartDTO> findAll(String sortAttribute) {
        return ShopcartMapper.toDTOList(shopcartRepository.findAll(), sortAttribute);
    }

    public ShopcartDTO findById(Long id, String sortAttribute) {
        Shopcart shopcart = shopcartRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException(id));
        return ShopcartMapper.toDTO(shopcart, sortAttribute);
    }

    public ShopcartDTO findById(Long id) {
        return findById(id, null);
    }

    public ShopcartDTO checkout(Long id) {
        ShopcartDTO dto = this.findById(id);
        dto.calcPrice();
        return dto;
    }

    public ShopcartDTO insert(List<ProductQuantityDTO> products) {
        Shopcart shopcart = new Shopcart();

        for (ProductQuantityDTO product : products) {
            Integer quantity = product.getQuantity().equals(0) ? 1 : product.getQuantity();
            shopcart.getProducts().add(new ShopcartProduct(shopcart, productRepository.getById(product.getId()), quantity));
        }
        return ShopcartMapper.toDTO(shopcartRepository.save(shopcart));
    }
}
