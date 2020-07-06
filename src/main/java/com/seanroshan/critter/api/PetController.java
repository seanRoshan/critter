package com.seanroshan.critter.api;

import com.seanroshan.critter.dto.PetDTO;
import com.seanroshan.critter.entity.Pet;
import com.seanroshan.critter.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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


    // NOTE: This is not the best implementation, the better implementation could be wrapping PetDTO in ResponseEntity
    // So we can set status for the response based on the input, right now I had to stick with run-time exception

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet insertedPet = petService.savePet(Pet.getInstance(petDTO), petDTO.getOwnerId());
        return PetDTO.getInstance(insertedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Optional<Pet> optionalPet = petService.getPet(petId);
        return optionalPet.map(PetDTO::getInstance).orElse(null);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.getPets();
        return pets.stream().map(PetDTO::getInstance).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
        return pets.stream().map(PetDTO::getInstance).collect(Collectors.toList());
    }
}
