package com.w.school_herper_front.Talk;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.w.school_herper_front.R;

public class MessageTalkActivity extends Activity {
    EditText message;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_talk);
        message = findViewById(R.id.et_message);
    }
}
