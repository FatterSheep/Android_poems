package com.example.myapplications.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplications.R;

public class Content extends AppCompatActivity {
    private Bundle bundle;
    private TextView neirong;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        initViews();
        bundle = this.getIntent().getExtras();
//        String Name = bundle.getString("Name");
//        String Title = bundle.getString("Title");
//        String JiaQin = bundle.getString("JiaQin");
        String Poem = bundle.getString("Poem");
        neirong.setText(Poem);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Content.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {
        neirong = findViewById(R.id.neirong);
        back = findViewById(R.id.back);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}