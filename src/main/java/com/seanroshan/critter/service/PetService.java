package com.seanroshan.critter.service;

import com.seanroshan.critter.entity.Customer;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.repository.CustomerRepository;
import com.seanroshan.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public List<Pet> getList() {
        return petRepository.findAll();
    }

    public List<Pet> getListByCustomerId(long customerId) {
        return petRepository.getAllByCustomerId(customerId);
    }

    public Pet getById(long petId) {
        return petRepository.getOne(petId);
    }


    public Pet saveByCustomerId(Pet pet, long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        pet.setCustomer(customer);
        Pet insertedPet = petRepository.save(pet);
        customer.addPet(insertedPet);
        customerRepository.save(customer);
        return insertedPet;
    }

}
