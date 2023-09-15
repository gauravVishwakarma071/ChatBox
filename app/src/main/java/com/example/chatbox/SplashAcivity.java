package com.example.chatbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chatbox.model.UserModel;
import com.example.chatbox.utils.AndroidUtil;
import com.example.chatbox.utils.FirebaseUtil;

public class SplashAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_acivity);

        if(getIntent().getExtras()!=null){
            // from notification
            String userId = getIntent().getExtras().getString("userId");
            FirebaseUtil.allUserCollectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            UserModel model = task.getResult().toObject(UserModel.class);
                            try {


                                Intent mainIntent = new Intent(this, MainActivity.class);
                                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(mainIntent);

                                Intent intent = new Intent(this, ChatActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                AndroidUtil.passUserModelAsIntent(intent, model);
                                startActivity(intent);
                                finish();
                            }catch (Exception e){

                            }
                        }
                    });
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(FirebaseUtil.isLoggedIn()){
                        startActivity(new Intent(SplashAcivity.this,MainActivity.class));
                    }else{
                        startActivity(new Intent(SplashAcivity.this,LoginPhoneNumberActivity.class));
                    }

                    finish();
                }
            },4000);
        }

    }
}