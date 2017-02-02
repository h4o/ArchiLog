package com.al.music.musiccity;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

//import com.github.nkzawa.emitter.Emitter;
//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;


/**
 * Created by user on 12/01/2017.
 */

public class Menu2 extends Fragment  {
    String a ;
    ListView lv;
    SearchView sv;
    Pusher pusher;
   // String[] teams = {"Pop", "Metal", "Rock", "Rap", "Rai", "Ragga", "Reggae", "Hip-Hop"};
   public List<String> teams = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    /*  partie music */
    static MediaPlayer mPlayer;
    Button buttonPlay;
    Button buttonStop;

    private String playedResource;

    String urlRecuperer;


    @Override
    public void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Genre> {

        @Override
        protected Genre doInBackground(Void... params) {
            try {
                final String url = "http://al-zones.herokuapp.com/genres";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Genre genre = restTemplate.getForObject(url, Genre.class);
                //ResponseEntity<Greeting> responseEntity = restTemplate.postForEntity(url,new Greeting(),Greeting.class);

                Log.e("MainActivity",genre+"");
                return genre;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Genre genre) {

            Log.e("MainActivity est bien ",genre.getGenres().toString());
            teams=genre.getGenres();
            Toast.makeText(getContext(),"List of kind found and added " , Toast.LENGTH_LONG).show();
            adapter.clear();
            adapter.addAll(teams);

        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search by kind");
        lv = (ListView) view.findViewById(R.id.listView1);
        sv = (SearchView) view.findViewById(R.id.searchView1);
       final TextView GenreText = (TextView) view.findViewById(R.id.genre);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, teams);
        lv.setAdapter(adapter);
       // teams=greeting.getGenres();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long id) {
                //le code à effectuer suite à un click
                Toast.makeText(getContext(),teams.get(position) +" is chosen !", Toast.LENGTH_LONG).show();
                GenreText.setText(teams.get(position));
                a= teams.get(position);
            }
        });



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
               // Toast.makeText(getContext(), "je l'ai!", Toast.LENGTH_LONG).show();

                a=text;

                Toast.makeText(getContext(), a + " is a good choice " , Toast.LENGTH_LONG).show();
                GenreText.setText(a);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });


        buttonPlay = (Button) view.findViewById(R.id.play2);
        buttonPlay.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mSocket.on("synchronize", onNewMessage);
                Log.e("Socket","Connecting");
                mSocket.connect();
                mSocket.emit("test","test2");
                Log.e("Listeners:",mSocket.listeners("synchronize").toString());
                Toast.makeText(getContext(), "Playing sound", Toast.LENGTH_SHORT).show();


                if (mPlayer == null)
                    mPlayer = new MediaPlayer();
                if (mPlayer.isPlaying()) {
                    mPlayer.stop();
                    mPlayer.reset();
                    //mPlayer = new MediaPlayer();
                }
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mPlayer.stop();
                        mPlayer.start();
                    }
                });
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                try {
                    mPlayer.setDataSource("http://46.101.31.80/streamer/genre/"+a.toUpperCase());
                    playedResource = "http://46.101.31.80/streamer/genre/"+a.toUpperCase();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
                } catch (SecurityException e) {
                    Toast.makeText(getContext(), "Securité!", Toast.LENGTH_LONG).show();
                } catch (IllegalStateException e) {
                    Toast.makeText(getContext(), "illegalState!", Toast.LENGTH_LONG).show();
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

            }
        });
        buttonStop = (Button) view.findViewById(R.id.stop2);
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
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        Log.e("Socket","disconnecting from socket");
        mSocket.disconnect();
        mSocket.off("synchroniza", onNewMessage);
        mSocket.close();
    }
    public void onResume() {
        super.onResume();
    }


    public void onPause() {
        super.onPause();
    }


    private Socket mSocket;
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mSocket = IO.socket("http://46.101.31.80");
        } catch (URISyntaxException e) {
            Toast.makeText(getContext(), "erreur 1", Toast.LENGTH_LONG).show();
        }
        PusherOptions options = new PusherOptions();
        options.setCluster("eu");

        Log.e("Pusher","connection");
        pusher = new Pusher("3e482bab59f985e9b9d7",options);
        pusher.connect();

        Channel channel = pusher.subscribe("my-channel");
        Log.e("Pusher",channel.isSubscribed()+"");
        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                if (mPlayer.isPlaying()) {
                    Log.e("Player", "Resetting the player");
                    mPlayer.stop();
                    mPlayer.reset();
                    // Toast.makeText(getContext(), "new music  ! !!", Toast.LENGTH_LONG).show();
                    try {
                        Log.e("Player", "Resource" + playedResource);
                        mPlayer.setDataSource(playedResource);

                        mPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mPlayer.start();
                }
            }
        });


    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {


        @Override
        public void call(final Object... args) {
            Log.e("Socket","test");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                        Log.e("ssssss","fffff");
                        mPlayer.stop();
                        Toast.makeText(getContext(), "new music  ! !!", Toast.LENGTH_LONG).show();
                        mPlayer.start();
                    } catch (JSONException e) {
                        return;
                    }

                }
            });
        }
    };



}




