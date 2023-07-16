package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.fredgogh.sleepapp.MD5Utils.md5;

/**
 * Created by FredGogh on 2022/5/16.
 */

public class EditinfoActivity extends AppCompatActivity{
    private EditText edit_id;
    private EditText edit_pwd;
    private EditText edit_pwd2;
    private EditText edit_age;
    private EditText edit_height;
    private EditText edit_weight;
    private Button done;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        edit_pwd2 = (EditText)findViewById(R.id.edit_pwd2);
        edit_age = (EditText)findViewById(R.id.edit_age);
        edit_height = (EditText)findViewById(R.id.edit_height);
        edit_weight = (EditText)findViewById(R.id.edit_weight);
        done = (Button)findViewById(R.id.done);
        //初始化
        fillin();


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_id.getText().toString();
                String pwd = edit_pwd.getText().toString();
                String pwd2 = edit_pwd2.getText().toString();
                String age = edit_age.getText().toString();
                String height = edit_height.getText().toString();
                String weight = edit_weight.getText().toString();
                SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
                SharedPreferences sp_age = getSharedPreferences("age",MODE_PRIVATE);
                SharedPreferences sp_height = getSharedPreferences("height",MODE_PRIVATE);
                SharedPreferences sp_weight = getSharedPreferences("weight",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                SharedPreferences.Editor editor_age = sp_age.edit();
                SharedPreferences.Editor editor_height = sp_height.edit();
                SharedPreferences.Editor editor_weight = sp_weight.edit();
                String old_id = sp.getString("loginUserName", null);
                boolean id_done = true;
                boolean pwd_done = true;
                boolean age_done = true;
                boolean height_done = true;
                boolean weight_done = true;

                //修改id
                if(!id.isEmpty() && !id.equals(old_id)){
                    if (sp.contains(id)){
                        Toast.makeText(EditinfoActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                        id_done = false;
                    }else{
                        //以新id插入个人信息
                        //密码
                        editor.putString(id,sp.getString(old_id,null)).commit();
                        //年龄
                        editor_age.putString(id,sp_age.getString(old_id,null)).commit();
                        //身高
                        editor_height.putString(id,sp_height.getString(old_id,null)).commit();
                        //体重
                        editor_weight.putString(id,sp_weight.getString(old_id,null)).commit();

                        //更新登录用户信息
                        editor.putString("loginUserName",id).commit();
                        //删除旧id所保留的信息
                        editor.remove(old_id).commit();
                        editor_age.remove(old_id).commit();
                        editor_height.remove(old_id).commit();
                        editor_weight.remove(old_id).commit();
                        old_id = id;
                        id_done = true;
                    }
                }else{
                    id_done = true;
                }

                //修改密码
                if(!pwd.isEmpty() && !md5(pwd).equals(sp.getString(old_id,null))) {
                    if (pwd2.isEmpty()) {
                        Toast.makeText(EditinfoActivity.this,"请再次输入密码",Toast.LENGTH_SHORT).show();
                        pwd_done = false;
                    }else if (!pwd.equals(pwd2)){
                        Toast.makeText(EditinfoActivity.this,"两次输入密码不一样",Toast.LENGTH_SHORT).show();
                        pwd_done = false;
                    }else{
                        editor.putString(old_id, md5(pwd)).commit();
                        pwd_done = true;
                    }
                }else {
                    pwd_done = true;
                }

                //修改年龄
                if (!age.isEmpty() && !age.equals(sp_age.getString(old_id,null))) {
                    if (!age.matches("[0-9]*")) {
                        Toast.makeText(EditinfoActivity.this,"输入年龄不合法",Toast.LENGTH_SHORT).show();
                        age_done = false;
                    } else {
                        editor_age.putString(old_id, age).commit();
                        age_done = true;
                    }
                }else {
                    age_done = true;
                }

                //修改身高
                if (!height.isEmpty() && !height.equals(sp_height.getString(old_id,null))) {
                    if (!height.matches("^[0-9]+(\\.[0-9]*)?$")) {
                        Toast.makeText(EditinfoActivity.this,"输入身高不合法",Toast.LENGTH_SHORT).show();
                        height_done = false;
                    } else {
                        editor_height.putString(old_id,height).commit();
                        height_done = true;
                    }
                }else {
                    height_done = true;
                }

                //修改体重
                if (!weight.isEmpty() && !weight.equals(sp_weight.getString(old_id,null))) {
                    if (!weight.matches("^[0-9]+(\\.[0-9]*)?$")) {
                        Toast.makeText(EditinfoActivity.this,"输入体重不合法",Toast.LENGTH_SHORT).show();
                        weight_done = false;
                    } else {
                        editor_weight.putString(old_id,weight).commit();
                        weight_done = true;
                    }
                }else {
                    weight_done = true;
                }
                if(id_done && pwd_done && age_done && height_done && weight_done) {
                    Toast.makeText(EditinfoActivity.this, "修改完毕", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditinfoActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                fillin();
            }
        });
    }

    private void fillin(){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences sp_age = getSharedPreferences("age",MODE_PRIVATE);
        SharedPreferences sp_height = getSharedPreferences("height",MODE_PRIVATE);
        SharedPreferences sp_weight = getSharedPreferences("weight",MODE_PRIVATE);
        String id = sp.getString("loginUserName",null);
        edit_id.setText(id);
        edit_age.setText(sp_age.getString(id,null));
        edit_height.setText(sp_height.getString(id,null));
        edit_weight.setText(sp_weight.getString(id,null));
    }
}
