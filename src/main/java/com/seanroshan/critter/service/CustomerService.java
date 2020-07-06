package com.seanroshan.critter.service;

import com.seanroshan.critter.entity.Customer;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.repository.CustomerRepository;
import com.seanroshan.critter.repository.pet.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public List<Customer> getList() {
        return customerRepository.findAll();
    }

    public Customer getByPetId(long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public Customer save(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<Pet>();
        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds.stream().map(petRepository::getOne).collect(Collectors.toList());
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

}
