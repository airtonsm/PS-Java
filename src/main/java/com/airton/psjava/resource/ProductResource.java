package com.airton.psjava.resource;

import com.airton.psjava.dto.ProductDTO;
import com.airton.psjava.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(@RequestParam(required = false) String sortAttribute) {
        List<ProductDTO> list = service.findAll(StringUtils.upperCase(sortAttribute));
        return ResponseEntity.ok().body(list);
    }

}
