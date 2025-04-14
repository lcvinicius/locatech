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
    public List<Vehicle> findAll(int size, int offset) {
         return this.jdbcClient
                .sql("SELECT * FROM vehicle LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Vehicle.class)
                .list();         
    }

    @Override
    public Integer save(Vehicle vehicle) {
        return this.jdbcClient
                .sql("INSERT INTO vehicle (model, brand, plate, year_manufacture, color, daily_value) VALUES (:model, :brand, :plate, :year_manufacture, :color, :daily_value)")
                .param("model", vehicle.getModel())
                .param("brand", vehicle.getBrand())
                .param("plate", vehicle.getPlate())
                .param("year_manufacture", vehicle.getYear_manufacture())
                .param("color", vehicle.getColor())
                .param("daily_value", vehicle.getDaily_value())
                .update();
    }

    @Override
    public Integer update(Vehicle vehicle, Long id) {
        return this.jdbcClient
                .sql("UPDATE vehicle SET model = :model, brand = :brand, plate = :plate, year_manufacture = :year_manufacture, color = :color, daily_value = :daily_value WHERE id = :id")
                .param("model", vehicle.getModel())
                .param("brand", vehicle.getBrand())
                .param("plate", vehicle.getPlate())
                .param("year_manufacture", vehicle.getYear_manufacture())
                .param("color", vehicle.getColor())
                .param("daily_value", vehicle.getDaily_value())
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
