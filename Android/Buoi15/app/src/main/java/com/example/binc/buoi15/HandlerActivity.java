package com.example.binc.buoi15;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    private Handler mHandler;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = (TextView) findViewById(R.id.tvText);

//nhan thong diep
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTextView.setText(String.valueOf(msg.arg1));
            }
        };

        //send thong diep tu thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int t = 0;
                while (t < 100) {
                    Message msg = new Message();
                    msg.arg1 = t;
                    //gui lai ve main thread
                    mHandler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t++;
                }
            }
        });
        thread.start();
    }
}
