package com.android.example.lockscreen;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by bodhidipta on 02/06/17.
 */

public class ViewServiceHandler extends IntentService {
    public  static final String SHOW_VIEW="show_view";
    public  static final String NorMAL="start_up";
    public ViewServiceHandler() {
        super("");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("screen_control_**", "onHandleIntent");

    }


}
