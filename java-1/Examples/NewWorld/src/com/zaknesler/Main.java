package com.zaknesler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        // Unix epoch starts on 1/1/1970. Time is in seconds since that date.
        // A Date object also contains time information.
        Date now = new Date();

        // Second, create a formatter object
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy HH:mm:ss");

        // Third, apply the formatter to the date
        String formattedDate = formatter.format(now);

        // Finally, add our formatted date to our output
        System.out.println(formattedDate + "> Hello, " + args[0] + "!");
    }
}
