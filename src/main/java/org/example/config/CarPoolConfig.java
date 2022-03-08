package org.example.config;

import org.example.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CarPoolConfig{

    @Bean
    public ArrayList<Car> getCars(){
        return new ArrayList<>();
    }
}