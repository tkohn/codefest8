package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.models.UserDTO;

public class HistoryResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/history/";

    private UserDTO user;
    private TripDTO[] history;

    public HistoryResolver(ResponseCallback callback, UserDTO user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL+String.valueOf(user.getId());
    }

    public TripDTO[] getHistory() {
        return history;
    }
}
