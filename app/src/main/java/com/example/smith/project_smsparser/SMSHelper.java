package com.example.smith.project_smsparser;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class SMSHelper extends AsyncQueryHandler {
    private static final Uri DB_URI = Uri.parse("content://sms/inbox");
    private OnScrapingCompleteListener listener;
    private static final String TAG = "SMSHelper";
    private ArrayList<String> messagesList;
    public SMSHelper(Context context){
        super(context.getContentResolver());
    }

    SMSHelper(Context context, String address,OnScrapingCompleteListener listener ) {
        super(context.getContentResolver());
        this.listener =listener;
        startQuery(1,null,DB_URI, null, "address=\""+address+"\"", null, null);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        //super.onQueryComplete(token, cookie, cursor);
        if (cursor != null) {
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                int count = 0;
                do {
                    String sms = cursor.getString(13);
                    //Log.d(TAG, "Index: " + (++count) + " Address: " + cursor.getString(2) + " Message: " + cursor.getString(13));
                    messagesList.add(sms);
                } while (cursor.moveToNext());
            } else {
                // empty box, no SMS
                Log.d(TAG, "onScrapingComplete: No SMS found");
            }
        }
        listener.onScrapingComplete(messagesList);
    }

    interface OnScrapingCompleteListener{
        void onScrapingComplete(ArrayList<String> messagesList);
    }
}
