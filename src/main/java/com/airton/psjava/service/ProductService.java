package com.airton.psjava.service;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.entities.Product;
import com.airton.psjava.exception.DataBaseException;
import com.airton.psjava.exception.ResourcesNotFoundException;
import com.airton.psjava.mapper.ProductMapper;
import com.airton.psjava.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(String sortAttribute) {
        String sortBy = ProductDTO.sortProduct(sortAttribute);
        return ProductMapper.toDTOList(repository.findAll(Sort.by(Sort.Direction.ASC, sortBy)));
    }

    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException(id));
        return ProductMapper.toDTO(product);
    }

    public ProductDTO insert(ProductDTO obj) {
        Product newProduct = ProductMapper.toEntity(obj);
        return ProductMapper.toDTO(repository.save(newProduct));
    }


    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourcesNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public ProductDTO update(Long id, ProductDTO newProduct) {
        try {
            Product entity = repository.getById(id);
            ProductMapper.merge(entity, newProduct);

            return ProductMapper.toDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourcesNotFoundException(id);
        }
    }

}
