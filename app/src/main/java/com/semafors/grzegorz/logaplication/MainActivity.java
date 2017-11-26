package com.semafors.grzegorz.logaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Log> allLogs;
    ConnectionService connectionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionService = ConnectionService.getConnectionService();
        Intent intent = getIntent();
        if(intent.getSerializableExtra(Configuration.USER) == null){
            refreshLogs();
        }
        else{
            User user = (User)intent.getSerializableExtra(Configuration.USER);
            getLogsByUser(user.getId());
        }
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLogs();
                SwipeRefreshLayout refresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
                refresh.setRefreshing(false);
            }
        });
    }

    public void setAllLogs(List<Log> logs){
        allLogs = logs;
        ListView futureReservation = (ListView)findViewById(R.id.mainActivityLogList);
        ArrayAdapter<Log> adapter = new ArrayAdapter<Log>(this,android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.addAll(logs);
        futureReservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),LogDetails.class);
                intent.putExtra(Configuration.LOG,allLogs.get(position));
                startActivity(intent);
            }
        });
        futureReservation.setAdapter(adapter);
    }

    private void refreshLogs(){
        connectionService.getAllLogs(this);
    }

    private void getLogsByUser(long id){
        connectionService.getLogsByUser(this, id);
    }

}
