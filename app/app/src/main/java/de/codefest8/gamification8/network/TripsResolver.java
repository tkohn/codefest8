package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.models.UserDTO;

public class TripsResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/users/";
    private static final String URL_TRIPS = "/trips/";

    private long userId;
    private TripDTO[] history;

    public TripsResolver(ResponseCallback callback, long userId) {
        super(callback);
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL + String.valueOf(userId) + URL_TRIPS;
    }

    public TripDTO[] getHistory() {
        return history;
    }
}
