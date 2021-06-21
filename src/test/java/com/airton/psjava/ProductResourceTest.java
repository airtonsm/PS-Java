package com.airton.psjava;

import com.airton.psjava.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PsJavaApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
class ProductResourceTest {

    private final static String BASE_URL = "/products/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void Delete_Product_Resource() throws Exception {

        ProductDTO product = getExampleProduct();

        MvcResult result = mvc.perform(post(BASE_URL)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mvc.perform(delete(BASE_URL + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void Insert_Product_Resource() throws Exception {

        ProductDTO product = getExampleProduct();
        mvc.perform(post(BASE_URL)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void List_Product_Resource() throws Exception {

        mvc.perform(get(BASE_URL + "?sortAttribute=SCORE").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(6))))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].score", is(80)));
    }

    @Test
    public void Find_By_Id_Product_Resource() throws Exception {

        mvc.perform(get(BASE_URL + 1).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.score", is(200)));
    }

    @Test
    public void Edit_Product_Resource() throws Exception {

        ProductDTO product = getExampleProduct();

        MvcResult result = mvc.perform(post(BASE_URL)
                .content(new ObjectMapper().writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        ProductDTO editedProduct = getExampleProduct();
        editedProduct.setScore(Short.parseShort("200"));

        mvc.perform(put(BASE_URL + id)
                .content(new ObjectMapper().writeValueAsString(editedProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(200)));
    }

    private ProductDTO getExampleProduct() {
        return new ProductDTO(null, "PES2021", BigDecimal.valueOf(199.00), Short.parseShort("170"), "pes2021.png");
    }
}
