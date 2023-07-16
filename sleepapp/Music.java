package com.example.fredgogh.sleepapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Button;

/**
 * Created by FredGogh on 2022/5/12.
 */

public class Music {
    private String musicname;
    private String musicuri;
    public Music(String name,String uri){
        this.musicname = name;
        this.musicuri = uri;
    }

    public String getName() {
        return musicname;
    }

    public String getMusicuri() {
        return musicuri;
    }
}
