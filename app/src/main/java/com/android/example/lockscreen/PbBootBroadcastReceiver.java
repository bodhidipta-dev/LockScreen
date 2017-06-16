package com.android.example.lockscreen;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.icu.util.Calendar;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;


/**
 * Created by apple on 30/01/17.
 */

public class PbBootBroadcastReceiver extends BroadcastReceiver {
    Context mCon = null;
    WindowManager mWindowManager;
    LinearLayout neLinearLayout;
    View mView;

    @Override
    public void onReceive(Context context, Intent intent) {
        mCon = context;
        Log.i("screen_control", "on recieve");

        if (intent != null) {
            Log.i("screen_control", "Screen " + intent.getAction());
            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                // DO WHATEVER YOU NEED TO DO HERE
                Log.i("screen_control", "ACTION_USER_PRESENT");

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                // AND DO WHATEVER YOU NEED TO DO HERE
                Log.i("screen_control", "ACTION_USER_UNLOCKED");

//                Intent i = new Intent();
//                i.setClassName("com.android.example.lockscreen", "com.android.example.lockscreen.FullscreenActivity");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
                showView();

            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                try {
                    mWindowManager.removeView(neLinearLayout);
                    mWindowManager.removeView(mView);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Intent service = new Intent(context, ScreenObservationSeervice.class);
                context.startService(service);

            }
        }

    }


    void showView() {
        mWindowManager = (WindowManager) mCon.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater layoutInflater = (LayoutInflater) mCon.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mView = layoutInflater.inflate(R.layout.activity_fullscreen, null);

        neLinearLayout  =new LinearLayout(mCon);
        neLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        neLinearLayout.setBackgroundColor(Color.parseColor("#0099cc"));

        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 0,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
    /* | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON */,
                PixelFormat.RGBA_8888);

        mWindowManager.addView(neLinearLayout, mLayoutParams);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;


        TextView fullscreen_content = (TextView) mView.findViewById(R.id.fullscreen_content);
        TextView unlock = (TextView) mView.findViewById(R.id.unlock);
        TextView system_time = (TextView) mView.findViewById(R.id.system_time);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        String DateUtil = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(calendar.getTimeInMillis());
        system_time.setText(DateUtil);

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mWindowManager.removeView(mView);
                    mWindowManager.removeView(neLinearLayout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fullscreen_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("screen_control", "********* TOUCHEDDDDD ***********");

            }
        });

        mWindowManager.addView(mView, params);


    }

}