package Model;

/**
 * Created by USER on 10/31/2017.
 */

public class oneWayScheduleModel {
    private int bus_id;
    private String departure;
    private String arrival;
    private String date;
    private String time;
    private int cost;

    public oneWayScheduleModel(int bus_id, String departure, String arrival, String date, String time, int cost) {
        this.bus_id = bus_id;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public int getBus_id() {
        return bus_id;
    }
    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

