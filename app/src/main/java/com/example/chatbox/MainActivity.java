package com.example.chatbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatbox.utils.FirebaseUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    //New Attempt To Create ChatBox
    BottomNavigationView bottomNavigationView;
    ImageButton searchButton;


    ChatRecentFrag recentChatFrag;
    ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //Toast.makeText(this, "Still in development phase", Toast.LENGTH_SHORT).show();


        recentChatFrag = new ChatRecentFrag();
        profileFragment = new ProfileFragment();

        //Finding ID of bottom navigation view and search button.
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,recentChatFrag).commit();
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

        getFCMToken();
    }

    //firebase notification
    void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               String token = task.getResult();
               FirebaseUtil.currentUserDetails().update("fcmToken",token);
           }
        });
    }
}