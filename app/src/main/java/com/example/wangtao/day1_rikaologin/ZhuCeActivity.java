package com.example.wangtao.day1_rikaologin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangtao.day1_rikaologin.bean.UserBean;
import com.example.wangtao.day1_rikaologin.https.HttpUtil;
import com.google.gson.Gson;

public class ZhuCeActivity extends AppCompatActivity {
    private String url="http://120.27.23.105/user/reg";
    private EditText name;
    private EditText pwd;
    private Button zhuceBtn;
    private static final String TAG = "ZhuCeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        InitView();
    }

    private void InitView() {
           //获取组件
        name = findViewById(R.id.zc_name);
        pwd = findViewById(R.id.zc_pwd);
        zhuceBtn = findViewById(R.id.zc_zhuce);
        zhuceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String  names = name.getText().toString();
               String pwds = pwd.getText().toString();
                Log.d(TAG, "InitView: "+names);
                Log.d(TAG, "InitView: "+pwds);
                    String url1=url+"?mobile="+ names +"&password="+pwds;
                Log.d(TAG, "onClick: "+url1);
                HttpUtil httpUtile = HttpUtil.getHttpUtile();
                httpUtile.get(url1);
                httpUtile.getHttpListener(new HttpUtil.HttpListener() {
                    @Override
                    public void getDataSuccess(String json) {
                        Gson gson=new Gson();
                        UserBean userBean = gson.fromJson(json, UserBean.class);
                        Log.d(TAG, "getDataSuccess: ======================="+"你好");
                        String msg = userBean.getMsg();
                         if (msg.equals("注册成功")){
                             Toast.makeText(ZhuCeActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                             finish();
                         }else{
                             Toast.makeText(ZhuCeActivity.this,"用户已存在",Toast.LENGTH_LONG).show();
                         }
                    }

                    @Override
                    public void getDataError(String error) {

                    }
                });
            }
        });
    }
}
