package com.w.school_herper_front.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.w.school_herper_front.R;

public class WalletWithDrawActivity extends AppCompatActivity {
    private ImageView back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_with_draw);
        back1 = findViewById(R.id.back_wallet2);


        /*
         * 姓名：赵璐
         * 日期：2018.12.12
         * 说明：基本的页面跳转
         * */
        back1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",1);
                setResult(3,intent);
                finish();
            }
        });
    }
}
