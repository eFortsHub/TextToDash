package com.efortshub.demo.timermovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.efortshub.demo.timermovie.databinding.ActivityMainBinding;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    int timeToPlay =30;
    int timeToShowHint = 0;
    private static final String TAG = "timetoshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // here is the movie name///
        String movieName = "I love coding good";

        timeToShowHint = getTimeToShowHint(timeToPlay, movieName);
        Log.d("timetoshow", "onCreate: time to show hint "+timeToShowHint);
        List<MovieHintPattern> movieHintPatternList = getMoviePatternList(movieName);


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

    private List<MovieHintPattern> getMoviePatternList(String movieName) {
        List<MovieHintPattern> list = new ArrayList<>();
        String[] wordsArray = movieName.split(" ");
        int wordCount = wordsArray.length;
        int charToShow = wordCount+2;


        for (int i=0;i<charToShow;i++){
            int randWord = new Random().nextInt(wordCount);
            String randWordStr = wordsArray[randWord];
            int randChar = new Random().nextInt(randWordStr.length());

            String randCharStr = String.valueOf(randWordStr.charAt(randChar));


            Log.d(TAG, "getMoviePatternList: rand word: "+randWord);
            Log.d(TAG, "getMoviePatternList: rand word string :"+randWordStr);
            Log.d(TAG, "getMoviePatternList: rand char :"+randChar);
            Log.d(TAG, "getMoviePatternList: rand char string :: "+randCharStr);




        }

        return list;
    }

    private int getTimeToShowHint(int timeToPlay, String movieName) {
        int i =0;
        String[] arr = movieName.split(" ");
        int words = arr.length;
        Log.d("timetoshow", "getTimeToShowHint: words count :"+words);
        int devidedBy =  words+2; //3 - 4
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
        int charToShow = words+2; //5
        Log.d(TAG, "getMovieNameHint: char to show: "+charToShow);

        int specialWordToShowTwoChar = new Random().nextInt(words); //3

        int specialWordToShowTwoCharTwo = new Random().nextInt(words); //3

        if (specialWordToShowTwoChar==specialWordToShowTwoCharTwo){

            if (specialWordToShowTwoCharTwo==words){
                --specialWordToShowTwoCharTwo;//3
            }else {
                ++specialWordToShowTwoCharTwo;//4
            }
        }
        Log.d(TAG, "getMovieNameHint: special word one :"+specialWordToShowTwoChar);
        Log.d(TAG, "getMovieNameHint: special word two :"+specialWordToShowTwoCharTwo);


        for (int i = 0; i<words; i++){
            String word = splits[i];
            char[] chars = word.toCharArray();
            int charOne=0, charTwo=-1;

            charOne = new Random().nextInt(chars.length);

            if (specialWordToShowTwoChar==i || specialWordToShowTwoCharTwo==i) {
                // show two character from this word
                charTwo = new Random().nextInt(chars.length);

                if (charOne == charTwo) {
                    if (charTwo == chars.length) {
                        --charTwo;//3
                    } else {
                        ++charTwo;//4
                    }

                }
            }

            for (int j=0; j<chars.length; j++){

                if (j==charOne || j== charTwo){
                    sb.append(chars[j]);
                }else sb.append("_");


            }

            sb.append(" ");


        }









/*

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
*/


        return sb.substring(0, sb.toString().length()-1);
    }
}