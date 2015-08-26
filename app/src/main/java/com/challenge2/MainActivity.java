package com.challenge2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
    String jsonFile = "movies.json";
    EditText etSearch;
    Button btnSwitch;
    RecyclerView lvContainer;
    MovieAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Movie> listMovie = new ArrayList<Movie>();

    // For filter
    ArrayList<Movie> tempList = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        etSearch = (EditText) findViewById(R.id.etSearch);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        lvContainer = (RecyclerView) findViewById(R.id.lvContainer);

        initListMovie();
        addEvents();

        tempList.addAll(listMovie);
    }

    private void initListMovie() {
        readJson("movies.json");
        layoutManager = new LinearLayoutManager(this);
        lvContainer.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(listMovie);
        lvContainer.setAdapter(adapter);
    }

    // Load data into listMovie
    private void readJson(String fileName) {
        try {
            // Get Reader
            InputStream is = getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(is,
                    Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(isr);

            // Parse Json
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(reader, Movie[].class);

            for (Movie m : movies) {
                listMovie.add(m);
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEvents() {

        // EditText Search event
        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Switch view style (LinearLayout - GridLayout)
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvContainer.getLayoutManager() instanceof GridLayoutManager) {
                    lvContainer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    lvContainer.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    // hide text watchnow
                }
            }
        });
    }


    protected void filter(CharSequence s) {
        listMovie.clear();
        adapter.getCheckedPositions().clear();

        String charText = s.toString().toLowerCase();
        if (charText.length() == 0) {
            listMovie.addAll(tempList);
        } else {
            for (Movie m : tempList) {
                if (m.getOriginal_title().toLowerCase().contains(charText)) {
                    listMovie.add(m);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
