package com.example.demo5;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PlatformControler {
    private AnchorPane plane;


    public PlatformControler(AnchorPane plane){

        this.plane = plane;

    }

    public Rectangle createPlatfhorm(double PosX, double PosY)
    {
        int Wight = 100;
        int Height = 20;
        Rectangle Platform = new Rectangle(PosX, PosY, Wight, Height);
        Platform.setFill(Color.web("#87F03C"));
        Platform.setStroke(Color.web(("#0B0B0A")));
        Platform.setStrokeWidth(2);
        plane.getChildren().add(Platform);
        return Platform;
    }
    public void remove (Rectangle Platform){
        plane.getChildren().remove(Platform);
    }

    public void movePlatform(Rectangle Platform,double moveX, double moveY)
    {
        Platform.setX(Platform.getX() + moveX);
        Platform.setY(Platform.getY() + moveY);
    }


    public double distance(Rectangle platform1,Rectangle platform2){
        return sqrt(pow((platform1.getY() + platform1.getHeight()/2 - platform2.getY() - platform2.getHeight()/2 + platform1.getX() + platform1.getWidth()/2 - platform2.getX() - platform2.getHeight()/2),2));
    }

    public double distanceForDot(Rectangle platform1, int PosX, int PosY){
        return sqrt(pow((platform1.getY() + platform1.getHeight()/2 - PosY + platform1.getX() + platform1.getWidth()/2 - PosY) , 2));
    }
}
