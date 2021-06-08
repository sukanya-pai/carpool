package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CarPool {

    ArrayList cars;


    public CarPool() {
        this.cars = loadList();
    }


    public ArrayList loadList() {
        ArrayList list = new ArrayList();
        try {
            FileInputStream stream = new FileInputStream("src/main/java/carpool.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Pattern p = Pattern.compile("\\{\"id\": (\\d+), \"color\": \"(\\w+)\", \"type\": \"(.+)\", \"yearOfConstruction\": (\\d+)"
                        + ", \"price\": (\\d+)(, \"notes\": \"(.+)\")?\\}");
                Matcher matcher = p.matcher(line);

                if (matcher.find()) {
                    Car car = new Car();
                    car.id = matcher.group(1);
                    car.color = matcher.group(2);
                    car.type = matcher.group(3);
                    car.yearOfConstruction = matcher.group(4);
                    car.price = Integer.parseInt(matcher.group(5));
                    car.notes = matcher.group(6);

                    list.add(car);
                }
            }
        } catch (Exception e) {
        }
        return list;
    }


    public Car addCar(Car car) {
        cars.add(car);
        return car;
    }


    public Car getCar(String id) {
        Car car = null;
        for (int i = 0; i < cars.size(); i++) {
            if (id == ((Car) cars.get(i)).id) {
                car = (Car) cars.get(i);
            }
        }
        return car;
    }


    public ArrayList getCars() {
        return cars;
    }


    public void deleteCar(String id) {
        for (Object carObj : cars) {
            Car car = (Car) carObj;
            if (id.equals(car.id)) {
                cars.remove(car);
            }
        }
    }


    public String getCarColor(String id) {
        Car car = getCar(id);

        String color = null;
        switch (car.color) {
            case "red":
                color = "FF0000";
            case "black":
                color = "000000";
            case "green":
                color = "00FF00";
            case "blue":
                color = "0000FF";
            case "yellow":
                color = "FFFF00";
            case "gray":
                color = "BEBEBE";
            default:
                color = "unknown color";
        }
        return color;
    }
}
