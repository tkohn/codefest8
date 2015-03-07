package de.codefest8.gamification8.network;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.models.User;

public class UserResolver extends AbstractResolver {
    private static final String URL_GENERAL = "/users/";
    private Integer userId;
    private User user;

    public UserResolver(ResponseCallback callback, Integer userId) {
        super(callback);
        this.userId = userId;
    }

    @Override
    protected String getRelativeRequestUrl() {
        return URL_GENERAL+String.valueOf(userId);
    }

    public User getUser() {
        return user;
    }
}
