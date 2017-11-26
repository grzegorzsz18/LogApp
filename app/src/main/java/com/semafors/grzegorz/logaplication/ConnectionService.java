package com.semafors.grzegorz.logaplication;

import android.app.Activity;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by grzegorz on 23.11.17.
 */

public class ConnectionService {

    private static RetrofitService retrofitService;
    private static ConnectionService connectionService;
    private static User user;

    private ConnectionService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configuration.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static ConnectionService getConnectionService() {
        if(connectionService == null){
            connectionService = new ConnectionService();
        }
        return connectionService;
    }

    public void getAllLogs(final MainActivity activity){
            final Call<List<Log>> call = retrofitService.getAllLogs();
            call.enqueue(new retrofit2.Callback<List<Log>>() {
                @Override
                public void onResponse(Call<List<Log>> call, retrofit2.Response<List<Log>> response) {
                    if(response != null){
                        activity.setAllLogs(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Log>> call, Throwable t) {

                }
            });
    }

    public void getUserById(final LogDetails logDetails, long userId){
        final Call<User> call = retrofitService.getUserById(userId);
        call.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response != null){
                    logDetails.setUser(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getLogsByUser(final MainActivity activity, long userId){
        final Call<List<Log>> call = retrofitService.getLogsByUser(userId);
        call.enqueue(new retrofit2.Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, retrofit2.Response<List<Log>> response) {
                if(response != null){
                    activity.setAllLogs(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {

            }
        });
    }

    public void getActiveUsers(final StartActivity startActivity){
        final Call<List<User>> call = retrofitService.getAllActiveUsers();
        call.enqueue(new retrofit2.Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if(response != null){
                    startActivity.setActiveUsers(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}
