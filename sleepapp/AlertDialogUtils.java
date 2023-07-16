package com.example.fredgogh.sleepapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import static com.example.fredgogh.sleepapp.R.id.image;

/**
 * Created by FredGogh on 2022/5/10.
 */

// 自定义dialog对话框
public class AlertDialogUtils {
    private static View view_custom;
    public static AlertDialog.Builder builder;
    public  static AlertDialog alert ;
    public static TextView tv_dialog_title, tv_dialog_content;
    public static ImageView tv_dialog_image;
    public static AlertDialogUtils getInstance() {
        return new AlertDialogUtils();
    }

    /**
     * todo 带有确认取消按钮的自定义dialog
     * @param context 上下文对象
     * @param title 标题
     * @param image 图片
     * @param content 内容
     */
    public static void showConfirmDialog(Context context, String title,Drawable image, String content){
        builder = new AlertDialog.Builder(context);
        alert = builder.create();
        alert.show();

        //引入布局
        view_custom = LayoutInflater.from(context).inflate(R.layout.dialog,null,false);
        tv_dialog_title = view_custom.findViewById(R.id.title);
        tv_dialog_image = view_custom.findViewById(R.id.image);
        tv_dialog_title.setText(title);
        tv_dialog_image.setImageDrawable(image);
        tv_dialog_content =  view_custom.findViewById(R.id.content);
        tv_dialog_content.setText(content);

        alert.setCancelable(true); //点击空白处关闭弹窗

        //为取消按钮设置点击监听
        view_custom.findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        //使用布局
        alert.getWindow().setContentView(view_custom);
    }

}


