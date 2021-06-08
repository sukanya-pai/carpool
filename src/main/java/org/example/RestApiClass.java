package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


@Controller
public class RestApiClass {

    CarPool carPool = new CarPool();

    @PutMapping("/car/add")
    @ResponseBody
    public Car add(@RequestBody Car body) {
        return carPool.addCar(body);
    }

    @GetMapping("/car/get")
    @ResponseBody
    public Car get(@RequestParam(name = "id") String id) {
        Car car = carPool.getCar(id);
        return car;
    }

    @GetMapping("/car/get/color")
    @ResponseBody
    public String getRGBColor(@RequestParam(name = "id") String id) {
        String color = carPool.getCarColor(id);
        return color;
    }

    @GetMapping("/car/get/all")
    @ResponseBody
    public ArrayList getAll() {
        ArrayList cars = carPool.getCars();
        return cars;
    }

    @GetMapping("/car/delete")
    @ResponseBody
    public void delete(@RequestParam(name = "id") String id) {
        carPool.deleteCar(id);
    }
}
