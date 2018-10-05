package com.example.xchen10_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class AddFeelingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String FILENAME = "file.sav";
    private Spinner chooseEmotionSpinner;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_feeling);

        comment = (EditText) findViewById(R.id.comment);

        chooseEmotionSpinner = findViewById(R.id.chooseEmotionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.emotions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseEmotionSpinner.setAdapter(adapter);
        chooseEmotionSpinner.setOnItemSelectedListener(this);

        Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String emotion = chooseEmotionSpinner.getSelectedItem().toString();
                String commentText = comment.getText().toString();
                Date date = new Date();
                boolean c = false;
                Feeling feeling;
                if (commentText == "") {
                    feeling = new Feeling(emotion, date);
                }
                else {
                    feeling = new Feeling(emotion, date, commentText);
                }
                saveInFile(feeling, c);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void saveInFile(Feeling feeling, boolean c) {
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(FILENAME, true));
            if (c) {
                Toast.makeText(AddFeelingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                file.append((String)(feeling.getEmotion() + " | " + feeling.getDate() + " | " + feeling.getComment()));
            }
            else {
                Toast.makeText(AddFeelingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                file.append((String)(feeling.getEmotion() + " | " + feeling.getDate()));
            }
            file.close();
        }
        catch(IOException e){}
    }
}
