package comp208.ca.on.sl.timerdemo;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar timeSeekbar;
    TextView timerText;
    Button timerBtn;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerText.setText("0:30");
        timeSeekbar.setProgress(30);
        countDownTimer.cancel();
        timerBtn.setText("Go!");
        timeSeekbar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60;
        int seconds =  secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

     if (seconds <= 9){
            secondString = "0" + secondString;
        }
        timerText.setText(Integer.toString(minutes)+ ":"+secondString);

    }

    public void controlTimer(View view){

        if(counterIsActive == false) {
            Log.i("button Pressed","Pressed");
            counterIsActive = true;
            timeSeekbar.setEnabled(false);
            timerBtn.setText("Stop");
            countDownTimer = new CountDownTimer(timeSeekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    timerText.setText("0:00");
                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    Log.i("finished", "done");
                }
            }.start();
        }else {
        resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timeSeekbar = (SeekBar) findViewById(R.id.timerBar);
        timerText = (TextView) findViewById(R.id.time);
        timeSeekbar.setMax(600);
        timeSeekbar.setProgress(30);
        timerBtn = (Button) findViewById(R.id.timerBtn);
        timeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
       /* new CountDownTimer(10000,1000){
            public void onTick(long milTillDone){

                //Happens when countdown counts down every 1 second in this case
                Log.i("Seconds Left", String.valueOf(milTillDone /1000));

            }
            public void onFinish(){
                // happens when countdown finishes
                Log.i("DONE","Countdown finished");

            }
        }.start();*/

       /* final Handler handler = new Handler();
        // allows chunks of code to be run in a delayed way - every X seconds
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // in here use code that will be run every Second
                Log.i("Runnable is run","A second must have passed");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(run);
        // this executes the runnable at the start - basically like saying START*/


    }
}
