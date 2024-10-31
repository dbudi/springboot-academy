package com.example.unittest.service;

import com.example.unittest.dto.CustomerDto;
import com.example.unittest.exception.IdentificationNumberNotValidException;
import com.example.unittest.model.Customer;
import com.example.unittest.repository.CustomerRepository;
import com.example.unittest.service.validator.IdentificationNumberValidator;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final IdentificationNumberValidator identificationNumberValidator;

    @Transactional
    public Optional<Customer> create(CustomerDto request){

        // Is the identification number valid ?
        boolean isValidIdentificationNumber = identificationNumberValidator.
                test(request.getIdentificationNumber());

        if (!isValidIdentificationNumber) {
            throw new IdentificationNumberNotValidException("Identification number not valid.");
        }

        Optional<Customer> customerOptional = customerRepository
                .findByIdentificationNumber(request.getIdentificationNumber());

        if(customerOptional.isPresent()){
            throw new EntityExistsException("This customer already exist!");
        }

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(request.getFirstName());
        newCustomer.setLastName(request.getLastName());
        newCustomer.setPhoneNumber(request.getPhoneNumber());
        newCustomer.setIdentificationNumber(request.getIdentificationNumber());

        return Optional.of(customerRepository.save(newCustomer));
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> findAll(){
        final List<CustomerDto> customerDtoArrayList = new ArrayList<CustomerDto>();
        customerRepository.findAll()
                .forEach(customer -> {

                    CustomerDto customerDto = new CustomerDto();
                    if(customer != null){
                        customerDto.setFirstName(customer.getFirstName());
                        customerDto.setLastName(customer.getLastName());
                        customerDto.setIdentificationNumber(customer.getIdentificationNumber());
                        customerDto.setPhoneNumber(customer.getPhoneNumber());
                    }

                    customerDtoArrayList.add(customerDto);

                });

        return customerDtoArrayList;
    }

    @Transactional(readOnly = true)
    public CustomerDto findById(long id){
        final Customer customer =  customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        CustomerDto customerDto = new CustomerDto();
        if(customer != null){
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setIdentificationNumber(customer.getIdentificationNumber());
            customerDto.setPhoneNumber(customer.getPhoneNumber());
        }

        return customerDto;
    }

    @Transactional
    public void deleteById(long id){
        final Customer customer =  customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        customerRepository.deleteById(id);
    }
}
