package de.codefest8.gamification8.network;

import org.json.JSONObject;

public interface ResponseCallback {
    public void success(JSONObject response);
    public void fail(int code, String message);
}
