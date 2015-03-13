package de.codefest8.gamification8.network;


import de.codefest8.gamification8.models.UserDTO;

public class FriendsResolver extends AbstractResolver {
    private static final String URL_RELATIVE = "/users/";

    private long userId;

    /**
     * Get friends of the user.
     * @param callback
     * @param userId
     */
    public FriendsResolver(ResponseCallback callback, long userId) {
        super(callback);
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_RELATIVE;
    }
}
