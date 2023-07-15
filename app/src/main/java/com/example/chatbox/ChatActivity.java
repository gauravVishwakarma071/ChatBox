package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chatbox.model.UserModel;
import com.example.chatbox.utils.AndroidUtil;

public class ChatActivity extends AppCompatActivity {

    UserModel otherUser;
    EditText messageInput;
    ImageButton sendMessageBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        
        //get UserModel
        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());

        messageInput = findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backBtn.setOnClickListener(v -> onBackPressed());

        otherUsername.setText(otherUser.getUsername());
    }
}