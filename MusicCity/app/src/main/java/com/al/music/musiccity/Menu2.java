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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/01/2017.
 */

public class Menu2 extends Fragment  {
    String a ;
    ListView lv;
    SearchView sv;
   // String[] teams = {"Pop", "Metal", "Rock", "Rap", "Rai", "Ragga", "Reggae", "Hip-Hop"};
   public List<String> teams = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    /*  partie music */
    static MediaPlayer mPlayer;
    Button buttonPlay;
    Button buttonStop;

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
                Genre greeting = restTemplate.getForObject(url, Genre.class);
                //ResponseEntity<Greeting> responseEntity = restTemplate.postForEntity(url,new Greeting(),Greeting.class);

                Log.e("MainActivity",greeting+"");
                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Genre greeting) {
            // TextView greetingContentText = (TextView) findViewById(R.id.content_value3);

            Log.e("MainActivity est bien ",greeting.getGenres().toString());
            teams=greeting.getGenres();
            Toast.makeText(getContext(),"Liste de genre trouvée  " , Toast.LENGTH_LONG).show();
            adapter.clear();
            adapter.addAll(teams);
            //  test=greeting.getContent();

            // Toast.makeText(getContext(),greeting.getGenres().toString() + "récuperé ! " , Toast.LENGTH_LONG).show();

            //  TextView greetingContentText = (TextView) getView().findViewById(R.id.content_value3);
            //  greetingContentText.setText(test);

           // teams=greeting.getGenres();
          //  Toast.makeText(getContext(),teams.toString() + "récuperé dans teams ! " , Toast.LENGTH_LONG).show();



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
       // urlRecuperer = "http://al-music-streamer.herokuapp.com/streamer/genre/"+a ;
       // urlRecuperer ="http://al-music-streamer.herokuapp.com/streamer/genre/"+a;


        buttonPlay = (Button) view.findViewById(R.id.play2);
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
                    mPlayer.setDataSource("http://al-music-streamer.herokuapp.com/streamer/genre/"+a.toUpperCase());
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
        // TODO Auto-generated method stub
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
    public void onResume() {
        super.onResume();
    }


    public void onPause() {
        super.onPause();
    }





}




