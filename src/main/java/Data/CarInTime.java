package Data;

import app.Sensor;

/**
 * Created by prnc on 17/08/2016.
 */
public class CarInTime extends Sensor{
    private Long time;

    public CarInTime(Long time) {
        this.time = time;
    }

    public CarInTime(double x, double y, double r, Long time) {
        super(x, y, r);
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
