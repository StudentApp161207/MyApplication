package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    private boolean supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                    if(new SaveUser().teacher_loadData(getApplicationContext())){
                        startActivity(new Intent(MainActivity.this, MainMenu.class));
                        finish();
                    }else if(new SaveUser().Student_loadData(getApplicationContext())){
                        startActivity(new Intent(MainActivity.this, UserMainMenu.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(MainActivity.this, SelectType.class));
                        finish();
                    }

                }



        },SPLASH_TIME_OUT);
    }
}


