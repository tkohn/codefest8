package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.UserDTO;

public class AchievementsResolver extends AbstractResolver {
    private static final String URL_USER = "/users/";
    private static final String URL_ACHIEVEMENTS = "/achievements/";

    private long userId;

    public AchievementsResolver(ResponseCallback callback, long userId) {
        super(callback);
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_USER + String.valueOf(userId) + URL_ACHIEVEMENTS;
    }
}
