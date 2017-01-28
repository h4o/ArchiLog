package com.al.music.musiccity;


import android.graphics.Color;
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

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/01/2017.
 */

public class Menu3 extends Fragment  {
    String a ;
    ListView lv;
    SearchView sv;
    List<String> music = new ArrayList<String>();
    List<String> musicArtiste = new ArrayList<String>();
    List<String> musicNom = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    Button getMusic;






    @Override
    public void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Musics> {

        @Override
        protected Musics doInBackground(Void... params) {
            try {
                final String url = "http://46.101.31.80:8080/musicRequest";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Musics music_t = restTemplate.getForObject(url, Musics.class);
                //ResponseEntity<Greeting> responseEntity = restTemplate.postForEntity(url,new Greeting(),Greeting.class);

                Log.e("MainActivity",music_t+"");
                return music_t;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Musics music_t) {
            // TextView greetingContentText = (TextView) findViewById(R.id.content_value3);

           // Log.e("MainActivity est bien ",greeting.getGenres().toString());
           // teams=greeting.getGenres();

           // Toast.makeText(getContext(),greeting.getMusics().get(1).getName() + "récuperé ! " , Toast.LENGTH_LONG).show();
            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                musicNom.add(music_t.getMusics().get(i).getName());
            }

            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                musicArtiste.add(music_t.getMusics().get(i).getArtist());
            }



            Toast.makeText(getContext(),"Liste of music found and added " , Toast.LENGTH_LONG).show();
           // adapter.clear();

            ArrayList<String> music = new ArrayList<String>(music_t.getMusics().size()); // Make a new list
            for (int i = 1; i < music_t.getMusics().size()-1; i++) { // Loop through every name/phone number combo
               music.add(musicNom.get(i) + "  " + musicArtiste.get(i)); // Concat the two, and add it
            }
          //  Toast.makeText(getContext(),musicNom.toString() + "récuperé ! " , Toast.LENGTH_LONG).show();


            adapter.addAll(music);

          //  adapter.clear();
           // adapter.addAll(teams);
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
        return inflater.inflate(R.layout.menu3, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Schedule Music");
        lv = (ListView) view.findViewById(R.id.listMusic);
       sv = (SearchView) view.findViewById(R.id.searchMusic);
        final TextView GenreText = (TextView) view.findViewById(R.id.m);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, music);
        lv.setAdapter(adapter);

       /*// sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        getMusic = (Button) view.findViewById(R.id.getListMusic);
        getMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // do something
                Toast.makeText(getContext(), "your list is added now!", Toast.LENGTH_LONG).show();
               // music.add("issam");

            }
        });*/

       /* lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // do something
                Toast.makeText(getContext(), "t as cliqué!", Toast.LENGTH_LONG).show();
                // music.add("issam");

            }
        });*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long id) {
                //le code à effectuer suite à un click
                Toast.makeText(getContext(),music.get(position) +" is chosen !", Toast.LENGTH_LONG).show();
                GenreText.setText(music.get(position));

            }
        });



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                // Toast.makeText(getContext(), "je l'ai!", Toast.LENGTH_LONG).show();

               // a=text;

               // Toast.makeText(getContext(), a + " is a good choice " , Toast.LENGTH_LONG).show();
              //  GenreText.setText(a);
                a=text;

                Toast.makeText(getContext(), a + " will be shedule" , Toast.LENGTH_LONG).show();
                GenreText.setText(a);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });


    }

    public void onResume() {
        super.onResume();
    }


    public void onPause() {
        super.onPause();
    }




}




