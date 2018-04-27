package com.example.harukaedu.alarmashari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by meta on 27/04/18.
 */

public class AnimationActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AnimationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        View parentView = getWindow().getDecorView();
        parentView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        finish();
                        break;
                }

                return false;
            }
        });
    }
}
