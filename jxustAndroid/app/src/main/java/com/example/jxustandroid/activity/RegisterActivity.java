package com.example.jxustandroid.activity;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.jxustandroid.MainActivity;
import com.example.jxustandroid.R;
import com.example.jxustandroid.storage.MyFile;
import com.example.jxustandroid.storage.MySharedPreference;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText,passwordText,passwordSureText;
    private RadioGroup radioGroup;
    private String sex="男";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void init() {
        usernameText= findViewById(R.id.r_username);
        passwordText= findViewById(R.id.r_password);
        passwordSureText= findViewById(R.id.r_password_sure);

        //对注册按钮添加监听
        findViewById(R.id.r_register).setOnClickListener(this);;

        // 判断男女
        radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                sex = checkedId == R.id.male ? "男" : "女";
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册按钮
            case R.id.r_register: {
                //获取账号密码
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String passwordSure = passwordSureText.getText().toString();

                if("".equals(username)||"".equals(password)){
                    Toast.makeText(this, "账号或密码不为空", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(passwordSure)){
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }else{
                    //注册成功
                    //使用SharedPreference保存用户信息
                    MySharedPreference sharedPreference=new MySharedPreference(this);
                    sharedPreference.saveUser(username,password,sex);

                    //使用内部保存用户信息
                    MyFile myFile=new MyFile(this);
                    myFile.writeFile(username,password,sex);

                    AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                            .setTitle("注册成功")
                            .setMessage("跳转到登录界面")
                            .setIcon(R.mipmap.ic_launcher)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // 跳转到登录界面
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                ).create();
                    alertDialog2.show();

                    Toast.makeText(this, "用户信息已经保存!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }


}