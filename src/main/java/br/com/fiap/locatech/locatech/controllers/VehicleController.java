package br.com.fiap.locatech.locatech.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.locatech.services.VehicleService;
import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicle", description = "Endpoints for vehicle management")
public class VehicleController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAllVehicles(  @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
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

    @PostMapping
    public ResponseEntity<Void> saveVehicle(@RequestBody Vehicle vehicle) {
        logger.info(" POST -> /vehicle", vehicle);
        this.vehicleService.saveVehicle(vehicle);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@RequestBody Vehicle vehicle, @PathVariable Long id) {
        logger.info(" PUT -> /vehicle/" + id, vehicle);
        this.vehicleService.updateVehicle(vehicle, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        logger.info(" DELETE -> /vehicle/" + id);
        this.vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }

}
