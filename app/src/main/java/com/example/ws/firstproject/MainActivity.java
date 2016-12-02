package com.example.ws.firstproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import static com.example.ws.firstproject.R.id.edt_order;

public class MainActivity extends AppCompatActivity {
    TextView tv_show_order; //显示订单
    EditText edt_order; //编辑订单号
    ImageView iv_code; //显示生成的二维码图片


    boolean haveData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean haveData = sp.getBoolean("haveData", false);
        if(!haveData){

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
            case R.id.btn_search:
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
                Toast.makeText(getApplicationContext(), "解析成功！+结果为"+string,Toast.LENGTH_LONG).show();
            }else if(extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                Toast.makeText(getApplicationContext(),"解析失败！",Toast.LENGTH_LONG).show();
            }
        }
    }
}
