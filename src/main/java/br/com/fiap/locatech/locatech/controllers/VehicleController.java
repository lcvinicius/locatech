package br.com.fiap.locatech.locatech.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.locatech.services.VehicleService;
import ch.qos.logback.classic.Logger;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.Vehicle;



@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    @GetMapping("")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAllVehicles( @RequestParam int page, @RequestParam int size){
        logger.info("Finding all vehicles with page: {} and size: {}", page, size);
        List<Vehicle> vehicles = vehicleService.findAllVehicles(page, size);
        return ResponseEntity.ok(vehicles);
        
    }
    @GetMapping("{id}")
    public ResponseEntity <Optional<Vehicle>> findVehicleById (@PathVariable ("id") Long id){
        logger.info("/vehicles/" + id);
        var vehicle = this.vehicleService.findVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }
    
}
