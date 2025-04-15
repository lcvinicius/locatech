package br.com.fiap.locatech.locatech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.fiap.locatech.locatech.entities.Rent;
import br.com.fiap.locatech.locatech.repositories.RentRepository;


@Service
public class RentService {
    private final RentRepository rentRepository;

    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public List<Rent> findAllRents(int page, int size) {
        int offset = page  * size;
        return this.rentRepository.findAll(size, offset);

    }

    public  Optional<Rent> findRentById(Long id) {
        return this.rentRepository.findById(id);
    }

    public void saveRent(Rent rent) {
        var save = this.rentRepository.save(rent);
        Assert.state(save == 1, "Error saving rent"
        + " - " + rent.getPeopleName());
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

}





