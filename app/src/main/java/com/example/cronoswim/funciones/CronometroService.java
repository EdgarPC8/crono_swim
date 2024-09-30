package com.example.cronoswim.funciones;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.example.cronoswim.entidades.Cronometros;

import java.util.Locale;

public class CronometroService extends Service {

    public static final String ACTION_UPDATE_TIME = "com.example.cronoswim.ACTION_UPDATE_TIME";
    public static final String EXTRA_TIEMPO = "extra_tiempo";

    private Handler handler;
    private Runnable runnable;
    private long startTime, timeInMilliseconds = 0L;
    private int seconds, minutes, hours;
    private boolean isRunning;

    private Cronometros cronometro;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        isRunning = false;

        runnable = new Runnable() {
            public void run() {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
                updateTime();
                handler.postDelayed(this, 0);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UPDATE_TIME);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            cronometro = intent.getParcelableExtra("cronometro");
            if (cronometro != null) {
                startChronometer();
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopChronometer();
        unregisterReceiver(broadcastReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startChronometer() {
        if (!isRunning) {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            isRunning = true;
        }
    }

    private void stopChronometer() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            isRunning = false;
        }
    }

    private void updateTime() {
        seconds = (int) (timeInMilliseconds / 1000);
        minutes = seconds / 60;
        hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;

        // Formato del tiempo: HH:MM:SS
        String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);

        // Enviar actualización al Cronometro (Activity o Fragment)
        Intent intent = new Intent(ACTION_UPDATE_TIME);
        intent.putExtra(EXTRA_TIEMPO, time);
        sendBroadcast(intent);
    }

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                if (intent.getAction().equals(ACTION_UPDATE_TIME)) {
                    String action = intent.getAction();
                    if (action != null && action.equals(ACTION_UPDATE_TIME)) {
                        String time = intent.getStringExtra(EXTRA_TIEMPO);
                    }
                }
            }
        }
    };
}
