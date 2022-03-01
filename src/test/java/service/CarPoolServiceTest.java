package service;

import org.example.Car;
import org.example.CarPool;
import org.example.exception.CarAlreadyExistsException;
import org.example.service.CarPoolService;
import org.junit.Assert;
import org.junit.Test;

public class CarPoolServiceTest {

    /**
     * Test case to test if addition of new car is success
     */
    @Test
    public void addNewCarSuccessTest() throws CarAlreadyExistsException {
        CarPoolService carPool = new CarPoolService();
        Car newCar = new Car();
        newCar.setId("10");
        newCar.setColor("silver");
        newCar.setType("Mercedes");
        newCar.setYearOfConstruction("2020");
        newCar.setNotes("Refurbished");

        Car car = carPool.addCar(newCar);
        Assert.assertEquals(newCar.getId(),car.getId());
    }

    /**
     * Test case to test if addition of new car with existing id is failed
     * @throws CarAlreadyExistsException
     */
    @Test(expected = CarAlreadyExistsException.class)
    public void addNewCarFailureTest() throws CarAlreadyExistsException {
        CarPoolService carPool = new CarPoolService();
        Car newCar = new Car();
        newCar.setId("10");
        newCar.setColor("silver");
        newCar.setType("Mercedes");
        newCar.setYearOfConstruction("2020");
        newCar.setNotes("Refurbished");

        for(int i=0;i<2;i++){   //adding this to simulate two calls.
            Car addCarAgain = carPool.addCar(newCar);
            System.out.println(addCarAgain);
        }

    }
}
