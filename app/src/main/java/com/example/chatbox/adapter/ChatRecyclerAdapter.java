package com.example.chatbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbox.ChatActivity;
import com.example.chatbox.R;
import com.example.chatbox.model.ChatMessageModel;
import com.example.chatbox.utils.AndroidUtil;
import com.example.chatbox.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatRecyclerAdapter.ChatModelViewHolder> {

    Context context;
    //get the context and assin to he this context.
    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
       if(model.getSenderId().equals(FirebaseUtil.currentUserId())){
           holder.leftChatLayout.setVisibility(View.GONE);
           holder.rightChatLayout.setVisibility(View.VISIBLE);
           holder.rightChatTextView.setText(model.getMessage());
       }else{
            holder.rightChatLayout.setVisibility(View.GONE);
            holder.leftChatLayout.setVisibility(View.VISIBLE);
            holder.leftChatTextView.setText(model.getMessage());

        }

    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);
        return new ChatModelViewHolder(view);//we pass the view to this tooo...
    }
    //to here....
    class ChatModelViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatLayout,rightChatLayout;
        TextView leftChatTextView,rightChatTextView;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_view_layout);
            rightChatLayout = itemView.findViewById(R.id.right_chat_view_layout);
            leftChatTextView = itemView.findViewById(R.id.left_chat_text_view);
            rightChatTextView = itemView.findViewById(R.id.right_chat_text_view);

        }
    }
}
