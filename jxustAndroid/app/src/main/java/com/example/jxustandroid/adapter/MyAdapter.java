package com.example.jxustandroid.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.jxustandroid.R;
import com.example.jxustandroid.activity.LoginSuccessActivity;
import com.example.jxustandroid.activity.TableDataActivity;
import com.example.jxustandroid.activity.UpdateDataActivity;
import com.example.jxustandroid.sqlite.MySQLite;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private List<Map<String,Object>> datas; //datas表示需要绑定到View的数据
    private Context context; //传入的数据
    private  MySQLite mySQLite;

    public MyAdapter(List<Map<String,Object>> datas,Context context){
        this.datas=datas;
        this.context=context;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 只有getCount() 不为0的时候才调用
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            //使用自定义的布局文件作为Layout
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item_layout,null);
            //减少findView
            holder=new ViewHolder();
            //初始化布局中的元素
            holder.tv_0=convertView.findViewById(R.id.tv_0);
            holder.tv_1=convertView.findViewById(R.id.tv_1);
            holder.tv_2=convertView.findViewById(R.id.tv_2);
            holder.tv_3=convertView.findViewById(R.id.tv_3);
            holder.button=convertView.findViewById(R.id.button_4);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alter(holder);
                    //Toast.makeText(context, holder.tv_0.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        //重传入的数据中提取数据并绑定到指定的View中
        holder.tv_0.setText(datas.get(position).get("id").toString());
        holder.tv_1.setText(datas.get(position).get("name").toString());
        holder.tv_2.setText(datas.get(position).get("age").toString());
        holder.tv_3.setText(datas.get(position).get("position").toString());
        holder.button.setText("操作");
        return convertView;
    }

    static class ViewHolder{
        TextView tv_0,tv_1,tv_2,tv_3;
        Button button;
    }

    public void alter(ViewHolder holder){
        String id=holder.tv_0.getText().toString();
        //获取数据连接
        mySQLite=new MySQLite(context);
        // 只有调用getReadableDatabase()或者getWritableDatabase()函数后才能返回一个SQLiteDatabase对象
        SQLiteDatabase sqLiteDatabase= mySQLite.getReadableDatabase();

        AlertDialog alertDialog2 = new AlertDialog.Builder(context)
                .setTitle("操作")
                .setMessage("当前操作的数据是id= " + id)
                .setIcon(R.mipmap.ic_launcher)

                .setNegativeButton("删除数据", new DialogInterface.OnClickListener() {//添加取消
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mySQLite.delete(sqLiteDatabase,Integer.parseInt(id));
                        TableDataActivity tableDataActivity=(TableDataActivity)context;
                        //删除完重新更新数据
                        tableDataActivity.initData();
                        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("修改数据", new DialogInterface.OnClickListener() {//添加普通按钮
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 跳转到登录成功的界面
                        Intent intent = new Intent(context, UpdateDataActivity.class);
                        // 页面间传输数据
                        Bundle b = new Bundle();
                        b.putString("id", holder.tv_0.getText().toString());
                        b.putString("name", holder.tv_1.getText().toString());
                        b.putString("age", holder.tv_2.getText().toString());
                        b.putString("position", holder.tv_3.getText().toString());
                        intent.putExtra("data", b);
                        context.startActivity(intent);
                    }
                })
                .create();
        alertDialog2.show();
    }
}
