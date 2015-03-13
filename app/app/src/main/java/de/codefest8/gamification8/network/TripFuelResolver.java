package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;

public class TripFuelResolver extends AbstractResolver {
    private final static String URL_USERS = "/users/";
    private final static String URL_TRIPS = "/trips/";
    private final static String URL_FUEL = "/fuel/";

    private long tripId;
    private long userId;

    public TripFuelResolver(ResponseCallback callback, long tripId, long userId) {
        super(callback);
        this.tripId = tripId;
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_USERS + String.valueOf(userId) + URL_TRIPS + String.valueOf(tripId) + URL_FUEL;
    }
}
