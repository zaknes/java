package com.zaknesler.lyricsperformancetask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public TextView output;

    public String current = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);

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
        current = "Row row row your boat " +
                "\n\nGently down the stream " +
                "\n\nMerrily merrily merrily merrily " +
                "\n\nLife is but a dream";

        output.setText(current);
    }

    public void onButtonClick(View view)
    {
        if (current.split("\\w+").length == 0) {
            return;
        }

        String next = current.substring(0, current.lastIndexOf(' ', current.length()));

        output.setText(next + "\n\n\n\n" + output.getText().toString());

        current = next;
    }
}
