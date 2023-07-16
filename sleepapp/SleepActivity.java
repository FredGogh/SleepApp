package com.example.fredgogh.sleepapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DigitalClock;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import static android.R.attr.duration;
import static android.support.v7.appcompat.R.id.chronometer;
import static com.example.fredgogh.sleepapp.MusicActivity.mediaplayer;
import static com.example.fredgogh.sleepapp.R.drawable.cycle;

/**
 * Created by FredGogh on 2022/5/11.
 */

public class SleepActivity extends AppCompatActivity {
    private Button music;
    private Button end;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleep);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        final int sleep_hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        final int sleep_minute = Calendar.getInstance().get(Calendar.MINUTE);
        final int startsleep_date = Calendar.getInstance().get(Calendar.DATE);
        music = (Button)findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepActivity.this,MusicActivity.class);
                startActivity(intent);
            }
        });
        end = (Button)findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepActivity.this,MainActivity.class);
                intent.putExtra("sleep_hour",sleep_hour);
                intent.putExtra("sleep_minute",sleep_minute);
                intent.putExtra("startsleep_date",startsleep_date);
                intent.putExtra("wakeup_hour",Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
                intent.putExtra("wakeup_minute",Calendar.getInstance().get(Calendar.MINUTE));
                intent.putExtra("wakeup_date",Calendar.getInstance().get(Calendar.DATE));
                Toast.makeText(SleepActivity.this,"睡眠结束",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        final Intent intent = getIntent();
        String hour_string = intent.getStringExtra("hour");
        String minute_string = intent.getStringExtra("minute");
        double hour = 0;
        double minute = 0;
        if (hour_string != null && minute_string != null) {
            hour = Float.parseFloat(hour_string);
            minute = Float.parseFloat(minute_string);
        }
        double duration = 1000*(hour*3600 + minute*60);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mediaplayer.stop();
            }
        }, (int)duration); //时间单位为毫秒
    }

    //屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


}
