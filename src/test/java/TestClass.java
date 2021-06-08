import org.example.Car;
import org.example.CarPool;
import org.junit.Assert;
import org.junit.Test;


public class TestClass {


    @Test
    public void test1() {
        CarPool carPool = new CarPool();
        Car newCar = new Car();
        newCar.id = "1";
        carPool.addCar(newCar);

        Car car = carPool.getCar("1");
        Assert.assertNotNull(car);
    }

    @Test
    public void test2() {
        CarPool carPool = new CarPool();

        String color = carPool.getCarColor("1");
        Assert.assertNotNull(color);
    }
}
