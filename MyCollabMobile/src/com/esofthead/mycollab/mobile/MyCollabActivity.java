package com.esofthead.mycollab.mobile;

import org.apache.cordova.DroidGap;

import android.os.Bundle;

public class MyCollabActivity extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.loadUrl("http://192.168.0.103:8080/mycollab-web");
    }
}
