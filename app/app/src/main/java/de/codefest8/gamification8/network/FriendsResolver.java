package de.codefest8.gamification8.network;


import de.codefest8.gamification8.models.UserDTO;

public class FriendsResolver extends AbstractResolver {
    private static final String URL_RELATIVE = "/users/";
    private UserDTO user;
    /**
     * Get friends of the user.
     * @param callback
     * @param user
     */
    public FriendsResolver(ResponseCallback callback, UserDTO user) {
        super(callback);
        this.user = user;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_RELATIVE;
    }
}
