package com.example.myapplications.LoginRe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplications.R;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";//验证密码是否有特殊符号或长度不满6位

    EditText username = null;
    EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        Button button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得账号密码
                new Thread(() -> {
                    Looper.prepare();//增加部分
                    String number = username.getText().toString().trim();
                    String pa = password.getText().toString().trim();
                    if (number == null || "".equals(number) || pa == null || "".equals(pa)) {
                        Toast.makeText(Register.this, "账号与密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String name = username.getText().toString().trim();
                        boolean judge = isMobile(name);
                        String pass = password.getText().toString().trim();
                        boolean judge1 = isPassword(pass);

                        User user = new User();
                        UserDao userDao = new UserDao();
                        User uu = userDao.findUser(name);

                        if (uu != null){
                            Log.e("error","走不了toast");
                            Toast.makeText(Register.this, "手机号码已存在", Toast.LENGTH_SHORT).show();
                        }else{
                          if (judge == true && judge1 == true ) {

//                            User user = new User();
                            user.setName(name);
                            user.setPassword(pass);

//                            UserDao userDao = new UserDao();
                            userDao.register(user);

                            Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);//启动跳转
                            finish();
                        } else {
                            Toast.makeText(Register.this, "手机号码不法与密码不能有特殊符号", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();//增加部分
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * 校验密码
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    /**
     * 验证手机格式
     */

    public static boolean isMobile(String number) {
 /*
 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
 联通：130、131、132、152、155、156、185、186
 电信：133、153、180、189、（1349卫通）
 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
 */
        String num = "[1][3589]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }
}

/**
 * 弃用的方法
 */
//    public void register(View view){
//        String cname = username.getText().toString();
//        String cpassword = password.getText().toString();
//        if(cname.length() < 2 || cname.length() < 2 || cpassword.length() < 2 ){
//            Toast.makeText(getApplicationContext(),"输入信息不符合要求请重新输入",Toast.LENGTH_LONG).show();
//            return; }
//        User user = new User();
//        user.setName(cname);
//        user.setPassword(cpassword);
//        new Thread(){
//            @Override
//            public void run() {
//                int msg = 0;
//                UserDao userDao = new UserDao();
//                User uu = userDao.findUser(user.getName());
//                if(uu != null){
//                    msg = 1;
//                }
//                boolean flag = userDao.register(user);
//                if(flag){
//                    msg = 2;
//                }
//
//            }
//        }.start();
//
//    }