package com.fury.myaccounts;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by fury on 9/14/2017.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //SETUP CONFIG
        Realm.init(this);

    }
}
