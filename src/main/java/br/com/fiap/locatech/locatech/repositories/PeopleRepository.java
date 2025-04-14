package br.com.fiap.locatech.locatech.repositories;
import java.util.List;
import java.util.Optional;

import br.com.fiap.locatech.locatech.entities.People;

public interface PeopleRepository {

    Optional<People> findById(Long id);
    List<People> findAll(int size, int offset);
    Integer save(People people);
    Integer update(People people, Long id);
    Integer delete(Long id);

}





