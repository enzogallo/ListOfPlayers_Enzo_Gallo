package com.example.listofplayers;

public class Player {

    private final String speed_score;
    private final String defending_score;
    private final String stamina_score;
    private final String passing_score;
    private final String dribbling_score;
    private final String shooting_score;
    private final String score;
    private final String name, lieu, photo;

    public Player(String name, String lieu, String imagePlayer, String speed, String defending, String stamina,
                  String passing, String dribbling, String shooting, String score){
        this.name = name;
        this.lieu = lieu;
        this.photo = imagePlayer;
        this.speed_score = speed;
        this.defending_score = defending;
        this.stamina_score = stamina;
        this.passing_score = passing;
        this.dribbling_score = dribbling;
        this.shooting_score = shooting;
        this.score = score;
    }

    public String getSpeed() {
        return speed_score;
    }

    public String getDefending() {
        return defending_score;
    }

    public String getStamina() {
        return stamina_score;
    }

    public String getPassing() {
        return passing_score;
    }

    public String getDribbling() {
        return dribbling_score;
    }

    public String getShooting() {
        return shooting_score;
    }

    public String getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getLieu() {
        return lieu;
    }

    public String getPhoto() {
        return photo;
    }
}
