package car.dao;

import car.model.Car;
import car.model.EngineVolume;
import car.model.ModelCar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.MysqlSessionFactory;

import static org.junit.Assert.assertEquals;


public class CarDaoTest{

    CarDao testObjects;

    @Before
    public void setUp() {
        testObjects = new CarDaoImpl(MysqlSessionFactory
                .getSessionFactory("hibernate-test.cfg.xml"));
    }

    @After
    public void tearDown() {
        testObjects = null;
    }

    @Test
    public void readAll() {
        //Given
        Car modelCar = ModelCar
                .builder()
                .marks("Mersedes")
                .color("White")
                .model("Sprinter")
                .year(1998)
                .build();
        Car engineVolume = EngineVolume
                .builder()
                .marks("Iveco")
                .color("White")
                .volume(2900)
                .vin("123d3334JHG332")
                .build();
        Car car = Car
                .builder()
                .marks("VolksWagen")
                .color("White")
                .build();

        int initialSize = testObjects.readAll().size();
        testObjects.create(modelCar);
        testObjects.create(engineVolume);
        testObjects.create(car);

        //When
        int actualSize = testObjects.readAll().size();

        //Then
        assertEquals(initialSize + 3, actualSize);
    }
}