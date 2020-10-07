/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LOGamez.player;

import com.LOGamez.level.*;
import com.LOGamez.asteroids.*;
import com.LOGamez.audio.Sound;
import com.LOGamez.bullets.P1Bullet;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector2f;

/**
 *
 * @author Nicholas Wright
 */
public class Player {

    /**Attributes*/
    
    /**x variable of Player*/ 
    public float x;
    
    /**y variable of Player*/ 
    public float y;
    
    /**dx variable of Player*/ 
    float dx;
    
    /**dy variable of Player*/ 
    float dy;
    
    /**Pwidth variable of Player*/ 
    public static int Pwidth;
    
    /**Pheight variable of Player*/ 
    public static int Pheight;
    
    /**lives variable of Player*/ 
    public static int lives;
    
    /**startLives variable of Player*/
    int startLives;
    
    /**LALCount variable of Player*/
    int LALCount;
    
    /**P1Rect variable of Player*/
    public static Rectangle P1Rect;
    
    /**moveSpeed variable of Player*/
    public static int moveSpeed;
    
    /**pLives0X variable of Player*/
    public static int pLives0X = 160;
    
    /**pLives1X variable of Player*/
    public static int pLives1X = 190;
    
    /**pLives2X variable of Player*/
    public static int pLives2X = 220;
    
    /**pLivesScale variable of Player*/
    public static int pLivesScale = 16;
    
    /**pLivesY variable of Player*/
    public static int pLivesY = 26;
    
    /**isFiring variable of Player*/
    boolean isFiring;
    
    /**isDead variable of Player*/
    public boolean isDead;
    
    /**mouseLeft variable of Player*/
    public static boolean mouseLeft;
    
    /**mouseRight variable of Player*/
    public static boolean mouseRight;
    
    /**mouseFire variable of Player*/
    public static boolean mouseFire;
    
    /**playerImg variable of Player*/
    BufferedImage playerImg;
    
    /**pLivesImg variable of Player*/
    public static BufferedImage pLivesImg;
    
    /**imgP1 variable of Player*/
    BufferedImage imgP1;
    
    /**imgPLives variable of Player*/
    public static BufferedImage imgPLives;
    
    /**playerName variable of Player*/
    String playerName;
    
    /**playerImg0 variable of Player*/
    BufferedImage playerImg0;
    
    /**playerImg1 variable of Player*/
    BufferedImage playerImg1;
    
    /**playerImg2 variable of Player*/
    BufferedImage playerImg2;
    
    /**playerVec variable of Player*/
    public static Vector2d playerVec;
    
    /**power variable of Player*/
    public static int power;

    /**levelNo variable of Player*/
    public static String levelNo;
    
    /**livesNo variable of Player*/
    public static String livesNo;
    
    /**playScore variable of Player*/
    private String playScore;
    
    /**leftBlocked variable of Player*/
    //private boolean leftBlocked = false;
    
    /**rightBlocked variable of Player*/
    //private boolean rightBlocked = false;
    
    /**upBlocked variable of Player*/
    //private boolean upBlocked = false;
    
    /**downBlocked variable of Player*/
    //private boolean downBlocked = false;
    
    /**rLeft variable of Player*/
    //private Rectangle rLeft;
    
    /**rRight variable of Player*/
    //private Rectangle rRight;
    
    /**rTop variable of Player*/
    //private Rectangle rTop;
    
    /**rBottom variable of Player*/
    //private Rectangle rBottom;
    
    /**isMoving variable of Player*/
    //private boolean isMoving;
    
    /**playerAnim variable of Player*/
//    private Animation playerAnim = new Animation(18, 
//        new Texture("/sprites/pac_R0"), 
//        new Texture("/sprites/pac_R1"), 
//        new Texture("/sprites/pac_R0"), 
//        new Texture("/sprites/pac_R1")
//    );
    
    /**playerLeftAnim variable of Player*/
//    private final Animation playerLeftAnim = new Animation(18, 
//        new Texture("/sprites/pac_L0"), 
//        new Texture("/sprites/pac_L1"), 
//        new Texture("/sprites/pac_L0"), 
//        new Texture("/sprites/pac_L1")
//    );
    
    /**playerRightAnim variable of Player*/
//    private final Animation playerRightAnim = new Animation(18, 
//        new Texture("/sprites/pac_R0"), 
//        new Texture("/sprites/pac_R1"), 
//        new Texture("/sprites/pac_R0"), 
//        new Texture("/sprites/pac_R1")
//    );
    
    /**playerUpAnim variable of Player*/
//    private final Animation playerUpAnim = new Animation(18, 
//        new Texture("/sprites/pac_U0"), 
//        new Texture("/sprites/pac_U1"), 
//        new Texture("/sprites/pac_U0"), 
//        new Texture("/sprites/pac_U1")
//    );
    
    /**playerDownAnim variable of Player*/
//    private final Animation playerDownAnim = new Animation(18, 
//        new Texture("/sprites/pac_D0"), 
//        new Texture("/sprites/pac_D1"), 
//        new Texture("/sprites/pac_D0"), 
//        new Texture("/sprites/pac_D1")
//    );
    
    /**playerDeadAnim variable of Player*/
//    private final Animation playerDeadAnim = new Animation(18, 
//        new Texture("/sprites/pac_Dead0"), 
//        new Texture("/sprites/pac_Dead1"), 
//        new Texture("/sprites/pac_Dead2"), 
//        new Texture("/sprites/pac_Dead3"),
//        new Texture("/sprites/pac_Dead4"),
//        new Texture("/sprites/pac_Dead5")    
//    );
    
    /**isDeadCount variable of Player*/
    public int isDeadCount = 0;
    private Vector2d playerVel;
    private Vector2d playerAcc;
    public float ang;
    
    public Vector2f[] vecModelShip;

    
    /**Links*/
    
    
    
    
    
    /**Constructor*/
    
    
    /**
     * Player Constructor
     * 
     * 
     * @param _x
     * @param _y
     * @param _w
     * @param _h
     * @param name
     * @param Lives
     */
    public Player(float _x, float _y, int _w, int _h, String name, int Lives) {
        System.out.println("Player: New Player Created");
        
        this.x = _x;
        this.y = _y;
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.Pwidth = _w;
        this.Pheight = _h;
        this.playerVec = new Vector2d(this.x, this.y);
        this.playerVel = new Vector2d(this.x, this.y);
        this.playerAcc = new Vector2d(this.x, this.y);
        this.ang = 0.0f;
        
        playerName = name;
        lives = Lives;
        this.startLives = lives;
        this.LALCount = 0;
        isDead = false;
                
        vecModelShip = new Vector2f[]{
            new Vector2f(0.0f, -15.5f),
            new Vector2f(-12.5f, +12.5f),
            new Vector2f(+12.5f, +12.5f)
        };
    }
    
    
    
    /**Public Protocol*/
    
    
    /**
     * increasePlayerLives(int liv)
     * 
     * 
     * @param liv
     */
    public void increasePlayerLives(int liv) {
        lives += liv;
    }
    
    
    /**
     * render(Game game, Graphics2D g2d)
     * 
     *
     * @param g2d
     */
    public void render(Graphics2D g2d){
        Graphics2D g2d_Player = g2d;
        AffineTransform oldXForm = g2d.getTransform();
        
        //Draw Game Title
        g2d_Player.setColor(Color.GREEN);
        g2d_Player.drawString(Game.TITLE, Game.gameWidth/2 - Game.TITLE.length() * 3, 12);
        ///////////////////////////////////////////////////////////////////////////////////

        //Draw Player Name
        g2d_Player.setColor(Color.GRAY);
        g2d_Player.drawString("Player Name: ", Game.gameWidth/2 - Game.getPName().length() * 8, 28);
        g2d_Player.setColor(Color.GRAY);
        g2d_Player.drawString(Game.getPName(), Game.gameWidth/2 + Game.getPName().length() * 2, 28);
        ///////////////////////////////////////////////////////////////////////////////////
        
        //Draw Player Lives
        if(lives == 3){            
            drawWireFrameModel(g2d_Player, vecModelShip, pLives2X, pLivesY, 0f, 1.0f, Color.WHITE);
            drawWireFrameModel(g2d_Player, vecModelShip, pLives1X, pLivesY, 0f, 1.0f, Color.WHITE);
            drawWireFrameModel(g2d_Player, vecModelShip, pLives0X, pLivesY, 0f, 1.0f, Color.WHITE);
        }
        
        if(lives == 2){
            drawWireFrameModel(g2d_Player, vecModelShip, pLives1X, pLivesY, 0f, 1.0f, Color.WHITE);
            drawWireFrameModel(g2d_Player, vecModelShip, pLives0X, pLivesY, 0f, 1.0f, Color.WHITE);
        }
        
        if(lives == 1){
            drawWireFrameModel(g2d_Player, vecModelShip, pLives0X, pLivesY, 0f, 1.0f, Color.WHITE);
        }
        ///////////////////////////////////////////////////////////////////////////////////
        
        //Draw Hi-Score
        Font currentFont = new Font("default", Font.BOLD, 28);
        g2d_Player.setFont(currentFont);
        g2d_Player.setColor(Color.YELLOW);
        ///////////////////////////////////////////////////////////////////////////////////
        
        //Draw Current Score
        String currentScore;
        if(Game.gameScore == 0){ 
            currentScore = "000000";
        } else if(Game.gameScore > 0 && Game.gameScore < 1001){
            currentScore = "000"+Game.gameScore;
        } else if(Game.gameScore > 1001 && Game.gameScore < 10000){ 
            currentScore = "00"+Game.gameScore;
        } else if(Game.gameScore > 10000 && Game.gameScore < 100000){
            currentScore = "0"+Game.gameScore;
        } else if(Game.gameScore > 100000 && Game.gameScore < 1000000){ 
            currentScore = "0"+Game.gameScore;
        } else {
            currentScore = ""+Game.gameScore;
        }
        
        g2d_Player.drawString(currentScore, 36, 30);
        currentFont = new Font("default", Font.PLAIN, 10);
        g2d_Player.setFont(currentFont);
        ///////////////////////////////////////////////////////////////////////////////////
        
//        if(isDead) playerDeadAnim.render(g2d, x, y, Pwidth, Pheight);
                
        if(this.isDead == false){
            
            drawWireFrameModel(g2d_Player, vecModelShip, this.x, this.y, this.ang, 1.0f, Color.WHITE);
            
            //g2d_Player.drawImage(imgP1, x, y, Pwidth, Pheight, null);//Pwidth, Pheight, null);
            //g2d_Player.drawLine(x, y-8, x-4, y+8);
            //g2d_Player.drawLine(x, y-8, x+4, y+8);
            //g2d_Player.drawLine(x-4, y+8, x+4, y+8);
            ///////////////////////////////////////////////////////////////////////////////////
        
            
            //Draw Bounds
            if(Game.showBounds || Game.showPlayerBounds){
                //Draw Bounding Box
                g2d_Player.setColor(Color.CYAN);
                g2d_Player.drawRect((int) this.x, (int) this.y, this.Pwidth, this.Pheight);
                g2d_Player.drawString("x:"+this.x+" y:"+this.y+" ang:"+this.ang, (int) this.x, (int) this.y+12);
                ///////////////////////////////////////////////////////////////////////////////////
            
                g2d_Player.setColor(Color.red);
                g2d_Player.drawRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
                
                g2d_Player.setColor(Color.DARK_GRAY);
//                g2d_Player.drawRect(rRight.x, rRight.y, rRight.width, rRight.height);
                
                g2d_Player.setColor(Color.BLUE);
//                g2d_Player.drawRect(rLeft.x, rLeft.y, rLeft.width, rLeft.height);
                
                g2d_Player.setColor(Color.white);
//                g2d_Player.drawRect(rTop.x, rTop.y, rTop.width, rTop.height);
                
                g2d_Player.setColor(Color.yellow);
//                g2d_Player.drawRect(rBottom.x, rBottom.y, rBottom.width, rBottom.height);
                
                g2d_Player.setColor(Color.green);
                g2d_Player.drawOval((int) this.x, (int) this.y, this.Pwidth, this.Pheight);
                
                g2d_Player.setColor(Color.orange);
//                g2d_Player.drawLine(x + Pwidth/2, y, x + Pwidth/2, y+14);
//                g2d_Player.drawLine(x, y + Pheight/2, x + 64, y + Pheight/2);
            }
            ///////////////////////////////////////////////////////////////////////////////////
            
        }
        
        g2d.setTransform(oldXForm);
        g2d_Player.setTransform(oldXForm);
    
    }
    
    
    /**
     * tick(Game game, boolean up, boolean down, boolean left, boolean right, boolean fire)
     * 
     * 
     * @param game
     * @param up
     * @param down
     * @param left
     * @param right
     * @param fire
     */
    public void tick(Game game, boolean up, boolean down, boolean left, boolean right, boolean fire){//, boolean rotLeft, boolean rotRight){
        //checkCollisions();
        //Load Player Lives Image
        imgPLives = pLivesImg;        
        
        if(left){
            this.ang -= 0.03f;// * time;
            
        } else 
        
        if(right){
            this.ang += 0.03f;// * time;
            
        } else
            
        if(down){

        } else 
        
        if(up){
            this.dx += Math.sin(this.ang) / 280.0f;
            this.dy += -Math.cos(this.ang) / 280.0f;
            
            Thrust.FireP1(this);
        }
        
        if(fire){
            if(game.menu == null){
                if(isFiring == false){
                    P1Bullet.FireP1(this);
                    //isFiring = true;
                    //shotsFired++;
                }
            }
        }
        
//        if(mouseFire){
//            if(game.menu == null){
//                if(isFiring == false){
//                    P1Bullet.FireP1(this);
//                    isFiring = true;
//                    fire = false;
//                }
//            }
//        }
        
        if(Player.lives == 0){
            isDead = true;
            dead();
	}
        
        Player.lives = Player.getLives();
        this.x += this.dx * 10.0f;
        this.y += this.dy * 10.0f;
        
        if(this.x < 0) this.x = Asteroids.getMainWidth() - 5;
        if(this.x > Asteroids.getMainWidth()) this.x = 5;
        if(this.y < 0) this.y = Asteroids.getMainHeight() - 5;
        if(this.y > Asteroids.getMainHeight()) this.y = 5;
        
        //wrapCoordinates(this.x, this.y, this.x, this.y);
        
        playerVec = new Vector2d(x, y);
        if(dx > 0.0f || dy > 0.0f){
        dx += 0.001f / 10000;
        dy += -(0.001f / 10000);
        }
        
//        if(!up && !down && !left && !right){
//            isMoving = false;
//        } else {
//            playerAnim.run();
//            playerLeftAnim.run();
//            playerRightAnim.run();
//            playerUpAnim.run();
//            playerDownAnim.run();
//        }
        
//        playerDeadAnim.run();
        
        if(isDeadCount > 0 && isDead){ 
            isDeadCount--;
            //System.out.println("Target Count: "+isDeadCount);
        } 
        if(isDeadCount == 0 && isDead){
            resetPlayer();
        }
        
    }
    
    
    public void drawWireFrameModel(Graphics2D g2d, Vector2f[] vecModelCoordinates, float x, float y, float r, float s, Color col){
        
        //Vector2f[] vecTransformedCoordinates = vecModelCoordinates.clone();//new Vector2f[3];
        int verts = vecModelCoordinates.length;
        Vector2f[] vecTransformedCoordinates = new Vector2f[verts];
        g2d.setColor(col);
        
        //Rotate
        for(int i = 0; i < verts; i++){
            vecTransformedCoordinates[i] = new Vector2f(0,0);
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
        for(int i = 0; i < verts + 1; i++){
            int j = (i + 1);
            g2d.drawLine((int) vecTransformedCoordinates[i % verts].x, (int) vecTransformedCoordinates[i % verts].y, (int) vecTransformedCoordinates[j % verts].x, (int) vecTransformedCoordinates[j % verts].y);
        }
    }
    
    
    /**
    * P1Hit()
    * 
    */
    public void P1Hit(){
        if(isDeadCount == 0 && isDead == false){
            loseALife();
            if(LALCount < startLives){
                LALCount += 1;
            } else {
                LALCount = 0;
            }
        }
    }
    
    
    /**
    * loseALife()
    * 
    */
    public void loseALife(){
        if(LALCount < 1){
            if(lives >= 1){
                this.isDeadCount = 110;
                this.isDead = true;
                lives--;
                
                if(Game.getGameSound()){
                    Sound.P1Exp.play();
                }
            }
        }
    }
    
    
    /**
    * dead()
    * 
    * 
    */
    public void dead(){
        System.out.println("Player: GAME OVER!!!");
        isDead = true;
        Game.loseGame();
    }
    
    
    /**
    * getLives()
    * 
    * 
    * @return lives
    */
    public static int getLives() {
        return lives;
    }
    
    
    /**
    * setPlayerName(String name)
    * 
    * 
    * @param name
    */
    public void setPlayerName(String name) {
        this.playerName = name;
    }
    
    
    
    /**
    * setPOW(int pow)
    * 
    * 
    * @param pow
    */
    public static void setPOW(int pow) {
        
    }
    
    
    /**
    * getPOW()
    * 
    * 
    * @return power
    */
    public static int getPOW() {
        return power;
    }

    
    /**
    * extraLife()
    * 
    * Checks if LALCount is less than 1 then 
    * checks if live is greater than or equal 
    * to 1. As the checks pass lives is 
    * decremented by 1 and a P1Explode is 
    * fired via the P1Explode.P1Detonate(this) 
    * message.
    * 
    */
    public void extraLife(){
	lives++;
	
        setPOW(getPOW());
    }

    
    /**
    * getPlayScore()
    * 
    * 
    * @return playScore
    */
    public String getPlayScore() {
        return playScore;
    }

    
    /**
    * setLives(int liv)
    * 
    * 
    * @param liv
    */
    public void setLives(int liv) {
        lives = liv;
    }

    
    /**
     * getBounds()
     * 
     * 
     * @return n1
     */
    public Rectangle getBounds() {
        Rectangle n1 = new Rectangle((int) this.x-this.Pwidth/2, (int) this.y-this.Pheight/2, this.Pwidth, this.Pheight);
        return n1;
    }

    
    /**
     * checkCollisions()
     * 
     * 
     */
    private void checkCollisions() {
//        mazes = Maze.getAllRect();
//        foods = Food.getAll();
//        pills = Pill.getAll();
//        ghosts = Enemy.getAllEnemies();
        
//        for(Rectangle rMaze : mazes){
//            if(rLeft.intersects(rMaze)) leftBlocked = true;
//            
//            if(rRight.intersects(rMaze)) rightBlocked = true;
//            
//            if(rTop.intersects(rMaze)) upBlocked = true;
//            
//            if(rBottom.intersects(rMaze)) downBlocked = true;
//            
//        }
        
//        Rectangle pR = new Rectangle(this.x, this.y, this.Pwidth, this.Pheight);
//        for(Food aFood : foods){
//            Rectangle r1 = new Rectangle(aFood.x, aFood.y, aFood.width, aFood.height);
//            if(pR.intersects(r1)){
//                //increase score
//                Game.increaseScore(10);
//                
//                //Food hit
//                Food.hit(aFood);
//            }
//        }
        
//        for(Pill aPill : pills){
//            Rectangle r1 = new Rectangle(aPill.x, aPill.y, aPill.width, aPill.height);
//            if(pR.intersects(r1)){
//                //increase score
//                Game.increaseScore(50);
//                
//                //Change Enemy Behavior
////                for(Enemy aGhost : ghosts){
////                    aGhost.isTarget = true;
////                    Enemy.isTargetCount = 400;
////                }
//                
//                //Pill hit
//                Pill.hit(aPill);
//            }
//        }
        
//        for(Enemy aGhost : ghosts){
//            Rectangle r1 = new Rectangle(aGhost.x, aGhost.y, aGhost.width, aGhost.height);
//            if(pR.intersects(r1)){
//                
//                if(aGhost.isTarget){
//                    //increase score
//                    Game.increaseScore(200);
//
//                    //Change Enemy Behavior
//
//                    //Enemy hit
//                    Enemy.EnemyHit(aGhost);
//                } else {
//                    if(!aGhost.isDead){
//                        P1Hit();
//                    
//                        break;
//                    }
//                }
//                
//            }
//            
//        }
    }

    public void wrapCoordinates(int ox, int oy, int ix, int iy){
        ox = ix;
        oy = iy;
        
        if(ix < 0) ox = ix + Asteroids.getMainWidth();
        if(iy < 0) oy = iy + Asteroids.getMainHeight();
        if(ix > Asteroids.getMainWidth()) ix = 0;
        if(iy > Asteroids.getMainHeight()) iy = 0;
    }
    
    /**
     * getX()
     * 
     * @return (int) playerVec.x
     */
    public static int getX(){
        return (int) playerVec.x;
    }
    
    
    /**
     * getY()
     * 
     * @return (int) playerVec.y
     */
    public static int getY(){
        return (int) playerVec.y;
    }

    
    /**
     * resetPlayer()
     * 
     */
    private void resetPlayer() {
        this.x = Level.playerStartX;
        this.y = Level.playerStartY;
        this.ang = 0f;
        this.dx = 0;
        this.dy = 0;
        isDead = false;
    }

}
