package com.example.myapplications.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.Data.PoemDao;
import com.example.myapplications.R;
import com.example.myapplications.Tool.ActivityCollectorUtil;

public class Search extends AppCompatActivity implements View.OnClickListener {

    private ImageView minor;
    private TextView back;
    private EditText information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        ActivityCollectorUtil.addActivity(this);
    }

    private void initView() {
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        minor = findViewById(R.id.minor);
        minor.setOnClickListener(this);
        information = findViewById(R.id.information);
        information.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent i = new Intent(Search.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.minor:
                dbQuery();
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }

    public void dbQuery() {
        new Thread(() -> {
            Looper.prepare();//增加部分
            String content = information.getText().toString().trim();

            PoemDao poemDao = new PoemDao();
            boolean aa =poemDao.select(content,content,content,content,content);
            boolean bb =poemDao.select1(content,content,content,content);
            //aa == true ->(简化) aa
            if(aa || bb){
                android.util.Log.e("search","success");
                Toast.makeText(Search.this, "查询成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Search.this, com.example.myapplications.Activity.Log.class);
                Bundle bundle = new Bundle();
                bundle.putString("Content", content);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }else{
                Log.e("search","error");
                Toast.makeText(Search.this, "找不到与您输入相符的数据", Toast.LENGTH_LONG).show();
            }
            Looper.loop();//增加部分
        }).start();
        }
    }
