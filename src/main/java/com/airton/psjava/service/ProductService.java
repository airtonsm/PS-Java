package com.airton.psjava.service;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.mapper.ProductMapper;
import com.airton.psjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(String sortAttribute) {
        String sortBy = ProductDTO.sortProduct(sortAttribute);
        return ProductMapper.toDTOList(repository.findAll(Sort.by(Sort.Direction.ASC, sortBy)));
    }

}
