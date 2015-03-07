package de.codefest8.gamification8.network;

import org.json.JSONArray;
import org.json.JSONObject;

import de.codefest8.gamification8.UserMessagesHandler;

public class DummyResponseCallback implements ResponseCallback {
    @Override
    public void success(JSONObject response) {
        UserMessagesHandler.getInstance().registerMessage(response.toString());
    }

    @Override
    public void successArray(JSONArray response) {
        UserMessagesHandler.getInstance().registerMessage("Got array of "+response.length()+" objects");
    }

    @Override
    public void fail(int code, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Network error ");
        sb.append(code);
        sb.append(" ");
        sb.append(message);
        UserMessagesHandler.getInstance().registerError(sb.toString());
    }
}
