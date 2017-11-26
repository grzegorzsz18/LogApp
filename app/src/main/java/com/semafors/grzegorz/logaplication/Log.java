package com.semafors.grzegorz.logaplication;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by grzegorz on 23.11.17.
 */

public class Log implements Serializable{
    private long id;
    private long userId;
    private long loginTime;
    private boolean positiveVerification;
    private boolean expired;
    private long logoutTime;

    public Log() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isPositiveVerification() {
        return positiveVerification;
    }

    public void setPositiveVerification(boolean positiveVerification) {
        this.positiveVerification = positiveVerification;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(long logoutTime) {
        this.logoutTime = logoutTime;
    }

    @Override
    public String toString() {
        return (new Time(loginTime)).toGMTString() + ((isPositiveVerification()) ? " good" : " bad");
    }
}
