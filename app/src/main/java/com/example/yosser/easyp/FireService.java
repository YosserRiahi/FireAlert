package com.example.yosser.easyp;

import android.app.Application;


import com.firebase.client.Firebase;

public class FireService extends Application {
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
