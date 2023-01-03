package car.dao;

import car.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarDao {
    void create(Car car);
    List<String> readIdCar();
    List<Car> readAll();
}
