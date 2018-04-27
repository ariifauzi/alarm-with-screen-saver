package com.example.harukaedu.alarmashari;

import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.harukaedu.alarmashari.animation.Timer;


/**
 * Created by meta on 27/04/18.
 */

public class BaseActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private Timer timer;

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.stopTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.stopTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.stopTimer();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        startTimer();
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        startTimer();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        startTimer();
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        startTimer();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        startTimer();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        startTimer();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        startTimer();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        startTimer();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        startTimer();
        return true;
    }

    public void startTimer() {
        timer.start(new Timer.TimeOutListener() {
            @Override
            public void timeOut() {
                AnimationActivity.startActivity(BaseActivity.this);
            }
        });
    }
}
