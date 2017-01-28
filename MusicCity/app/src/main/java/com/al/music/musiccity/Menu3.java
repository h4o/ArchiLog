package com.al.music.musiccity;


import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    ArrayAdapter<String> adapter;

    Button getMusic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu3, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Schudle");
        lv = (ListView) view.findViewById(R.id.listMusic);
       sv = (SearchView) view.findViewById(R.id.searchMusic);

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, music);
        lv.setAdapter(adapter);

       // sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        getMusic = (Button) view.findViewById(R.id.getListMusic);
        getMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // do something
                Toast.makeText(getContext(), "your list is added now!", Toast.LENGTH_LONG).show();
                music.add("issam");

            }
        });
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                // Toast.makeText(getContext(), "je l'ai!", Toast.LENGTH_LONG).show();

                a=text;

                Toast.makeText(getContext(),"your music "+ a + " is added in our playList" , Toast.LENGTH_LONG).show();


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




