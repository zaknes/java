package com.zaknesler.lyricsperformancetask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public TextView display;

    public String lyrics = "Row row row your boat " +
            "\nGently down the stream " +
            "\nMerrily merrily merrily merrily " +
            "\nLife is but a dream";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        reset();

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    private void reset()
    {
        display.setText(lyrics);
    }

    public void onButtonClick(View view)
    {
        String current = lyrics;
        String output = current + "\n\n";

        int index = lyrics.lastIndexOf(' ');

        while (index != -1) {
            output += lyrics.substring(0, index) + "\n\n";

            current = current.substring(0, index);

            index = current.lastIndexOf(' ');
        }

        display.setText(output);
    }
}
