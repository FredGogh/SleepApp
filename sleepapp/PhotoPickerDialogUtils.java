package com.example.fredgogh.sleepapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.fredgogh.sleepapp.MainActivity.profilephoto_image;
import static com.example.fredgogh.sleepapp.R.id.happy;
/**
 * Created by FredGogh on 2022/5/10.
 */

// 自定义dialog对话框
public class PhotoPickerDialogUtils extends MainActivity{
    public static View view_custom2;
    public static AlertDialog.Builder builder2;
    public static AlertDialog alert2;



    public static void showConfirmDialog(Context context){
        builder2 = new AlertDialog.Builder(context);
        alert2 = builder2.create();
        alert2.show();
        //引入布局
        view_custom2 = LayoutInflater.from(context).inflate(R.layout.photopicker_dialog,null,false);

        alert2.setCancelable(true); //点击空白处关闭弹窗


        //使用布局
        alert2.getWindow().setContentView(view_custom2);

    }



}


