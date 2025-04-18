package br.com.fiap.locatech.locatech.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.locatech.locatech.entities.People;
import br.com.fiap.locatech.locatech.services.PeopleService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/peoples")
@Tag(name = "People", description = "Endpoints for people management")
public class PeopleController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PeopleController.class);
     
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<List<People>> findAllPeoples(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        logger.info("Finding all peoples with page: {} and size: {}", page, size);
        List<People> peoples = peopleService.findAllPeoples(page, size);
        return ResponseEntity.ok(peoples);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Optional<People>> findPeopleById(@PathVariable("id") Long id){
        logger.info("/peoples/" + id);
        var people = this.peopleService.findPeopleById(id);
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<String> savePeople(@RequestBody People people) {
        logger.info(" POST -> /peoples", people);
        this.peopleService.savePeople(people);
        return ResponseEntity.status(201).body("People created successfully");

    }

    @PutMapping("/{id}")
    public ResponseEntity<People> updatePeople(@RequestBody People people, @PathVariable Long id){
        logger.info(" PUT -> /peoples/" + id, people);
        this.peopleService.updatePeople(people, id);
        return ResponseEntity.ok(people);   

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> updatePeople(@PathVariable Long id) {
        logger.info(" DELETE -> /peoples/" + id);
        this.peopleService.deletePeople(id);
        return ResponseEntity.ok("People deleted successfully");
    }
        
}
