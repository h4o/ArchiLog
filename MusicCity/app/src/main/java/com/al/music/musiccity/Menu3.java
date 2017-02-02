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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.ResponseEntity;
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
    List<Music> music = new ArrayList<Music>();
    List<String> musicArtiste = new ArrayList<String>();
    List<String> musicNom = new ArrayList<String>();
    List<String> musicId = new ArrayList<String>();
    ArrayAdapter<Music> adapter;
    Music mSchedule=new Music();
    String textEnvoyer;
    Button scheduleMusic;
    EditText GenreText;






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
                final String urlPost="http://al-request.herokuapp.com/request/add";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Musics music_t = restTemplate.getForObject(url, Musics.class);

                Log.e("MainActivity",music_t+"");
                return music_t;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Musics music_t) {

        /*   *//*******récuperer la liste de ID   ***************//*
            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                musicId.add(music_t.getMusics().get(i).get_id());
            }
            *//*******récuperer la liste de Nom  ***************//*
           // Toast.makeText(getContext(),greeting.getMusics().get(1).getName() + "récuperé ! " , Toast.LENGTH_LONG).show();
            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                musicNom.add(music_t.getMusics().get(i).getName());
            }
            *//*******récuperer la liste de Artiste  ***************//*
            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                musicArtiste.add(music_t.getMusics().get(i).getArtist());
            }



            Toast.makeText(getContext(),"Liste of music found and added " , Toast.LENGTH_LONG).show();


             *//*******Fusionner la liste de NOM et la liste d Artiste pour l'affichage  ***************//*
            ArrayList<String> music = new ArrayList<String>(music_t.getMusics().size()); // Make a new list
            for (int i = 1; i < music_t.getMusics().size()-1; i++) { // Loop through every name/phone number combo
               music.add(musicNom.get(i) + "  " + musicId.get(i)); // Concat the two, and add it
            }
*/
            ArrayList<String> music = new ArrayList<String>(music_t.getMusics().size());
            for (int i = 1;i <=  music_t.getMusics().size()-1;i++){
                music.add(music_t.getMusics().get(i).getName());
            }

            adapter.clear();
            adapter.addAll(music_t.getMusics());





        }





        }



    private class HttpRequestTask2 extends AsyncTask<Void, Void, Musics> {

        @Override
        protected Musics doInBackground(Void... params) {
            try {
              //  final String url = "http://46.101.31.80:8080/musicRequest";
                final String urlPost="http://al-requests.herokuapp.com/requests/add/";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                String ret =  restTemplate.postForObject(urlPost,textEnvoyer, String.class);
                Toast.makeText(getContext(),"Scheduled the track !",Toast.LENGTH_LONG).show();
                return null;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Musics music_t) {

            Toast.makeText(getContext(), textEnvoyer +"est schedule" , Toast.LENGTH_LONG).show();



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
        GenreText = (EditText) view.findViewById(R.id.m);
        textEnvoyer = GenreText.getText().toString();

        adapter = new ArrayAdapter<Music>(getContext(), android.R.layout.simple_list_item_1, music);
        lv.setAdapter(adapter);


        scheduleMusic = (Button) view.findViewById(R.id.scheduleMusic);
        scheduleMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                new HttpRequestTask2().execute();


            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long id) {
                //le code à effectuer suite à un click
                Toast.makeText(getContext(),music.get(position) +" is chosen !", Toast.LENGTH_LONG).show();
                GenreText.setText(music.get(position).toString());
                Toast.makeText(getContext(), music.get(position).toString(), Toast.LENGTH_LONG).show();

                textEnvoyer = music.get(position)._id;



            }
        });



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                a=text;

                Toast.makeText(getContext(), a + " will be schedule" , Toast.LENGTH_LONG).show();
                GenreText.setText(a);
                textEnvoyer = GenreText.getText().toString();

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




