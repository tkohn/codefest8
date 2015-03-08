package de.codefest8.gamification8.network;

import de.codefest8.gamification8.models.UserDTO;

public class UserResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/users/";
    private Long userId;
    private UserDTO user;

    public UserResolver(ResponseCallback callback, Long userId) {
        super(callback);
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL+String.valueOf(userId);
    }

    public UserDTO getUser() {
        return user;
    }
}
