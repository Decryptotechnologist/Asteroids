package com.LOGamez.bullets;

//import com.LOGamez.explodes.P1BExplode;
import com.LOGamez.graphics.*;
import com.LOGamez.asteroids.*;
import com.LOGamez.audio.Sound;
import java.awt.*;
import java.util.List;
import com.LOGamez.player.Player;
import java.util.ArrayList;

/**
 * P1Bullet objects represent Player 1 bullets in the game
 * represented by a small white square shape bullets
 *  fired by the player. 
 * 
 * @author(s) Ghomez
 */
public class P1Bullet extends Bullet {
    
    /**Attributes*/                                      
    
    /**ang variable of P1Bullet*/
    private float ang;
    
    /**dx variable of P1Bullet*/
    private float dx;
    
    /**dy variable of P1Bullet*/
    private float dy;



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
    */
    public P1Bullet(int _x, int _y, float _ang, float _dx, float _dy, int h, int w, boolean isDead){
        this.x = _x;
        this.y = _y;
        this.dx = _dx;
        this.dy = _dy;
        this.ang = _ang;
        this.width = w;
        this.height = h;
        this.isDead = isDead;
    }
    

    /**
     * setUp()
     * 
     */
    public static void setUp(){
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                playerBulletScale = 0.225;
                playerBulletXoffs = (Player.Pwidth /8)-1;
                playerBulletYoffs = -3;
            
                break;
                
            case 480:
                playerBulletScale = 0.45;
                playerBulletXoffs = -15;
                playerBulletYoffs = 6;
                
                break;
                 
            case 604:
                playerBulletScale = 0.45;
                playerBulletXoffs = (Player.Pwidth /2)-12;
                playerBulletYoffs = 16;
                
                break;
                
            default:
                playerBulletScale = 0.45;
                playerBulletXoffs = (Player.Pwidth /2)-12;
                playerBulletYoffs = 16;
                
                break;
                
        }
        
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
        //System.out.println("Firing Bullet!! x:"+player.x+" y:"+player.y+" ang:"+player.ang+" dx:"+(5.0f * Math.sin(player.ang))+" dy:"+-(5.0f * Math.cos(player.ang)));
        P1Bullet p1b;
        
        p1b = new P1Bullet((int) player.x, (int) player.y, 0f, (float) (5f * Math.sin(player.ang)), (float) -(5f * Math.cos(player.ang)), 2, 2, false);
        P1B.add(p1b);
        
        if(Game.getGameSound()){
            Sound.P1Shoot.play();
        }
        //System.out.println("No of Bullets:"+P1B.size());
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
    public static void P1BHit(P1Bullet pB){
//	P1BExplode.P1BDetonate(pB);
	pB.isDead = true;
    }

    
    /**
    * tick()
    * 
    * Updates P1Bullet Objects
    * 
    */
    public static void tick(){
        P1B = P1Bullet.getAll();
        //System.out.println("Tick: No of Bullets:"+P1B.size());
        for(P1Bullet p : P1B){
            p.x += p.dx;// * time;
            p.y += p.dy;// * time;

            if((int) p.x < 0) P1BHit(p);
            if((int) p.x > Asteroids.getMainWidth()) P1BHit(p);
            if((int) p.y < 0) P1BHit(p);
            if((int) p.y > Asteroids.getMainHeight()) P1BHit(p);
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
            g.setColor(Color.white);
            
            //Draw P1Bullets
            for (P1Bullet P1B1 : P1B) {
                if (P1B1.isDead == false) {
                    g.drawRect((int) P1B1.x, (int) P1B1.y, P1B1.width, P1B1.height);
                    if(Game.showBounds || Game.showBulletBounds){
                        Rectangle r = P1B1.getBounds();
                        g.setColor(Color.green);
                        g.drawRect(r.x, r.y, r.width, r.height);
                    }
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
    public static List<P1Bullet> getAll() {
        P1BA = new ArrayList<>();
        int j = 0;
        
        for (int i = 0; i < P1B.size(); i++) {
            if (P1B.get(i).isDead == false) {
                P1BA.add(j, P1B.get(i));
                
                j++;
            }
        }
        
	return P1BA;
    }

    
    
    /**
    * clearAll()
    * 
    */
    public static void clearAll() {
        P1B.clear();
    }
    
    
    /**
     * getBounds()
     * 
     * @return new Rectangle(this.x, this.y, this.width, this.height)
     */
    public Rectangle getBounds(){
      return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
}
