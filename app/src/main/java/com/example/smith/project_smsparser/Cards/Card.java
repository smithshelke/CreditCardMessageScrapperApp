package com.example.smith.project_smsparser.Cards;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Card {
    private static final String TAG = "Card";
    private ArrayList<String> messageList;
    private String dueDate;
    private String totalAmount;
    private String minimumAmount;

    Card(ArrayList<String> messageList) {
        this.messageList = messageList;
    }

    ArrayList<String> getMessageList() {
        return messageList;
    }

    void setMessageList(ArrayList<String> messageList) {
        this.messageList = messageList;
    }

    public String getDueDate() {
        return dueDate;
    }

    void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMinimumAmount() {
        return minimumAmount;
    }

    void setMinimumAmount(String minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    void parseMessages(ArrayList<String> messageList){
        Log.d(TAG, "parseMessage: parsing card ...");

    }

    String getParseResult(String regex, String message){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(message);
        if (m.find()){
            Log.d(TAG, "getRegexStringIfFound: "+m.group(1));
            return m.group(1);
        }
        else{
            return null;
        }
    }
}

