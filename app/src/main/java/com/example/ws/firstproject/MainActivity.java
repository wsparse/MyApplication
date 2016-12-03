package com.example.ws.firstproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.ws.firstproject.R.id.edt_order;

public class MainActivity extends AppCompatActivity {
    TextView tv_show_order; //显示订单
    EditText edt_order; //编辑订单号
    ImageView iv_code; //显示生成的二维码图片


    boolean haveData = false;
    DBDos dbDos ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbDos  = new DBDos(this);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean haveData = sp.getBoolean("haveData", false);
        if(!haveData){
            //第一条OrderEntity实体类对象的创建
            List<OrderEntity.Detail> detailList1 = new ArrayList<>();
            OrderEntity.Detail detail1 = new OrderEntity.Detail("20161201","1001","微观经济学",null,null,"本","2","56$",null,null,null,null,null,null,null,"书本");
            OrderEntity.Detail detail2 = new OrderEntity.Detail("20161201","1002","宏观经济学",null,null,"本","1",null,null,null,null,null,null,null,null,"书本");
            detailList1.add(detail1);
            detailList1.add(detail2);
            OrderEntity orderEntity1 = new OrderEntity("20161201","20161201","苏宁","110","国美",null,null,null,detailList1);
            dbDos.insertOrderEntity(orderEntity1);
            Log.e("ws","加入了第一个实体类对象");

            //第二条OrderEntity实体类对象的创建
            List<OrderEntity.Detail> detailList2 = new ArrayList<>();
            OrderEntity.Detail detail3 = new OrderEntity.Detail("20161202","1003","坏蛋是怎样炼成的",null,null,"本","2","26$",null,null,null,null,null,null,null,"书本");
            OrderEntity.Detail detail4 = new OrderEntity.Detail("20161202","1004","学校霸王",null,null,"本","1",null,null,null,null,null,null,null,null,"书本");
            detailList2.add(detail3);
            detailList2.add(detail4);
            OrderEntity orderEntity2 = new OrderEntity("20161202","20161202","苏宁","110","京东",null,null,null,detailList2);
            dbDos.insertOrderEntity(orderEntity2);
            Log.e("ws","加入了第二个实体类对象");

            //第三条OrderEntity实体类对象的创建
            List<OrderEntity.Detail> detailList3 = new ArrayList<>();
            OrderEntity.Detail detai5 = new OrderEntity.Detail("20161203","1005","卫衣","S",null,null,null,null,null,null,null,null,null,null,null,null);
            OrderEntity.Detail detai6 = new OrderEntity.Detail("20161203","1006","鞋子","S",null,null,null,null,null,null,null,null,null,null,null,null);
            detailList3.add(detai5);
            detailList3.add(detai6);
            OrderEntity orderEntity3 = new OrderEntity("20161203","20161203","苏宁","100","淘宝",null,null,null,detailList3);
            dbDos.insertOrderEntity(orderEntity3);
            Log.e("ws","加入了第三个实体类对象");

            SharedPreferences sps = getSharedPreferences("config",Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sps.edit();
            edit.putBoolean("haveData",true);
            edit.commit();
            Log.e("ws","提交添加成功数据成功的布尔值！");
        }
    }

    private void initView() {
        tv_show_order = (TextView) findViewById(R.id.tv_show_order);
        edt_order = (EditText) findViewById(R.id.edt_order);
        iv_code = (ImageView) findViewById(R.id.iv_code);
    }

    public int  reduce(int a ,int b){
        return  a-b-1;
    }

    public void myClick(View view){
        switch (view.getId()){
            case R.id.btn_search: //查询订单生成订单列表
                List<OrderEntity> entityList = dbDos.queryAll();
                StringBuffer buffer = new StringBuffer("myBuffer");
                buffer.append("查询得到的订单列表为："+"\n");
                for(OrderEntity orderEntity:entityList){
                   buffer.append(orderEntity.order_num+"\n");
                }
                tv_show_order.setText(buffer.toString());
                break;
            case R.id.btn_create://生成二维码图片
                String infor = edt_order.getText().toString();
                Bitmap image = CodeUtils.createImage(infor, 400, 400, null);
                iv_code.setImageBitmap(image);
                break;
            case R.id.btn_parse: //扫码解析二维码图片
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,1);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            Bundle extras = data.getExtras();
            if(extras == null){
                return;
            }
            if(extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                String string = extras.getString(CodeUtils.RESULT_STRING);
                for(OrderEntity orderEntity : dbDos.queryAll()){
                    if(orderEntity.order_num.equals(string)){
                        Toast.makeText(getApplicationContext(), "解析成功！+结果为"+orderEntity.toString(),Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                Toast.makeText(getApplicationContext(), "解析成功！+结果为"+string,Toast.LENGTH_LONG).show();
            }else if(extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                Toast.makeText(getApplicationContext(),"解析失败！",Toast.LENGTH_LONG).show();
            }
        }
    }
}
