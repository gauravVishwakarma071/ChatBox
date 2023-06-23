package com.example.chatbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    CountryCodePicker countryCodePiker;
    EditText phoneInput;
    Button sendOtpBtn;
    ProgressBar progreeBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

         countryCodePiker = findViewById(R.id.login_country_code);
            phoneInput = findViewById(R.id.login_mobile_number);
            sendOtpBtn = findViewById(R.id.send_otp_btn);
            progreeBar = findViewById(R.id.login_progress_bar);

            progreeBar.setVisibility(View.GONE);

            countryCodePiker.registerCarrierNumberEditText(phoneInput);
            sendOtpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!countryCodePiker.isValidFullNumber()){
                        phoneInput.setError("Phone number is not valid");
                        return;
                    }
                    Intent intent = new Intent(LoginPhoneNumberActivity.this,LoginOtpActivity.class);
                    intent.putExtra("phone",countryCodePiker.getFullNumberWithPlus());
                    startActivity(intent);
                }
            });
        }

}
