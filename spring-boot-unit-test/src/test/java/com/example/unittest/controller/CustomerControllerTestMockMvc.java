package com.example.unittest.controller;

import com.example.unittest.dto.CustomerDto;
import com.example.unittest.model.Customer;
import com.example.unittest.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTestMockMvc {
    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void create_shouldCreateSuccessfully() throws Exception{
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(this.customerService.create(Mockito.any()))
         .thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(log())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("identificationNumber").value(customer.getIdentificationNumber()));
    }

    @Test
    void create_shouldReturnStatusBadRequest() throws Exception{
        // given
        CustomerDto request = new CustomerDto();
        request.setIdentificationNumber("12345678900");
        Mockito.when(this.customerService.create(Mockito.any()))
                .thenReturn(Optional.empty());

        // when
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest()); // then
    }

    @Test
    void deleteById_shouldReturnStatusNotFound_whenCustomerIdNotExist() throws Exception{
        // given
        Mockito.doThrow(EntityNotFoundException.class).
                when(this.customerService).deleteById(anyLong());

        // when
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
