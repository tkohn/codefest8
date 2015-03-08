package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.UserDTO;

public class AchievementsResolver extends AbstractResolver {
    private static final String URL_USER = "/users/";
    private static final String URL_ACHIEVEMENTS = "/achievements/";

    private UserDTO user;

    public AchievementsResolver(ResponseCallback callback, UserDTO user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_USER+String.valueOf(user.getId())+URL_ACHIEVEMENTS;
    }
}
