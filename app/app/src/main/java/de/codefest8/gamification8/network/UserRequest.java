package de.codefest8.gamification8.network;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import de.codefest8.gamification8.UserMessagesHandler;

public class UserRequest {
    private static final String URL_GENERAL = "/users/";

    public void requestGeneralInfo(int userId) {
        String url = URL_GENERAL+String.valueOf(userId);
        AixHTTPClient.get(URL_GENERAL+String.valueOf(userId), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                UserMessagesHandler.getInstance().registerMessage(response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                UserMessagesHandler.getInstance().registerError("Network error without detailed message. Response code: "+String.valueOf(statusCode));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    UserMessagesHandler.getInstance().registerError(errorResponse.toString());
                } else {
                    UserMessagesHandler.getInstance().registerError("Network error without detailed message. Response code: "+String.valueOf(statusCode));
                }
            }
        });
    }
}
