package com.example.arjun.badwordfilterkata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    BadWordFilter badWordFilter;
    ArrayList<String> badwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText enterText = (EditText) findViewById(R.id.etEnterText);
        Button buttonCensor = (Button) findViewById(R.id.btnCensorButton);
        final TextView textViewCensoredText = (TextView) findViewById(R.id.tvCensoredText);
        badWordFilter = new BadWordFilter();

        InputStream stream = null;

        try {

            stream = getAssets().open("badwords.txt");
        } catch (IOException e) {

            e.printStackTrace();
        }

        if (stream != null) {

            badwords = badWordFilter.readInFile(stream);
        }


        buttonCensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredText = enterText.getText().toString();
                BadWordFilter badWordFilter = new BadWordFilter();

                String sentence = badWordFilter.censorSentence(enteredText, badwords);
                textViewCensoredText.setText(sentence);
                enterText.getText().clear();

            }
        });
    }
}
