package com.example.demo;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Location extends Rectangle {

    ImageView imageView;
    String Name;
    String[] Questions;
    public Circle circle = new Circle(30, Color.RED);
    public Location(Double height, Double width, String name, String ImageName, String[] questions){
        this.setHeight(height);
        this.setWidth(width);
        this.setFill(Color.BLUE);
        Name = name;
        imageView = new ImageView(getClass().getResource("/"+ImageName+".jpg").toExternalForm());
        Questions = questions;
    }

    public void Move(Double XPos, Double YPos){
        this.setTranslateX(XPos);
        this.setTranslateY(YPos);
        imageView.setTranslateX(this.getTranslateX()+200);
        imageView.setTranslateY(this.getTranslateY());
        circle.setTranslateX(this.getTranslateX());
        circle.setTranslateY(this.getTranslateY());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getName() {
        return Name;
    }

    public String getQuestions(int i) {
        return Questions[i];
    }

    public void setQuestions(String[] questions) {
        Questions = questions;
    }

    public Circle areaCheck(){return circle;}
}
