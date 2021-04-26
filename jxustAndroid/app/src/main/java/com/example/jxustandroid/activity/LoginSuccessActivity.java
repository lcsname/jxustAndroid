package com.example.jxustandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.jxustandroid.R;

public class LoginSuccessActivity extends AppCompatActivity {
    private TextView showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        //获取登录信息
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("data");
        showText = findViewById(R.id.show_text);

        showText.setText("恭喜你登录成功!\n" +
                "账号: " + b.getString("username") + "\n" +
                "密码: " + b.getString("password") +"\n" +
                "性别: "+ b.getString("sex")
        );

        //对按钮添加监听
        findViewById(R.id.btn_sql).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSuccessActivity.this, TableDataActivity.class);
                startActivity(intent);
            }
        });
    }



}