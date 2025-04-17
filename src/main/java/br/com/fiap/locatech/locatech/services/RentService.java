package br.com.fiap.locatech.locatech.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.dtos.RentRequestDTO;
import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.repositories.RentRepository;
import br.com.fiap.locatech.locatech.repositories.VehicleRepository;
import br.com.fiap.locatech.locatech.services.services.exceptions.ResourceNotFoundException;
import jakarta.annotation.Resource;


@Service
public class RentService {
    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;

    public RentService(RentRepository rentRepository, VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.rentRepository = rentRepository;
    }

    public List<Rent> findAllRents(int page, int size) {
        int offset = page  * size;
        return this.rentRepository.findAll(size, offset);

    }

    public  Optional<Rent> findRentById(Long id) {
        return Optional.ofNullable(this.rentRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Rent not found"));
    }

    public void saveRent(RentRequestDTO rent) {
        var rentEntity = this.calculateRentTotal(rent);
        var save = this.rentRepository.save(rentEntity);
        Assert.state(save == 1, "Error saving rent"
        + " - " + rent.peopleId());
    }

    public void updateRent(Rent rent, Long id) {
        var update = this.rentRepository.update(rent, id);
        Assert.state(update == 1, "Error updating rent"
        + " - " + rent.getPeopleName());
    }
    
    public void deleteRent(Long id) {
        var delete = this.rentRepository.delete(id);
        Assert.state(delete == 1, "Error deleting rent"
        + " - " + id);

    }

    private Rent calculateRentTotal(RentRequestDTO rentRequestDTO) {
        var vehicle = this.vehicleRepository.findById(rentRequestDTO.vehicleId())
        .orElseThrow(()-> new IllegalArgumentException("Vehicle not found"));
        var days = BigDecimal.valueOf(rentRequestDTO.endDate().getDayOfYear() - rentRequestDTO.startDate().getDayOfYear());
        var totalValue = vehicle.getDaily_value().multiply(days);
        return new Rent(rentRequestDTO, totalValue);              
    } 

}





