package com.example.demo5;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane Plane;


    @FXML
    private Rectangle PlayerModel;

    public double score;
    boolean leftPressed = false;
    boolean rightPressed = false;
    private PlatformControler platformControler;
    private collisionDetection collision;
    private PlayerModel PlayerComponent;

    ArrayList<Rectangle> PlatformMap = new ArrayList<>();

    double pDelta = 0.003;

    @FXML
    void PressedButton(KeyEvent event) {
        if(event.getCode() == KeyCode.SPACE){
            PlayerComponent.jump();
        }

        if(event.getCode() == KeyCode.D){
            rightPressed = true;
        }

        if(event.getCode() == KeyCode.A){
            leftPressed = true;
        }
    }

    @FXML
    void ReleasedKey(KeyEvent event) {
        if(event.getCode() == KeyCode.D){
            rightPressed = false;
        }

        if(event.getCode() == KeyCode.A){
            leftPressed = false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        platformControler = new PlatformControler(Plane);
        PlayerComponent = new  PlayerModel(PlayerModel);
        collision = new collisionDetection();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        gameLoop.start();
        load();
    }

    int Timer = 0;

    //Called every game frame
    private void update() {
        PlayerComponent.PlayerUpdate();
        //System.out.println((int) score);
        if ((int)score % 5 == 1){
            System.out.println("Create");
            platformGenerater((int)getRandomNumber(1, 3));}
        platformRemove();
        if ((PlayerModel.getY()+PlayerModel.getLayoutY() <= 300)&&( PlatformMap.get(PlatformMap.size()-1).getY() <= 300 )){screnMove();}
        if (collision.checkPlayerCollisionDetection(PlatformMap,PlayerComponent)){PlayerComponent.jump();}
        if (rightPressed){PlayerComponent.moveRight();}
        if (leftPressed){PlayerComponent.moveLeft();}

        if(PlayerComponent.isPlayerDead(Plane)){
            PlayerComponent.resetPlayer();
        }
    }




    private void load(){
        System.out.println("Game starting");
        PlatformMap.add(platformControler.createPlatfhorm(300, 700));
        PlatformMap.add(platformControler.createPlatfhorm(90, 500));
        PlatformMap.add(platformControler.createPlatfhorm(200, 100));
        platformGenerater(5);
        score = 0;
    }


    private void platformGenerater(int count){
        boolean badGeneration;
        Rectangle newPlatform;
        for (int i = 1; i <= count; i++) {
            do {
                badGeneration = false;
                newPlatform = platformControler.createPlatfhorm(getRandomNumber(20, 500), getRandomNumber(-100, -10));
                for (Rectangle platform : PlatformMap) {
                    if(collision.CollisionDetection(platform, newPlatform) || (platformControler.distance(platform,newPlatform) < 30)){
                        badGeneration = true;
                        platformControler.remove(newPlatform);
                        break;
                        }
                    }
                } while (badGeneration);
                PlatformMap.add(newPlatform);
            }
        }


    public double getRandomNumber(double min, double max){
        return (Math.random() * (max - min)) + min;
    }

    private void screnMove(){
        for (Rectangle platform: PlatformMap){platformControler.movePlatform(platform, 0,(Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY()))*pDelta);}
        PlayerComponent.setPlayer(0,(Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY()))*pDelta);
        score += (Plane.getHeight() - (PlayerModel.getY()+PlayerModel.getLayoutY())*pDelta)/ 10000;
    }
    public void platformRemove()
    {
        for (Rectangle platform: PlatformMap){
            if (platform.getY() > 500) {
                platformControler.remove(platform);
                PlatformMap.remove(platform);
            }
        }
    }
}

