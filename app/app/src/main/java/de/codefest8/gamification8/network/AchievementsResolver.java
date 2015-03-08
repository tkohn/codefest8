package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.UserDTO;

public class AchievementsResolver extends AbstractResolver {
    private static final String URL_RELATIVE = "/achievements/";

    private UserDTO user;

    public AchievementsResolver(ResponseCallback callback, UserDTO user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_RELATIVE+String.valueOf(user.getId());
    }
}
