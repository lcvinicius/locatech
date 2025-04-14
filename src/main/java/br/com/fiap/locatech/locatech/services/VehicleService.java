package br.com.fiap.locatech.locatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.entities.Vehicle;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllVehicles(int page, int size) {
        int offset = page  * size;
        return this.vehicleRepository.findAll(size, offset);

    }

    public  Optional<Vehicle> findVehicleById(Long id) {
        return this.vehicleRepository.findById(id);
    }

    public void saveVehicle(Vehicle vehicle) {
        var save = this.vehicleRepository.save(vehicle);
        Assert.state(save == 1, "Error saving vehicle"
        + " - " + vehicle.getModel());
    }

    public void updateVehicle(Vehicle vehicle, Long id) {
        var update = this.vehicleRepository.update(vehicle, id);
        Assert.state(update == 1, "Error updating vehicle"
        + " - " + vehicle.getModel());
    }
    
    public void deleteVehicle(Long id) {
        var delete = this.vehicleRepository.delete(id);
        Assert.state(delete == 1, "Error deleting vehicle"
        + " - " + id);

    }

}
