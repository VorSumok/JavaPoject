package com.example.demo5;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class collisionDetection {


    public boolean playerCollisionDetection(Rectangle platform, PlayerModel Player) {

        return (intersectionY (platform, Player.getPlayerModel()) && intersectionX(platform, Player.getPlayerModel()) && (Player.getImpulseY() > 0));
    }
    public boolean checkPlayerCollisionDetection(ArrayList<Rectangle> PlatformMap, PlayerModel Player){
        for (Rectangle platform: PlatformMap){
            if (playerCollisionDetection(platform, Player)){
                return true;}
        }
        return false;
    }
    public boolean CollisionDetection(Rectangle platform1, Rectangle platform2){
        return (platform1.getBoundsInParent().intersects(platform2.getBoundsInParent()));
    }
    private boolean intersectionY (Rectangle Platform, Rectangle PlayerModel){

        return ((Platform.getY() <= PlayerModel.getLayoutY() + PlayerModel.getY() + PlayerModel.getHeight())
                && (Platform.getY() + Platform.getHeight() >= PlayerModel.getLayoutY() + PlayerModel.getY() + PlayerModel.getHeight()));
    }

    private boolean intersectionX (Rectangle Platform, Rectangle PlayerModel){

        return (((Platform.getX() <= PlayerModel.getLayoutX() + PlayerModel.getX())
                && (Platform.getX() + Platform.getWidth() >= PlayerModel.getLayoutX() + PlayerModel.getX())) ||
                ((Platform.getX() <= PlayerModel.getLayoutX() + PlayerModel.getX() + PlayerModel.getWidth())
                        && (Platform.getX() + Platform.getWidth() >= PlayerModel.getLayoutX() + PlayerModel.getX() + PlayerModel.getWidth())));
    }



}
