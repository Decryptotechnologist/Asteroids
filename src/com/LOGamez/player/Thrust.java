/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LOGamez.player;

import com.LOGamez.asteroids.Game;
import com.LOGamez.audio.Sound;
import com.LOGamez.graphics.Render;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nicholas Wright
 */
public class Thrust {
    
    /**Attributes*/                                      
    
    /**ang variable of P1Bullet*/
    public float ang;
    
    /**dx variable of P1Bullet*/
    public float dx;
    
    /**dy variable of P1Bullet*/
    public float dy;
    private int x;
    private int y;
    public int width;
    public int height;
    private boolean isDead;
    private static List<Thrust> thrust = new ArrayList<>();
    private static List<Thrust> thrustA; 
    private int life;
    public static Random random = new Random(System.currentTimeMillis());
    private Color col;


    /**Constructor*/
        
    /**
    * P1Bullet(int x, int y, int h, int w, boolean isDead)
    * 
    * Initialises a new P1Bullet object with a this.x set to x,
    * this.y set to y, this.isDead set to isDead, 
    * this.height set to h, this.width set to w, this.visible 
    * set to vis
    * 
    * @param _x - the x coordinate of P1Bullet
    * @param _y - the y coordinate of P1Bullet
    * @param _ang - the ang of P1Bullet
    * @param _dx - the dx of P1Bullet
    * @param _dy - the dy of P1Bullet
    * @param h - the height of P1Bullet
    * @param w - the width of P1Bullet
    * @param isDead - the isDead boolean of P1Bullet
    * @param _col
    */
    public Thrust(int _x, int _y, float _ang, float _dx, float _dy, int h, int w, boolean isDead, Color _col){
        this.x = _x;
        this.y = _y;
        this.dx = _dx;
        this.dy = _dy;
        this.ang = _ang;
        this.width = w;
        this.height = h;
        this.isDead = isDead;
        this.life = random.nextInt(15)+1;
        this.col = _col;
    }
    

    /**
     * setUp()
     * 
     */
    public static void setUp(){
//        int width = Asteroids.getMainWidth();
//        switch(width){
//            case 300:
//                playerBulletScale = 0.225;
//                playerBulletXoffs = (Player.Pwidth /8)-1;
//                playerBulletYoffs = -3;
//            
//                break;
//                
//            case 480:
//                playerBulletScale = 0.45;
//                playerBulletXoffs = -15;
//                playerBulletYoffs = 6;
//                
//                break;
//                 
//            case 604:
//                playerBulletScale = 0.45;
//                playerBulletXoffs = (Player.Pwidth /2)-12;
//                playerBulletYoffs = 16;
//                
//                break;
//                
//            default:
//                playerBulletScale = 0.45;
//                playerBulletXoffs = (Player.Pwidth /2)-12;
//                playerBulletYoffs = 16;
//                
//                break;
//                
//        }
        
    }
    
    
    /**
    * FireP1(Player player)
    * 
    * Fires a bullet, creating a new P1Bullet 
    * object with Player 1 objects x and y 
    * coordinates as a starting point
    * 
    * @param player - a reference to a Player object
    */
    public static void FireP1(Player player){
        random = new Random(System.currentTimeMillis());
        for(int i = 0; i < random.nextInt(6)+3; i++){
            Thrust t1;
            Thrust t2;
            Thrust t3;

            t1 = new Thrust((int) (player.x + random.nextFloat() * i), (int) player.y, player.ang, (float) (1.5f * Math.sin(player.ang + i * 0.06f)), (float) -(1.5f * Math.cos(player.ang + i * 0.06f)), 1, 1, false, Color.decode("#333333"));
            t2 = new Thrust((int) player.x, (int) player.y, player.ang, (float) (1.3f * Math.sin(player.ang)), (float) -(1.68f * Math.cos(player.ang)), 1, 1, false, Color.decode("#666666"));
            t3 = new Thrust((int) (player.x - random.nextFloat() * i), (int) player.y, player.ang, (float) (1.3f * Math.sin(player.ang - i * 0.06f)), (float) -(1.6f * Math.cos(player.ang - i * 0.03f)), 1, 1, false, Color.decode("#131313"));
            thrust.add(t1);
            thrust.add(t2);
            thrust.add(t3);
        }
        if(Game.getGameSound()){
            //Sound.P1Shoot.play();
        }
    }

    
    /**
    * move(int id)
    * 
    * Moves each of the P1Bullet objects (depending how
    * many P1Bullet objects remain in P1Bulletz List) 
    * after checking that the P1Bullet object isDead 
    * value is false and visible value is true.
    * 
    * @param pB the pB reference of an P1Bullet object (within P1Bulletz List)
    * @param player
    */  
//    public static void move(P1Bullet pB, Player player){
//        if(pB.isDead == false){
//            //if(pB.y < Asteroids.getMainHeight() && pB.y > 0 && pB.x > 0 && pB.x < Asteroids.getMainWidth()){
//                //pB.y -= bulletSpeed;
//                pB.x += pB.dx;//Math.sin(pB.ang) / 280.0f;
//                pB.y += pB.dy;//-Math.cos(pB.ang) / 280.0f;
//            //}
//        } else {
//            //P1BHit(pB);
//        }
//    }

    
    /**
    * P1BHit(P1Bullet pB)
    * 
    * P1Bullet objects (referenced by id) 
    * visible and isDead values are changed to false 
    * and true respectively. Before this a P1BDetonate(id) 
    * message is sent
    * 
    * @param pB the pB reference of an P1Bullet object (within P1Bulletz List)
    */
    public static void hit(Thrust pB){
	pB.isDead = true;
    }

    
    /**
    * tick()
    * 
    * Updates P1Bullet Objects
    * 
    */
    public static void tick(){
        thrust = Thrust.getAll();
        //System.out.println("Tick: No of Bullets:"+P1B.size());
        for(Thrust p : thrust){
            p.x -= p.dx;// * time;
            p.y -= p.dy;// * time;
            p.life--;
            
            if(p.life == 0) hit(p);
        }
    }
    
    
    /**
     * render(Render target, Graphics2D g)
     * 
     * @param target
     * @param g
     */
    public static void render(Render target, Graphics2D g){
        if (null == Game.aGame.menu){
            
            
            //Draw P1Bullets
            for(Thrust p : thrust){
                if (p.isDead == false) {
                    g.setColor(p.col);
                    g.drawRect((int) p.x, (int) p.y, p.width, p.height);
//                    if(Game.showBounds){
//                        g.setColor(Color.green);
//                        g.drawRect(p.x+13, p.y+p.height-8, p.width, p.height);
//                    }
                }
            }
            
        }
    }
    
    
    /**
    * getAll()
    * 
    * Returns the List of the P1Bullet 
    * objects referenced by P1Bulletz
    * 
    * @return P1Bulletz
    */
    public static List<Thrust> getAll() {
        thrustA = new ArrayList<>();
        int j = 0;
        
        for (int i = 0; i < thrust.size(); i++) {
            if (thrust.get(i).isDead == false) {
                thrustA.add(j, thrust.get(i));
                
                j++;
            }
        }
        
	return thrustA;
    }

    
    
    /**
    * clearAll()
    * 
    */
    public static void clearAll() {
        thrust.clear();
    }
    
    
    /**
     * getBounds()
     * 
     * @return new Rectangle(this.x, this.y, this.width, this.height)
     */
    public Rectangle getBounds(){
      return new Rectangle(this.x+13, this.y+this.height-8, this.width/4, this.height);
    }
    
}
