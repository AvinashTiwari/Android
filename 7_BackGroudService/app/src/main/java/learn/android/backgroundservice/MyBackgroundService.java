package learn.android.backgroundservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyBackgroundService extends Service {
    private static final String TAG = MyBackgroundService.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate, Thread name : " + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand , Thread name : " + Thread.currentThread().getName());

        new MyAsyncTask().execute();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy , Thread name : " + Thread.currentThread().getName());
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind, Thread name : " + Thread.currentThread().getName());
        return null;
    }


    class MyAsyncTask extends AsyncTask<Void, String, Void>{

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute, Thread name : " + Thread.currentThread().getName());
            super.onPreExecute();
        }

        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG, "doInBackground, Thread name : " + Thread.currentThread().getName());

            int ctr = 1;
            while(ctr <=12){
                onProgressUpdate("Time elapsed " + ctr + " sec");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctr++;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate, Counter value "+values[0] +" Thread name : " + Thread.currentThread().getName());

            //Toast.makeText(MyBackgroundService.this, values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.i(TAG, "onPostExecute, Thread name : " + Thread.currentThread().getName());
            super.onPostExecute(aVoid);
            stopSelf();
        }
    }
}
