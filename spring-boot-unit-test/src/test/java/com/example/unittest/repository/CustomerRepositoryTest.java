package com.example.unittest.repository;

import com.example.unittest.dto.CustomerDto;
import com.example.unittest.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void givenNewCustomer_whenSave_thenSuccess(){
        // given
        Customer expected = new Customer();
        expected.setIdentificationNumber("25252567348");
        expected.setPhoneNumber("5554443322");

        // when
        Customer actual = customerRepository.save(expected);

        // then
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, entityManager.find(Customer.class, actual.getId())),
                () -> assertEquals(expected.getIdentificationNumber(), actual.getIdentificationNumber()),
                () -> assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber())
        );
    }

    @Test
    void givenCustomerCreated_whenUpdate_thenSuccess(){
        // given
        Customer initialCustomer = new Customer();
        initialCustomer.setFirstName("FirstName");
        initialCustomer.setLastName("LastName");
        initialCustomer.setIdentificationNumber("25252567348");
        initialCustomer.setPhoneNumber("1234567890");

        entityManager.persist(initialCustomer);
        log.info("initialCustomer: {}", initialCustomer);

        // when
        initialCustomer.setPhoneNumber("0987654321");
        Customer updateCustomer = customerRepository.save(initialCustomer);
        log.info("updateCustomer: {}", updateCustomer);

        // then
        assertAll(
                () -> assertNotNull(updateCustomer),
                () -> assertEquals(initialCustomer.getPhoneNumber(), entityManager.find(Customer.class, initialCustomer.getId()).getPhoneNumber())
        );
    }

    @Test
    void givenCustomerCreated_whenFindById_thenSuccess() {
        // given
        Customer customer = new Customer();
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");
        customer.setIdentificationNumber("25252567348");
        customer.setPhoneNumber("1234567890");

        entityManager.persist(customer);
        log.info("customer: {}", customer);

        // when
        Optional<Customer> retrievedCustomer = customerRepository.findById(customer.getId());

        // then
        assertThat(retrievedCustomer).contains(customer);
    }

    @Test
    void givenCustomerCreated_whenFindByIdentificationNumber_thenSuccess() {
        // given
        Customer customer = new Customer();
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");
        customer.setIdentificationNumber("25252567348");
        customer.setPhoneNumber("1234567890");

        entityManager.persist(customer);
        log.info("customer: {}", customer);

        // when
        Optional<Customer> retrievedCustomer = customerRepository.findByIdentificationNumber(customer.getIdentificationNumber());

        // then
        assertThat(retrievedCustomer).contains(customer);
    }

    @Test
    void givenCustomersCreated_whenFindAll_thenReturnCustomerList(){
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("25252567348");
        customer.setPhoneNumber("5554443322");
        entityManager.persist(customer);

        Customer customer1 = new Customer();
        customer1.setIdentificationNumber("44555666777");
        customer1.setPhoneNumber("1234567890");
        entityManager.persist(customer1);

        // when
        List<Customer> customerList = customerRepository.findAll();

        // then
        assertAll(
                () -> assertNotNull(customerList),
                () -> assertThat(customerList).contains(customer, customer1)
        );
    }

    @Test
    void givenCustomerCreated_whenDelete_thenSuccess() {
        // given
        Customer customer = new Customer();
        customer.setIdentificationNumber("25252567348");
        customer.setPhoneNumber("5554443322");
        entityManager.persist(customer);

        customerRepository.delete(customer);

        assertThat(entityManager.find(Customer.class, customer.getId())).isNull();
    }
}
