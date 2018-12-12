package com.w.school_herper_front.Message;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.w.school_herper_front.Authen.AuthenNoActivity;
import com.w.school_herper_front.ChangeKeyActivity;
import com.w.school_herper_front.ChangePassword.ChangePassword;
import com.w.school_herper_front.ForgotKeyActivity;
import com.w.school_herper_front.HomePage.HomeActivity;
import com.w.school_herper_front.MainActivity;
import com.w.school_herper_front.R;

public class MessageSetActivity extends Activity {
    private LinearLayout authen;
    private LinearLayout changepsd;
    private ImageView back1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_set);
        authen = findViewById(R.id.ln_authens);
        changepsd = findViewById(R.id.ln_changepsd);
        back1 = findViewById(R.id.back_1);

        /*
         * 姓名：赵璐
         * 日期：2018.12.12
         * 说明：基本的页面跳转
         * */
        //返回个人主页
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",1);
                setResult(4,intent);
                finish();
            }
        });

        //跳转到认证页面
        authen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageSetActivity.this,AuthenNoActivity.class);
                startActivity(intent);
            }
        });

        //跳转到修改密码
        changepsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageSetActivity.this,ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}
