package com.smm.newscoorer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public SharedPreferences sp;
    //跳过按钮
    private Button btnSkip;
    //判断程序是否是第一次运行
//    private static final String SHARE_IS_FIRST = "isFirst";
    private Handler handler = new Handler();
    private Runnable runnableToLogin = new Runnable() {
        @Override
        public void run() {
            toHomeActivity();
        }
//            boolean isFirst = sp.getBoolean("isFirst",true);
//            if(isFirst){
//                Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
//                startActivity(intent);
//                sp.edit().putBoolean("isFirst",false).apply();
//            }else {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//            finish();
//        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(runnableToLogin, 6000);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        btnSkip=findViewById(R.id.splash_btn_skip);
//        监听事件
        initClick();

    }

    /**
     * 判断是否是第一次然后跳转到主界面
     */
    @SuppressLint("CommitPrefEdits")
    private void toHomeActivity() {
//        第一次打开页面
         boolean isFirst = sp.getBoolean("isFirst",true);
        if(isFirst){
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            sp.edit().putBoolean("isFirst",false).apply();
        }else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
    private void initClick(){
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //防止LoginActivity被打开两次
                handler.removeCallbacks(runnableToLogin);
                toHomeActivity();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        handler.removeCallbacks(runnableToLogin);

    }
}