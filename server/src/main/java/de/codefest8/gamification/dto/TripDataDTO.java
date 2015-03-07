package de.codefest8.gamification.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;

/**
 * Created by torsten on 07/03/15.
 */
public class TripDataDTO {
    private long id;
    @JsonBackReference
    private TripDTO trip;
    private Timestamp datetime;
    private double GPSSpeedMS;
    private double GPSSpeedKMH;
    private double OBDSpeedKMH;
    private double altitude;
    private double engineLoad;
    private double engineRPM;
    private double throttlePosition;
    private double airTemperature;
    private double fuelLevel;
    private double KMPerLiter;

    public TripDataDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public double getGPSSpeedMS() {
        return GPSSpeedMS;
    }

    public void setGPSSpeedMS(double GPSSpeedMS) {
        this.GPSSpeedMS = GPSSpeedMS;
    }

    public double getGPSSpeedKMH() {
        return GPSSpeedKMH;
    }

    public void setGPSSpeedKMH(double GPSSpeedKMH) {
        this.GPSSpeedKMH = GPSSpeedKMH;
    }

    public double getOBDSpeedKMH() {
        return OBDSpeedKMH;
    }

    public void setOBDSpeedKMH(double OBDSpeedKMH) {
        this.OBDSpeedKMH = OBDSpeedKMH;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getEngineLoad() {
        return engineLoad;
    }

    public void setEngineLoad(double engineLoad) {
        this.engineLoad = engineLoad;
    }

    public double getEngineRPM() {
        return engineRPM;
    }

    public void setEngineRPM(double engineRPM) {
        this.engineRPM = engineRPM;
    }

    public double getThrottlePosition() {
        return throttlePosition;
    }

    public void setThrottlePosition(double throttlePosition) {
        this.throttlePosition = throttlePosition;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public double getKMPerLiter() {
        return KMPerLiter;
    }

    public void setKMPerLiter(double KMPerLiter) {
        this.KMPerLiter = KMPerLiter;
    }
}
