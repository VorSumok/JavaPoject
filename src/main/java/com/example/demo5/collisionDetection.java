package com.example.demo5;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class collisionDetection {


    private boolean playerCollisionDetection(Rectangle platform, PlayerModel Player) {

        return (intersectionY (platform, Player.getPlayerModel()) && intersectionX(platform, Player.getPlayerModel()) && (Player.getImpulseY() > 0));
    }
    public boolean checkPlayerCollisionDetection(ArrayList<Platform> PlatformMap, PlayerModel Player){
        for (Platform platform: PlatformMap){
            if (playerCollisionDetection(platform.getPlatform(), Player)){
                return true;}
        }
        return false;
    }

    public Platform GetPlayerOnPlatform(ArrayList<Platform> PlatformMap, PlayerModel Player){
        for (Platform platform: PlatformMap){
            if (playerCollisionDetection(platform.getPlatform(), Player)){
                return platform;}
        }
        return null;
    }
    public boolean CollisionDetection(Platform platform1, Platform platform2){
        return (platform1.getPlatform().getBoundsInParent().intersects(platform2.getPlatform().getBoundsInParent()));
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
