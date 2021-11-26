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

    private RecyclerView rvPlayerItems; //on utilise le recyclerview pour eviter de charger toute la base de donnée
    //on prefere charger uniquement ce que l'on voit
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
        String url = "https://api.npoint.io/7f09322753e3ceff87eb";
        //Ici je me suis permis de concentrer toute la base de données sur une même page que j'ai générée sur un générateur json

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String score = Double.toString(Math.round(jsonObject.getDouble("score")*10.0)/10.0); //permet d'arrondir au dixième les scores
                        String lieu = jsonObject.getString("city");
                        String speed_score = "Speed : " + Math.round(jsonObject.getDouble("speed_score")*10.0)/10.0;
                        String stamina_score = "Stamina : " + Math.round(jsonObject.getDouble("stamina_score")*10.0)/10.0;
                        String dribbling_score = "Dribbling : " + Math.round(jsonObject.getDouble("dribbling_score")*10.0)/10.0;
                        String defending_score = "Defense : " + Math.round(jsonObject.getDouble("defending_score")*10.0)/10.0;
                        String passing_score = "Passing : " + Math.round(jsonObject.getDouble("passing_score")*10.0)/10.0;
                        String shooting_score = "Shooting : " + Math.round(jsonObject.getDouble("shooting_score")*10.0)/10.0;
                        String imagePlayer;
                        if(!jsonObject.getString("profile_pic").equals("null")){
                            imagePlayer = jsonObject.getString("profile_pic");
                        }
                        else{
                            imagePlayer = "https://thumbs.dreamstime.com/b/footballeur-dans-l-action-50903382.jpg"; //affichage d'une image libre de droit en cas d'absence de photo de profil des joueurs
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