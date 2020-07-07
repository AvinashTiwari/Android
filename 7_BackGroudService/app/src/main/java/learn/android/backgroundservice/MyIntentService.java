package learn.android.backgroundservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = IntentService.class.getName();
    public MyIntentService() {
        super("MyBackgroundThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "OnCreate , ThreadName " + Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent , ThreadName " + Thread.currentThread().getName());
        int duration = intent.getIntExtra("sleepTime", -1);
        int ctr = 1;
        while(ctr <=duration){
            Log.i(TAG, "Time elapsed " + ctr + " sec");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
        }

        Intent localIntent = new Intent("my.own.broadcast");
        localIntent.putExtra("result", ctr);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
/*
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy , ThreadName " + Thread.currentThread().getName());

    }*/
}
