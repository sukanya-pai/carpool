package org.example.config;

import org.example.Car;
import org.example.mapper.ObjectToCarMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ObjectToCarMapper objectToCarMapper;

    @Autowired
    ArrayList<Car> cars;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        cars.addAll(loadList());
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
        } catch ( IOException | ParseException e) {
            e.printStackTrace();
        }
        return carList;
    }
}