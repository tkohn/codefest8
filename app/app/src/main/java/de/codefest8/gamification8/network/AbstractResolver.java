package de.codefest8.gamification8.network;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

abstract public class AbstractResolver {
    abstract protected String getRelativeRequestUrl();
    ResponseCallback callback;

    public AbstractResolver(ResponseCallback callback) {
        this.callback = callback;
    }

    /**
     * Ask server for the data.
     */
    public void doRequest() {
        AixHTTPClient.get(getRelativeRequestUrl(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                callback.success(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.fail(statusCode, "no message");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    callback.fail(statusCode, errorResponse.toString());
                } else {
                    callback.fail(statusCode, "no message");
                }
            }
        });
    }
}
