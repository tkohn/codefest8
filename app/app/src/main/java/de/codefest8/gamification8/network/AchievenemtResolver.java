package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.User;

public class AchievenemtResolver extends AbstractResolver {
    private static final String URL_RELATIVE = "/achievements/";

    private User user;

    public AchievenemtResolver(ResponseCallback callback, User user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_RELATIVE+String.valueOf(user.getId());
    }
}
