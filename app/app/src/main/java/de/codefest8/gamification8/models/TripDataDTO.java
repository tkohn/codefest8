package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import de.codefest8.gamification8.UserMessagesHandler;

public class TripDataDTO {
    private static final String LOG_TAG = "TripDataDTO";

    private static final String FIELD_ID = "id";
    private static final String FIELD_TRIP = "trip";
    private static final String FIELD_DATETIME = "datetime";
    private static final String FIELD_GPS_SPEED_MS = "GPSSpeedMS";
    private static final String FIELD_GPS_SPEED_KMH = "GPSSpeedKMH";
    private static final String FIELD_OBD_SPEED_KMH = "OBDSpeedKMH";
    private static final String FIELD_ALTITUDE = "altitude";
    private static final String FIELD_ENGINE_LOAD = "engineLoad";
    private static final String FIELD_ENGINE_RPM = "engineRPM";
    private static final String FIELD_THROTTLE_POSITION = "throttlePosition";
    private static final String FIELD_AIR_TEMP = "airTemperature";
    private static final String FIELD_FUEL_LEVEL = "fuelLevel";
    private static final String FIELD_KM_PER_LITER = "KMPerLiter";

    private long id;
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

    public static TripDataDTO fromJson(JSONObject object, TripDTO trip) {
        TripDataDTO tripData = new TripDataDTO();
        try {
            tripData.setId(object.getLong(FIELD_ID));
            tripData.setGPSSpeedMS(object.getDouble(FIELD_GPS_SPEED_MS));
            tripData.setGPSSpeedKMH(object.getDouble(FIELD_GPS_SPEED_KMH));
            tripData.setOBDSpeedKMH(object.getDouble(FIELD_OBD_SPEED_KMH));
            tripData.setAltitude(object.getDouble(FIELD_ALTITUDE));
            tripData.setEngineLoad(object.getDouble(FIELD_ENGINE_LOAD));
            tripData.setEngineRPM(object.getDouble(FIELD_ENGINE_RPM));
            tripData.setThrottlePosition(object.getDouble(FIELD_THROTTLE_POSITION));
            tripData.setAirTemperature(object.getDouble(FIELD_AIR_TEMP));
            tripData.setFuelLevel(object.getDouble(FIELD_FUEL_LEVEL));
            tripData.setKMPerLiter(object.getDouble(FIELD_KM_PER_LITER));
            tripData.setDatetime(new Timestamp(object.getLong(FIELD_DATETIME)));
            tripData.setTrip(trip);

        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing UserDTO JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return tripData;
    }
}
