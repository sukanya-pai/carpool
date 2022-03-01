package org.example.mapper;

import org.example.Car;

import java.util.HashMap;

public class ObjectToCarMapper {

    public Car convertToCarObject(HashMap carMap){
        Car car = new Car();
        car.setId(String.valueOf(carMap.get("id")));
        car.setType((String) carMap.get("type"));
        car.setColor((String) carMap.get("color"));
        car.setPrice(Integer.valueOf(String.valueOf(carMap.get("price"))));
        car.setYearOfConstruction(String.valueOf(carMap.get("yearOfConstruction")));
        car.setNotes((String) carMap.getOrDefault("notes",null));

        return car;
    }
}
