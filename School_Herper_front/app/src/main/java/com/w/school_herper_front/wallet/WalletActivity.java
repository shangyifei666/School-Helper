package com.w.school_herper_front.wallet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.w.school_herper_front.HomePage.HomeActivity;
import com.w.school_herper_front.R;

public class WalletActivity extends AppCompatActivity {

    private ImageView back1;
    private LinearLayout deposit;
    private LinearLayout withdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        back1 = findViewById(R.id.wallet_image_goback);
        deposit = findViewById(R.id.deposit);
        withdraw = findViewById(R.id.with_draw);

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
                setResult(4,intent);
                finish();
            }
        });
        deposit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this,WalletDepositActivity.class);
                startActivity(intent);
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this, WalletWithDrawActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 4){
        }
    }
}
