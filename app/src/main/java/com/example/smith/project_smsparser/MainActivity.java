package com.example.smith.project_smsparser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.smith.project_smsparser.Cards.CITICard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSMS();
    }

    public void getSMS() {
        new SMSHelper(this, "VK-Citibk", new SMSHelper.OnScrapingCompleteListener() {
            @Override
            public void onScrapingComplete(ArrayList<String> messagesList) {
                CITICard card = new CITICard(messagesList);
                card.parseMessages(messagesList);
                TextView tv = findViewById(R.id.info);
                TextView dd = findViewById(R.id.due_date);
            }

        });
    }

}
