package com.example.jxustandroid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLite extends SQLiteOpenHelper {
    public MySQLite(Context context){
        //创建 "user_db" 数据库
        super(context,"user_db",null,1);
    }

    /**
     * 数据库第一次创建时调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(" +
                "id integer primary key autoincrement," +
                "name varchar(20)," +
                "age varchar(20)," +
                "position varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库版本号更新的时候调用
    }

    //添加数据
    public void addData(SQLiteDatabase sqLiteDatabase,String name,String age,String position){
        //创建一个 ContentValues对象
        ContentValues values=new ContentValues();
        //以键值对的形式插入
        values.put("name",name);
        values.put("age",age);
        values.put("position",position);
        //执行方法
        sqLiteDatabase.insert("user",null,values);
        sqLiteDatabase.close();
    }

    //删除数据
    public void delete(SQLiteDatabase sqLiteDatabase,int id){
        //第一个参数: 表名
        //第二个参数: 需要删除的属性名,?代表占位符
        //第三个参数: 属性名的属性值
        sqLiteDatabase.delete("user","id=?",new String[]{id+""});
        sqLiteDatabase.close();
    }

    //更新数据
    public void update(SQLiteDatabase sqLiteDatabase,int id,String name,String age,String position){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("position",position);
        sqLiteDatabase.update("user",values,"id=?",new String[]{id+""});
        sqLiteDatabase.close();
    }
    //查询数据
    public List<Map<String,Object>> queryData(SQLiteDatabase sqLiteDatabase){
        Cursor cursor=sqLiteDatabase.query("user",null,null,null,null,null,"id ASC");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(1);
            String age=cursor.getString(2);
            String position=cursor.getString(3);

            Map<String, Object> map=new HashMap<>();
            map.put("id",id+"");
            map.put("name",name);
            map.put("age",age);
            map.put("position",position);
            list.add(map);
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
}
