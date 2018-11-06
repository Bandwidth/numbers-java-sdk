package com.bandwidth.sdk.numbers;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Realm;


public class NumbersClient {

    private static String BASE_URL = "https://dashboard.bandwidth.com/api";

    private final String account;
    private final AsyncHttpClient httpClient;

    /**
     * Credentials to access Bandwidth's Numbers api
     *
     * @param account Your account id to use on dashboard.bandwidth.com
     * @param username Your username to login to dashboard.bandwidth.com
     * @param password Your password to login to dashboard.bandwidth.com
     */
    public NumbersClient(String account, String username, String password) {
        this.account = account;

        AsyncHttpClientConfig httpClientConfig = new DefaultAsyncHttpClientConfig.Builder()
                .setRealm(new Realm.Builder(username, password)
                        .setUsePreemptiveAuth(true)
                        .setScheme(Realm.AuthScheme.BASIC))
                .build();

        httpClient = asyncHttpClient(httpClientConfig);
    }

}
