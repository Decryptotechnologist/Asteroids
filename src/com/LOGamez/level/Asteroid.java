/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LOGamez.level;

import com.LOGamez.asteroids.Asteroids;
import com.LOGamez.asteroids.Game;
import com.LOGamez.graphics.Texture;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;

/**
 *
 * @author Nicholas Wright
 */
public class Asteroid {
    
    /**Attributes*/
    
    /**x variable of Asteroid*/
    public float x;
    
    /**y variable of Asteroid*/
    public float y;
    
    /**size variable of Asteroid*/
    public int size;
    
    /**vel variable of Asteroid*/
    public float vel;
    
    /**ang variable of Asteroid*/
    public float ang;
    
    /**isDead variable of Asteroid*/
    private boolean isDead;
    
    /**random variable of Asteroid*/
    public static Random random;
    
    /**asteroids variable of Asteroid*/
    public static List<Asteroid> asteroids = new ArrayList<>();
    
    /**asteroidsA variable of Asteroid*/
    public static List<Asteroid> asteroidsA = new ArrayList<>();
    
    /**asteroidVec variable of Asteroid*/
    private Vector2d asteroidVec;
    
    /**asteroidVel variable of Asteroid*/
    private Vector2f asteroidVel;
    
    /**asteroidAcc variable of Asteroid*/
    private Vector2f asteroidAcc;
    
    /**asteroidAcc variable of Asteroid*/
    private long currentTime;
    
    /**asteroidAcc variable of Asteroid*/
    private long prevTime;
    
    /**asteroidAcc variable of Asteroid*/
    private long time;
    
    public Vector2f[] vecModelAsteroid;
    private float dx;
    private float dy;
    public static BufferedImage asteroidIMG;
    public static BufferedImage asteroidIMG1;
    private TexturePaint rockTP;
    private TexturePaint rock1TP;
    
    
    /**Links*/
    
    
    /**Constructor*/
    
    
    /**
     * Asteroid Constructor
     * 
     * @param _x
     * @param _y
     * @param _size
     * @param velX
     * @param velY
     * @param _ang
     * @param _model
     */
    public Asteroid(float _x, float _y, int _size, float velX, float velY, float _ang, Vector2f[] _model, BufferedImage _IMG){
        //System.out.println("Asteroid: New Asteroid Created: x:"+_x+" y:"+_y+" size:"+_size+" ang:"+_ang+" model:"+Arrays.toString(_model));
        this.x = _x;
        this.y = _y;
        this.dx = 0;
        this.dy = 0;
        this.asteroidVec = new Vector2d(this.x, this.y);
        this.asteroidVel = new Vector2f(velX, velY);
        this.asteroidAcc = new Vector2f(0.0f, 0.0f);
        this.size = _size;
        this.ang = (float) Math.toRadians(_ang);
        this.isDead = false;
        this.vecModelAsteroid = _model;
        
        this.rockTP = new TexturePaint(_IMG, new Rectangle((int) this.x, (int) this.y, 280, 320));
    }

    public Asteroid() {
        init();
    }
    
    
    /**Public protocol*/
    
    
    /**
    * init()
    * 
    */
    private void init(){
        
        asteroidIMG = new Texture("/sprites/darkRock").getImage();
        asteroidIMG1 = new Texture("/sprites/dark-rock-texture").getImage();
        
        //rockTP = new TexturePaint(asteroidIMG, new Rectangle(0,0, 564, 848));
        //rock1TP = new TexturePaint(asteroidIMG1, new Rectangle(0,0, 90, 60));
        
        random = new Random(System.currentTimeMillis());
        for(int i = 0; i < 8; i++){
            Asteroid anAsteroid;
            int verts = 10;
            int size = 60;
            int offSet = random.nextInt(12)+3;
            int firstQuarter = Asteroids.getMainWidth()/4;
            int firstQuarterH = Asteroids.getMainHeight()/4;
            int lastQuarter = Asteroids.getMainWidth()-Asteroids.getMainWidth()/4;
            int lastQuarterH = Asteroids.getMainHeight()-Asteroids.getMainHeight()/4;
            float radius = 1.12f;
            double da = Math.PI*2 / radius;
            float noise = (float) (random.nextFloat() * random.nextInt((int) da));
            if(i % 3 == 0) {
                size = 40;
                verts = 20;
            }
            vecModelAsteroid = new Vector2f[verts];
            
            for(int j = 0; j < verts; j++){
                float a1 = (float) ((j / radius) * Math.PI*2);
                if(j % offSet == 0) noise = (float) (random.nextFloat() * random.nextInt((int) da));
                if(noise > radius * 2.5f) noise = radius * 2.5f;
                if(noise < 1.8f) noise = 1.8f;

                vecModelAsteroid[j] = new Vector2f(noise * (radius * (float) Math.sin(a1)), noise * (radius * (float) Math.cos(a1)));
            }

            if(i % 2 == 0){
                anAsteroid = new Asteroid(50 * i + random.nextInt(firstQuarter * i + 100) + noise, random.nextInt(firstQuarterH * i + 100) + noise, size, random.nextFloat(),  random.nextFloat(), random.nextInt(180)/8, vecModelAsteroid, asteroidIMG);
                asteroids.add(anAsteroid);
            } else {
                anAsteroid = new Asteroid(50 * i + random.nextInt(lastQuarter * i + 100) + noise, -50 * i + random.nextInt(lastQuarterH * i + 100) - noise, size, random.nextFloat(),  random.nextFloat(), random.nextInt(180)/8, vecModelAsteroid, asteroidIMG1);
                asteroids.add(anAsteroid);
            }
        }
        
    }
    
    
    /**
    * render()
    * 
    * 
    * @param g2d
    */
    public void render(Graphics2D g2d){
        Graphics2D g2d_Asteroid = g2d;
        AffineTransform oldXForm = g2d.getTransform();
        
        for(Asteroid a : asteroids){
            
            drawWireFrameModel(g2d_Asteroid, a.vecModelAsteroid, a.x, a.y, a.ang, (float) a.size, Color.DARK_GRAY, a.rockTP);
            if(Game.showBounds || Game.showAsteroidBounds){
                Rectangle r = a.getBounds();
                g2d_Asteroid.setColor(Color.yellow);
                g2d_Asteroid.drawRect(r.x, r.y, r.width, r.height);
            }
        }
        
        g2d.setTransform(oldXForm);
        g2d_Asteroid.setTransform(oldXForm);
        
    }
    
    
    /**
    * tick()
    * 
    */
    public void tick(){
        asteroids = getAll();
        
        for(Asteroid a : asteroids){
            a.dx += Math.sin(a.ang) / 5800.0f;
            a.dy += -Math.cos(a.ang) / 5800.0f;
            
            a.x += a.dx;
            a.y += a.dy;
            
            a.ang += 0.05f / 30.0f;

            int offSet = a.size/2;
            if(a.x+offSet < 0) a.x = Asteroids.getMainWidth() - 2+offSet;
            if(a.x-offSet > Asteroids.getMainWidth()) a.x = 2-offSet;
            if(a.y+offSet < 0) a.y = Asteroids.getMainHeight() - 2+offSet;
            if(a.y-offSet > Asteroids.getMainHeight()) a.y = 2-offSet;
        
        }
        
    }
    
    public void drawWireFrameModel(Graphics2D g2d, Vector2f[] vecModelCoordinates, float x, float y, float r, float s, Color col, TexturePaint _rockTP){
        int verts = vecModelCoordinates.length;
        Vector2f[] vecTransformedCoordinates = new Vector2f[verts];
        //g2d.setColor(col);
        g2d.setPaint(_rockTP);
        
        //Rotate
        for(int i = 0; i < verts; i++){
            vecTransformedCoordinates[i] = new Vector2f(0, 0);
            vecTransformedCoordinates[i].x = vecModelCoordinates[i].x * (float) Math.cos(r) - vecModelCoordinates[i].y * (float) Math.sin(r);
            vecTransformedCoordinates[i].y = vecModelCoordinates[i].x * (float) Math.sin(r) + vecModelCoordinates[i].y * (float) Math.cos(r);
        }
        
        //Scale
        for(int i = 0; i < verts; i++){
            vecTransformedCoordinates[i].x = vecTransformedCoordinates[i].x * s;
            vecTransformedCoordinates[i].y = vecTransformedCoordinates[i].y * s;
        }

        //Translate
        for(int i = 0; i < verts; i++){
            vecTransformedCoordinates[i].x = vecTransformedCoordinates[i].x + x;
            vecTransformedCoordinates[i].y = vecTransformedCoordinates[i].y + y;
        }

        //Draw Closed Polygon
//        for(int i = 0; i < verts + 1; i++){
//            int j = (i + 1);
//            g2d.drawLine((int) vecTransformedCoordinates[i % verts].x, (int) vecTransformedCoordinates[i % verts].y, (int) vecTransformedCoordinates[j % verts].x, (int) vecTransformedCoordinates[j % verts].y);
//        }
        
        GeneralPath ast = new GeneralPath();
        ast.moveTo(vecTransformedCoordinates[0].x, vecTransformedCoordinates[0].y);
        for(int i = 1; i < verts - 1; i++){
            ast.lineTo(vecTransformedCoordinates[i % verts].x, (int) vecTransformedCoordinates[i % verts].y);
        }
        ast.closePath();
        g2d.fill(ast);
        
        g2d.setColor(col);
        g2d.draw(ast);
        
    }
    
    
    /**
    * move(Asteroid a)
    * 
    * 
    * @param a
    */
    public void move(Asteroid a){
    }
    
    
    /**
    * hit(Asteroid a)
    * 
    * 
    * @param a
    */
    public static void hit(Asteroid a){
        Game.increaseScore(250);
        random = new Random(System.currentTimeMillis());
        if(a.size == 60){
            //Create 2 new Asteroids size == 40
            Asteroid a0 = new Asteroid(a.x + random.nextInt(60)+160, a.y - random.nextInt(140), 40, random.nextFloat(), -random.nextFloat(), (float) (random.nextFloat()*Math.PI*2), a.vecModelAsteroid.clone(), asteroidIMG);
            Asteroid a1 = new Asteroid(a.x - random.nextInt(60)+160, a.y - random.nextInt(140), 40, random.nextFloat(), -random.nextFloat(), (float) (random.nextFloat()*Math.PI*2), a.vecModelAsteroid.clone(), asteroidIMG);
            
            asteroids.add(a0);
            asteroids.add(a1);
        }
        if(a.size == 40){
            //Create 2 new Asteroids size == 30
            Asteroid a0 = new Asteroid(a.x + random.nextInt(60)+160, a.y - random.nextInt(140), 30, random.nextFloat(), -random.nextFloat(), random.nextInt(180)/8, a.vecModelAsteroid.clone(), asteroidIMG);
            Asteroid a1 = new Asteroid(a.x - random.nextInt(60)+60, a.y - random.nextInt(40), 30, random.nextFloat(), -random.nextFloat(), random.nextInt(180)/8, a.vecModelAsteroid.clone(), asteroidIMG);
            
            asteroids.add(a0);
            asteroids.add(a1);
        }
        a.dead();
    }
    
    
    /**
    * explode(Asteroid a)
    * 
    * 
    * @param a
    */
    public void explode(Asteroid a){
    }
    
    
    /**
    * dead(Asteroid a)
    * 
    */
    public void dead(){
        this.isDead = true;
    }
    
    
    /**
    * getAll()
    * 
    * 
    * @return 
    */
    public static List<Asteroid> getAll(){
        asteroidsA = new ArrayList<>();
        int j = 0;
        
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).isDead == false) {
                asteroidsA.add(j, asteroids.get(i));
                
                j++;
            }
        }
        return asteroidsA;
    }
    
    
    /**
    * clearAll()
    * 
    */
    public static void clearAll() {
        asteroids.clear();
    }
    

    /**
    * getBounds()
    * 
    * 
    * @return new Rectangle(this.x, this.y, this.size, this.size);
    */
    public Rectangle getBounds() {
        float offSet = 3.0f;
        return new Rectangle((int) (this.x - (this.size * offSet)/2), (int) (this.y - (this.size * offSet)/2), (int) (this.size * offSet), (int) (this.size * offSet));
    }
    
    public void wrapCoordinates(float ox, float oy, float ix, float iy){
        ox = ix;
        oy = iy;
        
        if(ix < 0.0f) ox = ix + Asteroids.getMainWidth();
        if(iy < 0.0f) oy = iy + Asteroids.getMainHeight();
        if(ix > Asteroids.getMainWidth()) ix = 0.0f;
        if(iy > Asteroids.getMainHeight()) iy = 0.0f;
    }
    
}
