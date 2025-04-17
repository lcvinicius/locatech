package br.com.fiap.locatech.locatech.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.services.RentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rents")
public class RentController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(RentController.class);
     
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<Rent>> findAllRents(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        logger.info("Finding all rents with page: {} and size: {}", page, size);
        List<Rent> rents = rentService.findAllRents(page, size);
        return ResponseEntity.ok(rents);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Optional<Rent>> findrentById(@PathVariable("id") Long id){
        logger.info("/rents/" + id);
        var rent = this.rentService.findRentById(id);
        return ResponseEntity.ok(rent);
    }

    @PostMapping
    public ResponseEntity<String> saveRent(@Valid @RequestBody RentRequestDTO rent) {
        logger.info(" POST -> /rents", rent);
        this.rentService.saveRent(rent);
        return ResponseEntity.status(201).body("Rent created successfully");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Rent> updateRent(@RequestBody Rent rent, @PathVariable Long id){
        logger.info(" PUT -> /rents/" + id, rent);
        this.rentService.updateRent(rent, id);
        return ResponseEntity.ok(rent);   

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updateRent(@PathVariable Long id) {
        logger.info(" DELETE -> /rents/" + id);
        this.rentService.deleteRent(id);
        return ResponseEntity.ok("Rent deleted successfully");
    }
        
}




