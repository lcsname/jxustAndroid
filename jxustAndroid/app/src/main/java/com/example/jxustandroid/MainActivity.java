package com.example.jxustandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jxustandroid.activity.LoginSuccessActivity;
import com.example.jxustandroid.activity.RegisterActivity;
import com.example.jxustandroid.storage.MySharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);

        //对按钮添加监听
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录按钮
            case R.id.login:{
                //获取账号密码
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                //使用SharedPreference读取保存的账号密码
                MySharedPreference sharedPreference=new MySharedPreference(this);
                String usernameTrue=sharedPreference.getValue("username");
                if(usernameTrue==null){
                   AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                            .setTitle("提示")
                            .setMessage("你还未注册,请先注册")
                            .setIcon(R.mipmap.ic_launcher)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // 跳转到登录界面
                                            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                            ).create();
                    alertDialog2.show();

                    return;
                }
                String passwordTrue=sharedPreference.getValue("password");
                if(usernameTrue.equals(username)&&passwordTrue.equals(password)){
                    //登录成功
                    // 跳转到登录成功的界面
                    Intent intent = new Intent(this, LoginSuccessActivity.class);
                    // 页面间传输数据
                    Bundle b = new Bundle();
                    b.putString("username", username);
                    b.putString("password", password);
                    b.putString("sex", sharedPreference.getValue("sex"));
                    intent.putExtra("data", b);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
                }
            }break;
            //注册按钮
            case R.id.register:{
                // 跳转到注册界面
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
            }break;
        }
    }
}