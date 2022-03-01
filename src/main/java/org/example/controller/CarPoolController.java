package org.example.controller;

import org.example.Car;
import org.example.exception.CarAlreadyExistsException;
import org.example.exception.CarNotFoundException;
import org.example.service.CarPoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
@RequestMapping("carpool")
public class CarPoolController {

    CarPoolService carPoolService = new CarPoolService();

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
