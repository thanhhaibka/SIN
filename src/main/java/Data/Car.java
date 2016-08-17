package Data;

import java.util.Set;

/**
 * Created by prnc on 17/08/2016.
 */
public class Car {
    private Set<CarInTime> cars;
    private String carID;

    public Car(Set<CarInTime> cars, String carID) {
        this.cars = cars;
        this.carID = carID;
    }

    public Set<CarInTime> getCars() {
        return cars;
    }

    public void setCars(Set<CarInTime> cars) {
        this.cars = cars;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }
}
