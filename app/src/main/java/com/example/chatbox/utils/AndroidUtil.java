package com.example.chatbox.utils;

import android.content.Context;
import android.widget.Toast;
// created for toast
public class AndroidUtil {

   public static void showToast(Context context,String message){
       Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
