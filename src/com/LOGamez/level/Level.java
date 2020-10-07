/*
OneLoneCoder.com - Code-It-Yourself! Asteroids at the command prompt (quick and simple c++)
"This one just kept growing..." - @Javidx9
License
~~~~~~~
One Lone Coder Console Game Engine  Copyright (C) 2018  Javidx9
This program comes with ABSOLUTELY NO WARRANTY.
This is free software, and you are welcome to redistribute it
under certain conditions; See license for details. 
Original works located at:
https://www.github.com/onelonecoder
https://www.onelonecoder.com
https://www.youtube.com/javidx9
GNU GPLv3
https://github.com/OneLoneCoder/videos/blob/master/LICENSE
From Javidx9 :)
~~~~~~~~~~~~~~~
Hello! Ultimately I don't care what you use this for. It's intended to be 
educational, and perhaps to the oddly minded - a little bit of fun. 
Please hack this, change it and use it in any way you see fit. You acknowledge 
that I am not responsible for anything bad that happens as a result of 
your actions. However this code is protected by GNU GPLv3, see the license in the
github repo. This means you must attribute me if you use it. You can view this
license here: https://github.com/OneLoneCoder/videos/blob/master/LICENSE
Cheers!
Background
~~~~~~~~~~
I needed a quick video to keep up with my upload schedule, and thought "oh asteroids
must be simple", and indeed it was, but it was a bit rubbish, and didn't really
offer anything educational, until I made the decision to ditch sprites and use
vector models like the original game. This made the game considerably more sophisticated,
has opened up new possibilities for the game engine, and just goes to show, jus 'cos
it's old, doesn't mean it's irrelevant.
I ended up with a demonstration of physics, wireframe graphics, 2D matrix transformations,
toroidal space mapping and vector manipulation.
Controls
~~~~~~~~
Left and Right arrow keys steer the ship. Up arrow is thrust. There is no reverse thrust.
Space bar fires bullets. Get the highest score by surviving waves of asteroids.
Author
~~~~~~
Twitter: @javidx9
Blog: www.onelonecoder.com
YouTube: www.youtube.com/javidx9
Video:
~~~~~~
https://youtu.be/QgDR8LrRZhk
Last Updated: 30/08/2017
*/
package com.LOGamez.level;

import com.LOGamez.graphics.*;
import com.LOGamez.asteroids.*;
import com.LOGamez.audio.Sound;
import com.LOGamez.bullets.Bullet;
import com.LOGamez.bullets.P1Bullet;
import com.LOGamez.player.Player;
import com.LOGamez.player.Thrust;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 *
 * @author Nicholas Wright, javidx9
 */
public class Level {
    
    /**Attributes*/ 
    
    /**EnSpeedGlobal variable of Level*/ 
    public static int EnSpeedGlobal = 1;
    
    /**background variable of Level*/ 
    public static int background = 1;
    
    /**bgW coordinate of Level*/ 
    public static double bgW = 0.18 * 6;
    
    /**bgH coordinate of Level*/ 
    public static double bgH = 0.18 * 6;
    
    /**bgX coordinate of Level*/ 
    public static double bgX = (Asteroids.getMainWidth()/5);
    
    /**bgY coordinate of Level*/ 
    public static double bgY = 200 + (720/6.0) /8;
    
    /**levelNo variable of Level*/ 
    public static int levelNo;
    
    /**levelName variable of Level*/ 
    private static String levelName;
    
    /**levelComplete variable of Level*/ 
    public static boolean levelComplete = false;
    
    /**gameComplete variable of Level*/ 
    private static boolean gameComplete;
    
    /**groundScale variable of Level*/ 
    public static double groundScale;
    
    /**groundX coordinate of Level*/ 
    public static int groundX;
    
    /**groundY coordinate of Level*/ 
    public static int groundY;
    
    /**powScale variable of Level*/ 
    public static double powScale;
    
    /**groundBounds Rectangle of Level*/ 
    public static Rectangle groundBounds;
    
    /**anim variable of Level*/ 
    private static Animation anim;
    
    /**animEarth variable of Level*/ 
    private static Animation animEarth;
    
    /**animMars variable of Level*/ 
    private static Animation animMars;
    
    /**animSun variable of Level*/ 
    private static Animation animSun;
    
    /**playerStartX variable of Level*/
    public static int playerStartX = Game.gameWidth/2 +10;
    
    /**playerStartY variable of Level*/
    public static int playerStartY = Game.gameHeight/2;
    
    /**levelCompleteCount variable of Level*/ 
    private int levelCompleteCount;
    
    /**levelStartCount variable of Level*/ 
    private int levelStartCount;
    
    /**firstTick variable of Level*/
    public static boolean firstTick;
    
    /**lastTick variable of Level*/
    public static boolean lastTick;
    
    /**groundColor variable of Level*/
    private int groundColor;
    
    /**levelTxtBGCol variable of Level*/
    private int levelTxtBGCol;
    
    /**enterLvlIconBG variable of Level*/
    private int enterLvlIconBG;
    
    /**enterLvlIconFG variable of Level*/
    private int enterLvlIconFG;
    
    /**theLevelTime variable of Level*/
    public static String theLevelTime;
    
    
    
    /**Constructor*/
    
    /**
     * Level Constructor
     * 
     * @param lNo - level number of Level Object
     * @param w
     * @param h
     */
    public Level(int lNo, int w, int h){
        System.out.println("Level: New Level "+lNo+" Created");
        Game.labels = new Labels();
        //Game.hud = new HUD();
        levelNo = lNo;
        
        System.out.println("Level: Loading Level "+levelNo+" : "+getLevelName());
        
        //Create Player
        if(Player.getLives() > 0 && levelNo != 1){
            //set ship lives to current ship lives
            Game.ship = new Player(playerStartX, playerStartY, 20, 30, Game.getPName(), Player.getLives());
        } else {
            //set ship lives to 3
            Game.ship = new Player(playerStartX, playerStartY, 20, 30, Game.getPName(), 3);
        }
        
        setUp();
        
        groundBounds = new Rectangle(0, groundY+72, Game.gameWidth-1, 28);
        
        init();
    }
    
    
    /**
     * setUp()
     * 
     */
    private void setUp(){
        
        switch(levelNo){
            case 1:
                setUpLevel1();
                break;
            case 2:
                setUpLevel2();
                break;
            case 3:
                setUpLevel3();
                break;
            case 4:
                setUpLevel4();
                break;
            case 5:
                setUpLevel5();
                break;
            case 6:
                setUpLevel6();
                break;
            case 7:
                setUpLevel7();
                break;
            case 8:
                setUpLevel8();
                break;
            case 9:
                setUpLevel9();
                break;
        }
        
    }
    
    
    /**
     * setUpLevel1()
     * 
     */
    private void setUpLevel1() {
        int width = Asteroids.getMainWidth();
        
        //Set Enemies Color
        //Set Enemy Color: White
//        Enemy.setEnemyCol(0xffffff);//White

        //Set Enemy Bullet Color: White
//        Bullet.setEnemyBulletCol(0xffffff);//White

        //Set Level Background: 1
        setBackground(1);//1: Moon(Small)
        
        //Set Barricade Color: Green
//        Barricade.setBarricadeCol(0x00ff00);//Green
        
        //Set Ground Color: Green
        setGroundColor(0x00ff00);//Green

        //Set Level Text Background Color: Green
        levelTxtBGCol = 0x00ff00;//Green

        //Set Enter Level Icon Background Color: Light Green
        enterLvlIconBG = 0x00CD02;//Light Green

        //Set Enter Level Icon Foreground Color: Crimson
        enterLvlIconFG = 0x00B200;//Crimson
                
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 10, E4rows = 10, E5rows = 10, E6rows = 10, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(10, 10, 10, 10, 10, 10, 10, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: White
//                Enemy.setEnemyCol(0xffffff);//White
//                
//                //Set Enemy Bullet Color: White
//                Bullet.setEnemyBulletCol(0xffffff);//White
//    
//                //Set Level Background: 1
//                setBackground(1);//1: Moon(Small)
            
                //Set Barricades
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **     **
                *  ****   ****   ****   ****
                *  ****   ****   ****   ****
                */
//                Barricade.setNoBarricades(4);
                
//                //Set Barricade Color: Green
//                Barricade.setBarricadeCol(0x00ff00);//Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Green
//                setGroundColor(0x00ff00);//Green
//                
//                //Set Level Text Background Color: Green
//                levelTxtBGCol = 0x00ff00;//Green
//                
//                //Set Enter Level Icon Background Color: Light Green
//                enterLvlIconBG = 0x00CD02;//Light Green
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0x00B200;//Crimson
                
                break;
                
            case 480:
                //Set Enemies
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 10, E4rows = 10, E5rows = 10, E6rows = 10, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(10, 10, 10, 10, 10, 10, 10, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: White
//                Enemy.setEnemyCol(0xffffff);//White
//                
//                //Set Enemy Bullet Color: White
//                Bullet.setEnemyBulletCol(0xffffff);//White
//    
//                //Set Level Background: 1
//                setBackground(1);//1: Moon(Small)
            
                //Set Barricades
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **     **
                *  ****   ****   ****   ****
                *  ****   ****   ****   ****
                */
//                Barricade.setNoBarricades(4);
                
//                //Set Barricade Color: Green
//                Barricade.setBarricadeCol(0x00ff00);//Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Green
//                setGroundColor(0x00ff00);//Green
//                
//                //Set Level Text Background Color: Green
//                levelTxtBGCol = 0x00ff00;//Green
//                
//                //Set Enter Level Icon Background Color: Light Green
//                enterLvlIconBG = 0x00CD02;//Light Green
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0x00B200;//Crimson
                
                break;
                
            case 604:
                //Set Enemies
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 10, E4rows = 10, E5rows = 10, E6rows = 10, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(10, 10, 10, 10, 10, 10, 10, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: White
//                Enemy.setEnemyCol(0xffffff);//White
//                
//                //Set Enemy Bullet Color: White
//                Bullet.setEnemyBulletCol(0xffffff);//White
//    
//                //Set Level Background: 1
//                setBackground(1);//1: Moon(Small)
            
                //Set Barricades
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **     **
                *  ****   ****   ****   ****
                *  ****   ****   ****   ****
                */
//                Barricade.setNoBarricades(4);
                
//                //Set Barricade Color: Green
//                Barricade.setBarricadeCol(0x00ff00);//Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Green
//                setGroundColor(0x00ff00);//Green
//                
//                //Set Level Text Background Color: Green
//                levelTxtBGCol = 0x00ff00;//Green
//                
//                //Set Enter Level Icon Background Color: Light Green
//                enterLvlIconBG = 0x00CD02;//Light Green
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0x00B200;//Crimson
                
                break;
                
            default:
                //Set Enemies
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 10, E4rows = 10, E5rows = 10, E6rows = 10, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                *   **********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(10, 10, 10, 10, 10, 10, 10, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: White
//                Enemy.setEnemyCol(0xffffff);//White
//                
//                //Set Enemy Bullet Color: White
//                Bullet.setEnemyBulletCol(0xffffff);//White
//    
//                //Set Level Background: 1
//                setBackground(1);//1: Moon(Small)
            
                //Set Barricades
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **     **
                *  ****   ****   ****   ****
                *  ****   ****   ****   ****
                */
//                Barricade.setNoBarricades(4);
                
//                //Set Barricade Color: Green
//                Barricade.setBarricadeCol(0x00ff00);//Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Green
//                setGroundColor(0x00ff00);//Green
//                
//                //Set Level Text Background Color: Green
//                levelTxtBGCol = 0x00ff00;//Green
//                
//                //Set Enter Level Icon Background Color: Light Green
//                enterLvlIconBG = 0x00CD02;//Light Green
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0x00B200;//Crimson
                
                break;
                
        }
        
    }

    
    /**
     * setUpLevel2()
     * 
     */
    private void setUpLevel2(){
        int width = Asteroids.getMainWidth();
        
        //Set Enemy Color: Red
//        Enemy.setEnemyCol(0xff0000);//Red

        //Set Enemy Bullet Color: Red
//        Bullet.setEnemyBulletCol(0xff0000);//Red

        //Set Level Background: 2
        setBackground(2);//2: Mars(Small)
        
        //Set Ground Color: Maroon
        setGroundColor(0x800000);

        //Set Level Text Background Color: Maroon
        levelTxtBGCol = 0x800000;//Maroon

        //Set Enter Level Icon Background Color: Indian Red
        enterLvlIconBG = 0xCD5C5C;//Indian Red

        //Set Enter Level Icon Foreground Color: Crimson
        enterLvlIconFG = 0xDC100C;//Crimson
        
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 11, E1rows = 11, E2rows = 11, E3rows = 9, E4rows = 7, E5rows = 5, E6rows = 5, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   ***********
                *   ***********
                *    *********
                *     *******
                *      *****
                *      *****
                *   
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(11, 11, 11, 9, 7, 5, 5, 0, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
//                //Set Enemy Color: Red
//                Enemy.setEnemyCol(0xff0000);//Red
//                
//                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
//    
//                //Set Level Background: 2
//                setBackground(2);//2: Mars(Small)
            
                //Set Barricades
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Indian Red
//                Barricade.setBarricadeCol(0xCD5C5C);//Indian Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
//                
//                //Set Ground Color: Maroon
//                setGroundColor(0x800000);
//                
//                //Set Level Text Background Color: Maroon
//                levelTxtBGCol = 0x800000;//Maroon
//                
//                //Set Enter Level Icon Background Color: Indian Red
//                enterLvlIconBG = 0xCD5C5C;//Indian Red
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0xDC100C;//Crimson
                
                break;
                
            case 480:
                //Set Enemies
                //E0rows = 11, E1rows = 11, E2rows = 11, E3rows = 9, E4rows = 7, E5rows = 5, E6rows = 5, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   ***********
                *   ***********
                *    *********
                *     *******
                *      *****
                *      *****
                *   
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(11, 11, 11, 9, 7, 5, 5, 0, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
//                //Set Enemy Color: Red
//                Enemy.setEnemyCol(0xff0000);//Red
//                
//                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
//    
//                //Set Level Background: 2
//                setBackground(2);//2: Mars(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Indian Red
//                Barricade.setBarricadeCol(0xCD5C5C);//Indian Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Maroon
//                setGroundColor(0x800000);//Maroon
//                
//                //Set Level Text Background Color: Maroon
//                levelTxtBGCol = 0x800000;//Maroon
//                
//                //Set Enter Level Icon Background Color: Indian Red
//                enterLvlIconBG = 0xCD5C5C;//Indian Red
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0xDC100C;//Crimson
                
                break;
                
            case 604:
                //Set Enemies
                //E0rows = 11, E1rows = 11, E2rows = 11, E3rows = 9, E4rows = 7, E5rows = 5, E6rows = 5, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   ***********
                *   ***********
                *    *********
                *     *******
                *      *****
                *      *****
                *   
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(11, 11, 11, 9, 7, 5, 5, 0, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
//                //Set Enemy Color: Red
//                Enemy.setEnemyCol(0xff0000);//Red
//                
//                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
//    
//                //Set Level Background: 2
//                setBackground(2);//2: Mars(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
//                //Set Barricade Color: Indian Red
//                Barricade.setBarricadeCol(0xCD5C5C);//Indian Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Maroon
//                setGroundColor(0x800000);//Maroon
//                
//                //Set Level Text Background Color: Maroon
//                levelTxtBGCol = 0x800000;//Maroon
//                
//                //Set Enter Level Icon Background Color: Indian Red
//                enterLvlIconBG = 0xCD5C5C;//Indian Red
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0xDC100C;//Crimson
                
                break;
                
            default:
                //Set Enemies
                //E0rows = 11, E1rows = 11, E2rows = 11, E3rows = 9, E4rows = 7, E5rows = 5, E6rows = 5, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   ***********
                *   ***********
                *    *********
                *     *******
                *      *****
                *      *****
                *   
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(11, 11, 11, 9, 7, 5, 5, 0, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
//                //Set Enemy Color: Red
//                Enemy.setEnemyCol(0xff0000);//Red
//                
//                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
//    
//                //Set Level Background: 2
//                setBackground(2);//2: Mars(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
//                //Set Barricade Color: Indian Red
//                Barricade.setBarricadeCol(0xCD5C5C);//Indian Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Maroon
//                setGroundColor(0x800000);//Maroon
//                
//                //Set Level Text Background Color: Maroon
//                levelTxtBGCol = 0x800000;//Maroon
//                
//                //Set Enter Level Icon Background Color: Indian Red
//                enterLvlIconBG = 0xCD5C5C;//Indian Red
//                
//                //Set Enter Level Icon Foreground Color: Crimson
//                enterLvlIconFG = 0xDC100C;//Crimson
                
                break;
        }
        
    }
    
    
    /**
     * setUpLevel3()
     * 
     */
    private void setUpLevel3(){
        int width = Asteroids.getMainWidth();
        
        //Set Enemy Color: Cadet Blue
//        Enemy.setEnemyCol(0x5F9EA0);//Cadet Blue

        //Set Enemy Bullet Color: Green
//        Bullet.setEnemyBulletCol(0x00ff00);//Green

        //Set Level Background: 3
        setBackground(3);//3: Earth(Small)
        
        //Set Barricade Color: Blue
//        Barricade.setBarricadeCol(0x0000ff);//Blue
        
        //Set Ground Color: Medium Blue
        setGroundColor(0x0000dd);//Medium Blue

        //Set Level Text Background Color: Medium Blue
        levelTxtBGCol = 0x0000dd;//Medium Blue

        //Set Enter Level Icon Background Color: Green Blue
        enterLvlIconBG = 0x00d5fc;//Green Blue

        //Set Enter Level Icon Foreground Color: Dark Green blue
        enterLvlIconFG = 0xc0c0c0;//Dark Green blue
        
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 0, E1rows = 11, E2rows = 11, E3rows = 11, E4rows = 0, E5rows = 11, E6rows = 11, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   
                *   ***********
                *   ***********
                *   ***********
                *   
                *   ***********
                *   ***********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(0, 11, 11, 11, 0, 11, 11, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: Cadet Blue
//                Enemy.setEnemyCol(0x5F9EA0);//Cadet Blue
//                
//                //Set Enemy Bullet Color: Green
//                Bullet.setEnemyBulletCol(0x00ff00);//Green
//    
//                //Set Level Background: 3
//                setBackground(3);//3: Earth(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
//                //Set Barricade Color: Blue
//                Barricade.setBarricadeCol(0x0000ff);//Blue
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Medium Blue
//                setGroundColor(0x0000dd);//Medium Blue
//                
//                //Set Level Text Background Color: Medium Blue
//                levelTxtBGCol = 0x0000dd;//Medium Blue
//                
//                //Set Enter Level Icon Background Color: Green Blue
//                enterLvlIconBG = 0x00d5fc;//Green Blue
//                
//                //Set Enter Level Icon Foreground Color: Dark Green blue
//                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 480:
                //E0rows = 0, E1rows = 11, E2rows = 11, E3rows = 11, E4rows = 0, E5rows = 11, E6rows = 11, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   
                *   ***********
                *   ***********
                *   ***********
                *   
                *   ***********
                *   ***********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(0, 11, 11, 11, 0, 11, 11, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: Cadet Blue
//                Enemy.setEnemyCol(0x5F9EA0);//Cadet Blue
//                
//                //Set Enemy Bullet Color: Green
//                Bullet.setEnemyBulletCol(0x00ff00);//Green
//    
//                //Set Level Background: 3
//                setBackground(3);//3: Earth(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
//                //Set Barricade Color: Blue
//                Barricade.setBarricadeCol(0x0000ff);//Blue
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Medium Blue
//                setGroundColor(0x0000dd);//Medium Blue
//                
//                //Set Level Text Background Color: Medium Blue
//                levelTxtBGCol = 0x0000dd;//Medium Blue
//                
//                //Set Enter Level Icon Background Color: Green Blue
//                enterLvlIconBG = 0x00d5fc;//Green Blue
//                
//                //Set Enter Level Icon Foreground Color: Dark Green blue
//                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 604:
                //E0rows = 0, E1rows = 11, E2rows = 11, E3rows = 11, E4rows = 0, E5rows = 11, E6rows = 11, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   
                *   ***********
                *   ***********
                *   ***********
                *   
                *   ***********
                *   ***********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(0, 11, 11, 11, 0, 11, 11, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: Cadet Blue
//                Enemy.setEnemyCol(0x5F9EA0);//Cadet Blue
//                
//                //Set Enemy Bullet Color: Green
//                Bullet.setEnemyBulletCol(0x00ff00);//Green
//    
//                //Set Level Background: 3
//                setBackground(3);//3: Earth(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
//                //Set Barricade Color: Blue
//                Barricade.setBarricadeCol(0x0000ff);//Blue
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Medium Blue
//                setGroundColor(0x0000dd);//Medium Blue
//                
//                //Set Level Text Background Color: Medium Blue
//                levelTxtBGCol = 0x0000dd;//Medium Blue
//                
//                //Set Enter Level Icon Background Color: Green Blue
//                enterLvlIconBG = 0x00d5fc;//Green Blue
//                
//                //Set Enter Level Icon Foreground Color: Dark Green blue
//                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            default:
                //E0rows = 0, E1rows = 11, E2rows = 11, E3rows = 11, E4rows = 0, E5rows = 11, E6rows = 11, E7rows = 0, E8rows = 0, E9rows = 0;
                /**
                *   
                *   ***********
                *   ***********
                *   ***********
                *   
                *   ***********
                *   ***********
                * 
                * 
                * 
                */
//                Enemy.setNoEnemy(0, 11, 11, 11, 0, 11, 11, 0, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
//                //Set Enemy Color: Cadet Blue
//                Enemy.setEnemyCol(0x5F9EA0);//Cadet Blue
//                
//                //Set Enemy Bullet Color: Green
//                Bullet.setEnemyBulletCol(0x00ff00);//Green
//    
//                //Set Level Background: 3
//                setBackground(3);//3; Earth(Small)
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
//                //Set Barricade Color: Blue
//                Barricade.setBarricadeCol(0x0000ff);//Blue
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
//                //Set Ground Color: Medium Blue
//                setGroundColor(0x0000dd);//Medium Blue
//                
//                //Set Level Text Background Color: Medium Blue
//                levelTxtBGCol = 0x0000dd;//Medium Blue
//                
//                //Set Enter Level Icon Background Color: Green Blue
//                enterLvlIconBG = 0x00d5fc;//Green Blue
//                
//                //Set Enter Level Icon Foreground Color: Dark Green blue
//                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
        }
        
    }
    
    
    /**
     * setUpLevel4()
     * 
     */
    private void setUpLevel4(){
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 11, E1rows = 6, E2rows = 11, E3rows = 11, E4rows = 6, E5rows = 11, E6rows = 4, E7rows = 0, E8rows = 0, E9rows = 2;
                /**
                *   ***********
                *     ******
                *   ***********
                *   ***********
                *     ******
                *   ***********
                *      ****
                * 
                * 
                *       **
                */
//                Enemy.setNoEnemy(11, 6, 11, 11, 6, 11, 4, 0, 0, 2);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: OrangeRed
                setGroundColor(0xFF4500);//OrangeRed
                
                //Set Level Text Background Color: OrangeRed
                levelTxtBGCol = 0xFF4500;//OrangeRed
                
                //Set Enter Level Icon Background Color: Orange
                enterLvlIconBG = 0xFFA500;//Orange
                
                //Set Enter Level Icon Foreground Color: Gold
                enterLvlIconFG = 0xffd700;//Gold
                
                break;
                
            case 480:
                //E0rows = 11, E1rows = 6, E2rows = 11, E3rows = 11, E4rows = 6, E5rows = 11, E6rows = 4, E7rows = 0, E8rows = 0, E9rows = 2;
                /**
                *   ***********
                *     ******
                *   ***********
                *   ***********
                *     ******
                *   ***********
                *      ****
                * 
                * 
                *       **
                */
//                Enemy.setNoEnemy(11, 6, 11, 11, 6, 11, 4, 0, 0, 2);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: OrangeRed
                setGroundColor(0xFF4500);//OrangeRed
                
                //Set Level Text Background Color: OrangeRed
                levelTxtBGCol = 0xFF4500;//OrangeRed
                
                //Set Enter Level Icon Background Color: Orange
                enterLvlIconBG = 0xFFA500;//Orange
                
                //Set Enter Level Icon Foreground Color: Gold
                enterLvlIconFG = 0xffd700;//Gold
                
                break;
                
            case 604:
                //E0rows = 11, E1rows = 6, E2rows = 11, E3rows = 11, E4rows = 6, E5rows = 11, E6rows = 4, E7rows = 0, E8rows = 0, E9rows = 2;
                /**
                *   ***********
                *     ******
                *   ***********
                *   ***********
                *     ******
                *   ***********
                *      ****
                * 
                * 
                *       **
                */
//                Enemy.setNoEnemy(11, 6, 11, 11, 6, 11, 4, 0, 0, 2);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: OrangeRed
                setGroundColor(0xFF4500);//OrangeRed
                
                //Set Level Text Background Color: OrangeRed
                levelTxtBGCol = 0xFF4500;//OrangeRed
                
                //Set Enter Level Icon Background Color: Orange
                enterLvlIconBG = 0xFFA500;//Orange
                
                //Set Enter Level Icon Foreground Color: Gold
                enterLvlIconFG = 0xffd700;//Gold
                
                break;
                
            default:
                //E0rows = 11, E1rows = 6, E2rows = 11, E3rows = 11, E4rows = 6, E5rows = 11, E6rows = 4, E7rows = 0, E8rows = 0, E9rows = 2;
                /**
                *   ***********
                *     ******
                *   ***********
                *   ***********
                *     ******
                *   ***********
                *      ****
                * 
                * 
                *       **
                */
//                Enemy.setNoEnemy(11, 6, 11, 11, 6, 11, 4, 0, 0, 2);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: OrangeRed
                setGroundColor(0xFF4500);//OrangeRed
                
                //Set Level Text Background Color: OrangeRed
                levelTxtBGCol = 0xFF4500;//OrangeRed
                
                //Set Enter Level Icon Background Color: Orange
                enterLvlIconBG = 0xFFA500;//Orange
                
                //Set Enter Level Icon Foreground Color: Gold
                enterLvlIconFG = 0xffd700;//Gold
                
                break;
        }
        
    }
    
    
    /**
     * setUpLevel5()
     * 
     */
    private void setUpLevel5() {
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 5, E1rows = 5, E2rows = 11, E3rows = 10, E4rows = 7, E5rows = 7, E6rows = 4, E7rows = 10, E8rows = 0, E9rows = 0;
                /**
                *      *****
                *      *****
                *   ***********
                *   **********
                *     *******
                *     *******
                *      ****
                *   **********
                * 
                *   
                */
//                Enemy.setNoEnemy(5, 5, 11, 10, 7, 7, 4, 10, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Grey
//                Enemy.setEnemyCol(0x778899);//Grey
                
                //Set Enemy Bullet Color: Dark Grey
//                Bullet.setEnemyBulletCol(0x2F4FFF);//Dark Grey
    
                //Set Level Background: 1
                setBackground(1);//1: Moon
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Dark Grey
//                Barricade.setBarricadeCol(0x090909);//Dark Grey
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Grey
                setGroundColor(0x696969);//Grey
                
                //Set Level Text Background Color: Grey
                levelTxtBGCol = 0x696969;//Grey
                
                //Set Enter Level Icon Background Color: Grey
                enterLvlIconBG = 0x696969;//Grey
                
                //Set Enter Level Icon Foreground Color: Dark Grey
                enterLvlIconFG = 0x090909;//Dark Grey
                
                break;
                
            case 480:
                //E0rows = 5, E1rows = 5, E2rows = 11, E3rows = 10, E4rows = 7, E5rows = 7, E6rows = 4, E7rows = 10, E8rows = 0, E9rows = 0;
                /**
                *      *****
                *      *****
                *   ***********
                *   **********
                *     *******
                *     *******
                *      ****
                *   **********
                * 
                *   
                */
//                Enemy.setNoEnemy(5, 5, 11, 10, 7, 7, 4, 10, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Grey
//                Enemy.setEnemyCol(0x778899);//Grey
                
                //Set Enemy Bullet Color: Dark Grey
//                Bullet.setEnemyBulletCol(0x2F4FFF);//Dark Grey
    
                //Set Level Background: 1
                setBackground(1);//1: Moon
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Dark Grey
//                Barricade.setBarricadeCol(0x090909);//Dark Grey
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Grey
                setGroundColor(0x696969);//Grey
                
                //Set Level Text Background Color: Grey
                levelTxtBGCol = 0x696969;//Grey
                
                //Set Enter Level Icon Background Color: Grey
                enterLvlIconBG = 0x696969;//Grey
                
                //Set Enter Level Icon Foreground Color: Dark Grey
                enterLvlIconFG = 0x090909;//Dark Grey
                
                break;
                
            case 604:
                //E0rows = 5, E1rows = 5, E2rows = 11, E3rows = 10, E4rows = 7, E5rows = 7, E6rows = 4, E7rows = 10, E8rows = 0, E9rows = 0;
                /**
                *      *****
                *      *****
                *   ***********
                *   **********
                *     *******
                *     *******
                *      ****
                *   **********
                * 
                *   
                */
//                Enemy.setNoEnemy(5, 5, 11, 10, 7, 7, 4, 10, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Grey
//                Enemy.setEnemyCol(0x778899);//Grey
                
                //Set Enemy Bullet Color: Dark Grey
//                Bullet.setEnemyBulletCol(0x2F4F4F);//Dark Grey
    
                //Set Level Background: 1
                setBackground(1);//1: Moon
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Dark Grey
//                Barricade.setBarricadeCol(0x090909);//Dark Grey
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Grey
                setGroundColor(0x696969);//Grey
                
                //Set Level Text Background Color: Grey
                levelTxtBGCol = 0x696969;//Grey
                
                //Set Enter Level Icon Background Color: Grey
                enterLvlIconBG = 0x696969;//Grey
                
                //Set Enter Level Icon Foreground Color: Dark Grey
                enterLvlIconFG = 0x090909;//Dark Grey
                
                break;
                
            default:
                //E0rows = 5, E1rows = 5, E2rows = 11, E3rows = 10, E4rows = 7, E5rows = 7, E6rows = 4, E7rows = 10, E8rows = 0, E9rows = 0;
                /**
                *      *****
                *      *****
                *   ***********
                *   **********
                *     *******
                *     *******
                *      ****
                *   **********
                * 
                *   
                */
//                Enemy.setNoEnemy(5, 5, 11, 10, 7, 7, 4, 10, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Grey
//                Enemy.setEnemyCol(0x778899);//Grey
                
                //Set Enemy Bullet Color: Dark Grey
//                Bullet.setEnemyBulletCol(0x2F4F4F);//Dark Grey
    
                //Set Level Background: 1
                setBackground(1);
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Dark Grey
//                Barricade.setBarricadeCol(0x090909);//Dark Grey
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Grey
                setGroundColor(0x696969);//Grey
                
                //Set Level Text Background Color: Grey
                levelTxtBGCol = 0x696969;//Grey
                
                //Set Enter Level Icon Background Color: Grey
                enterLvlIconBG = 0x696969;//Grey
                
                //Set Enter Level Icon Foreground Color: Dark Grey
                enterLvlIconFG = 0x090909;//Dark Grey
                
                break;
        }
        
    }
    
    
    /**
     * setUpLevel6()
     * 
     */
    private void setUpLevel6(){
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 6, E1rows = 6, E2rows = 6, E3rows = 6, E4rows = 6, E5rows = 6, E6rows = 10, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *   **********
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(6, 6, 6, 6, 6, 6, 10, 4, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
                //Set Enemy Color: Crimson
//                Enemy.setEnemyCol(0xDC1000);//Crimson
                
                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
    
                //Set Level Background: 2
                setBackground(2);//2: Mars
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Maroon
                setGroundColor(0x800000);//Maroon
                
                //Set Level Text Background Color: Maroon
                levelTxtBGCol = 0x800000;//Maroon
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 480:
                //E0rows = 6, E1rows = 6, E2rows = 6, E3rows = 6, E4rows = 6, E5rows = 6, E6rows = 10, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *   **********
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(6, 6, 6, 6, 6, 6, 10, 4, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
                //Set Enemy Color: Crimson
//                Enemy.setEnemyCol(0xDC1000);//Crimson
                
                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
    
                //Set Level Background: 2
                setBackground(2);//2: Mars
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Maroon
                setGroundColor(0x800000);//Maroon
                
                //Set Level Text Background Color: Maroon
                levelTxtBGCol = 0x800000;//Maroon
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 604:
                //E0rows = 6, E1rows = 6, E2rows = 6, E3rows = 6, E4rows = 6, E5rows = 6, E6rows = 10, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *   **********
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(6, 6, 6, 6, 6, 6, 10, 4, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
                //Set Enemy Color: Crimson
//                Enemy.setEnemyCol(0xDC1000);//Crimson
                
                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
    
                //Set Level Background: 2
                setBackground(2);//2: Mars
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Maroon
                setGroundColor(0x800000);//Maroon
                
                //Set Level Text Background Color: Maroon
                levelTxtBGCol = 0x800000;//Maroon
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            default:
                //E0rows = 6, E1rows = 6, E2rows = 6, E3rows = 6, E4rows = 6, E5rows = 6, E6rows = 10, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *     ******
                *   **********
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(6, 6, 6, 6, 6, 6, 10, 4, 0, 0);
                
                //Set Enemy UFOfreq: 8
//                Enemy.setUFOFreq(8);
                
                //Set Enemy Color: Crimson
//                Enemy.setEnemyCol(0xDC1000);//Crimson
                
                //Set Enemy Bullet Color: Red
//                Bullet.setEnemyBulletCol(0xff0000);//Red
    
                //Set Level Background: 2
                setBackground(2);//2: Mars
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *      **     **
                *     ****   ****
                *     ****   ****
                */
//                Barricade.setNoBarricades(2);
                
                //Set Barricade Color: Red
//                Barricade.setBarricadeCol(0xff0000);//Red
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Maroon
                setGroundColor(0x800000);//Maroon
                
                //Set Level Text Background Color: Maroon
                levelTxtBGCol = 0x800000;//Maroon
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
        }
        
    }
    
    
    /**
     * setUpLevel7()
     * 
     */
    private void setUpLevel7(){
        int width = Asteroids.getMainWidth();
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 10, E1rows = 2, E2rows = 10, E3rows = 8, E4rows = 10, E5rows = 6, E6rows = 9, E7rows = 6, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *       **
                *   **********
                *    ********
                *   **********
                *     ******
                *    *********
                *     ******
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 2, 10, 8, 10, 6, 9, 6, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Gold
//                Barricade.setBarricadeCol(0xFFD700);//Gold
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Red
                setGroundColor(0xff0000);//Red
                
                //Set Level Text Background Color: Red
                levelTxtBGCol = 0xff0000;//Red
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 480:
                //E0rows = 10, E1rows = 2, E2rows = 10, E3rows = 8, E4rows = 10, E5rows = 6, E6rows = 9, E7rows = 6, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *       **
                *   **********
                *    ********
                *   **********
                *     ******
                *    *********
                *     ******
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 2, 10, 8, 10, 6, 9, 6, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Gold
//                Barricade.setBarricadeCol(0xFFD700);//Gold
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Red
                setGroundColor(0xff0000);//Red
                
                //Set Level Text Background Color: Red
                levelTxtBGCol = 0xff0000;//Red
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 604:
                //E0rows = 10, E1rows = 2, E2rows = 10, E3rows = 8, E4rows = 10, E5rows = 6, E6rows = 9, E7rows = 6, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *       **
                *   **********
                *    ********
                *   **********
                *     ******
                *    *********
                *     ******
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 2, 10, 8, 10, 6, 9, 6, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Gold
//                Barricade.setBarricadeCol(0xFFD700);//Gold
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Red
                setGroundColor(0xff0000);//Red
                
                //Set Level Text Background Color: Red
                levelTxtBGCol = 0xff0000;//Red
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            default:
                //E0rows = 10, E1rows = 2, E2rows = 10, E3rows = 8, E4rows = 10, E5rows = 6, E6rows = 9, E7rows = 6, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *       **
                *   **********
                *    ********
                *   **********
                *     ******
                *    *********
                *     ******
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 2, 10, 8, 10, 6, 9, 6, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: 0xf7cb07
//                Enemy.setEnemyCol(0xf7cb07);
                
                //Set Enemy Bullet Color: 0xf7cb07
//                Bullet.setEnemyBulletCol(0xf7cb07);
    
                //Set Level Background: 4
                setBackground(4);//4: Sun
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Gold
//                Barricade.setBarricadeCol(0xFFD700);//Gold
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Red
                setGroundColor(0xff0000);//Red
                
                //Set Level Text Background Color: Red
                levelTxtBGCol = 0xff0000;//Red
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
        }
        
    }
    
    
    /**
     * setUpLevel8()
     * 
     */
    private void setUpLevel8(){
        int width = Asteroids.getMainWidth();
        Random random = new Random();
        switch(width){
            case 300:
                //Set Enemies
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 8, E4rows = 8, E5rows = 6, E6rows = 6, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *    ********
                *    ********
                *     ******
                *     ******
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 10, 10, 8, 8, 6, 6, 4, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Random Blue
//                Enemy.setEnemyCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
                
                //Set Enemy Bullet Color: Random Blue
//                Bullet.setEnemyBulletCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
    
                //Set Level Background: 3
                setBackground(3);//3: Earth
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
            
                //Set Ground
                //Set Ground Scale: 1.0
                groundScale = 1.0;
                
                //Set Ground POS: 4, Game.getGameHeight() - 140;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Random Green
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set Level Text Background Color: Green
                levelTxtBGCol = 0x00ff00;//Green
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 480:
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 8, E4rows = 8, E5rows = 6, E6rows = 6, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *    ********
                *    ********
                *     ******
                *     ******
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 10, 10, 8, 8, 6, 6, 4, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Random Blue
//                Enemy.setEnemyCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
                
                //Set Enemy Bullet Color: Random Blue
//                Bullet.setEnemyBulletCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
    
                //Set Level Background: 3
                setBackground(3);//3: Earth
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 1.25
                groundScale = 1.25;
                
                //Set Ground POS: 10, Game.getGameHeight() - 140;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Random Green
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set Level Text Background Color: Green
                levelTxtBGCol = 0x00ff00;//Green
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            case 604:
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 8, E4rows = 8, E5rows = 6, E6rows = 6, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *    ********
                *    ********
                *     ******
                *     ******
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 10, 10, 8, 8, 6, 6, 4, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Random Blue
//                Enemy.setEnemyCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
                
                //Set Enemy Bullet Color: Random Blue
//                Bullet.setEnemyBulletCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
    
                //Set Level Background: 3
                setBackground(3);//3: Earth
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Random Green
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set Level Text Background Color: Green
                levelTxtBGCol = 0x00ff00;//Green
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
            default:
                //E0rows = 10, E1rows = 10, E2rows = 10, E3rows = 8, E4rows = 8, E5rows = 6, E6rows = 6, E7rows = 4, E8rows = 0, E9rows = 0;
                /**
                *   **********
                *   **********
                *   **********
                *    ********
                *    ********
                *     ******
                *     ******
                *      ****
                * 
                *   
                */
//                Enemy.setNoEnemy(10, 10, 10, 8, 8, 6, 6, 4, 0, 0);
                
                //Set Enemy UFOfreq: 5
//                Enemy.setUFOFreq(5);
                
                //Set Enemy Color: Random Blue
//                Enemy.setEnemyCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
                
                //Set Enemy Bullet Color: Random Blue
//                Bullet.setEnemyBulletCol(0x0000ff + random.nextInt(0x0000ff));//Random Blue
    
                //Set Level Background: 3
                setBackground(3);//3: Earth
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set POW Scale: 0.75
                powScale = 0.75;
           
                //Set Ground
                //Set Ground Scale: 2.45
                groundScale = 2.45;
                
                //Set Ground POS: -14, Game.getGameHeight() - 140;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                
                //Set Ground Color: Random Green
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                //Set Level Text Background Color: Green
                levelTxtBGCol = 0x00ff00;//Green
                
                //Set Enter Level Icon Background Color: Green Blue
                enterLvlIconBG = 0x00d5fc;//Green Blue
                
                //Set Enter Level Icon Foreground Color: Dark Green Blue
                enterLvlIconFG = 0xc0c0c0;//Dark Green blue
                
                break;
                
        }
        
    }
    
    
    /**
     * setUpLevel9()
     * 
     */
    private void setUpLevel9(){
        int width = Asteroids.getMainWidth();
        Random random = new Random();
        switch(width){
            case 300:
                
            
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                powScale = 0.75;
            
                groundScale = 1.0;
                groundX = 4;
                groundY = Asteroids.getMainHeight() - 140;
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));
                
                break;
                
            case 480:
                
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                powScale = 0.75;
           
                groundScale = 1.25;
                groundX = 10;
                groundY = Asteroids.getMainHeight() - 140;
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));
                
                break;
                
            case 604:
                
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                powScale = 0.75;
           
                groundScale = 2.45;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));
                
                break;
                
            default:
                
                /**
                *   
                *   
                *   
                *    
                *    
                *     
                *     
                *   **     **     **
                *  ****   ****   ****
                *  ****   ****   ****
                */
//                Barricade.setNoBarricades(3);
                
                //Set Barricade Color: Random Green
//                Barricade.setBarricadeCol(0x00ff00 + random.nextInt(0xffffff));//Random Green
                
                powScale = 0.75;
           
                groundScale = 2.45;
                groundX = -14;
                groundY = Asteroids.getMainHeight() - 140;
                setGroundColor(0x00ff00 + random.nextInt(0xffffff));
                
                break;
                
        }
    }
    
    
    /**
     * init()
     * 
     */
    private void init(){
        Texture.clearMaps();
        
//        if(P1Explode.getAll().size() > 0){
//            P1Explode.clearAll();
//        }
//        if(P1BExplode.getAll().size() > 0){
//            P1BExplode.clearAll();
//        }
//        if(EnExplode.getAll0().size() + EnExplode.getAll1().size() + EnExplode.getAll2().size() + EnExplode.getAll3().size() + EnExplode.getAll4().size() > 0){
//            EnExplode.clearAll();
//        }
//        if(P1Bullet.getAll().size() > 0){
//            P1Bullet.clearAll();
//        }
//        if(EnBullet.getAll().size() > 0){
//            EnBullet.clearAll();
//        }

        
        Game.asteroidz = new Asteroid();
//        Game.explodes = new Explode();
        Game.bullets = new Bullet();
//        Game.pows = new POW();
        
//        Barricade.initBarz();
//        Enemy.init();
        
        if(levelNo == 1 || levelNo == 5){
            if(anim == null){
//                anim = new Animation(12, 
//                Texture.background0, 
//                Texture.background1, 
//                Texture.background2, 
//                Texture.background3,
//                Texture.background4, 
//                Texture.background5, 
//                Texture.background6, 
//                Texture.background7,
//                Texture.background8, 
//                Texture.background9, 
//                Texture.background10,  
//                Texture.background11, 
//                Texture.background12, 
//                Texture.background13,
//                Texture.background14, 
//                Texture.background15, 
//                Texture.background16, 
//                Texture.background17,
//                Texture.background18, 
//                Texture.background19, 
//                Texture.background20,
//                Texture.background21, 
//                Texture.background22, 
//                Texture.background23,
//                Texture.background24, 
//                Texture.background25, 
//                Texture.background26, 
//                Texture.background27,
//                Texture.background28, 
//                Texture.background29, 
//                Texture.background30,
//                Texture.background31, 
//                Texture.background32, 
//                Texture.background33,
//                Texture.background34, 
//                Texture.background35, 
//                Texture.background36, 
//                Texture.background37,
//                Texture.background38, 
//                Texture.background39, 
//                Texture.background40,
//                Texture.background41, 
//                Texture.background42, 
//                Texture.background43,
//                Texture.background44, 
//                Texture.background45, 
//                Texture.background46, 
//                Texture.background47,
//                Texture.background48, 
//                Texture.background49, 
//                Texture.background50,
//                Texture.background51, 
//                Texture.background52, 
//                Texture.background53,
//                Texture.background54, 
//                Texture.background55, 
//                Texture.background56, 
//                Texture.background57,
//                Texture.background58, 
//                Texture.background59, 
//                Texture.background60,
//                Texture.background61, 
//                Texture.background62, 
//                Texture.background63,
//                Texture.background64, 
//                Texture.background65, 
//                Texture.background66, 
//                Texture.background67,
//                Texture.background68, 
//                Texture.background69, 
//                Texture.background70,
//                Texture.background71, 
//                Texture.background72, 
//                Texture.background73,
//                Texture.background74, 
//                Texture.background75, 
//                Texture.background76, 
//                Texture.background77,
//                Texture.background78, 
//                Texture.background79, 
//                Texture.background80,
//                Texture.background81, 
//                Texture.background82, 
//                Texture.background83,
//                Texture.background84, 
//                Texture.background85, 
//                Texture.background86, 
//                Texture.background87,
//                Texture.background88
//                );
            }
        }
        
        if(levelNo == 2 || levelNo == 6){
            if(animMars == null){
//                animMars = new Animation(12, 
//                Texture.backgroundMars0, 
//                Texture.backgroundMars1, 
//                Texture.backgroundMars2, 
//                Texture.backgroundMars3,
//                Texture.backgroundMars4, 
//                Texture.backgroundMars5, 
//                Texture.backgroundMars6, 
//                Texture.backgroundMars7,
//                Texture.backgroundMars8, 
//                Texture.backgroundMars9, 
//                Texture.backgroundMars10,  
//                Texture.backgroundMars11, 
//                Texture.backgroundMars12, 
//                Texture.backgroundMars13,
//                Texture.backgroundMars14, 
//                Texture.backgroundMars15, 
//                Texture.backgroundMars16, 
//                Texture.backgroundMars17,
//                Texture.backgroundMars18, 
//                Texture.backgroundMars19, 
//                Texture.backgroundMars20,
//                Texture.backgroundMars21, 
//                Texture.backgroundMars22, 
//                Texture.backgroundMars23,
//                Texture.backgroundMars24, 
//                Texture.backgroundMars25, 
//                Texture.backgroundMars26, 
//                Texture.backgroundMars27,
//                Texture.backgroundMars28, 
//                Texture.backgroundMars29, 
//                Texture.backgroundMars30,
//                Texture.backgroundMars31, 
//                Texture.backgroundMars32, 
//                Texture.backgroundMars33,
//                Texture.backgroundMars34, 
//                Texture.backgroundMars35, 
//                Texture.backgroundMars36, 
//                Texture.backgroundMars37,
//                Texture.backgroundMars38, 
//                Texture.backgroundMars39, 
//                Texture.backgroundMars40,
//                Texture.backgroundMars41, 
//                Texture.backgroundMars42, 
//                Texture.backgroundMars43,
//                Texture.backgroundMars44, 
//                Texture.backgroundMars45, 
//                Texture.backgroundMars46, 
//                Texture.backgroundMars47,
//                Texture.backgroundMars48, 
//                Texture.backgroundMars49, 
//                Texture.backgroundMars50,
//                Texture.backgroundMars51, 
//                Texture.backgroundMars52, 
//                Texture.backgroundMars53,
//                Texture.backgroundMars54, 
//                Texture.backgroundMars55, 
//                Texture.backgroundMars56, 
//                Texture.backgroundMars57,
//                Texture.backgroundMars58, 
//                Texture.backgroundMars59, 
//                Texture.backgroundMars60,
//                Texture.backgroundMars61, 
//                Texture.backgroundMars62, 
//                Texture.backgroundMars63,
//                Texture.backgroundMars64, 
//                Texture.backgroundMars65, 
//                Texture.backgroundMars66, 
//                Texture.backgroundMars67,
//                Texture.backgroundMars68, 
//                Texture.backgroundMars69, 
//                Texture.backgroundMars70,
//                Texture.backgroundMars71, 
//                Texture.backgroundMars72, 
//                Texture.backgroundMars73,
//                Texture.backgroundMars74, 
//                Texture.backgroundMars75, 
//                Texture.backgroundMars76, 
//                Texture.backgroundMars77,
//                Texture.backgroundMars78, 
//                Texture.backgroundMars79, 
//                Texture.backgroundMars80,
//                Texture.backgroundMars81, 
//                Texture.backgroundMars82, 
//                Texture.backgroundMars83,
//                Texture.backgroundMars84, 
//                Texture.backgroundMars85, 
//                Texture.backgroundMars86, 
//                Texture.backgroundMars87,
//                Texture.backgroundMars88
//                );
            }
        }
        
        if(levelNo == 3 || levelNo == 8){
            if(animEarth == null){
//                animEarth = new Animation(12, 
//                Texture.backgroundEarth0, 
//                Texture.backgroundEarth1, 
//                Texture.backgroundEarth2, 
//                Texture.backgroundEarth3,
//                Texture.backgroundEarth4, 
//                Texture.backgroundEarth5, 
//                Texture.backgroundEarth6, 
//                Texture.backgroundEarth7,
//                Texture.backgroundEarth8, 
//                Texture.backgroundEarth9, 
//                Texture.backgroundEarth10,  
//                Texture.backgroundEarth11, 
//                Texture.backgroundEarth12, 
//                Texture.backgroundEarth13,
//                Texture.backgroundEarth14, 
//                Texture.backgroundEarth15, 
//                Texture.backgroundEarth16, 
//                Texture.backgroundEarth17,
//                Texture.backgroundEarth18, 
//                Texture.backgroundEarth19, 
//                Texture.backgroundEarth20,
//                Texture.backgroundEarth21, 
//                Texture.backgroundEarth22, 
//                Texture.backgroundEarth23,
//                Texture.backgroundEarth24, 
//                Texture.backgroundEarth25, 
//                Texture.backgroundEarth26, 
//                Texture.backgroundEarth27,
//                Texture.backgroundEarth28, 
//                Texture.backgroundEarth29, 
//                Texture.backgroundEarth30,
//                Texture.backgroundEarth31, 
//                Texture.backgroundEarth32, 
//                Texture.backgroundEarth33,
//                Texture.backgroundEarth34, 
//                Texture.backgroundEarth35, 
//                Texture.backgroundEarth36, 
//                Texture.backgroundEarth37,
//                Texture.backgroundEarth38, 
//                Texture.backgroundEarth39, 
//                Texture.backgroundEarth40,
//                Texture.backgroundEarth41, 
//                Texture.backgroundEarth42, 
//                Texture.backgroundEarth43,
//                Texture.backgroundEarth44, 
//                Texture.backgroundEarth45, 
//                Texture.backgroundEarth46, 
//                Texture.backgroundEarth47,
//                Texture.backgroundEarth48, 
//                Texture.backgroundEarth49, 
//                Texture.backgroundEarth50,
//                Texture.backgroundEarth51, 
//                Texture.backgroundEarth52, 
//                Texture.backgroundEarth53,
//                Texture.backgroundEarth54, 
//                Texture.backgroundEarth55, 
//                Texture.backgroundEarth56, 
//                Texture.backgroundEarth57,
//                Texture.backgroundEarth58, 
//                Texture.backgroundEarth59, 
//                Texture.backgroundEarth60,
//                Texture.backgroundEarth61, 
//                Texture.backgroundEarth62, 
//                Texture.backgroundEarth63,
//                Texture.backgroundEarth64, 
//                Texture.backgroundEarth65, 
//                Texture.backgroundEarth66, 
//                Texture.backgroundEarth67,
//                Texture.backgroundEarth68, 
//                Texture.backgroundEarth69, 
//                Texture.backgroundEarth70,
//                Texture.backgroundEarth71, 
//                Texture.backgroundEarth72, 
//                Texture.backgroundEarth73,
//                Texture.backgroundEarth74, 
//                Texture.backgroundEarth75, 
//                Texture.backgroundEarth76, 
//                Texture.backgroundEarth77,
//                Texture.backgroundEarth78, 
//                Texture.backgroundEarth79, 
//                Texture.backgroundEarth80,
//                Texture.backgroundEarth81, 
//                Texture.backgroundEarth82, 
//                Texture.backgroundEarth83,
//                Texture.backgroundEarth84, 
//                Texture.backgroundEarth85, 
//                Texture.backgroundEarth86, 
//                Texture.backgroundEarth87,
//                Texture.backgroundEarth88
//                );
            }
        }
        
        if(levelNo == 4 || levelNo == 7){
            if(animSun == null){
//                animSun = new Animation(12, 
//                Texture.backgroundSun0, 
//                Texture.backgroundSun1, 
//                Texture.backgroundSun2, 
//                Texture.backgroundSun3,
//                Texture.backgroundSun4, 
//                Texture.backgroundSun5, 
//                Texture.backgroundSun6, 
//                Texture.backgroundSun7,
//                Texture.backgroundSun8, 
//                Texture.backgroundSun9, 
//                Texture.backgroundSun10,  
//                Texture.backgroundSun11, 
//                Texture.backgroundSun12, 
//                Texture.backgroundSun13,
//                Texture.backgroundSun14, 
//                Texture.backgroundSun15, 
//                Texture.backgroundSun16, 
//                Texture.backgroundSun17,
//                Texture.backgroundSun18, 
//                Texture.backgroundSun19, 
//                Texture.backgroundSun20,
//                Texture.backgroundSun21, 
//                Texture.backgroundSun22, 
//                Texture.backgroundSun23,
//                Texture.backgroundSun24, 
//                Texture.backgroundSun25, 
//                Texture.backgroundSun26, 
//                Texture.backgroundSun27,
//                Texture.backgroundSun28, 
//                Texture.backgroundSun29, 
//                Texture.backgroundSun30,
//                Texture.backgroundSun31, 
//                Texture.backgroundSun32, 
//                Texture.backgroundSun33,
//                Texture.backgroundSun34, 
//                Texture.backgroundSun35, 
//                Texture.backgroundSun36, 
//                Texture.backgroundSun37,
//                Texture.backgroundSun38, 
//                Texture.backgroundSun39, 
//                Texture.backgroundSun40,
//                Texture.backgroundSun41, 
//                Texture.backgroundSun42, 
//                Texture.backgroundSun43,
//                Texture.backgroundSun44, 
//                Texture.backgroundSun45, 
//                Texture.backgroundSun46, 
//                Texture.backgroundSun47,
//                Texture.backgroundSun48, 
//                Texture.backgroundSun49, 
//                Texture.backgroundSun50,
//                Texture.backgroundSun51, 
//                Texture.backgroundSun52, 
//                Texture.backgroundSun53,
//                Texture.backgroundSun54, 
//                Texture.backgroundSun55, 
//                Texture.backgroundSun56, 
//                Texture.backgroundSun57,
//                Texture.backgroundSun58, 
//                Texture.backgroundSun59, 
//                Texture.backgroundSun60,
//                Texture.backgroundSun61, 
//                Texture.backgroundSun62, 
//                Texture.backgroundSun63,
//                Texture.backgroundSun64, 
//                Texture.backgroundSun65, 
//                Texture.backgroundSun66, 
//                Texture.backgroundSun67,
//                Texture.backgroundSun68, 
//                Texture.backgroundSun69, 
//                Texture.backgroundSun70,
//                Texture.backgroundSun71, 
//                Texture.backgroundSun72, 
//                Texture.backgroundSun73,
//                Texture.backgroundSun74, 
//                Texture.backgroundSun75, 
//                Texture.backgroundSun76, 
//                Texture.backgroundSun77,
//                Texture.backgroundSun78, 
//                Texture.backgroundSun79, 
//                Texture.backgroundSun80,
//                Texture.backgroundSun81, 
//                Texture.backgroundSun82, 
//                Texture.backgroundSun83,
//                Texture.backgroundSun84, 
//                Texture.backgroundSun85, 
//                Texture.backgroundSun86, 
//                Texture.backgroundSun87,
//                Texture.backgroundSun88
//                );
            }
        }
        firstTick = true;
        lastTick = false;

        //Reset Level Time
        resetLevelTime();
    }
    
    
    /**
     * changeLevel()
     * 
     */
    public static void changeLevel(){
        if(levelNo < 8){
            levelNo++;
            setLevelNo(levelNo);
        } else {
            gameComplete = true;
        }
    }
    
    
    /**
     * getLevelNo()
     * 
     * 
     * @return levelNo
     */
    public static int getLevelNo(){
        return levelNo;
    }
    
    
    /**
     * setLevelNo()
     * 
     * 
     * @param levNo
     */
    public static void setLevelNo(int levNo){
        levelNo = levNo;
        Game.level = new Level(levelNo, Asteroids.getMainWidth(), Asteroids.getMainHeight());
    }
    
    
    /**
     * getLevelName()
     * 
     * 
     * @return levelName
     */
    public static String getLevelName(){
        switch(levelNo){
            case 1:
                levelName = "Start Zone - Dark side of the moon";
                
                break;
                
            case 2:
                levelName = "Martian Orbit - The Red Planet";
                
                break;
                
            case 3:
                levelName = "Terra Ferma - Protect Earth";
                
                break;
                
            case 4:
                levelName = "Fourth Quarter - Save the Sun";
                
                break;
                
            case 5:
                levelName = "Lunar Orbit - The Full Moon";
                
                break;
                
            case 6:
                levelName = "Martian Resistance - Defend Mars";
                
                break;
                
            case 7:
                levelName = "3rd Rock From The Sun - Protect Earth (again)";
                
                break;
                
            case 8:
                levelName = "Final Frontier - Send em to the Sun";
                
                break;
            
            default:
                levelName = "GAME COMPLETE!";
                
                break;
            
        }

        return levelName;
    }
    
    
    /**
    * tick()
    * 
    * 
    * Updates Level Object
    * 
    */
    public static void tick(){
        //Manage Level: First Tick
        if(firstTick) {
            //Set firstTick: false
            firstTick = false;
            
            //Reset Game Time
            Game.resetGameTime();
            
            //Reset Level Time
            resetLevelTime();
            
            if(Game.getGame().getGameMusic()){
                if(Game.levelNo == 1 && !Sound.Level1.isPlaying()){
                    Sound.Level1.loop();
                }
                if(Game.levelNo == 2 && !Sound.Level2.isPlaying()){
                    Sound.Level2.loop();
                }
                if(Game.levelNo == 3 && !Sound.Level3.isPlaying()){
                    Sound.Level3.loop();
                }
                if(Game.levelNo == 4 && !Sound.Level4.isPlaying()){
                    Sound.Level4.loop();
                }
                if(Game.levelNo == 5 && !Sound.Level5.isPlaying()){
                    Sound.Level5.loop();
                }
                if(Game.levelNo == 6 && !Sound.Level6.isPlaying()){
                    Sound.Level6.loop();
                }
                if(Game.levelNo == 7 && !Sound.Level7.isPlaying()){
                    Sound.Level7.loop();
                }
                if(Game.levelNo == 8 && !Sound.Level8.isPlaying()){
                    Sound.Level8.loop();
                }
            }
        }
        
        //Manage Level: Last Tick
        if(lastTick) {
            lastTick = false;
            Sound.stopAll();
            if(!Sound.creditsSoundtrack.isPlaying()){
                Sound.creditsSoundtrack.play();
            }
            
            //Reset Level Time
            resetLevelTime();
        }
        
        if(Game.getGame().menu == null && !Game.getGame().stateManager.currentState.getName().equals("menu")){
            
            switch(levelNo){
                case 1:
//                    anim.runRen();
                    
                    break;
                    
                case 2:
//                    animMars.runRen();
                    
                    break;
                    
                case 3:
//                    animEarth.runRen();
                    
                    break;
                    
                case 4:
//                    animSun.runRen();
                    
                    break;
                    
                case 5:
//                    anim.runRen();
                    
                    break;
                    
                case 6:
//                    animMars.runRen();
                    
                    break;
                    
                case 7:
//                    animSun.runRen();
                    
                    break;
                    
                case 8:
//                    animEarth.runRen();
                    
                    break;
                    
            }
            
            Game.asteroidz.tick();
            
//            P1Explode.tick();
//            P1BExplode.tick();
            
            Thrust.tick();
            Bullet.tick();
            
            if(Game.asteroidz.getAll().size() == 0){
                Game.asteroidz = new Asteroid();
            }
        }
          
    }
    
    
    /**
    * render(Render target, Graphics2D g)
    * 
    * 
    * Renders/Repaints Level Objects
    * 
    * @param target - the Render of Level
    * @param g
    */
    public void render(Render target, Graphics2D g2d){
        Graphics2D g2d_Level = g2d;
        AffineTransform oldXForm = g2d.getTransform();
        
        //Game.hud.render(target, g);
        if(!levelComplete && Game.pauseTime == 0){

            switch(levelNo){
                case 1:
//                    anim.render(target, 180, 220, 0.75, 1280, 720);
                    break;
                case 2:
//                    animMars.render(target, 120, 190, 0.75, 480, 270);
                    break;
                case 3:
//                    animEarth.render(target, 180, 220, 0.5, 480, 270);
                    break;
                case 4:
//                    animSun.render(target, 10, 110, 0.6, 480, 270);
                    break;
                case 5:
//                    anim.render(target, 80, 170, 1.3, 1280, 720);
                    break;
                case 6:
//                    animMars.render(target, 60, 140, 1.0, 480, 270);
                    break;
                case 7:
//                    animSun.render(target, 10, 110, 0.6, 480, 270);
                    break;
                case 8:
//                    animEarth.render(target, 40, 140, 1.0, 480, 270);
                    break;
            }
            
        }
        
        if(Game.showFPS){
            g2d_Level.setColor(Color.decode("#fe1300"));
            g2d_Level.drawString(Game.theFPS, Labels.fpsX, Labels.fpsY);

        }
        if(Game.showUPS){
            g2d_Level.setColor(Color.decode("#f0ff00"));
            g2d_Level.drawString(Game.theUPS, Labels.upsX, Labels.upsY);
        }
        if(Game.showGameTime){
            g2d_Level.setFont(new Font("default", Font.BOLD, 18));
            g2d_Level.setColor(Color.DARK_GRAY);
            g2d_Level.drawString(Game.theGameTime, Labels.upsX+51, Labels.upsY+55);
            g2d_Level.setColor(Color.decode("#008fea"));
            g2d_Level.drawString(Game.theGameTime, Labels.upsX+50, Labels.upsY+54);
        }
        if(Game.showLevelTime){
            g2d_Level.setFont(new Font("default", Font.BOLD, 18));
            g2d_Level.setColor(Color.DARK_GRAY);
            g2d_Level.drawString(theLevelTime, Labels.upsX+231, Labels.upsY+55);
            g2d_Level.setColor(Color.decode("#ff8fea"));
            g2d_Level.drawString(theLevelTime, Labels.upsX+230, Labels.upsY+54);
        }
                
        g2d_Level.setFont(new Font("default", Font.BOLD, 18));
        g2d_Level.setColor(Color.DARK_GRAY);
        g2d_Level.drawString("Asteriods Left: "+Asteroid.getAll().size(), Labels.upsX+401, Labels.upsY+55);
        g2d_Level.setColor(Color.BLUE);
        g2d_Level.drawString("Asteriods Left: "+Asteroid.getAll().size(), Labels.upsX+400, Labels.upsY+54);

        if(Game.showBounds){
            g2d_Level.setColor(Color.GREEN);
            g2d_Level.drawRect(groundBounds.x, groundBounds.y, groundBounds.width, groundBounds.height);
            g2d_Level.setColor(Color.WHITE);
            //g2d_Level.drawRect(Enemy.leftSide - 5, 29, 5, Display.game.getHeight() - (28 + groundBounds.height + 8));
            g2d_Level.setColor(Color.WHITE);
            //g2d_Level.drawRect(Enemy.rightSide + 19, 29, 5, Display.game.getHeight() - (28 + groundBounds.height + 8));
        }    
        
        if (Game.pauseTime > 0 && !Game.paused && Game.getGame().menu == null) {
            if(levelComplete){
                long time = System.currentTimeMillis();
//                BRCExplode.clearAll();
//                Barricade.clearAll();
//                P1Explode.clearAll();
                P1Bullet.clearAll();
                
                for (levelCompleteCount = 0; levelCompleteCount < 500; levelCompleteCount++) {
                    //Draw scaled 3D SI Icon: GOLD
                    if(time / 25 % 5 == 0){
                        //render Space Invaders Icon
                        //target.scaleDraw(Texture.buttonsA, 3, 234, 132, 48, 126, 50, 38, 0xFFBF00);
                        //target.scaleDraw(Texture.buttonsA, 3, 230, 128, 48, 126, 50, 38, 0xBF9B30);
                    } else if(time / 200 % 40 == 0){
                        //render Space Invaders Icon
                        //target.scaleDraw(Texture.buttonsA, 3, 234, 132, 48, 126, 50, 38, 0xBF9B30);
                        //target.scaleDraw(Texture.buttonsA, 3, 230, 128, 48, 126, 50, 38, 0xFFBF00);
                    }
                    if (time / 450 % 2 == 0) {

                        //COMPLETE
                        //target.scaleDraw(Texture.buttonsA, Labels.click2FocusScale, Labels.click2FocusX-20, Labels.click2FocusY+40, 235, 200, 220, 30, 0xBF9B30);
                        //target.scaleDraw(Texture.buttonsA, Labels.click2FocusScale, Labels.click2FocusX-24, Labels.click2FocusY+36, 235, 200, 220, 30, 0xffffff);

                        
                        //Level
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelX-120, Labels.LevelY, 130, 300, 73, 35, levelTxtBGCol);
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelX-116, Labels.LevelY-4, 130, 300, 73, 35, 0xffffff);

                        if (Game.levelNo == 1){
                            //level 1 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 200, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 200, 300, 15, 35, 0xffffff);
                        }
                        
                        if (Game.levelNo == 2) {
                            //level 2 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 220, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 220, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 3) {
                            //level 3 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 240, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 240, 300, 15, 35, 0xffffff);
                        }
                        
                        if (Game.levelNo == 4) {
                            //level 4 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 260, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 260, 300, 15, 35, 0xffffff);
                        }
                        
                        if (Game.levelNo == 5) {
                            //level 5 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 276, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 276, 300, 15, 35, 0xffffff);
                        }
                        
                        if (Game.levelNo == 6) {
                            //level 6 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 292, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 292, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 7) {
                            //level 7 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 310, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 310, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 8) {
                            //level 8 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-120, Labels.LevelNoY, 332, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-116, Labels.LevelNoY-4, 332, 300, 15, 35, 0xffffff);
                        }                        
                    
                        //target.drawText("Lives Bonus: "+Game.getLivesBonus(), 2, Labels.enterLevelX+120, Labels.enterLevelY+170, 0xFeca00);
                        //target.drawText("Time Bonus: "+Game.getTimeBonus(), 2, Labels.enterLevelX+120, Labels.enterLevelY+200, 0xFF0000);
                        //target.drawText("Accuracy Bonus: "+Game.getAccuracyBonus(), 2, Labels.enterLevelX+120, Labels.enterLevelY+230, 0xFFaa00);
                        //target.drawText("Level Bonus: "+Game.getLevelBonus(), 3, Labels.enterLevelX+120, Labels.enterLevelY+275, 0xFFce00);
                        
                        Asteroids.setStatusBar("Level "+Game.levelNo+" : "+getLevelName()+" Completed!!");    
                    } else {
                        //target.drawText("Level Bonus: "+Game.getLevelBonus(), 3, Labels.enterLevelX+120, Labels.enterLevelY+275, 0xffffff);
                    }
                    
                    if (Game.levelNo == 1){
                        //level 1 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-30, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-32, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 2){
                        //level 2 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-2, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 3){
                        //level 3 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+10, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+8, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 4){
                        //level 4 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-5, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-7, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 5){
                        //level 5 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+10, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+8, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 6){
                        //level 6 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-22, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-24, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 7){
                        //level 7 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-60, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-62, Labels.enterLevelY+68, 0xffffff);
                    }
                    if (Game.levelNo == 8){
                        //level 8 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-50, Labels.enterLevelY+70, levelTxtBGCol);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-52, Labels.enterLevelY+68, 0xffffff);
                    }
                    
                    if (levelCompleteCount == 0) {
                        lastTick = true;
                        break;
                    }
                }
                
            } else {
                long time = System.currentTimeMillis();
                for (levelStartCount = 0; levelStartCount < 100; levelStartCount++) {    
                    //Draw scaled 3D SI Icon: BLUE
                    if(time / 25 % 5 == 0){
                        //render Space Invaders Icon
                        //target.scaleDraw(Texture.buttonsA, 3, 234, 132, 48, 126, 50, 38, enterLvlIconBG);
                        //target.scaleDraw(Texture.buttonsA, 3, 230, 128, 48, 126, 50, 38, enterLvlIconFG);
                    } else if(time / 20 % 40 == 0){
                        //render Space Invaders Icon
                        //target.scaleDraw(Texture.buttonsA, 3, 234, 132, 48, 126, 50, 38, enterLvlIconFG);
                        //target.scaleDraw(Texture.buttonsA, 3, 230, 128, 48, 126, 50, 38, enterLvlIconBG);
                    }
                    if (time / 450 % 2 == 0) {

                        //Entering 3D
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.enterLevelX, Labels.enterLevelY+30, 0, 300, 130, 35, levelTxtBGCol);
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.enterLevelX-4, Labels.enterLevelY+26, 0, 300, 130, 35, 0xffffff);

                        //Level
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelX, Labels.LevelY+30, 130, 300, 73, 35, levelTxtBGCol);
                        //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelX-4, Labels.LevelY+26, 130, 300, 73, 35, 0xffffff);

                        if (Game.levelNo == 1 || Game.levelNo == -1) {
                            
                            //level 1 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelY+30, 200, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelY+26, 200, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 2) {
                            //level 2 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 220, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 220, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 3) {
                            //level 3 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 240, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 240, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 4) {
                            //level 4 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 260, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 260, 300, 15, 35, 0xffffff);
                        }
                        
                        if (Game.levelNo == 5) {
                            //level 5 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 276, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 276, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 6) {
                            //level 6 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 292, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 292, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 7) {
                            //level 7 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 310, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 310, 300, 15, 35, 0xffffff);
                        }

                        if (Game.levelNo == 8) {
                            //level 8 No.
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX, Labels.LevelNoY+30, 332, 300, 15, 35, levelTxtBGCol);
                            //target.scaleDraw(Texture.buttonsA, Labels.enterLevelNoScale, Labels.LevelNoX-4, Labels.LevelNoY+26, 332, 300, 15, 35, 0xffffff);
                        }
                        Asteroids.setStatusBar("Entering Level "+Game.levelNo+" : "+getLevelName());
                        
                    }
                    
                    if (Game.levelNo == 1 || Game.levelNo == -1) {
                        //level 3D Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-30, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-31, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 2) {
                        //level 2 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-1, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 3) {
                        //level 3 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+10, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+9, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 4) {
                        //level 4 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-5, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-6, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 5) {
                        //level 5 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+10, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX+9, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 6) {
                        //level 6 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-22, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-21, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 7) {
                        //level 7 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-60, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-61, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    if (Game.levelNo == 8) {
                        //level 8 Name
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-50, Labels.enterLevelY+100, 0xffffff);
                        //target.drawText(getLevelName(), 2, Labels.enterLevelX-51, Labels.enterLevelY+99, levelTxtBGCol);
                    }
                    
                    if(levelStartCount == 100){
                        firstTick = true;
                        //Reset Level Time
                        resetLevelTime();
                        break;
                    }
                    
                }
            }
            
        }
    
    }

    
    /**
    * setBackground(int index)
    * 
    * 
    * @param index
    */
    public static void setBackground(int index) {
        background = index;
    }

    
    /**
    * setGroundColor(int col)
    * 
    * 
    * @param col
    */
    private void setGroundColor(int col) {
        groundColor = col;
    }

    
    /**
    * resetLevelTime()
    * 
    */
    public static void resetLevelTime() {
        System.out.println("Level: Resetting Level Time");
        Game.levelTime = 0;
    }
    
}
