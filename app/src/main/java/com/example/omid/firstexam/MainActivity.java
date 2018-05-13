package com.example.omid.firstexam;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.omid.firstexam.POJO.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    Button btnGetCountries;
    RestInterface CountryService;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Country> listItems;
    ProgressDialog progressDoalog;

    public static CountryDB countryDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);

        countryDB = Room.databaseBuilder(getApplicationContext(), CountryDB.class, "CountryDB").allowMainThreadQueries().build();

        CountryService = Common.getRestInterface();
        btnGetCountries = (Button) findViewById(R.id.btnGetCountries);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnGetCountries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDoalog.show();
                getCountries();
            }
        });


    }


    private void getCountries() {
        progressDoalog.setTitle("Loading");
        progressDoalog.setMessage("Load countries is in progress");
        progressDoalog.show();
        CountryService.GetCountries().enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response1) {
                String r = "";
                List<Country> lstCountries;
                try {
                    r = response1.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                lstCountries = Arrays.asList(gson.fromJson(r, Country[].class));
                MainActivity.countryDB.dao().deleteCountries();
                for (int i = 0; i < lstCountries.size(); i++) {
                    Country newCountry = lstCountries.get(i);
                    MainActivity.countryDB.dao().addCountry(newCountry);
                }
                lstCountries = MainActivity.countryDB.dao().getCountries();
                adapter = new CountriesAdapter(lstCountries, getBaseContext());
                recyclerView.setAdapter(adapter);
                progressDoalog.hide();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                List<Country> lstCountries;
                lstCountries = MainActivity.countryDB.dao().getCountries();
                adapter = new CountriesAdapter(lstCountries, getBaseContext());
                recyclerView.setAdapter(adapter);
                progressDoalog.hide();
            }

        });
    }

}