package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;

public class TripPointsResolver extends AbstractResolver {
    private final static String URL_USER = "/users/";
    private final static String URL_TRIPS = "/trips/";
    private final static String URL_DATA = "/data/";

    long tripId;
    long userId;

    public TripPointsResolver(ResponseCallback callback, long tripId, long userId) {
        super(callback);
        this.tripId = tripId;
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_USER + String.valueOf(userId) + URL_TRIPS + String.valueOf(tripId) + URL_DATA;
    }
}
