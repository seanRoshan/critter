package com.seanroshan.critter.repository.pet;

import com.seanroshan.critter.entity.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPetRepository {
    List<Pet> getCustomersPets(Long customerId);
}
