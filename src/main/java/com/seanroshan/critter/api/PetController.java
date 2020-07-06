package com.seanroshan.critter.api;

import com.seanroshan.critter.dto.PetDTO;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return PetDTO.getInstance(petService.saveByCustomerId(Pet.getInstance(petDTO), petDTO.getOwnerId()));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetDTO.getInstance((petService.getById(petId)));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.getList();
        return pets.stream().map(PetDTO::getInstance).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getListByCustomerId(ownerId);
        return pets.stream().map(PetDTO::getInstance).collect(Collectors.toList());
    }
}
