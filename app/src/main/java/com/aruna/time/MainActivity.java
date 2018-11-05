package com.aruna.time;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView date, tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date();
    }

    private void date() {
        Date d = new Date();

        Calendar calendar = Calendar.getInstance();
        //   calendar.setTime(d);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        date = (TextView) findViewById(R.id.tv_clock);
        tv_time = (TextView) findViewById(R.id.tv_time);

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                Date d = new Date();
                                DateFormat df = DateFormat.getInstance();

                                /*Calendar calendar = Calendar.getInstance();
                                calendar.setTime(d);
                                Integer hours = calendar.get(Calendar.HOUR_OF_DAY);
                                Integer minutes = calendar.get(Calendar.MINUTE);
                                int seconds = calendar.get(Calendar.SECOND);*/

//                                String currentTime = ":" + seconds;

                                date.setText(df.format(d).toString() );

                                time();

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    private void time() {
        Date d = new Date();
        DateFormat df = DateFormat.getDateInstance();


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        Integer hours = calendar.get(Calendar.HOUR_OF_DAY);
        Integer minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String currentTime = hours + " : " + minutes + " : " + seconds;

        tv_time.setText(currentTime);


    }
}
