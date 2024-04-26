package Controller;

import com.example.demo.Car;
import com.example.demo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.*;


    @RestController
    @RequestMapping("/cars")

    public class carControler {
        @Autowired

        private Repository repository;

        @PostMapping

        public ResponseEntity<Car> createCar(@RequestBody Car car) {

            Car savedcar = repository.save(car);

            return ResponseEntity.ok(savedcar);
        }

        @GetMapping

        public ResponseEntity<List<Car>> getAllCars() {

            List<Car> cars = repository.findAll();
            return ResponseEntity.ok(cars);


        }

        @GetMapping("/{id}")

        public ResponseEntity<Car> getCarById(@PathVariable long id) {

            if (repository.existsById(id)) {

                return ResponseEntity.ok(repository.findById(id).get());

            } else {

                return ResponseEntity.ok(new Car());
            }


        }

        @PutMapping("/{id}")

        public ResponseEntity<Car> updateCarType(@PathVariable long id, @RequestParam Car car) {

            if (repository.existsById(id)) {

                Car car1 = repository.findById(id).get();
                car1.setType(car1.getType());
                repository.save(car1);
                return ResponseEntity.ok(car1);


            } else {

                return ResponseEntity.ok(new Car());
            }
        }

@DeleteMapping("/{id}")

        public ResponseEntity<Void>deleteCar(@PathVariable long id) {

    if (repository.existsById(id)) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    } else {

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}

@DeleteMapping

        public ResponseEntity<Void> deleteAllCars() {

            repository.deleteAll();
    return ResponseEntity.noContent().build();
        }
    }