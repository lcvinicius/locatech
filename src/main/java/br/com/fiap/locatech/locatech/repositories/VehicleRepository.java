package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.Vehicle;

public interface VehicleRepository {

    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll(int size, int offset);
    Integer save(Vehicle vehicle);
    Integer update(Vehicle vehicle, Long id);
    Integer delete(Long id);

}
