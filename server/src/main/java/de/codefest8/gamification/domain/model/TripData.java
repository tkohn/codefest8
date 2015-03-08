package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table(name= "TRIP_DATA")
@NamedQueries({
        })
public class TripData {

    public static final String GET_ROUTE_LENGTH = "getRouteLength";
    @TableGenerator(name = "TRIP_DATA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRIP_DATA_GEN")
    private long id;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    @JsonBackReference
    private Trip trip;

    private Timestamp datetime;
    @Column(name = "GPS_SPEED_MS")
    private double GPSSpeedMS;
    @Column(name = "GPS_SPEED_KMH")
    private double GPSSpeedKMH;
    @Column(name = "SPEED_OBD_KMH")
    private double OBDSpeedKMH;
    private double altitude;
    @Column(name = "ENGINE_LOAD")
    private double engineLoad;
    @Column(name = "ENGINE_RPM")
    private double engineRPM;
    @Column(name = "THROTTLE_POSITION")
    private double throttlePosition;
    @Column(name = "AIR_TEMPERATURE")
    private double airTemperature;
    @Column(name = "FUEL_LEVEL")
    private double fuelLevel;
    @Column(name = "KPL")
    private double KMPerLiter;

    public TripData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
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
