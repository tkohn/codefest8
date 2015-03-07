package de.codefest8.gamification8;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UserMessagesHandler {
    private static final String LOG_TAG = "UserMessages";

    private static UserMessagesHandler instance;
    private Context context;

    private UserMessagesHandler() {

    }

    public static UserMessagesHandler getInstance() {
        if (instance == null) {
            instance = new UserMessagesHandler();
        }

        return instance;
    }

    public void setApplicationContext(Context appContext) {
        context = appContext;
    }

    public void registerMessage(String message) {
        if (context == null) {
            throw new RuntimeException("UserMessagesHandler not initialized.");
        }
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void registerError(String error) {
        if (context == null) {
            throw new RuntimeException("UserMessagesHandler not initialized.");
        }
        Toast toast = Toast.makeText(context, error, Toast.LENGTH_LONG);
        toast.show();
    }
}
