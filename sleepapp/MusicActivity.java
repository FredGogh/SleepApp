package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by FredGogh on 2022/5/11.
 */

public class MusicActivity extends AppCompatActivity {
    public static MediaPlayer mediaplayer = new MediaPlayer();
    private List<Music> musicList = new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initMusic();
        MusicAdapter adapter = new MusicAdapter(MusicActivity.this,R.layout.listitem,musicList);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (mediaplayer.isPlaying() == true){
                    mediaplayer.stop();
                }else {
                    Intent intent = new Intent(MusicActivity.this,EndmusicActivity.class);
                    startActivity(intent);
                    initMediaPlayer(position);
                    mediaplayer.start();
                }

            }
        });


    }

    private void initMusic(){
        Music music1 = new Music("雨声","rain.mp3");
        musicList.add(music1);
        Music music2 = new Music("鸟鸣","birds.mp3");
        musicList.add(music2);
        Music music3 = new Music("虫鸣","insects.mp3");
        musicList.add(music3);
        Music music4 = new Music("波浪","wave.mp3");
        musicList.add(music4);
        Music music5 = new Music("河流","river.mp3");
        musicList.add(music5);
        Music music6 = new Music("篝火","fire.mp3");
        musicList.add(music6);
        Music music7 = new Music("风声","wind.mp3");
        musicList.add(music7);
        Music music8 = new Music("森林","forest.mp3");
        musicList.add(music8);
        Music music9 = new Music("雷雨","storm.mp3");
        musicList.add(music9);
        Music music10 = new Music("音乐盒","musicbox.mp3");
        musicList.add(music10);
        Music music11 = new Music("瀑布","waterfall.mp3");
        musicList.add(music11);
    }

    private void initMediaPlayer(int position){
        try{
            mediaplayer.reset();
            File file = new File(Environment.getExternalStorageDirectory(),musicList.get(position).getMusicuri());
            mediaplayer.setDataSource(file.getPath());
            mediaplayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}