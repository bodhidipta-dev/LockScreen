package com.android.example.lockscreen;

import android.app.IntentService;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by bodhidipta on 02/06/17.
 */

public class ScreenObservationSeervice extends Service {

    private boolean isAlreadyShown=false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("screen_control","Service started");
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        BroadcastReceiver mReceiver = new PbBootBroadcastReceiver();
        registerReceiver(mReceiver, filter);
        isAlreadyShown=true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        // REGISTER RECEIVER THAT HANDLES SCREEN ON AND SCREEN OFF LOGIC

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
