package de.codefest8.gamification.dto;

/**
 * Created by torsten on 08/03/15.
 */
public class TripDataFuelDTO {

    private long id;
    private double fuelEconomy;

    public TripDataFuelDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getFuelEconomy() {
        return fuelEconomy;
    }

    public void setFuelEconomy(double fuelEconomy) {
        this.fuelEconomy = fuelEconomy;
    }
}
