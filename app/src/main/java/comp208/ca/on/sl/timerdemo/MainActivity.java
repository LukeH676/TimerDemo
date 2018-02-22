package comp208.ca.on.sl.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    SeekBar timeSeekbar;
    TextView timerText;

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60;
        int seconds =  secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (secondString.equals("0")){
            secondString = "00";
        }
        timerText.setText(Integer.toString(minutes)+ ":"+secondString);

    }

    public void controlTimer(View view){
        Log.i("button Pressed","Pressed");
        new CountDownTimer(timeSeekbar.getProgress()* 1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
             updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
            Log.i("finished","done");
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timeSeekbar = (SeekBar) findViewById(R.id.timerBar);
        timerText = (TextView) findViewById(R.id.time);
        timeSeekbar.setMax(600);
        timeSeekbar.setProgress(30);
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
