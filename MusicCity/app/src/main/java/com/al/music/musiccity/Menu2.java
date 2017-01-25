package com.al.music.musiccity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * Created by user on 12/01/2017.
 */

public class Menu2 extends Fragment {
    int a = 0;
    ListView lv;
    SearchView sv;
    String[] teams = {"Pop", "Metal", "Rock", "Rap", "Rai", "Ragga", "Reggae", "Hip-Hop"};
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Search");
        lv = (ListView) view.findViewById(R.id.listView1);
        sv = (SearchView) view.findViewById(R.id.searchView1);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, teams);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "je l'ai!", Toast.LENGTH_LONG).show();
                a = adapter.getCount();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });
    }


}




