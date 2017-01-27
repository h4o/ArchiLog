package com.al.music.musiccity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.al.music.library.*;
import android.support.v7.app.AppCompatActivity;

public class Read extends AppCompatActivity {
    private Toolbar toolbar;


    private static final int  MY_PERMISSIONS_REQUEST = 1 ;
    private SimpleLocation mLocation;

    /*  partie music */
    static MediaPlayer mPlayer;
    Button buttonPlay;
    Button buttonStop;

    String urlRecuperer;

    private TextView tx1,sourceText ;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
    private Handler myHandler = new Handler();
    private CheckBox zone1,zone2;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        addListenerOnChkIos();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tx1 = (TextView) findViewById(R.id.textView2);
        /********************** permisssion de geolocalisation    ************************************/

        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        // Assume thisActivity is the current activity
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        // construct a new instance
        mLocation = new SimpleLocation(this);

        // reduce the precision to 5,000m for privacy reasons
        mLocation.setBlurRadius(5000);



        // if we can't access the music yet
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable music access
            SimpleLocation.openSettings(this);
        }
        ((TextView) findViewById(R.id.latitude)).setText(String.valueOf(mLocation.getLatitude()));
        ((TextView) findViewById(R.id.longitude)).setText(String.valueOf(mLocation.getLongitude()));


        urlRecuperer = "http://al-music-streamer.herokuapp.com/streamer?latitude=" + String.valueOf(mLocation.getLatitude()) + "&longitude=" + String.valueOf(mLocation.getLongitude());

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setClickable(true);
        buttonPlay = (Button) findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                if(mPlayer == null)
                    mPlayer = new MediaPlayer();
                if(mPlayer.isPlaying()) {
                    mPlayer.stop();
                    mPlayer.reset();
                    //mPlayer = new MediaPlayer();
                }
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    if(zone1.isChecked()) {

                        mPlayer.setDataSource("http://al-music-streamer.herokuapp.com/streamer?latitude=1&longitude=1");

                    }
                    if(zone2.isChecked()) {
                        mPlayer.setDataSource("http://al-music-streamer.herokuapp.com/streamer?latitude=2&longitude=1");
                    }
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mPlayer.prepare();
                } catch (IllegalStateException e) {
                    Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                }
                mPlayer.start();


                finalTime = mPlayer.getDuration();
                startTime = mPlayer.getCurrentPosition();
                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );
                seekbar.setProgress((int) startTime);
                myHandler.postDelayed(UpdateSongTime, 100);

            }
        });


        buttonStop = (Button) findViewById(R.id.stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();
                    mPlayer.reset();

                }
            }
        });
    }
    public void addListenerOnChkIos() {

        zone1 = (CheckBox) findViewById(R.id.zone1);
        zone2 = (CheckBox) findViewById(R.id.zone2);

        zone1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getApplicationContext(), "zone  !", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // make the device update its music
        mLocation.beginUpdates();
    }

    @Override
    protected void onPause() {
        // stop music updates (saves battery)
        mLocation.endUpdates();

        super.onPause();
    }
    private Runnable UpdateSongTime = new Runnable() {
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        public void run() {
            startTime = mPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
