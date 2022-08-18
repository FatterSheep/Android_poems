package com.example.myapplications.Country;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.Activity.Content;
import com.example.myapplications.Activity.Log;
import com.example.myapplications.Activity.MainActivity;
import com.example.myapplications.LoginRe.JDBCUtils;
import com.example.myapplications.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Korea extends AppCompatActivity {
private Thread thread;
private ListView listView;
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korea);
        setThread(thread);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Korea.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ListView listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Map<String, Object> listItem = (Map<String, Object>) listView.getItemAtPosition(position);
//                et_name.setText((String) listItem.get("name")); //单击之后将数据显示到EditText中
//                et_pass.setText((String) listItem.get("pass"));
//                selId = (String) listItem.get("_id");   //将该记录的“_id”值存储到 selId
                Intent intent1 = new Intent(Korea.this, Content.class);
                Bundle bundle = new Bundle();
//                bundle.putString("Name", String.valueOf(listItem.get("name")));
//                bundle.putString("Title", String.valueOf(listItem.get("title")));
//                bundle.putString("JiaQin", String.valueOf(listItem.get("嘉庆时间")));
                bundle.putString("Poem", String.valueOf(listItem.get("poem")));
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
                android.util.Log.i("Demo", "监听ok");

//                Toast.makeText(getActivity(), "测试ing", Toast.LENGTH_LONG).show();
            }
        });
    }
    //        Handler部分
//        由子线程传出的数据在这里处理
    @SuppressLint("HandlerLeak")
//                先new一个Handler对象
    final Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//                        获取listview组件，因为我们要在这个部分更新UI组件
            listView = findViewById(R.id.listview);
//                        if的判断条件是区分msg是哪一条，即msg的ID
            if(msg.what==1){
//                            获取发送过来的msg.obj对象，因为我传的是List<HashMap<String, Object>>类型的obj，所以这边同样用List<HashMap<String, Object>> list去接收，要强转
                List<HashMap<String,Object>> list= (List<HashMap<String, Object>>) msg.obj;

//                            定义SimpleAdapter，参数分别为当前上下文，刚拿到的数据集合list，子项布局文件，数据集合中的字段信息，要添加到的子布局文件中的控件ID
                SimpleAdapter simpleAdapter=new SimpleAdapter(Korea.this,list,R.layout.listcontext,new String[]{"name","嘉庆时间","title","time","poem"},new int[]{R.id.tvYear,R.id.tvPerson,R.id.tvBook,R.id.tvTime});
//                            为listview设置适配器
                listView.setAdapter(simpleAdapter);

            }
        }
    };
//        Handler部分
//----------------------------------------------------------------------

    //--------------------------------------------------------------
// 连接数据库并进行相应操作的线程
//        第二、第三部分
// new 一个线程,接下来是数据库操作部分，要在子线程中执行

    public void setThread(Thread thread) {
        this.thread = thread;
        new Thread(new Runnable() {
            //定义一个子线程中的全局变量List<HashMap<String,Object>> list1，用于接收从DBUtils中返回的list
            List<HashMap<String,Object>> list1=new ArrayList<HashMap<String, Object>>();
            @Override
            public void run() {
//                        与数据库建立连接
                JDBCUtils.getConn();
                try {
//                            以下这些要用try/catch包含
//                            调用数据库工具类的getinfo函数，用list1接收返回的list数据
                    list1=JDBCUtils.getinfo();

//                            打印日志，测试用
//                    Log.d(TAG, list1.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                将从数据库拿到的list1对象传给message再由handler传出，再在handler中处理，可进行更新UI
//                新建一个message对象，尽量不要直接new，而是用这种方法，因为有内存的问题存在
                Message message=Message.obtain();

//                  设置message的辨认码，这里设为1
                message.what=1;

//                        把刚才接收到的list1赋给message.obj对象
                message.obj=list1;

//                        通过handler将携带数据的message传出去，传到handler中
                handler.sendMessage(message);
            }
        }).start();
    }
}