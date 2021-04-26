package com.example.jxustandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jxustandroid.R;
import com.example.jxustandroid.sqlite.MySQLite;

public class UpdateDataActivity extends AppCompatActivity {
    private MySQLite mySQLite;
    private TextView textView;
    private EditText nameText,ageText,positionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        init();
    }
    public void init(){
        mySQLite =new MySQLite(this);
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("data");

        textView=findViewById(R.id.update_et_0);
        nameText=findViewById(R.id.update_et_1);
        ageText=findViewById(R.id.update_et_2);
        positionText=findViewById(R.id.update_et_3);

        textView.setText(b.getString("id"));
        nameText.setText(b.getString("name"));
        ageText.setText(b.getString("age"));
        positionText.setText(b.getString("position"));

        findViewById(R.id.update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=textView.getText().toString();
                String name=nameText.getText().toString();
                String age=ageText.getText().toString();
                String position=positionText.getText().toString();
                if("".equals(name)||"".equals(age)||"".equals(position)){
                    Toast.makeText(UpdateDataActivity.this, "表单不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase sqLiteDatabase= mySQLite.getWritableDatabase();
                mySQLite.update(sqLiteDatabase,Integer.parseInt(id),name,age,position);
                Toast.makeText(UpdateDataActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateDataActivity.this,TableDataActivity.class);
                startActivity(intent);
            }
        });
    }
}