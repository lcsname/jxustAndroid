package com.example.jxustandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.jxustandroid.R;
import com.example.jxustandroid.adapter.MyAdapter;
import com.example.jxustandroid.sqlite.MySQLite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableDataActivity extends AppCompatActivity {


    MySQLite mySQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_data);
        init();
    }
    public void  init(){
        mySQLite=new MySQLite(this);
        initData();
        findViewById(R.id.table_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到添加数据页面
                Intent intent=new Intent(TableDataActivity.this,AddDataActivity.class);
                startActivity(intent);
            }
        });


    }

    public void initData(){
        ListView listView=findViewById(R.id.wr_areas);

        // 只有调用getReadableDatabase()或者getWritableDatabase()函数后才能返回一个SQLiteDatabase对象
        SQLiteDatabase sqLiteDatabase= mySQLite.getReadableDatabase();
        List<Map<String,Object>> list=mySQLite.queryData(sqLiteDatabase);
        MyAdapter myAdapter=new MyAdapter(list,this);
        listView.setAdapter(myAdapter);
    }
}