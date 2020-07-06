package com.seanroshan.critter.repository;

import com.seanroshan.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> getAllByCustomerId(Long customerId);

}
