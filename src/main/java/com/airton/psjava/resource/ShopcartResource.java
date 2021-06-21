package com.airton.psjava.resource;

import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.service.ShopcartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
