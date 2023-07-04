package com.example.chatbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.chatbox.model.UserModel;
import com.example.chatbox.utils.FirebaseUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class LoginUserNameActivity extends AppCompatActivity {


    EditText usernameInput;
    Button letmeInBtn;
    ProgressBar progressBar;
    String phoneNumber; //VIII
    UserModel userModel;//VIII

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_name);

        usernameInput = findViewById(R.id.login_username);
        letmeInBtn = findViewById(R.id.login_let_me_in_btn);
        progressBar = findViewById(R.id.login_progress_bar);

        //to get user phone number as input VII.
        phoneNumber = getIntent().getExtras().getString("phone");
        getUsername();

        letmeInBtn.setOnClickListener(v -> {
            setUsername();
        });

    }

    //method for set username VII.
    void setUsername(){
        String username = usernameInput.getText().toString();
        if(username.isEmpty() || username.length()<3){
            usernameInput.setError("Username length should be at least 3 chars");
            return;
        }
        setInProgress(true);
        //Checking data in usermodel ,there is anything or not VII.
        if (userModel != null) {
            userModel.setUsername(username);
        }else{
            userModel = new UserModel(phoneNumber,username, Timestamp.now());
        }
        // settinng the data to firebase store VII.
        FirebaseUtil.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                setInProgress(false);
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginUserNameActivity.this,MainActivity.class); //after login move to mainActivity VII.
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);// it generate new task and clear all task,we are doing this because ew want to clear all the task  VII.
                    startActivity(intent);
                }
            }
        });
    }


    //getting username gttt4.
    void getUsername(){
        setInProgress(true);
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                setInProgress(false);
                if(task.isSuccessful()){             //if task is succesful
                     userModel = task.getResult().toObject(UserModel.class);  //it will get some value over there it will convert the data into user.class.
                    if(userModel != null){ //if usermodel not mt then it will add username input.
                        usernameInput.setText(userModel.getUsername());

                    }
                }
            }
        });
    }

    //Progress bar Visibility after login has completed.
    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            letmeInBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            letmeInBtn.setVisibility(View.VISIBLE);
        }
    }
}