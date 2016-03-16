package com.example.kevin.mapapplication.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.mapapplication.R;

public class LoginActivity extends AppCompatActivity {


    private TextView title;
    private RelativeLayout login_panel;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in_slow);
        title = (TextView) findViewById(R.id.login_title);
        login_panel = (RelativeLayout) findViewById(R.id.login_panel);
        title.startAnimation(fadein);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Animation slideup = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.slide_up);
            slideup.setFillAfter(true);
            title.startAnimation(slideup);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1100);
                        handler2.sendMessage(new Message());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Animation fadeinF = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in_fast);
            fadeinF.setFillAfter(true);
            login_panel.startAnimation(fadeinF);
        }
    };
}
