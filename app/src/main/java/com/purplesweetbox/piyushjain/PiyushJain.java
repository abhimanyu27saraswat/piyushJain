package com.purplesweetbox.piyushjain;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by flip on 11/03/18.
 */

public class PiyushJain extends Application {
    @Override
    public void onCreate() {
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
