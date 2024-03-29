package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.models.UserDTO;

public class TripsResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/users/";
    private static final String URL_TRIPS = "/trips/";

    private UserDTO user;
    private TripDTO[] history;

    public TripsResolver(ResponseCallback callback, UserDTO user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL+String.valueOf(user.getId())+URL_TRIPS;
    }

    public TripDTO[] getHistory() {
        return history;
    }
}
