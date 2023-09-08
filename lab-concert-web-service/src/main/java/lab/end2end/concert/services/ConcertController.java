package lab.end2end.concert.services;

import java.util.List;
import java.util.Optional;

import lab.end2end.concert.domain.Concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcertController {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcertController.class);

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping("/concerts/{id}")
    public ResponseEntity<Concert> retrieveConcert(@PathVariable Long id) {
        Optional<Concert> concert = concertRepository.findById(id);
        if (concert.isPresent()) {
            return new ResponseEntity<>(concert.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Concert>> retrieveAllConcerts() {
        List<Concert> concerts = concertRepository.findAll();
        return new ResponseEntity<>(concerts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Concert> createConcert(@RequestBody Concert concert) {
        Concert createdConcert = concertRepository.save(concert);
        return new ResponseEntity<>(createdConcert, HttpStatus.CREATED);
    }

    @PutMapping("/concerts/{id}")
    public ResponseEntity<Concert> updateConcert(@PathVariable Long id, @RequestBody Concert concert) {
        if (!concertRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concert.setId(id);
        Concert updatedConcert = concertRepository.save(concert);
        return new ResponseEntity<>(updatedConcert, HttpStatus.OK);
    }

    @DeleteMapping("/concerts/{id}")
    public ResponseEntity<Void> deleteConcert(@PathVariable Long id) {
        if (!concertRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concertRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllConcerts() {
        concertRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
