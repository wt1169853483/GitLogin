package com.example.wangtao.day1_rikaologin.https;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wangtao on 2018/6/6.
 * 创建日期: on 2018/5/8.
 * 描述:
 * 作者:wangtao
 */
public class Https {
    public static String IntoString(InputStream inputStream){
        InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
        StringBuffer stringBuffer=new StringBuffer();
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String bty="";
        try {
             while ((bty = bufferedReader.readLine()) !=null){
                   stringBuffer.append(bty);
             }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  stringBuffer.toString();
    }
}
