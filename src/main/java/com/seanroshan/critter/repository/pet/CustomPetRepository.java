package com.seanroshan.critter.repository.pet;

import com.seanroshan.critter.entity.Pet;

import java.util.List;

public interface CustomPetRepository {
    List<Pet> getCustomersPets(Long customerId);
}
