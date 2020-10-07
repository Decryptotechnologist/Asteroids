/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LOGamez.bullets;

import com.LOGamez.asteroids.*;
import com.LOGamez.level.Asteroid;
import com.LOGamez.level.Level;
import com.LOGamez.player.Player;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Ghomez
 */
public class Bullet {
    
    /**Attributes*/
    
    /**x variable of Bullet*/
    public int x;
        
    /**y variable of Bullet*/
    public int y;
        
    /**height variable of Bullet*/
    public int height;
        
    /**width variable of Bullet*/
    public int width;
        
    /**width variable of Bullet*/
    public static int w;

    /**height variable of Bullet*/
    public static int h;
    
    /**isDead variable of Bullet*/
    public boolean isDead;
    
    /**bulletSpeed variable of Bullet*/
    public static int bulletSpeed;
    
    /**playerBulletScale variable of Bullet*/
    public static double playerBulletScale;
    
    /**playerBulletXoffs variable of Bullet*/
    public static int playerBulletXoffs;
    
    /**playerBulletYoffs variable of Bullet*/
    public static int playerBulletYoffs;
    
    /**P1B variable of Bullet*/
    public static List<P1Bullet> P1B = new ArrayList<>();
    
    /**P1BA variable of Bullet*/
    public static List<P1Bullet> P1BA;
    
    
    
    /**Constructor*/
    
    /**
    * Bullet()
    * 
    * Initialises a new Bullet object
    */
    public Bullet(){
        //System.out.println("Bullet: New Bullet Created");
        setUp();
    }
    
    
    /**Public Protocol*/
    
    
    /**
     * setUp()
     * 
     * Setup Bullet Objects
     */
    public static void setUp(){
        P1Bullet.setUp();
        
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                w = 10;
                h = 20;
            
                bulletSpeed = 9;
            
                break;
                
            case 480:
                w = 12;
                h = 25;
            
                bulletSpeed = 12;
            
                break;
                
            case 604:
                w = 15;
                h = 35;
            
                bulletSpeed = 17;
                break;
                
            default:
                w = 15;
                h = 35;
            
                bulletSpeed = 17;
                break;
                
        }
        
    }

    
    /**
    * tick()
    * 
    * Updates Bullet Objects
    */
    public static void tick(){
        //Check All Bullet Collisions
        checkCollisions();
        
//        for(int i = 0; i < P1B.size(); i++){
//            //if(P1B.get(i).isDead == false){
//                move(P1B.get(i));
//            //}
//        }
        
        //Tick(Update) Player Bullets
        P1Bullet.tick();
    }
    
    
    /**
    * move(Bullet b)
    * 
    * @param b - a Bullet instance
    */
    public static void move(Bullet b) {
        if(!Game.paused) {
            if(b instanceof P1Bullet){
                //Move Player Bullet: b
//                P1Bullet.move((P1Bullet) b, Game.ship);
            }
        }
    }
    
    
    /**
    * checkCollisions()
    * 
    */
    public static void checkCollisions() {

	//Check collisions between P1BulletRect&AllAsteroidRect
	for(int i = 0; i < P1B.size(); i++){
            if(P1B.get(i).isDead == false){

                //Check collisions between P1Bullet & Asteroid
                for (Asteroid a1 : Asteroid.getAll()) {
                    //for (int k = 0; k < Asteroid.getAll().size(); k++) {
                        if (P1B.get(i).getBounds().intersects(a1.getBounds())) {
                            
                            //message P1Bullet(id) hit
                            BulletHit(P1B.get(i));
                            
                            //message Asteroid(id) hit
                            Asteroid.hit(a1);
                        }
                    //}
                }
                
            }
            
	}
        
        //Check collisions between P1Bullet & Asteroid
        for (Asteroid a1 : Asteroid.getAll()) {
            if(Game.ship.isDead == false && Game.ship.isDeadCount == 0){
                if (Game.ship.getBounds().intersects(a1.getBounds())) {

                    //message P1Bullet(id) hit
                    Game.ship.P1Hit();

                    //message Asteroid(id) hit
                    Asteroid.hit(a1);
                }
            }
        }
        
    }
    
    
    /**
    * BulletHit(Bullet b)
    * 
    * @param b - a Bullet instance
    */
    public static void BulletHit(Bullet b){
        if(b instanceof P1Bullet){
            //Player Bullet Hit: b
            P1Bullet.P1BHit((P1Bullet) b);
        }
    }
    
}
