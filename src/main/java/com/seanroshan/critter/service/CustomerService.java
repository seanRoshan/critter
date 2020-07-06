package com.seanroshan.critter.service;

import com.seanroshan.critter.entity.Customer;
import com.seanroshan.critter.repository.customer.CustomerRepository;
import com.seanroshan.critter.repository.pet.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getByPetId(long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public List<Customer> getList() {
        return customerRepository.findAll();
    }




}
