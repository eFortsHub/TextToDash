package com.efortshub.demo.timermovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.efortshub.demo.timermovie.databinding.ActivityMainBinding;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int i =60;
    private static final String TAG = "hhhh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (i>0){
                    runOnUiThread(() -> binding.counter.setText(i+""));
                    i--;
                }else{
                    runOnUiThread(() -> binding.counter.setText("finished"));
                    timer.cancel();
                }

            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.name.setLetterSpacing(0.3f);
        }


        // here is the movie name///


        String movieName = "I love Coding";


        prepareMovieNameHint(movieName);


    }

    private void prepareMovieNameHint(String movieName) {
        //this is the code to get dashed text for each string or movie name
         String[] splits = movieName.split(" ");
         StringBuilder sb = new StringBuilder();

         int words = splits.length;
        Log.d(TAG, "prepareMovieNameHint: words"+words);
        for (int i=0; i<words; i++){

            for (char c: splits[i].toCharArray()){
                Log.d(TAG, "prepareMovieNameHint: char for "+i+" is : "+c);
                sb.append("_");
            }

            Log.d(TAG, "prepareMovieNameHint: word loop: "+i);
            sb.append(" ");

        }


        binding.name.setText(sb.toString().substring(0, sb.toString().length()-1));




    }
}