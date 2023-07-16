package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.fredgogh.sleepapp.MD5Utils.md5;

/**
 * Created by FredGogh on 2022/5/16.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText id;
    private EditText pwd;
    private Button login;
    private Button toregister;
    private CheckBox rememberid;
    private CheckBox autologin;
    private TextView rememberid_text;
    private TextView autologin_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);
        login = (Button)findViewById(R.id.login);
        toregister = (Button)findViewById(R.id.toregister);
        rememberid = (CheckBox)findViewById(R.id.rememberid);
        autologin = (CheckBox)findViewById(R.id.autologin);
        rememberid_text = (TextView)findViewById(R.id.rememberid_text);
        autologin_text = (TextView)findViewById(R.id.autologin_text);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);

        if(sp.getBoolean("rememberid",false)){
            id.setText(sp.getString("loginUserName",""));
        }
        if(sp.getBoolean("autologin",false)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("id", sp.getString("loginUserName",""));
            Toast.makeText(LoginActivity.this, sp.getString("loginUserName","") + "登录成功", Toast.LENGTH_SHORT).show();
            saveLoginStatus(true, sp.getString("loginUserName",""));
            startActivity(intent);
            LoginActivity.this.finish();
        }

        autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    autologin_text.setTextColor(Color.parseColor("#000000"));
                }else{
                    autologin_text.setTextColor(Color.GRAY);
                }
            }
        });

        rememberid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rememberid_text.setTextColor(Color.parseColor("#000000"));
                }else{
                    rememberid_text.setTextColor(Color.GRAY);
                }
            }
        });

        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id_input = id.getText().toString().trim();
                String pwd_input = pwd.getText().toString().trim();
                String sp_pwd = md5(pwd_input);

                if (id_input.isEmpty() || pwd_input.isEmpty()){
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (readPsw(id_input).isEmpty() || (!readPsw(id_input).isEmpty() && !readPsw(id_input).equals(sp_pwd))) {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }else {
                    setRememberid();
                    setAutologin();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", id_input);
                    SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
                    Toast.makeText(LoginActivity.this, id_input + "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true, id_input);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

            }
        });


    }

    private String readPsw(String id){
        //getSharedPreferences("loginInfo",MODE_PRIVATE);
        //"loginInfo",mode_private; MODE_PRIVATE表示可以继续写入
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //sp.getString() userName, "";
        return sp.getString(id,"");
    }

    private void saveLoginStatus(boolean status,String userName){
        //saveLoginStatus(true, userName);
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.commit();
    }

    private void setAutologin(){
        SharedPreferences sp= getSharedPreferences("loginInfo", MODE_PRIVATE);
        if (autologin.isChecked()){
            sp.edit().putBoolean("autologin",true).commit();
        }else{
            sp.edit().putBoolean("autologin",false).commit();
        }
    }

    private void setRememberid(){
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        if(rememberid.isChecked()){
            sp.edit().putBoolean("rememberid",true).commit();
        }else{
            sp.edit().putBoolean("rememberid",false).commit();
        }
    }

}
