package com.example.xchen10_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private FeelingList feelingList = new FeelingList();

    private ListView oldFeelingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFromFile(FILENAME);
        oldFeelingsList = (ListView) findViewById(R.id.oldFeelingsList);
        ArrayAdapter<Feeling> adapter = new ArrayAdapter<Feeling>(this, R.layout.activity_main, feelingList);
        oldFeelingsList.setAdapter(adapter);
        Button addButton = (Button) findViewById(R.id.add);
        Button statsButton = (Button) findViewById(R.id.stats);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddFeelingActivity.class);
                startActivity(intent);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadFromFile(String filename) {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                String emotion;
                Date date;
                String[] stringsLine = line.split("[|]", 3);
                emotion = stringsLine[0];
                DateFormat format = new SimpleDateFormat("MMM d, yyyy HH:mm:ss aaa", Locale.ENGLISH);
                date = format.parse(stringsLine[1]);
                if (stringsLine.length >= 3) {
                    String comment = stringsLine[2];
                    this.feelingList.addFeeling(new Feeling(emotion, date, comment));
                } else {
                    this.feelingList.addFeeling(new Feeling(emotion, date));
                }
                line = in.readLine();
            }
        }
        catch (IOException | ParseException e) {

        }
    }
}
