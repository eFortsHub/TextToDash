package com.efortshub.demo.timermovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.efortshub.demo.timermovie.databinding.ActivityMainBinding;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int timeToPlay =30;
    int timeToShowHint = 0;
    private static final String TAG = "hhhh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // here is the movie name///
        String movieName = "I love coding";

        timeToShowHint = getTimeToShowHint(timeToPlay, movieName);
        Log.d("timetoshow", "onCreate: time to show hint "+timeToShowHint);

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (timeToPlay>0){
                    runOnUiThread(() -> binding.counter.setText(timeToPlay+""));
                    timeToPlay--;
                }else{
                    runOnUiThread(() -> {
                        binding.counter.setText("finished");
                        binding.name.setText(movieName);
                    });
                    timer.cancel();
                }




                if (timeToPlay==timeToShowHint){
                    runOnUiThread(() -> {
                        String hint = getMovieNameHint(movieName);
                        binding.name.setText(hint);
                    });
                }



/*

                if (timeToPlay==50){
                    runOnUiThread(() -> {
                        String hint = getMovieNameHint(movieName, true);
                        binding.name.setText(hint);
                    });
                }
*/


            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.name.setLetterSpacing(0.3f);
        }

        String hint = getMovieNameDash(movieName);
        binding.name.setText(hint);


    }

    private int getTimeToShowHint(int timeToPlay, String movieName) {
        int i =0;
        String[] arr = movieName.split(" ");
        int words = arr.length;
        Log.d("timetoshow", "getTimeToShowHint: words count :"+words);
        int devidedBy =  words+2;
        Log.d("timetoshow", "getTimeToShowHint: time devided by : "+devidedBy);
        i = timeToPlay/devidedBy;
        int timeToshowHint = i;
        timeToshowHint = timeToPlay-i;

        return timeToshowHint;
    }


    private String getMovieNameDash(String movieName) {

        //this is the code to get dashed text for each string or movie name
         String[] splits = movieName.split(" ");
         StringBuilder sb = new StringBuilder();

         int words = splits.length;
        Log.d(TAG, "prepareMovieNameHint: words"+words);



        for (int i=0; i<words; i++){
            int randChar =0;
            char[] chars = splits[i].toCharArray();


            for (int j=0; j<chars.length; j++){
                Log.d(TAG, "prepareMovieNameHint: char for "+i+" is : "+chars[j]);

                sb.append("_");

            }
            Log.d(TAG, "prepareMovieNameHint: word loop: "+i);
            sb.append(" ");

        }


        return sb.substring(0, sb.toString().length()-1);
    }
    private String getMovieNameHint(String movieName) {

        //this is the code to get dashed text for each string or movie name
         String[] splits = movieName.split(" ");
         StringBuilder sb = new StringBuilder();

         int words = splits.length;
        Log.d(TAG, "prepareMovieNameHint: words"+words);


        //  I love coding
        int randWord = new Random().nextInt(words);
        int randWordTwo = -1;

        if (words>=4){
            randWordTwo = new Random().nextInt(words);
        }


        Log.d("timetoshow", "getMovieNameHint: randWord: "+randWord);

        for (int i=0; i<words; i++){
            int randChar =0;
            char[] chars = splits[i].toCharArray();

            if (randWord==i){
                 randChar = new Random().nextInt(chars.length);
                Log.d("timetoshow", "getMovieNameHint: randChar :"+randChar);
            }else if (randWordTwo!=-1&&randWordTwo==i){
                randChar = new Random().nextInt(chars.length);
                Log.d("timetoshow", "getMovieNameHint: randChar :"+randChar);
            }


            for (int j=0; j<chars.length; j++){
                Log.d(TAG, "prepareMovieNameHint: char for "+i+" is : "+chars[j]);
                if (j==randChar && i==randWord){
                        sb.append(chars[j]);
                }else if (j==randChar && i==randWordTwo) {
                    sb.append(chars[j]);
                }else sb.append("_");


            }
            Log.d(TAG, "prepareMovieNameHint: word loop: "+i);
            sb.append(" ");

        }


        return sb.substring(0, sb.toString().length()-1);
    }
}