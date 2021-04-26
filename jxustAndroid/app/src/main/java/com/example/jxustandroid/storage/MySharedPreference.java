package com.example.jxustandroid.storage;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class MySharedPreference {

    Context context;
    public MySharedPreference(Context context){
        this.context=context;
    }
    public  void saveUser(String username,String password,String sex){
        //getSharedPreferences第一个参数用于指定文件名称,不需要带后缀,后缀会由Android自动带上
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_mes",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putString("sex",sex);
        editor.commit();
    }

    public String getValue(String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_mes",MODE_PRIVATE);
        return sharedPreferences.getString(value,null);
    }


}
