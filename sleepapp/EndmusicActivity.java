package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by FredGogh on 2022/5/12.
 */

public class EndmusicActivity extends AppCompatActivity{
    private Button setendtime;
    private EditText sethour;
    private EditText setminute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endmusic);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setendtime = (Button)findViewById(R.id.setendtime);
        sethour = (EditText)findViewById(R.id.sethour);
        setminute = (EditText)findViewById(R.id.setminute);
        setendtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!setminute.getText().toString().matches("^[0-9]+(\\.[0-9]*)?$") || !sethour.getText().toString().matches("^[0-9]+(\\.[0-9]*)?$")){
                    Toast.makeText(EndmusicActivity.this,"输入不合法",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(EndmusicActivity.this, SleepActivity.class);
                    intent.putExtra("hour", sethour.getText().toString());
                    intent.putExtra("minute", setminute.getText().toString());
                    Toast.makeText(EndmusicActivity.this, "定时关闭已设置\n" + "时长: " + sethour.getText().toString() + "h " + setminute.getText().toString() + "min", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
        });
    }
}
