package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.locatech.entities.Vehicle;

@Repository
public class VehicleRepositoryImp implements VehicleRepository{

    private final JdbcClient jdbcClient;

    // Injecting JdbcClient to use for database operations
    public VehicleRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
       return this.jdbcClient
                .sql("SELECT * FROM vehicle WHERE id = :id")
                .param("id", id)
                .query(Vehicle.class)
                .optional();
    }

    @Override
    public List<Vehicle> findAll(int size, int offSet) {
         return this.jdbcClient
                .sql("SELECT * FROM vehicle LIMIT :size OFFSET :offSet")
                .param("size", size)
                .param("offSet", offSet)
                .query(Vehicle.class)
                .list();         
    }

    @Override
    public Integer save(Vehicle vehicle) {
        return this.jdbcClient
                .sql("INSERT INTO vehicle (model, brand, yearManufacture, color, dailyValue) VALUES (:model, :brand, :yearManufacture, :color, :dailyValue)")
                .param("model", vehicle.getModel())
                .param("brand", vehicle.getBrand())
                .param("year", vehicle.getYearManufacture())
                .param("color", vehicle.getColor())
                .param("dailyValue", vehicle.getDailyValue())
                .update();
    }

    @Override
    public Integer update(Vehicle vehicle, Long id) {
        return this.jdbcClient
                .sql("UPDATE vehicle SET model = :model, brand = :brand, yearManufacture = :yearManufacture, color = :color, dailyValue = :dailyValue WHERE id = :id")
                .param("model", vehicle.getModel())
                .param("brand", vehicle.getBrand())
                .param("yearManufacture", vehicle.getYearManufacture())
                .param("color", vehicle.getColor())
                .param("dailyValue", vehicle.getDailyValue())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM vehicle WHERE id = :id")
                .param("id", id)
                .update();
    }
}	
