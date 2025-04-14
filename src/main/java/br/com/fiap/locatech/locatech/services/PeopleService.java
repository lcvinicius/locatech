package br.com.fiap.locatech.locatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.entities.People;
import br.com.fiap.locatech.locatech.repositories.PeopleRepository;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> findAllPeoples(int page, int size) {
        int offset = page  * size;
        return this.peopleRepository.findAll(size, offset);

    }

    public  Optional<People> findPeopleById(Long id) {
        return this.peopleRepository.findById(id);
    }

    public void savePeople(People people) {
        var save = this.peopleRepository.save(people);
        Assert.state(save == 1, "Error saving people"
        + " - " + people.getName());
    }

    public void updatePeople(People people, Long id) {
        var update = this.peopleRepository.update(people, id);
        Assert.state(update == 1, "Error updating people"
        + " - " + people.getName());
    }
    
    public void deletePeople(Long id) {
        var delete = this.peopleRepository.delete(id);
        Assert.state(delete == 1, "Error deleting people"
        + " - " + id);

    }

}


