package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.Track;
import de.codefest8.gamification8.models.User;

public class HistoryResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/history/";

    private User user;
    private Track[] history;

    public HistoryResolver(ResponseCallback callback, User user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL+String.valueOf(user.getId());
    }

    public Track[] getHistory() {
        return history;
    }
}
