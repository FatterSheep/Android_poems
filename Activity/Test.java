package com.example.myapplications.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.R;

public class Test extends AppCompatActivity {
    private Bundle bundle;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        bundle = this.getIntent().getExtras();
        String Content = bundle.getString("Content");
        textView = findViewById(R.id.text);
        textView.setText(Content);
    }
}