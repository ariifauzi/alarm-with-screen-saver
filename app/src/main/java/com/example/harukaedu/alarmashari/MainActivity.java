package com.example.harukaedu.alarmashari;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends BaseActivity{

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    ToggleButton toggleButton;
    Camera camera;
    private CameraManager cameraManager;
    private String cameraId;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;
    private Button mBtLaunchActivity;
    private Button ntpdLauncActivity;
    private Button stpwtchLaunchActivity;
    private Button flash;
    private Button vibrate;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);

        buttonstartSetDialog = (Button) findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });

        //ketika button Alarm di touch
//        flash = (Button) findViewById(R.id.startSetDialog);
//        flash.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                launchActivity();
//            }
//        });

        mBtLaunchActivity = (Button) findViewById(R.id.paintActivity);

        mBtLaunchActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });

        ntpdLauncActivity = (Button) findViewById(R.id.notepadActivity);
        ntpdLauncActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                ntpdlaunchActivity();
            }
        });

        stpwtchLaunchActivity = (Button) findViewById(R.id.stopwatchActivity);
        stpwtchLaunchActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                stpwtchLaunchActivity();
            }
        });

        vibrate = (Button) findViewById(R.id.vibrateActivity);
        vibrate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                vibrate();
            }
        });

        //Ketika Turn On/Off flash di touch
        toggleButton = (ToggleButton) findViewById(R.id.startSetDialog4);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked){

                cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    try {
                        cameraId = cameraManager.getCameraIdList()[0];
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }

                if (checked) {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cameraManager.setTorchMode(cameraId, true);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {

                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            cameraManager.setTorchMode(cameraId, false);
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }


    private void launchActivity() {

        Intent intent = new Intent(this, PaintActivity.class);
        startActivity(intent);
    }

    private void ntpdlaunchActivity() {

        Intent intent = new Intent(this, NotepadActivity.class);
        startActivity(intent);
    }

    private void vibrate() {

        Intent intent = new Intent(this, VibrateActivity.class);
        startActivity(intent);
    }

    private void stpwtchLaunchActivity() {

        Intent intent = new Intent(this, StopwatchActivity.class);
        startActivity(intent);
    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(MainActivity.this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
        timePickerDialog.setTitle("Set Time for Alarm");

        timePickerDialog.show();

    }

    OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
                Log.i("hasil", " =<0");
            } else if (calSet.compareTo(calNow) > 0) {
                Log.i("hasil", " > 0");
            } else {
                Log.i("hasil", " else ");
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        textAlarmPrompt.setText("\n" + "Alarm set on " + targetCal.getTime() + "\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

}

