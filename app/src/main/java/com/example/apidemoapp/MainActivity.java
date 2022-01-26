package com.example.apidemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apidemoapp.adapters.RVAdapter;
import com.example.apidemoapp.data.State;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView errorTextView;
    RecyclerView recyclerView;
    RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        errorTextView = findViewById(R.id.errorTextView);

        recyclerView = findViewById(R.id.recyclerView);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.rootnet.in/covid19-in/stats/latest";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<State> states = new ArrayList<>();
                    for(int i=0;i<response.getJSONObject("data").getJSONArray("regional").length();i++)
                    {
                        String name = response.getJSONObject("data").getJSONArray("regional").getJSONObject(i).getString("loc");
                        int cases = response.getJSONObject("data").getJSONArray("regional").getJSONObject(i).getInt("totalConfirmed");
                        states.add(new State(name,cases));
                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                    adapter = new RVAdapter(states, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorTextView.setVisibility(View.VISIBLE);
                errorTextView.setText("That didn't work! inside getJSONOBJECT");
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);



    }

}