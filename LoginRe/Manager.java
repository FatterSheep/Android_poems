package com.example.myapplications.LoginRe;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.myapplications.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager extends Activity {
    private static String DB_NAME = "users";
    private EditText et_name;
    private EditText et_pass;
    private EditText et_id;
    private ArrayList<Map<String, Object>> data;
    private Mysqlist dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleAdapter listAdapter;
    private View view;
    private ListView listview;
    private Button selBtn, addBtn, updBtn, delBtn,selectBtn,emptyBtn;
    private Map<String, Object> item;
    private Map<String, Object> items;
    private String selId;
    private ContentValues selCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        itemviews();

        selectBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbQuery();
            }
        });
        selBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbFindAll();
            }
        });

        addBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbAdd();
                dbFindAll();
            }
        });
        updBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbUpdate();
                dbFindAll();
            }
        });
        delBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbDel();
                dbFindAll();
            }
        });
        emptyBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                nullList();
            }
        });
        dbHelper = new Mysqlist (this, DB_NAME, null, 1);
        db = dbHelper.getWritableDatabase();// 打开数据库
        data = new ArrayList<>();
        dbFindAll();
        //对ListView添加监听器，，单击某条数据时做出反应
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // TODO Auto-generated method stub
                Map<String, Object> listItem = (Map<String, Object>) listview.getItemAtPosition(position);
                et_name.setText((String) listItem.get("name")); //单击之后将数据显示到EditText中
                et_pass.setText((String) listItem.get("pass"));
                selId = (String) listItem.get("_id");   //将该记录的“_id”值存储到 selId
                Log.i("mydbDemo", "id=" + selId);
            }
        });
    }

    private void itemviews() {
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);
        listview = findViewById(R.id.listView);
        selBtn = findViewById(R.id.bt_query);
        addBtn = findViewById(R.id.bt_add);
        updBtn = findViewById(R.id.bt_modify);
        delBtn = findViewById(R.id.bt_del);
        selectBtn = findViewById(R.id.btn_select);
        emptyBtn =findViewById(R.id.bt_empty);
        et_id = findViewById(R.id.edit_id);
    }


    //数据删除
    protected void dbDel() {
        // TODO Auto-generated method stub
        String where = "_id=" + selId;
        int i = db.delete(dbHelper.TB_NAME, where, null);
        if (i > 0)
            Toast.makeText(getApplicationContext(),"数据删除成功",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据未删除",Toast.LENGTH_SHORT).show();
    }
//List
    private void showList() {
        listAdapter = new SimpleAdapter(this, data,
                R.layout.listview, new String[]{"_id", "name", "pass"}, new int[]{R.id.tvID, R.id.tvName, R.id.tvAge});
        listview.setAdapter(listAdapter);
    }
    private void nullList() {
        listAdapter = new SimpleAdapter(this, data,
                R.layout.listview, new String[]{"_id", "name", "pass"}, new int[]{R.id.tvID, R.id.tvName, R.id.tvAge});
        listview.setAdapter(null);
    //    listview.setAdapter(listAdapter);
    }

    //更新列表中的数据
    protected void dbUpdate() {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put("name", et_name.getText().toString().trim());
        values.put("pass", et_pass.getText().toString().trim());
        String where = "_id=" + selId;
        int i = db.update(dbHelper.TB_NAME, values, where, null);
        if (i > 0)
            Toast.makeText(getApplicationContext(),"数据更新成功",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据未更新",Toast.LENGTH_SHORT).show();

    }

    //插入数据
    protected void dbAdd() {
        // TODO Auto-generated method stub
        ContentValues values = new ContentValues();
        values.put("name", et_name.getText().toString().trim());
        values.put("pass", et_pass.getText().toString().trim());
        long rowid = db.insert(dbHelper.TB_NAME, null, values);
        if (rowid == -1)
            Toast.makeText(getApplicationContext(),"数据插入失败",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据插入成功",Toast.LENGTH_SHORT).show();
    }

    //查询数据
    protected void dbFindAll() {
        // TODO Auto-generated method stub
        data.clear();
        cursor = db.query(dbHelper.TB_NAME, null, null, null, null, null, "_id ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) { //从数据库中查询出所有数据并按id升序排列
            String id = cursor.getString(0);    //将每条数据的对应字段分别取出来
            String name = cursor.getString(1);  //通过 while 循环将所有数据保存到 data 中
            String pass = cursor.getString(2);
            item = new HashMap<String, Object>();
            item.put("_id", id);
            item.put("name", name);
            item.put("pass", pass);
            data.add(item);
            cursor.moveToNext();
        }
        showList();
    }
    public void dbQuery() {
        // 通过DBHelper类获取一个读写的SQLiteDatabase对象
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 参数1：table_name
        // 参数2：columns 要查询出来的列名。相当于 select  *** from table语句中的 ***部分
        // 参数3：selection 查询条件字句，在条件子句允许使用占位符“?”表示条件值
        // 参数4：selectionArgs ：对应于 selection参数 占位符的值
        // 参数5：groupby 相当于 select *** from table where && group by ... 语句中 ... 的部分
        // 参数6：having 相当于 select *** from table where && group by ...having %%% 语句中 %%% 的部分
        // 参数7：orderBy ：相当于 select  ***from ？？  where&& group by ...having %%% order by@@语句中的@@ 部分，如： personid desc（按person 降序）
        data.clear();
        ContentValues values = new ContentValues();

        //解决了
        String ID = et_id.getText().toString();
        String NAME = et_name.getText().toString();
        String PASS = et_pass.getText().toString();

        String[] columns={"_id","name","pass"};//你要的数据

        //错误,如何获取输入框内容
        String where="_id=? or name=? or pass=?";
        //对应上面查询的？
        String[] selectionArgs={ID,NAME,PASS};//具体的条件,注意要对应条件字段

        Cursor cursor = db.query(dbHelper.TB_NAME, columns, where, selectionArgs, null, null, null);

        // 将游标移到开头
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) { // 游标只要不是在最后一行之后，就一直循环
//            int id=cursor.getInt(0);
//            String name=cursor.getString(1);
            String id = cursor.getString(0);    //将每条数据的对应字段分别取出来
            String name = cursor.getString(1);  //通过 while 循环将所有数据保存到 data 中
            String pass = cursor.getString(2);
            items = new HashMap<String, Object>();
            items.put("_id", id);
            items.put("name", name);
            items.put("pass", pass);
            data.add(items);
            // 将游标移到下一行
            cursor.moveToNext();
        }
        showList();
        cursor.close();
    }
//    public void dbSelct{
//        String[] columns={"_id","name","age"};//你要的数据
//
//        String where="_id=? or name=? or age=?";
//
//        String[] selectionArgs={"","",""};//具体的条件,注意要对应条件字段
//
//        Cursor cursor=db.query(dbHelper.TB_NAME, columns, where,selectionArgs, null, null, null, null);
//
//        String strSql = "update appsort set number=number+1 where activityname='"+appSortInfo.getActivityName()+"' and packagename='"+appSortInfo.getPackageName()+"'";
//
//        select DB_NAME from appsort where number >0 order by number desc limit 0,20
//
//        String sql = "select * from appsort where activityname=? and packagename=?";
//
//        Cursor mCursor = db.rawQuery(sql,new String[]{"com.android.calendar.AllInOneActivity","com.google.android.calendar"}); mCursor.moveToNext();
//    }


}