package com.example.demo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class EnemyBoat extends Polygon {

    //Variables required
    String playerName;
    Double health;
    Circle shootArea = new Circle(300, Color.TRANSPARENT);
    Circle MoveArea = new Circle(450, Color.TRANSPARENT);
    boolean isShoot = true;
    double speedBullet = 10;
    Circle bullet;

    boolean oneHitPlayer = false;
    double bulletTheta;

    Double MiddlePointBoatX = 0.00;
    Double MiddlePointBoatY = 0.00;
    double xVelocity;
    double yVelocity;
    double MovementTheta;

    double speed = 3.5;


    //Constructor
    public EnemyBoat(Double p1x, Double p1y, Double p2x, Double p2y, Double p3x, Double p3y) {
        this.getPoints().addAll(p1x, p1y,
                p2x, p2y,
                p3x, p3y);
        health = 100.0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        playerName = name;
    }

    public Double getHealth() {
        return health;
    }

    //Check if dead
    public boolean isDead() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //Reduce Health
    public void reduceHealth(Double damagedone) {
        health -= damagedone;
        if (isDead()) {
            health = 0.0;
        }
    }

    public Circle getShootArea() {
        return shootArea;
    }

    public void setSpeedBullet(double speed) {
        speedBullet = speed;
    }

    public double getSpeedBullet() {
        return speedBullet;
    }

    //Move boat and all the objects required for functionalities
    public void MoveBoat(Double xPos, Double yPos) {
        this.setTranslateX(this.getTranslateX() + xPos);
        this.setTranslateY(this.getTranslateY() + yPos);
        shootArea.setTranslateX(this.getTranslateX());
        shootArea.setTranslateY(this.getTranslateY());
        MoveArea.setTranslateX(this.getTranslateX());
        MoveArea.setTranslateY(this.getTranslateY());
    }

    //Methods related to shoot
    public void setIsShoot(boolean speed) {
        isShoot = speed;
    }

    public boolean getIsShoot() {
        return isShoot;
    }

    public void CreateBullet(int radius, Color color) {
        bullet = new Circle(radius, color);
    }

    public Circle getBullet() {
        return bullet;
    }

    public void TranslateBullet(double xPos, double yPos) {
        bullet.setTranslateX(xPos);
        bullet.setTranslateY(yPos);
    }

    public void setOnHitPlayer(boolean onehit) {
        oneHitPlayer = onehit;
    }

    public boolean getOnHitPlayer() {
        return oneHitPlayer;
    }

    public void setBulletTheta(double bulletTheta) {
        this.bulletTheta = bulletTheta;
    }

    public double getBulletTheta() {
        return bulletTheta;
    }

    public Circle getMoveArea() {
        return MoveArea;
    }
    public void setMiddlePointBoatX(Double middlePointBoatX) {
        MiddlePointBoatX = middlePointBoatX;
    }
    public Double getMiddlePointBoatX() {
        return MiddlePointBoatX;
    }
    public void setMiddlePointBoatY(Double middlePointBoatY) {
        MiddlePointBoatY = middlePointBoatY;
    }
    public Double getMiddlePointBoatY() {
        return MiddlePointBoatY;
    }
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public double getyVelocity() {
        return yVelocity;
    }
    public double getMovementTheta() {
        return MovementTheta;
    }
    public void setMovementTheta(double movementTheta) {
        MovementTheta = movementTheta;
    }
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

