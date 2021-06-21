package com.airton.psjava;

import com.airton.psjava.dto.ProductQuantityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
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

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PsJavaApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopcartResourceTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void Delete_Shopcart_Resource() throws Exception {

        List<ProductQuantityDTO> products = Collections.singletonList(new ProductQuantityDTO(1L, 1));

        MvcResult result = mvc.perform(post("/shopcarts/")
                .content(new ObjectMapper().writeValueAsString(products))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mvc.perform(delete("/shopcarts/" + id).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void Insert_Shopcart_Resource() throws Exception {

        List<ProductQuantityDTO> products = Collections.singletonList(new ProductQuantityDTO(1L, 1));
        mvc.perform(post("/shopcarts/")
                .content(new ObjectMapper().writeValueAsString(products))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void List_Shopcarts_Resource() throws Exception {

        mvc.perform(get("/shopcarts?sortAttribute=SCORE").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].products", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$[0].products[0].id", is(2)))
                .andExpect(jsonPath("$[0].products[0].score", is(80)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void Find_By_Id_Shopcart_Resource() throws Exception {

        mvc.perform(get("/shopcarts/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.products", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$.products[0].score", is(200)));
    }

    @Test
    public void Checkout_Shopcart_Resource() throws Exception {

        mvc.perform(get("/shopcarts/checkout/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.shipping", is(0.0)))
                .andExpect(jsonPath("$.total", is(685.75)))
                .andExpect(jsonPath("$.subtotal", is(685.75)))
                .andExpect(jsonPath("$.productQuantity", is(7)))
                .andExpect(jsonPath("$.products", hasSize(greaterThanOrEqualTo(3))))
                .andExpect(jsonPath("$.products[0].score", is(200)));
    }

}
