package com.example.smith.project_smsparser.Cards;

import android.util.Log;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CITICard extends Card {
    private static final String TAG = "CITICard";

    public CITICard(ArrayList<String> messageList) {
        super(messageList);
    }


    /*
     * Reminder: Payment for card******4000 is due on 03-JAN-19. Total=Rs.5875.52,Minimum=Rs.610.80.Pay early to avoid charges.Please ignore if paid.
     * */

    @Override
    public void parseMessages(ArrayList<String> smsList) {
        super.parseMessages(smsList);
        String regexDueDate = "due on (.*?). ";
        String regexTotalAmnt = "Total=Rs.(.*?),";
        String regexMinimumAmnt = "Minimum=Rs.(.*?).P";
        for(String message : smsList){
            //Log.d(TAG, "parseMessages: message"+message);
            if(getDueDate()!=null){
                break;
            }
            setDueDate( getParseResult(regexDueDate,message));
            setTotalAmount( getParseResult(regexTotalAmnt,message));
            setMinimumAmount(getParseResult(regexMinimumAmnt,message));
        }
        super.parseMessages(smsList);
    }
}
