package com.example.listofplayers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder> {

    private Context context;
    private List<Player> playerList;

    public PlayerAdapter(Context context, List<Player> players){
        this.context = context;
        this.playerList = players;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {

        Player player = playerList.get(position);
        holder.name.setText(player.getName());
        holder.lieu.setText(player.getLieu());
        holder.score.setText(player.getScore().toString());
        holder.speed_score.setText(player.getSpeed().toString());
        holder.stamina_score.setText(player.getStamina().toString());
        holder.dribbling_score.setText(player.getDribbling().toString());
        holder.defending_score.setText(player.getDefending().toString());
        holder.passing_score.setText(player.getPassing().toString());
        holder.shooting_score.setText(player.getShooting().toString());
        Glide.with(context).load(player.getPoster()).into(holder.imageview);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", player.getName());
                bundle.putString("score", player.getScore());
                bundle.putString("lieu", player.getLieu());
                bundle.putString("poster", player.getPoster());
                bundle.putString("speed_score", player.getSpeed());
                bundle.putString("stamina_score", player.getStamina());
                bundle.putString("dribbling_score", player.getDribbling());
                bundle.putString("defending_score", player.getDefending());
                bundle.putString("passing_score", player.getPassing());
                bundle.putString("shooting_score", player.getShooting());

                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class PlayerHolder extends RecyclerView.ViewHolder{

        ImageView imageview;
        TextView name, score, lieu, speed_score, stamina_score, dribbling_score,
                defending_score, passing_score, shooting_score;

        ConstraintLayout constraintLayout;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.name_tv);
            score = itemView.findViewById(R.id.score_tv);
            lieu = itemView.findViewById(R.id.lieu_tv);
            speed_score = itemView.findViewById(R.id.speed_score_tv);
            stamina_score = itemView.findViewById(R.id.stamina_score_tv);
            dribbling_score = itemView.findViewById(R.id.dribbling_score_tv);
            defending_score = itemView.findViewById(R.id.defending_score_tv);
            passing_score = itemView.findViewById(R.id.passing_score_tv);
            shooting_score = itemView.findViewById(R.id.shooting_score_tv);

            constraintLayout = itemView.findViewById(R.id.main_layout);

        }
    }
}
