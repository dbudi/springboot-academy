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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTestRestTemplate {
    @MockBean
    CustomerService customerService;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void create_shouldCreateSuccessfully() throws Exception{
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        Optional<Customer> expected = Optional.of(customer);

        Mockito.when(this.customerService.create(Mockito.any()))
         .thenReturn(expected);

        // when
        CustomerDto request = new CustomerDto();
        ResponseEntity<Customer> response = restTemplate.postForEntity("/api/v1/customers", request, Customer.class );
        Customer actual = response.getBody();

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(customer.getIdentificationNumber(), actual.getIdentificationNumber())
        );
    }

    @Test
    void create_shouldReturnStatusBadRequest() throws Exception{
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("12345678900");
        Mockito.when(this.customerService.create(Mockito.any()))
                .thenReturn(Optional.empty());

        // when
        CustomerDto request = new CustomerDto();
        ResponseEntity<Customer> response = restTemplate.postForEntity("/api/v1/customers", request, Customer.class );
        Customer actual = response.getBody();

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteById_shouldDeleteSuccessfully(){
        // given
        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/customers/{id}", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, 1L);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_shouldReturnStatusNotFound_whenCustomerIdNotExist() throws Exception{
        // given
        Mockito.doThrow(EntityNotFoundException.class).
                when(this.customerService).deleteById(anyLong());

        // when
        ResponseEntity<Void> response = restTemplate.exchange("/api/v1/customers/{id}", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class, 1L);
        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}
