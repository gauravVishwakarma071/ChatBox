package com.example.chatbox.model;

import com.google.firebase.Timestamp;

import java.util.List;

public class ChatroomModel {

    //XIII
    String chatroomId;
    List<String> userId;
    Timestamp lastMessageTimestamp;
    String lastMessageSenderId;
    String lastMessage;

    public ChatroomModel() {
    }

    public ChatroomModel(String chatroomId, List<String> userId, Timestamp lastMessageTimestamp, String lastMessageSenderId) {
        this.chatroomId = chatroomId;
        this.userId = userId;
        this.lastMessageTimestamp = lastMessageTimestamp;
        this.lastMessageSenderId = lastMessageSenderId;
    }


//    public ChatroomModel(String chatroomId, List<String> userId, Timestamp lastMessageTimestamp, String lastMessageSenderId, String lastMessage) {
//        this.chatroomId = chatroomId;
//        this.userId = userId;
//        this.lastMessageTimestamp = lastMessageTimestamp;
//        this.lastMessageSenderId = lastMessageSenderId;
//        this.lastMessage = lastMessage;
//    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public com.google.firebase.Timestamp getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(com.google.firebase.Timestamp lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }

    public String getLastMessageSenderId() {
        return lastMessageSenderId;
    }

    public void setLastMessageSenderId(String lastMessageSenderId) {
        this.lastMessageSenderId = lastMessageSenderId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
