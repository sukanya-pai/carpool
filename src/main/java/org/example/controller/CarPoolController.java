package org.example.controller;

import org.example.Car;
import org.example.exception.CarAlreadyExistsException;
import org.example.exception.CarNotFoundException;
import org.example.service.CarPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequestMapping("carpool")
public class CarPoolController {

    // Removed tight coupling and autowired the dependencies
    @Autowired
    CarPoolService carPoolService;

    /**
     * API to add new car to the car pool
     * @param car
     * @return Car added object and status as OK if car is added successfully
     * If car with id exists, return the error message with status  code 500
     */
    @PostMapping("/car/add")
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody Car car) {
        try{
            Car carAdded = carPoolService.addCar(car);
            return new ResponseEntity<Car>(carAdded,HttpStatus.OK);
        }catch(CarAlreadyExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to get car of given ID
     * @param id
     * @return If car is found for given ID, return the car object
     * Else return car not found message
     */
    @GetMapping("/car/get")
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(name = "id") String id) {
        try{
            Car car = carPoolService.getCar(id);
            return new ResponseEntity<Car>(car,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to return color in RGB format of a given car
     * @param id
     * @return If car with id is found, return the RGB equivalent color, else return car not found
     * If color does not have RGB equivalent value, unknown color is returned
     */
    @GetMapping("/car/get/color")
    @ResponseBody
    public ResponseEntity getRGBColor(@RequestParam(name = "id") String id) {
        try{
            String color = carPoolService.getCarColor(id);
            return new ResponseEntity<>(color,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to return list of all cars available in carpool
     * @return
     */
    @GetMapping("/car/get/all")
    @ResponseBody
    public ResponseEntity<?> getAll() {
        try{
            ArrayList cars = carPoolService.getCars();
            return new ResponseEntity<>(cars,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API to delete car from a car pool
     * @param id
     * @return Return success message if deleted successfully, else return error message.
     */
    @DeleteMapping("/car/delete")
    @ResponseBody
    public ResponseEntity<?> delete(@RequestParam(name = "id") String id) {
        try{
            String idDeleted = carPoolService.deleteCar(id);
            return new ResponseEntity<>("Car "+idDeleted+" deleted successfully",HttpStatus.OK);
        }catch(CarNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
