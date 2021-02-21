package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.navigation.NavigationView;

public class UserMainMenu extends AppCompatActivity  {
    private  DrawerLayout drawerLayout;
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        drawerLayout=findViewById(R.id.nav_view);
        Toolbar toolbar=findViewById(R.id.toolbar);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserMainMenu.this,ViewTimeTable.class);
                startActivity(intent1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(UserMainMenu.this,Coursepage.class);
                startActivity(intent2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(UserMainMenu.this,Calendar.class);
                startActivity(intent2);
            }
        });





    }


    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }


    public void logout(MenuItem item) {
        new SaveUser().Student_saveData(getApplicationContext(),false);
        Intent intent=new Intent(UserMainMenu.this,SelectType.class);
        startActivity(intent);
        finish();
    }
}


