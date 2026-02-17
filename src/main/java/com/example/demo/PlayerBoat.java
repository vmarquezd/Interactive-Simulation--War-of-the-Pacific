package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class PlayerBoat extends Polygon {

    //Setup Variables
    private String playerName;
    private Double health;
    private Timeline timer;
    private boolean isYellow = false;
    private boolean canBeHit = true;
    double elapsed = 0;

    //Constructor
    public PlayerBoat(String name, Double p1x, Double p1y, Double p2x, Double p2y, Double p3x, Double p3y){
        this.playerName = name;
        this.getPoints().addAll(p1x, p1y,
                p2x, p2y,
                p3x, p3y);
        health = 100.0;
        SetupTimer();
    }
    public String GetPlayerName(){
        return playerName;
    }
    public Double GetHealth(){return health;}

    //Check if dead
    public boolean IsDead(){
        if (health <= 0){
            return true;
        }else {
            return false;
        }
    }

    //Reduce health
    public void ReduceHealth(Double damagedone){
        if (canBeHit){
            canBeHit = false;
            health -= damagedone;
            if(IsDead()){
                health = 0.0;
            }else {
                StartTimer();
            }
        }
    }
    //Timer for colour toggle
    private void SetupTimer() {
        timer = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            // Timer logic here
            // This code will be executed every 50 milliseconds (20 times per second)
            ToggleColor();
            CheckTimerCompletion();
        }));
        timer.setCycleCount(Timeline.INDEFINITE); // Run indefinitely

    }
    private void StartTimer() {
        timer.stop(); // Reset the timer if it was already running
        timer.play();
        elapsed = 0;
    }
    private void CheckTimerCompletion() {
        if (timer.getStatus() == Timeline.Status.RUNNING) {
            elapsed += 50;
            if (1500 <= elapsed) {
                timer.stop();
                this.setFill(Color.BLACK);
                canBeHit = true;
            }
        }
    }
    private void ToggleColor() {
        if (isYellow) {
            setFill(Color.BLUE);
            isYellow = false;
        } else {
            setFill(Color.YELLOW);
            isYellow = true;
        }
    }
}
