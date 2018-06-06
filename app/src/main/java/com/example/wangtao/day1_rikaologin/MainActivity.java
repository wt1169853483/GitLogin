package com.example.wangtao.day1_rikaologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangtao.day1_rikaologin.bean.UserBean;
import com.example.wangtao.day1_rikaologin.bean.UserBeanLogin;
import com.example.wangtao.day1_rikaologin.https.HttpUtil;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   //mobile=15635472530&password=123456789
    private String url="http://120.27.23.105/user/login";
    private EditText name;
    private EditText pwd;
    private Button loginBtn;
    private Button zhuceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
    }

    private void InitView() {
           //获取组件
        name = findViewById(R.id.et_name);
        pwd = findViewById(R.id.et_pwd);
        loginBtn = findViewById(R.id.btn_login);
        zhuceBtn = findViewById(R.id.btn_zhuce);
        loginBtn.setOnClickListener(this);
        zhuceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btn_login:
                String  tv_name = name.getText().toString();
                 String tv_pwd = pwd.getText().toString();
                  String url1=url+"?mobile="+ tv_name +"&password="+tv_pwd;
                  HttpUtil httpUtile = HttpUtil.getHttpUtile();
                  httpUtile.get(url1);
                  httpUtile.getHttpListener(new HttpUtil.HttpListener() {
                      @Override
                      public void getDataSuccess(String json) {
                          Gson gson = new Gson();
                          UserBeanLogin userBeanLogin = gson.fromJson(json, UserBeanLogin.class);
                          String msg = userBeanLogin.getMsg();
                          if (msg.equals("登录成功")) {
                              Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                          } else {
                              Toast.makeText(MainActivity.this, "用户已存在", Toast.LENGTH_LONG).show();
                          }
                      }

                      @Override
                      public void getDataError(String error) {

                      }
                  });
                  break;

              case R.id.btn_zhuce:
                  Intent intent=new Intent(MainActivity.this,ZhuCeActivity.class);
                  startActivity(intent);
                  break;
          }
    }
}
