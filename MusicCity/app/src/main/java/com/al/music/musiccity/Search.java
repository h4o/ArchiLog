package com.al.music.musiccity;

/**
 * Created by user on 05/01/2017.
 */


import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class Search extends Activity {
    int a = 0 ;
    ListView lv;
    SearchView sv;
    String[] teams={"Pop","Metal","Rock","Rap","Rai","Ragga","Reggae","Hip-Hop"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        lv=(ListView) findViewById(R.id.listView1);
        sv=(SearchView) findViewById(R.id.searchView1);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,teams);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "je l'ai!", Toast.LENGTH_LONG).show();
                a =adapter.getCount();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                adapter.getFilter().filter(text);
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
