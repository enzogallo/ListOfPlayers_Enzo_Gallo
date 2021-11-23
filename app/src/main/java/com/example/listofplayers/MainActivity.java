package com.example.listofplayers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPlayerItems;
    private RequestQueue requestQueue;
    private List<Player> playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlayerItems = findViewById(R.id.rvPlayerItems);
        rvPlayerItems.setHasFixedSize(true);
        rvPlayerItems.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        playerList = new ArrayList<>();

        fetchPlayers();


    }

    private void fetchPlayers(){
        String url = "https://api.npoint.io/555573a717aaea5cec66";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String score = Double.toString(Math.round(jsonObject.getDouble("score")*100.0)/100.0);
                        String lieu = jsonObject.getString("city");
                        String speed_score = "Speed : " + Double.toString(Math.round(jsonObject.getDouble("speed_score")*100.0)/100.0);
                        String stamina_score = "Stamina : " + Double.toString(Math.round(jsonObject.getDouble("stamina_score")*100.0)/100.0);
                        String dribbling_score = "Dribbling : " + Double.toString(Math.round(jsonObject.getDouble("dribbling_score")*100.0)/100.0);
                        String defending_score = "Defense : " + Double.toString(Math.round(jsonObject.getDouble("defending_score")*100.0)/100.0);
                        String passing_score = "Passing : " + Double.toString(Math.round(jsonObject.getDouble("passing_score")*100.0)/100.0);
                        String shooting_score = "Shooting : " + Double.toString(Math.round(jsonObject.getDouble("shooting_score")*100.0)/100.0);
                        String imagePlayer;
                        if(jsonObject.getString("profile_pic") != "null"){
                            imagePlayer = jsonObject.getString("profile_pic");
                        }
                        else{
                            imagePlayer = "https://thumbs.dreamstime.com/b/footballeur-dans-l-action-50903382.jpg";
                        }
                        Player player = new Player(name, lieu, imagePlayer, speed_score, defending_score, stamina_score, passing_score, dribbling_score, shooting_score, score );
                        playerList.add(player);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    PlayerAdapter adapter = new PlayerAdapter(MainActivity.this, playerList);
                    rvPlayerItems.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);


    }
}