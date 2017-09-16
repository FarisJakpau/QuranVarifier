package com.example.farisjakpau.quranvarifier;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FarisJakpau on 9/16/2017.
 */

public class feedActivity extends AppCompatActivity{
    Connnection connnection;
    RecyclerView recyclerView;
    List<FeedHolder> data;
    feedAdapter adapter;
    FeedHolder current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_activity);

        declaration();
        toolbar();
        getData();

    }

    public void declaration(){
        connnection = new Connnection();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_feed);
        data = new ArrayList<>();
    }

    public void toolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitle(connnection.getUserName(getApplicationContext()));

        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL);
        collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_HORIZONTAL);
    }

    public void setAdapter(){
        adapter = new feedAdapter(data,getApplicationContext());
        System.out.println("DATA SIZE -- >" +data.size());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void getData(){
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        System.out.println("FEED RESPONSE -->"+object);
                        JSONObject feed ,object1;
                        JSONArray object2;
                        try {
                            feed = object.getJSONObject("feed");
                            object2 = feed.getJSONArray("data");

                            for(int i =0 ;i <object2.length() ; i++){
                                current = new FeedHolder();
                                object1 = object2.getJSONObject(i);

//                                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//                                String string1 = object1.getString("created_time");
//                                Date result1 = df1.parse(string1);
//                                System.out.println("DATEE -- >" +result1.toString());
                                if(object1.has("message") && object1.has("description") && object1.has("full_picture")){
                                    current.setMessage(object1.getString("message"));
                                    current.setDescription(object1.getString("description"));
                                    current.setDate(object1.getString("full_picture"));
                                }
                                else if(object1.has("message")&& object1.has("full_picture")){
                                    current.setMessage(object1.getString("message"));
                                    current.setDate(object1.getString("full_picture"));
                                }
                                else if(object1.has("description")&& object1.has("full_picture")){
                                    current.setDescription(object1.getString("description"));
                                    current.setDate(object1.getString("full_picture"));
                                }
                                else if(object1.has("message")){
                                    current.setMessage(object1.getString("message"));
                                }
                                else if(object1.has("description")){
                                    current.setDescription(object1.getString("description"));
                                }
                                else if(object1.has("full_picture")){
                                    current.setDate(object1.getString("full_picture"));
                                }
                                else
                                    continue;

                                data.add(current);
                            }
                            adapter = new feedAdapter(data,getApplicationContext());
                            System.out.println("DATA SIZE -- >" +data.size());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(adapter);
//                            setAdapter();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "feed{message,description,created_time,picture,full_picture}");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
