package com.semafors.grzegorz.logaplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class StartActivity extends AppCompatActivity{

    ConnectionService connectionService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        connectionService = ConnectionService.getConnectionService();
        connectionService.getActiveUsers(this);

    }

    public void setActiveUsers(List<User> users){
        ListView activeUsers = (ListView)findViewById(R.id.activeUsersList);
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.addAll(users);
        activeUsers.setAdapter(adapter);
        TextView numberOfActiveUsers = (TextView) findViewById(R.id.activeUsers);
        numberOfActiveUsers.setText(users.size() + "");
    }

    public void showLogs(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
