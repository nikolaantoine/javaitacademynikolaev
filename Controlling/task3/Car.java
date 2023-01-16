package Controlling.task3;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private String name;
    private CarModel model;

    private int engineCapacity;

    public Car() {
    }

    public Car(String name, CarModel model, int engineCapacity) {
        this.name = name;
        this.model = model;
        this.engineCapacity = engineCapacity;
    }

    enum CarModel{
        AUTO,
        TRUCK,
        BUS
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return engineCapacity == car.engineCapacity && Objects.equals(name, car.name) && model == car.model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, engineCapacity);
    }

    public String getName() {
        return name;
    }

    public CarModel getModel() {
        return model;
    }


    public int getEngineCapacity() {
        return engineCapacity;
    }
}