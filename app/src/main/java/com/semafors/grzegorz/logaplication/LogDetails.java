package com.semafors.grzegorz.logaplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.Time;

public class LogDetails extends AppCompatActivity {
    User user;
    ConnectionService connectionService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);
        connectionService = ConnectionService.getConnectionService();
        Intent intent = getIntent();
        Log log = (Log)intent.getSerializableExtra(Configuration.LOG);
        connectionService.getUserById(this, log.getUserId());
        TextView logTime = (TextView)findViewById(R.id.logTime);
        logTime.setText((new Time(log.getLoginTime())).toGMTString());
        setImages(log);
        setLoggedProperties(log);



    }

    public void setUser(User user){
        this.user = user;
        TextView userName = (TextView)findViewById(R.id.userName);
        userName.setText(user.getName());
        TextView userSurname = (TextView)findViewById(R.id.userSurname);
        userSurname.setText(user.getsName());

    }

    private void setLoggedProperties(Log log){
        TextView isLogged = (TextView) findViewById(R.id.isLogged);
        TextView loggedOut = (TextView) findViewById(R.id.logOutTime);
        TextView loggedOutText = (TextView) findViewById(R.id.logOutTimeText);
        if(!log.isPositiveVerification()){
            TextView isLoggedText = (TextView) findViewById(R.id.isLoggedText);
            isLogged.setVisibility(View.INVISIBLE);
            isLoggedText.setVisibility(View.INVISIBLE);
            loggedOut.setVisibility(View.INVISIBLE);
            loggedOutText.setVisibility(View.INVISIBLE);
            return;
        }
        if(log.isExpired()) {
            isLogged.setText("offline");
            isLogged.setTextColor(Color.RED);
            loggedOut.setText((new Time(log.getLogoutTime())).toGMTString());
        }
        else{
            isLogged.setText("online");
            isLogged.setTextColor(Color.GREEN);
            loggedOut.setVisibility(View.INVISIBLE);
            loggedOutText.setVisibility(View.INVISIBLE);

        }
    }

    public void setImages(Log log){
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Picasso.with(getApplicationContext()).load(Configuration.SERVER_URL + "/log/images/" + log.getId()).into(image);
        TextView goodPassword = (TextView)findViewById(R.id.goodPassword);
        if(log.isPositiveVerification()) {
            goodPassword.setText("POPRAWNE");
            goodPassword.setTextColor(Color.GREEN);
        }
        else{
            goodPassword.setText("BLEDNE");
            goodPassword.setTextColor(Color.RED);
        }
    }

    public void userLogs(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(Configuration.USER, user);
        startActivity(intent);
    }
}
