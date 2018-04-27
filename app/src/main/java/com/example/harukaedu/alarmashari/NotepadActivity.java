package com.example.harukaedu.alarmashari;

/**
 * Created by Harukaedu on 4/5/18.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Harukaedu on 3/27/18.
 */

public class NotepadActivity extends BaseActivity implements View.OnClickListener {

    EditText inputText;
    TextView tampilText;
    Button btnTampil, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notepad);

        inputText = (EditText) findViewById(R.id.editText);
        tampilText = (TextView) findViewById(R.id.textView);
        btnTampil = (Button) findViewById(R.id.btnTampil);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnTampil.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnTampil:
                tampilText.setText(inputText.getText().toString());
                break;
            case R.id.btnReset:
                tampilText.setText("");
                inputText.setText("");
                break;
        }
    }

}
