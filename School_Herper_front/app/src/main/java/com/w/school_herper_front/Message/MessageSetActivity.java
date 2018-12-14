package com.w.school_herper_front.Message;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.w.school_herper_front.Authen.AuthenNoActivity;
import com.w.school_herper_front.ChangePassword.ChangePassword;
import com.w.school_herper_front.HomePage.HomeActivity;
import com.w.school_herper_front.MainActivity;
import com.w.school_herper_front.R;
import com.w.school_herper_front.SendDatesToServer;
import com.w.school_herper_front.bean.ReviseUser;

import java.io.File;

public class MessageSetActivity extends Activity {
    private int userId;
    private LinearLayout authen;
    private LinearLayout changepsd;
    private ImageView back1;
    private ImageView headphoto;
    private EditText username;
    private EditText names;
    private EditText autograph;
    private EditText sex;
    private EditText phone;
    private TextView authentication;

    /*
     * 姓名：赵璐
     * 日期：2018.12.14
     * 说明：多线程
     * */
    Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SendDatesToServer.SEND_SUCCESS:
                    Toast.makeText(MessageSetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    break;
                case SendDatesToServer.SEND_FAIL:
                    Toast.makeText(MessageSetActivity.this, "数据错误或没有获取回应", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


    public void handleReviseUser(ReviseUser reviseUser){

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_set);
        authen = findViewById(R.id.ln_authens);
        changepsd = findViewById(R.id.ln_changepsd);
        back1 = findViewById(R.id.back_1);
        /*
         * 姓名：赵璐
         * 日期：2018.12.14
         * 说明：根据id进行数据导入
         * */

        headphoto = findViewById(R.id.img_touxiang);
        username = findViewById(R.id.img_username);
        names = findViewById(R.id.img_names);
        autograph = findViewById(R.id.img_autograph);
        sex = findViewById(R.id.img_sex);
        phone = findViewById(R.id.img_phone);
        authentication = findViewById(R.id.img_authentication);

        Bundle extras = getIntent().getExtras();
        userId =  extras.getInt("userId");
        String ID  = userId + "";
        if(ID.equals("")){
            Toast.makeText(MessageSetActivity.this, "登录失效请重新登录", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MessageSetActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            ReviseUser reviseUser = new SetMessageToServer(handler).SetMessageToServer(ID);
            String photoUrl = reviseUser.getHeadPhoto();
            String path= Environment.getExternalStorageDirectory()+ File.separator+photoUrl;
            Bitmap bm = BitmapFactory.decodeFile(path);
            headphoto.setImageBitmap(bm);
            username.setText(reviseUser.getUsername());
            names.setText(reviseUser.getNames());
            autograph.setText(reviseUser.getAutograph());
            sex.setText(reviseUser.getSex());
            phone.setText(reviseUser.getPhone());
            authentication.setText(reviseUser.getAuthentication());
        }


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
        }
    }
}
