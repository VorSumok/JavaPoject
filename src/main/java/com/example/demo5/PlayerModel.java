package com.example.demo5;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class PlayerModel {

    private Rectangle PlayerModel;
    public PlayerModel(Rectangle PlayerModel){

        this.PlayerModel = PlayerModel;
    }


    private int jumpHeight = -300;
    private int moveHeight = 50;
    private double ImpulseY = 0;
    private double ImpulseX = 0;
    private double yDelta = 0.03;
    private double xDelta = 0.05;


    public double getImpulseX(){return ImpulseX;}
    public double getImpulseY(){return ImpulseY;}

    public Rectangle getPlayerModel(){return PlayerModel;}

    public void jump(){
        ImpulseY = jumpHeight;
    }

    public void moveRight(){
        ImpulseX = moveHeight;
    }

    public void moveLeft(){
        ImpulseX = -moveHeight;
    }

    private void movePlayer() {
        PlayerModel.setY(PlayerModel.getY() + ImpulseY * yDelta);
        PlayerModel.setX(PlayerModel.getX() + ImpulseX * xDelta);
    }

    public void setPlayer(double positionChangeY, double positionChangeX){
        PlayerModel.setY(PlayerModel.getY() + positionChangeY);
        PlayerModel.setX(PlayerModel.getX() + positionChangeX);
    }


    public boolean isPlayerDead(AnchorPane Plane){
        double Player = PlayerModel.getLayoutY() + PlayerModel.getY();
        return Player >= Plane.getHeight();
    }

    public void resetPlayer(){
        PlayerModel.setY(0);
        ImpulseY = 0;
    }

    public void PlayerUpdate(){

    ImpulseY = ImpulseY + 3;
    if (ImpulseX > 0){ImpulseX = ImpulseX-2;} else if (ImpulseX < 0) { ImpulseX = ImpulseX+2;}

    movePlayer();
    }
}