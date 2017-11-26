package com.semafors.grzegorz.logaplication;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by grzegorz on 23.11.17.
 */



public interface RetrofitService {

    @GET("log/all")
    Call<List<Log>> getAllLogs();

    @GET("log/byLogId/{logId}")
    Call<Log> getLogDetails(@Path("logId") long logId);

    @GET("user/byId/{userId}")
    Call<User> getUserById(@Path("userId") long userId);

    @GET("log/byUser/{userId}")
    Call<List<Log>> getLogsByUser(@Path("userId") long userId);

    @GET("user/allActive")
    Call<List<User>> getAllActiveUsers();
}
