package com.vapps.eggtimerr;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    SeekBar seekBar;
    TextView timerText;
    Button controlButton;
    Boolean timerActive=false;
    ImageView image;

    public void updateTimer(int progress1)
    {
        int minutes=(int) progress1/60;
        int seconds=(progress1-(minutes*60));


        timerText.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));

    }


    public void startStopTimer(View view)
    {
       timerActive = true;

        if (timerActive==true){

            seekBar.setEnabled(false);

        }
        timerActive = false;
       new CountDownTimer(seekBar.getProgress()*1000+100,1000){

           @Override
           public void onTick(long millisUntilFinished) {

               updateTimer((int) millisUntilFinished/1000);
               controlButton.setText("STOP!");


           }

           @Override
           public void onFinish() {

               MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
               mediaPlayer.start();


               timerText.setText("00:00");
               seekBar.setEnabled(true);


               controlButton.setAlpha(0f);
               timerText.setAlpha(0f);

               image.setImageResource(R.drawable.eggb);
               new CountDownTimer(2000,1000) {

                   public void onTick(long millisecondsUntilDone) {


                   }


                   public void onFinish()

                   {

                       image.setImageResource(R.drawable.egg);
                       timerText.setAlpha(1f);
                       controlButton.setAlpha(1f);
                       controlButton.setText("GO!");

                   }

               }.start();
           }

       }.start();


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         seekBar = (SeekBar)findViewById(R.id.seekBar);
         controlButton = (Button) findViewById(R.id.controller);
         timerText =(TextView) findViewById(R.id.timerText);
         image=(ImageView)findViewById(R.id.imageOf);


        seekBar.setMax(600);
        seekBar.setProgress(30);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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



    }
}
