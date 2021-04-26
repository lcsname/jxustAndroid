package com.example.jxustandroid.storage;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class MyFile {

    Context context;
    public MyFile(Context context){
        this.context=context;
    }

    public void writeFile(String username,String password,String sex){
        String user_mes="用户名: "+username+"\n"+
                "密码: "+password+"\n"+
                "性别: "+sex;

        //保存输入的账号和密码
        FileOutputStream fi_out;
        try{
            //MODE_PRIVATE 默认操作权限,只能被当前应用程序所读写
            fi_out=context.openFileOutput("user_mes.txt",MODE_PRIVATE);
            fi_out.write(user_mes.getBytes());
            fi_out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void readFile(){
        String mes="";
        try{
            FileInputStream fi_input;
            fi_input=context.openFileInput("user_mes.txt");
            byte[] buffer=new byte[fi_input.available()];
            fi_input.read(buffer);
            mes=new String(buffer);
            fi_input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(context, mes, Toast.LENGTH_SHORT).show();
    }
}
