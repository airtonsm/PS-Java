package com.airton.psjava.service;

import com.airton.psjava.dto.ShopcartDTO;
import com.airton.psjava.mapper.ShopcartMapper;
import com.airton.psjava.repository.ShopcartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartService {

    @Autowired
    private ShopcartRepository shopcartRepository;

    public List<ShopcartDTO> findAll(String sortAttribute) {
        return ShopcartMapper.toDTOList(shopcartRepository.findAll(), sortAttribute);
    }

}
