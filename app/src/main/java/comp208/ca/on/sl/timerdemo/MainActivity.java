package comp208.ca.on.sl.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(10000,1000){
            public void onTick(long milTillDone){

                //Happens when countdown counts down every 1 second in this case
                Log.i("Seconds Left", String.valueOf(milTillDone /1000));

            }
            public void onFinish(){
                // happens when countdown finishes
                Log.i("DONE","Countdown finished");

            }
        }.start();

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
