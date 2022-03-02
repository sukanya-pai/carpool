package org.example.service;

import org.example.Car;
import org.example.exception.CarAlreadyExistsException;
import org.example.exception.CarNotFoundException;
import org.example.mapper.ObjectToCarMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CarPoolService {

    ArrayList cars;

    //Autowiring dependency
    @Autowired
    private ObjectToCarMapper objectToCarMapper;

    public CarPoolService() {
        this.cars = loadList();
    }

    /**
     * Loading data from JSON file to POJO Object - too much hardcoding
     * If new parameters are added, we have to change the code
     * notes attribute was displayed incorrectly in getAll or get car API
     *
     * SOLUTION: Make use of org.json library to parse the list of objects
     * @return
     */
    public ArrayList<Car> loadList(){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<Car> carList =new ArrayList<>();
        try (FileReader reader = new FileReader("src/main/java/carpool.json"))
        {
            //Read JSON file
            Object carsObj = jsonParser.parse(reader);
            JSONArray carsArr = (JSONArray) carsObj;

            for (Object carObj: carsArr){
                HashMap<String,String> carMap = (HashMap<String, String>)carObj;
                carList.add(objectToCarMapper.convertToCarObject(carMap));
            }
        } catch ( IOException|ParseException  e) {
            e.printStackTrace();
        }
        return carList;
    }

    /**
     * Method to add new car to carpool.
     * First it checks if car with id is already present, if found, it throws CarAlreadyExistsException
     * Else, it adds to the car pool and returns the car object
     * @param car
     * @return car object after adding successfully
     * @throws CarAlreadyExistsException
     */
    public Car addCar(Car car) throws CarAlreadyExistsException {
        boolean carExists = checkIfCarExists(car.getId());
        if(carExists){
            throw new CarAlreadyExistsException("Car already exists");
        }
        cars.add(car);
        return car;
    }

    /**
     * Method to check if car with given id exists in the car pool
     * @param id
     * @return true if car found, else false
     */
    private boolean checkIfCarExists(String id){
        for (int i = 0; i < cars.size(); i++) {
            if (id.equals(((Car) cars.get(i)).getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get the car details for a given id
     * @param id
     * @return car details if car found
     * @throws CarNotFoundException if car not found
     */
    public Car getCar(String id) throws CarNotFoundException {
        Car car;
        for (int i = 0; i < cars.size(); i++) {
            if (id.equals(((Car) cars.get(i)).getId())) {
                car = (Car) cars.get(i);
                return car;
            }
        }
        throw new CarNotFoundException("Car not found");    // raise exception if car not found
    }


    /**
     * Method to get all cars of the carpool
     * @return all cars
     */
    public ArrayList getCars() {
        return cars;
    }

    /**
     * Method to delete a car with given ID from car pool
     * @param id
     * @return deleted car's id if car is deleted successfully
     * @throws CarNotFoundException if car id is not found
     */
    public String deleteCar(String id) throws CarNotFoundException {
        for (Object carObj : cars) {
            Car car = (Car) carObj;
            if (id.equals(car.id)) {
                cars.remove(car);
                return car.getId();
            }
        }
        throw new CarNotFoundException("Car not found");    // id not found
    }


    /**
     * Method to return the RGB representation of the color attribute in a car.
     * @param id
     * @return
     * @throws CarNotFoundException if car with given id is not found
     */
    public String getCarColor(String id) throws CarNotFoundException {
        Car car = getCar(id);
        if (car == null){
            throw new CarNotFoundException("Car not Found");
        }
        String color;
        switch (car.color) {
            case "red":
                color = "FF0000";
                break;
            case "black":
                color = "000000";
                break;
            case "green":
                color = "00FF00";
                break;
            case "blue":
                color = "0000FF";
                break;
            case "yellow":
                color = "FFFF00";
                 break;
            case "gray":
                color = "BEBEBE";
                break;
            default:
                color = "unknown color";
        }
        return color;
    }
}
