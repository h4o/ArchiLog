package com.al.music.musiccity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.al.music.library.SimpleLocation;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by user on 12/01/2017.
 */

public class Menu1 extends Fragment  {
    TextView title;

    private static final int  MY_PERMISSIONS_REQUEST = 1 ;
    private SimpleLocation mLocation;

    /*  partie music */
    static MediaPlayer mPlayer;
    Button buttonPlay;
    Button buttonStop;
    String test = " ";

    String urlRecuperer;

    private TextView tx1,sourceText ;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
    private Handler myHandler = new Handler();
    private CheckBox zone1,zone2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu1,container,false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Your music");


        zone1 = (CheckBox) view.findViewById(R.id.zone1);
        zone2 = (CheckBox) view.findViewById(R.id.zone2);


        zone1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(getContext(), "zone  !", Toast.LENGTH_LONG).show();
                }

            }
        });

        tx1 = (TextView) view.findViewById(R.id.textView2);

        // construct a new instance
        mLocation = new SimpleLocation(getContext());

        // reduce the precision to 5,000m for privacy reasons
        mLocation.setBlurRadius(5000);
        // if we can't access the music yet
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable music access
            SimpleLocation.openSettings(getContext());
        }
        ((TextView) view.findViewById(R.id.latitude)).setText(String.valueOf(mLocation.getLatitude()));
        ((TextView) view.findViewById(R.id.longitude)).setText(String.valueOf(mLocation.getLongitude()));


        urlRecuperer = "http://46.101.31.80/streamer?latitude=" + String.valueOf(mLocation.getLatitude()) + "&longitude=" + String.valueOf(mLocation.getLongitude());


        buttonPlay = (Button) view.findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                if (mPlayer == null)
                    mPlayer = new MediaPlayer();
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                    mPlayer.reset();
                    //mPlayer = new MediaPlayer();
                }
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    if (zone1.isChecked()) {

                        mPlayer.setDataSource("http://46.101.31.80/streamer?latitude=1&longitude=1");

                    }
                    if (zone2.isChecked()) {
                        mPlayer.setDataSource("http://46.101.31.80/streamer?latitude=2&longitude=1");
                    }
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mPlayer.prepare();
                } catch (IllegalStateException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                }
                mPlayer.start();


                finalTime = mPlayer.getDuration();
                startTime = mPlayer.getCurrentPosition();





            }
        });


        buttonStop = (Button) view.findViewById(R.id.stop);
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


    public void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


    public void onResume() {
        super.onResume();

        // make the device update its music
        mLocation.beginUpdates();
    }


    public void onPause() {
        // stop music updates (saves battery)
        mLocation.endUpdates();

        super.onPause();
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
