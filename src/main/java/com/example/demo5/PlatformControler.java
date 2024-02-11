package com.example.demo5;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class PlatformControler {
    private AnchorPane plane;

    private collisionDetection collision = new collisionDetection();
    public PlatformControler(AnchorPane plane){

        this.plane = plane;

    }

    public Platform createPlatfhorm(double PosX, double PosY)
    {
        int Wight = 100;
        int Height = 20;
        Platform Platform = new Platform(new Rectangle(PosX, PosY, Wight, Height));
        Platform.getPlatform().setFill(Color.web("#87F03C"));
        Platform.getPlatform().setStroke(Color.web(("#0B0B0A")));
        Platform.getPlatform().setStrokeWidth(2);
        plane.getChildren().add(Platform.getPlatform());
        return Platform;
    }

    public void platformGenerater(int count, ArrayList<Platform> PlatformMap,PlayerModel Player){
        boolean badGeneration;
        Platform newPlatform;
        Platform PlayerOnPlatform = collision.GetPlayerOnPlatform(PlatformMap, Player);
        System.out.println("1");
        if (PlayerOnPlatform != null){
            System.out.println("4");
            if (!PlayerOnPlatform.WasUsed) {
                System.out.println("2");
                PlayerOnPlatform.WasUsed = true;
                System.out.println("3");
                for (int i = 1; i <= count; i++) {
                    do {
                        badGeneration = false;
                        newPlatform = createPlatfhorm(getRandomNumber(20, 450), getRandomNumber(-170, -10));
                        for (Platform platform : PlatformMap) {
                            if (collision.CollisionDetection(platform, newPlatform) || (distance(platform, newPlatform) < 30)) {
                                badGeneration = true;
                                remove(newPlatform);
                                break;
                            }
                        }
                    } while (badGeneration);
                    PlatformMap.add(newPlatform);
            }
        }
        }
    }

    private double getRandomNumber(double min, double max){
        return (Math.random() * (max - min)) + min;
    }
    public void removeOutOfscreen(ArrayList<Platform> PlatformMap)
    {
        ArrayList<Platform> OutOfScreen = new ArrayList<>();
        for (Platform platform: PlatformMap){
            if (platform.getPlatform().getY() > 750) {
                OutOfScreen.add(platform);
            }
        }
        removeAll(OutOfScreen);
        PlatformMap.removeAll(OutOfScreen);
    }
    public void remove (Platform platform){
        plane.getChildren().remove(platform.getPlatform());
    }
    public void removeAll (ArrayList<Platform>  OutOfScreen){
        ArrayList<Rectangle> RectangelMass = new ArrayList<>();
        for (Platform platform1 : OutOfScreen){RectangelMass.add(platform1.getPlatform());}
        plane.getChildren().removeAll(RectangelMass);
    }
    public void movePlatform(Platform platform,double moveX, double moveY)
    {
        platform.getPlatform().setX(platform.getPlatform().getX() + moveX);
        platform.getPlatform().setY(platform.getPlatform().getY() + moveY);
    }


    public double distance(Platform platform1,Platform platform2){
        return sqrt(pow((platform1.getPlatform().getY() + platform1.getPlatform().getHeight()/2 - platform2.getPlatform().getY() - platform2.getPlatform().getHeight()/2 + platform1.getPlatform().getX() + platform1.getPlatform().getWidth()/2 - platform2.getPlatform().getX() - platform2.getPlatform().getHeight()/2),2));
    }

    public double distanceForDot(Platform platform1, int PosX, int PosY){
        return sqrt(pow((platform1.getPlatform().getY() + platform1.getPlatform().getHeight()/2 - PosY + platform1.getPlatform().getX() + platform1.getPlatform().getWidth()/2 - PosX) , 2));
    }
}
