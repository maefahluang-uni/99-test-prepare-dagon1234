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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcertController {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcertController.class);

    @Autowired
    private ConcertRepository concertRepository;

    @GetMapping("/concerts/{id}")
    public ResponseEntity<String> retrieveConcert(@PathVariable Long id) {

        Optional<Concert> optConcert = concertRepository.findById(id);

        if (!optConcert.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }
        optConcert.get();

        return ResponseEntity.status(HttpStatus.CONTINUE).body("Success Getting");

    }

    @GetMapping("/concerts")
    public ResponseEntity<List<Concert>> retrieveAllConcert() {

        concertRepository.findAll();
        return new ResponseEntity<>(HttpStatus.CONTINUE);

    }

    @PostMapping("/concerts")
    public ResponseEntity<String> createConcert(@RequestBody Concert concert) {

        concertRepository.save(concert);
        return ResponseEntity.status(HttpStatus.CREATED).body("COncert created");

    }

    @PutMapping("concerts/{id}")
    public ResponseEntity<String> updateConcert(@RequestBody Concert concert) {

        concertRepository.save(concert);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @DeleteMapping("/concert/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<Concert> optConcert = concertRepository.findById(id);

        if (!optConcert.isPresent()) {
            return ResponseEntity.status(HttpStatus.valueOf(404)).body("Not found this id");
        }
        concertRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.CONTINUE);

    }

    @DeleteMapping("/concerts")
    public ResponseEntity<String> deleteAllConcerts() {

        concertRepository.deleteAll();

        return ResponseEntity.status(HttpStatus.CONTINUE).body("Delete Success");
    }
}