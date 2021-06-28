package com.example.scheduler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


// 메인 月달력과 함께 햄버거 등 툴바가 표시되는 페이지

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavBar();
    }

    public void NavBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //Toast.makeText(this, "현재 페이지입니다.", Toast.LENGTH_SHORT).show();
        if (id == R.id.month) {
            Toast.makeText(this, "월간 달력 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 월간 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.week) {
            Intent intent = new Intent(MainActivity.this, Week.class);
            startActivity(intent);
            Toast.makeText(this, "주간 달력 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 주간 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.todo) {
            Intent intent = new Intent(MainActivity.this, Todo.class);
            startActivity(intent);
            Toast.makeText(this, "할일 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 할일 클릭했을 때 나오는 텍스트
        }
        else if (id == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, Setting.class);
            startActivity(intent);
            Toast.makeText(this, "설정 페이지 입니다.", Toast.LENGTH_SHORT).show();
            // 햄버거에서 세팅 클릭했을 때 나오는 텍스트
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



