package com.example.chatbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //New Attempt To Create ChatBox
    BottomNavigationView bottomNavigationView;
    ImageButton searchButton;

    ChatFragment chatFragment;
    ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Toast.makeText(this, "Still in development phase", Toast.LENGTH_SHORT).show();

        chatFragment = new ChatFragment();
        profileFragment = new ProfileFragment();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        searchButton = findViewById(R.id.main_search_btn);


        //Search Button Intent...
        searchButton.setOnClickListener(v -> {
        startActivity(new Intent(MainActivity.this,SearchUserActivity.class));
        });


        //Botom Navigaton View...
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_chat){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,chatFragment).commit();
                    Toast.makeText(MainActivity.this, "Your chats", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.menu_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFragment).commit();
                    Toast.makeText(MainActivity.this, "Your profile", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.menu_chat);
    }
}