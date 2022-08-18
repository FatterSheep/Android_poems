package com.example.myapplications.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplications.Activity.Content;
import com.example.myapplications.Data.DataBean;
import com.example.myapplications.Adapter.ImageAdapter;
import com.example.myapplications.LoginRe.JDBCUtils;
import com.example.myapplications.R;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @programName: OneFragment.java
 * @programFunction:
 * @createDate: 2018/09/25
 * @author: AnneHan
 * @version:
 * xx.   yyyy/mm/dd   ver    author    comments
 * 01.   2018/09/25   1.00   AnneHan   New Create
 */
public class TwoFragment extends Fragment {
    private Thread thread;
    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanners();
        initViews();
        setThread(thread);
        ListView listView = getActivity().findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Map<String, Object> listItem = (Map<String, Object>) listView.getItemAtPosition(position);
//                et_name.setText((String) listItem.get("name")); //单击之后将数据显示到EditText中
//                et_pass.setText((String) listItem.get("pass"));
//                selId = (String) listItem.get("_id");   //将该记录的“_id”值存储到 selId
                Intent intent1 = new Intent(getActivity(), Content.class);
                Bundle bundle = new Bundle();
//                bundle.putString("Name", String.valueOf(listItem.get("name")));
//                bundle.putString("Title", String.valueOf(listItem.get("title")));
//                bundle.putString("JiaQin", String.valueOf(listItem.get("嘉庆时间")));
                bundle.putString("Poem", String.valueOf(listItem.get("poem")));
                intent1.putExtras(bundle);
                startActivity(intent1);
                getActivity().finish();
                Log.i("Demo", "监听ok");

//                Toast.makeText(getActivity(), "测试ing", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initViews() {
    }
    private void initBanners() {
        //本地图片
        List<DataBean> list_DataBean = new ArrayList<>();
        list_DataBean.add(new DataBean(R.drawable.maom, "", 0));
        list_DataBean.add(new DataBean(R.drawable.mami, "", 1));

        Banner banner = this.getView().findViewById(R.id.banner);
        //默认直接设置adapter就行了

        //自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(list_DataBean);

        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))
                .setOnBannerListener((data, position) -> {
                    getIntent(position);
                });
    }

    private void getIntent(int position) {
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
            listView = getView().findViewById(R.id.listview);
//                        if的判断条件是区分msg是哪一条，即msg的ID
            if(msg.what==1){
//                            获取发送过来的msg.obj对象，因为我传的是List<HashMap<String, Object>>类型的obj，所以这边同样用List<HashMap<String, Object>> list去接收，要强转
                List<HashMap<String,Object>> list= (List<HashMap<String, Object>>) msg.obj;

//                            定义SimpleAdapter，参数分别为当前上下文，刚拿到的数据集合list，子项布局文件，数据集合中的字段信息，要添加到的子布局文件中的控件ID
                SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(),list,R.layout.listcontext,new String[]{"name","作者时间","title","poem"},new int[]{R.id.tvYear,R.id.tvTime,R.id.tvBook});
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
//                            调用数据库工具类的getinfo1函数，用list1接收返回的list数据
                    list1=JDBCUtils.getinfo1();

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