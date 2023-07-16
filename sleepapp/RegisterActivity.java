package com.example.fredgogh.sleepapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity{
    private EditText id;
    private EditText pwd;
    private EditText confirmpwd;
    private Button register;
    private String id_input;
    private String pwd_input;
    private String pwd2_input;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        confirmpwd = (EditText)findViewById(R.id.confirmpwd);
        register = (Button) findViewById(R.id.register);    
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditString();
                if (id_input.isEmpty() || pwd_input.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(pwd2_input.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                }else if(!pwd_input.equals(pwd2_input)) {
                    Toast.makeText(RegisterActivity.this, "两次输入密码不一样", Toast.LENGTH_SHORT).show();
                }else if(IdExists(id_input)) {
                    Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }else{
                        saveRegisterInfo(id_input,pwd_input);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        RegisterActivity.this.finish();
                }
            }
        });

    }

    private void getEditString(){
        id_input = id.getText().toString().trim();
        pwd_input = pwd.getText().toString().trim();
        pwd2_input = confirmpwd.getText().toString().trim();
    }

    private boolean IdExists(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!spPsw.isEmpty()) {
            has_userName=true;
        }
        return has_userName;
    }

    private void saveRegisterInfo(String userName,String psw){
        String md5Psw = MD5Utils.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }


}
