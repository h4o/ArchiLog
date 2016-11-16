package im.delight.android.music;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import im.delight.android.music.R;
import im.delight.android.music.SimpleLocation;

public class MainActivity extends Activity {

    private SimpleLocation mLocation;
	Button getLocationButton;
    Button getStreamingButton;
    WebService s= new WebService();

    /*  partie music */
    static MediaPlayer mPlayer;
    Button buttonPlay;
    Button buttonStop;
    Button buttonSyncro;
	Button buttonGetMusic;
    String url = "http://android.programmerguru.com/wp-content/uploads/2013/04/hosannatelugu.mp3";
    String urlDist="";
    private TextView tx1,tx2;
    private SeekBar seekbar;
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly = 0;
    private Handler myHandler = new Handler();;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        tx1 = (TextView)findViewById(R.id.textView2);
       // tx2  = (TextView)findViewById(R.id.textView6);


        // construct a new instance
        mLocation = new SimpleLocation(this);

        // reduce the precision to 5,000m for privacy reasons
        mLocation.setBlurRadius(5000);

        // if we can't access the music yet
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable music access
            SimpleLocation.openSettings(this);
        }
        ((TextView)findViewById(R.id.latitude)).setText(String.valueOf(mLocation.getLatitude()));
        ((TextView)findViewById(R.id.longitude)).setText(String.valueOf(mLocation.getLongitude()));


      /*  getStreamingButton = (Button) findViewById(R.id.button1);
        getStreamingButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				afficherLienStreaming();
			}


		});*/
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setClickable(true);
        buttonPlay = (Button) findViewById(R.id.play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
				mPlayer = new MediaPlayer();
				mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				try {
					mPlayer.setDataSource(url);
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
				/*tx2.setText(String.format("%d min, %d sec",
								TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
								TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
										TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
												finalTime)))
				);*/
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
                if(mPlayer!=null && mPlayer.isPlaying()){
                    mPlayer.stop();
                }
            }
        });

		buttonGetMusic = (Button) findViewById(R.id.button1);
		buttonGetMusic.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

			}
		});
/*
        buttonSyncro = (Button) findViewById(R.id.syncro);
        buttonSyncro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                int currentPosition = mPlayer.getCurrentPosition();
                if(mPlayer!=null && mPlayer.isPlaying() && currentPosition + 10000 <= mPlayer.getDuration()){
                    mPlayer.seekTo(currentPosition + 20000);
                }else {
                    mPlayer.seekTo(mPlayer.getDuration());
                }
            }
        });
*/


    }
	public void envoyerLong(View v){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http:// ... ");
		double l = mLocation.getLatitude();
		String lStr = String.valueOf(l);
		if(lStr.length() > 0 ){
			try {
				List<NameValuePair> lEnv = new ArrayList<NameValuePair>(1);
				lEnv.add(new BasicNameValuePair("longitude",lStr));
				post.setEntity(new UrlEncodedFormEntity(lEnv));
				client.execute(post);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}


		}

	}

    protected void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }


   /* private void afficherLienStreaming(){
      //  s.sendRequest
        s.getQuartier();
        ((TextView)findViewById(R.id.lienStreaming)).setText(String.valueOf(s.getQuartier()));

    }*/
	/*private void afficherLocation() {
		//On affiche les informations de la position a l'écran
		((TextView)findViewById(R.id.latitude)).setText(String.valueOf(mLocation.getLatitude()));
		((TextView)findViewById(R.id.longitude)).setText(String.valueOf(mLocation.getLongitude()));

	}*/
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

}
