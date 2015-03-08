package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

import de.codefest8.gamification8.UserMessagesHandler;

public class Fuel {
    private static final String LOG_TAG = "Fuel";

    private static final String FIELD_ID = "id";
    private static final String FIELD_FUEL_ECONOMY = "fuelEconomy";

    private long id;
    private double fuelEconomy;

    public long getId() { return id; }
    public double getFuelEconomy() { return fuelEconomy; }

    public static Fuel fromJSON(JSONObject object) {
        Fuel fuel = new Fuel();
        try {
            fuel.id = object.getLong(FIELD_ID);
            fuel.fuelEconomy = object.getDouble(FIELD_FUEL_ECONOMY);
        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing Fuel JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return fuel;
    }
}
