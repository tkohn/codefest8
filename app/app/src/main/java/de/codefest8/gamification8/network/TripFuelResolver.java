package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;

public class TripFuelResolver extends AbstractResolver {
    private final static String URL_USERS = "/users/";
    private final static String URL_TRIPS = "/trips/";
    private final static String URL_FUEL = "/fuel/";

    private TripDTO trip;

    public TripFuelResolver(ResponseCallback callback, TripDTO trip) {
        super(callback);
        this.trip = trip;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_USERS+trip.getUserId()+URL_TRIPS+trip.getId()+URL_FUEL;
    }
}
