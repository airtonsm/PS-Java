package com.airton.psjava.resource;

import com.airton.psjava.dto.ProductQuantityDTO;
import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.service.ShopcartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/shopcarts")
public class ShopcartResource {

    @Autowired
    private ShopcartService service;

    @GetMapping
    public ResponseEntity<List<ShopcartDTO>> findAll(@RequestParam(required = false) String sortAttribute) {
        List<ShopcartDTO> list = service.findAll(StringUtils.upperCase(sortAttribute));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShopcartDTO> findById(@PathVariable Long id, @RequestParam(required = false) String sortAttribute) {
        ShopcartDTO obj = service.findById(id, StringUtils.upperCase(sortAttribute));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/checkout/{id}")
    public ResponseEntity<ShopcartDTO> checkout(@PathVariable Long id) {
        ShopcartDTO obj = service.checkout(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ShopcartDTO> insert(@RequestBody List<ProductQuantityDTO> products) {
        ShopcartDTO newShopcart = service.insert(products);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newShopcart.getId()).toUri();
        return ResponseEntity.created(uri).body(newShopcart);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "add-product/{id}")
    public ResponseEntity<ShopcartDTO> addProduct(@PathVariable Long id, @RequestBody List<ProductQuantityDTO> products) {
        ShopcartDTO editedShopcart = service.addProduct(id, products);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(products).toUri();
        return ResponseEntity.created(uri).body(editedShopcart);
    }

    @DeleteMapping(value = "remove-product/{id}")
    public ResponseEntity<ShopcartDTO> removeProduct(@PathVariable Long id, @RequestBody List<ProductQuantityDTO> products) {
        ShopcartDTO editedShopcart = service.removeProduct(id, products);
        return ResponseEntity.ok().body(editedShopcart);
    }

}
