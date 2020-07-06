package com.seanroshan.critter.repository.pet;

import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.repository.pet.CustomPetRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>, CustomPetRepository {

}
