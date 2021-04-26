package com.example.jxustandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jxustandroid.R;
import com.example.jxustandroid.sqlite.MySQLite;

public class AddDataActivity extends AppCompatActivity {
    private MySQLite mySQLite;
    private EditText nameText,ageText,positionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        init();
    }
    public void init(){
        nameText=findViewById(R.id.add_et_1);
        ageText=findViewById(R.id.add_et_2);
        positionText=findViewById(R.id.add_et_3);

        //获取数据连接
        mySQLite=new MySQLite(this);


        findViewById(R.id.add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString();
                String age=ageText.getText().toString();
                String position=positionText.getText().toString();
                if("".equals(name)||"".equals(age)||"".equals(position)){
                    Toast.makeText(AddDataActivity.this, "表单不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase sqLiteDatabase= mySQLite.getReadableDatabase();
                mySQLite.addData(sqLiteDatabase,name,age,position);
                Toast.makeText(AddDataActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddDataActivity.this,TableDataActivity.class);
                startActivity(intent);
            }
        });
    }
}