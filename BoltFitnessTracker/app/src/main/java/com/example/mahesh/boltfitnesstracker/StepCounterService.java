package com.example.mahesh.boltfitnesstracker;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;

public class StepCounterService extends Service{
//        implements SensorEventListener,
//        StepListener {

    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private static  Long startTime;
    private static  Long stopTime;
    public static final String BROADCAST_ACTION = "com.example.mahesh.boltfitnesstracker.HomeScreen";
    private final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
    Intent intent;

    public StepCounterService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        simpleStepDetector = new SimpleStepDetector();
//        simpleStepDetector.registerListener(this);

        Long pauseTime = intent.getLongExtra("pause_time",(long)0);

        startTime = SystemClock.elapsedRealtime() - pauseTime;

        handler.removeCallbacks(sendUpdates);
        handler.postDelayed(sendUpdates, 1000);

        //stopSelf();

        return START_STICKY;


    }

    private Runnable sendUpdates = new Runnable() {
        @Override
        public void run() {
            displayInfo();
            handler.postDelayed(this, 1000);
        }
    };


    private void displayInfo(){

        Long timeElapsed = (SystemClock.elapsedRealtime() - startTime);
//        String counter =  String.format("%02d:%02d:%02d",
//                TimeUnit.MILLISECONDS.toHours(timeElapsed),
//                TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
//                TimeUnit.MILLISECONDS.toSeconds(timeElapsed)
//                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed))
//        );

        int calories = (int)(timeElapsed * 0.0003);
        intent.putExtra("elapsed_time", timeElapsed);
        intent.putExtra("calories_burnt", calories);
        sendBroadcast(intent);

    }
}
