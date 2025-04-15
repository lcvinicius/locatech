package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.Rent;

public interface RentRepository {

    Optional<Rent> findById(Long id);
    List<Rent> findAll(int size, int offset);
    Integer save(Rent people);
    Integer update(Rent people, Long id);
    Integer delete(Long id);


}
