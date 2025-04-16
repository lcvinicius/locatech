package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.locatech.entities.Rent;

@Repository
public class RentRepositoryImp implements RentRepository{

      private final JdbcClient jdbcClient;

    // Injecting JdbcClient to use for database operations
    public RentRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Rent> findById(Long id) {
       return this.jdbcClient
                .sql("SELECT r.id, r.people_id, r.vehicle_id, r.start_date, r.end_date, r.total_value, p.name AS people_name, p.cpf AS people_cpf, p.email AS people_email, p.phone AS people_phone, v.model AS vehicle_model, v.plate AS vehicle_plate FROM rent r INNER JOIN people p ON r.people_id = p.id INNER JOIN vehicle v ON r.vehicle_id = v.id WHERE r.id = :id")
                .param("id", id)
                .query(Rent.class)
                .optional();
    }

    @Override
    public List<Rent> findAll(int size, int offset) {
         return this.jdbcClient
                .sql("SELECT r.id, r.people_id, r.vehicle_id, r.start_date, r.end_date, r.total_value, p.name AS people_name, p.cpf AS people_cpf, p.email AS people_email, p.phone AS people_phone, v.model AS vehicle_model, v.plate AS vehicle_plate FROM rent r INNER JOIN people p ON r.people_id = p.id INNER JOIN vehicle v ON r.vehicle_id = v.id LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Rent.class)
                .list();         
    }

    @Override
    public Integer save(Rent rent) {
        return this.jdbcClient
                .sql("INSERT INTO rent (people_id, vehicle_id, start_date, end_date, total_value) VALUES (:people_id, :vehicle_id, :start_date, :end_date, :total_value)")
                .param("people_id", rent.getPeopleId())
                .param("vehicle_id", rent.getVehicleId())
                .param("start_date", rent.getStartDate())
                .param("end_date", rent.getEndDate())
                .param("total_value", rent.getTotalValue())
                .update();
    }

    @Override
    public Integer update(Rent rent, Long id) {
        return this.jdbcClient
                .sql("UPDATE rent SET people_id = :people_id, vehicle_id = :vehicle_id, start_date = :start_date, end_date = :end_date, total_value = :total_value WHERE id = :id")
                .param("id", id)
                .param("people_id", rent.getPeopleId())
                .param("vehicle_id", rent.getVehicleId())
                .param("start_date", rent.getStartDate())
                .param("end_date", rent.getEndDate())
                .param("total_value", rent.getTotalValue())
                .update();

    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM rent WHERE id = :id")
                .param("id", id)
                .update();
    }

}
