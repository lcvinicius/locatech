package br.com.fiap.locatech.locatech.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.com.fiap.locatech.locatech.entities.People;

@Repository
public class PeopleRepositoryImp implements PeopleRepository{

        private final JdbcClient jdbcClient;

    // Injecting JdbcClient to use for database operations
    public PeopleRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<People> findById(Long id) {
       return this.jdbcClient
                .sql("SELECT * FROM people WHERE id = :id")
                .param("id", id)
                .query(People.class)
                .optional();
    }

    @Override
    public List<People> findAll(int size, int offset) {
         return this.jdbcClient
                .sql("SELECT * FROM people LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(People.class)
                .list();         
    }

    @Override
    public Integer save(People people) {
        return this.jdbcClient
                .sql("INSERT INTO people (name, cpf, email, phone) VALUES (:name, :cpf, :email, :phone)")
                .param("name", people.getName())
                .param("cpf", people.getCpf())
                .param("email", people.getEmail())
                .param("phone", people.getPhone())
                .update();
    }

    @Override
    public Integer update(People people, Long id) {
        return this.jdbcClient
                .sql("UPDATE people SET name = :name, cpf = :cpf, email = :email, phone = :phone WHERE id = :id")
                .param("name", people.getName())
                .param("cpf", people.getCpf())  
                .param("email", people.getEmail())
                .param("phone", people.getPhone())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM people WHERE id = :id")
                .param("id", id)
                .update();
    }


}
