package de.codefest8.gamification8.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


public class AixHTTPClient {
    private static final String LOG_TAG = "AixHTTPClient";
    private final static String SERVER_URL = "http://137.226.183.140:8080/aix-cruise/api/v1";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        String url = SERVER_URL + relativeUrl;
        Log.i(LOG_TAG, url);
        return url;
    }
}
