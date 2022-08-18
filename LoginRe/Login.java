package com.example.myapplications.LoginRe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.Activity.MainActivity;
import com.example.myapplications.R;

public class Login extends AppCompatActivity  {
    private EditText editText,editText2;
    private Button button,button2;

    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";//验证密码是否有特殊符号或长度不满6位



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editText = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);
        Button  button = findViewById(R.id.button);
        Button  button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                Looper.prepare();//增加部分
                String name1 = editText.getText().toString().trim();
                String pass1 = editText2.getText().toString().trim();
//                User user = new User();
//                user.setName(name1);
//                user.setPassword(pass1);

                UserDao userDao = new UserDao();
                boolean aa =userDao.login(name1,pass1);
                //aa == true ->(简化) aa
                    if(aa){
                        Log.e("login","success");
                        Toast.makeText(Login.this, "登录成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Name", name1);
//                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }else{
                        Log.e("login","error");
                        Toast.makeText(Login.this, "登录失败", Toast.LENGTH_LONG).show();
                    }
                    Looper.loop();//增加部分
                }).start();
            }
//                    else if (msg.what == 2){
//                        Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
//                    } else if (msg.what == 3){
//                        Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_LONG).show();
//                    }
                    //else if(name1.equals("13530235231")&&pass1.equals("lyy123456")){
//                        Intent m = new Intent(Login.this, MainActivity2.class);
//                        startActivity(m);
                });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Login.this,Register.class);
                startActivity(intent1);
            }
        });
    }
}
