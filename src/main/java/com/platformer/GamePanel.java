package com.platformer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16
    final int scale = 3;
    private int jumpSpeed = -15; // Initial jump speed
    final int gravity = 1; // Gravity
    private boolean isJumping = false;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int nollakohta = screenHeight - 148;
    int currentLevel = 1;
    int lifes = 5;
    int points = 0;
    int suunta = 1;
    Coin cointoremove;
    private int cameraX = 0;

    final int worldWidth = 3000; // example width
    // hahmon kuvat
    Image testikuva = ImageLoader.loadImage("src\\main\\resources\\mariotesti_transparent.png");
    Image testikuva2 = ImageLoader.loadImage("src\\main\\resources\\mariotesti_transparent_flipped.png");
    Image emptyblock = ImageLoader.loadImage("src\\main\\resources\\d4k5lsw-68b58c7d-09c1-43e2-bcfe-99a568a39be5.png");
    Image putki = ImageLoader.loadImage("src\\main\\resources\\resized_image.png");
    Image goombaimage = ImageLoader.loadImage("src\\main\\resources\\8_Bit_goomba_corrected.png");
    
    int fps = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    List<Coin> coins;
    List<Pipe> ykköskenttä;
    List<Pipe> kakkoskenttä;
    List<Pipe> kolmoskenttä;
    List<Block> kakkoskenttäBlock;
    List<Block> kolmoskenttäBlock;
    List<Block> ykköskenttäBlock;
    List<Goomba> ykköskenttäGoomba;
    List<Goomba> kakkoskenttäGoomba;
    List<Goomba> kolmoskenttäGoomba;
    List<Shellcreeper> ykköskenttäShellcreepers;
    List<Shellcreeper> kakkoskenttäShellcreepers;
    List<Shellcreeper> kolmoskenttäShellcreepers;
    List<List<Pipe>> levelPipes;
    List<List<Goomba>> levelGoombas;
    List<List<Block>> levelBlocks;
    List<List<Shellcreeper>> levelShellcreepers;
    List<Shellcreeper> deletedShellcreepers;
    List<Goomba> deletedGoombas;
    List<Goal> levelGoals;

    Goal ykkösmaali;
    Goal kakkosmaali;
    Goal kolmosmaali;

    // Player's default position
    int playerX = 100;
    int playerY = screenHeight - 148;
    int playerSpeed = 4;
    boolean levelpassed = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        coins = new ArrayList<>();
        levelPipes = new ArrayList<>();
        levelGoombas = new ArrayList<>();
        levelGoals = new ArrayList<>();
        levelShellcreepers = new ArrayList<>();
        levelBlocks = new ArrayList<>();
        deletedGoombas = new ArrayList<>();
        deletedShellcreepers = new ArrayList<>();

        // Level 1 initialization
        ykköskenttä = new ArrayList<>();
        ykköskenttä.add(new Pipe(300, screenHeight - 200, 50, 100));
        ykköskenttä.add(new Pipe(500, screenHeight - 200, 50, 100));
        ykköskenttä.add(new Pipe(1500, screenHeight - 200, 50, 100));

        ykköskenttäGoomba = new ArrayList<>();
        ykköskenttäGoomba.add(new Goomba(600, 800, 700, nollakohta, 2));
        ykköskenttäGoomba.add(new Goomba(1000, 1200, 1100, nollakohta, 2));

        ykköskenttäBlock = new ArrayList<>();
        ykköskenttäBlock.add(new QuestionMarkBlock(100, screenHeight - 200,  30, 30, "coin", 0));
        ykköskenttäBlock.add(new QuestionMarkBlock(130, screenHeight - 200,  30,30, "coin", 0));
        ykköskenttäBlock.add(new Brick(160,screenHeight - 200,30,30,0));
        ykkösmaali = new Goal(1700, 200, 500, 100);
        ykköskenttäShellcreepers = new ArrayList<>();
        levelPipes.add(ykköskenttä);
        levelGoombas.add(ykköskenttäGoomba);
        levelGoals.add(ykkösmaali);
        levelShellcreepers.add(ykköskenttäShellcreepers);
        levelBlocks.add(ykköskenttäBlock);
        // Level 2 initialization
        kakkoskenttä = new ArrayList<>();
        kakkoskenttä.add(new Pipe(300, screenHeight - 200, 50, 100));

        kakkoskenttäGoomba = new ArrayList<>();
        kakkoskenttäGoomba.add(new Goomba(800, 1000, 900, nollakohta, 2));
        kakkoskenttäBlock = new ArrayList<>();
        kakkosmaali = new Goal(1700, 200, 500, 100);
        kakkoskenttäShellcreepers = new ArrayList<>();
        levelPipes.add(kakkoskenttä);
        levelGoombas.add(kakkoskenttäGoomba);
        levelGoals.add(kakkosmaali);
        levelShellcreepers.add(kakkoskenttäShellcreepers);
        levelBlocks.add(kakkoskenttäBlock);
        // Level 3 initialization
        kolmoskenttä = new ArrayList<>();
        kolmoskenttä.add(new Pipe(300, screenHeight - 200, 50, 100));
        kolmoskenttä.add(new Pipe(1300, screenHeight - 200, 50, 100));
        kolmoskenttäGoomba = new ArrayList<>();
        kolmoskenttäGoomba.add(new Goomba(1000, 1200, 1100, nollakohta, 2));
        kolmoskenttäBlock= new ArrayList<>();
        kolmoskenttäShellcreepers = new ArrayList<>();
        kolmoskenttäShellcreepers.add(new Shellcreeper(800, 1000, 900, nollakohta, 1));

        kolmosmaali = new Goal(1700, 200, 500, 100);

        levelPipes.add(kolmoskenttä);
        levelGoombas.add(kolmoskenttäGoomba);
        // Note: If you have a separate list for shellcreepers, make sure to add them to a similar structure.
        levelShellcreepers.add(kolmoskenttäShellcreepers);
        levelGoals.add(kolmosmaali);
        levelBlocks.add(kolmoskenttäBlock);
    }
    /* 
    public void resetLevel() {
        ykköskenttä.clear();
        ykköskenttä.addAll(initialYkköskenttä);
    
        ykköskenttäGooomba.clear();
        ykköskenttäGooomba.addAll(initialYkköskenttäGooomba);
    
        kakkoskenttä.clear();
        kakkoskenttä.addAll(initialKakkoskenttä);
    
        kakkoskenttäGooomba.clear();
        kakkoskenttäGooomba.addAll(initialKakkoskenttäGooomba);
    
        // Clear deleted goombas list
        deletedGoombas.clear();
    }
    */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            long currentTime = System.nanoTime();
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            jumpSpeed = -15; // Initial jump speed
            isJumping = true;
        }
        playerY += jumpSpeed;
        jumpSpeed += gravity;
        if (playerY >= screenHeight - 148 || isOnGround(playerX, playerY + 1)) {
            
            playerY = getGroundLevel(playerX, playerY);
            
            System.out.println(getGroundLevel(playerX, playerY));
            
            jumpSpeed = 0; // Reset jump speed
            isJumping = false; // Stop jumping
        }
    }

    public void update() {
        if (levelpassed) {
            if (keyH.spacePressed) {
                
                // Add logic to continue to the next level
                levelpassed = false; // Reset the flag
                playerX = 100;
                currentLevel++; // Example of advancing to the next level
            }
            return; // Skip the rest of the update logic if level is passed
        }
        if(lifes == 0){
            if (keyH.spacePressed) {
                
                lifes = 1;
                playerX = 100; 
                currentLevel = 1;
                //resetLevel(); // Reset all objects
                

               
            }
            return;
        }
        if (isJumping) {
            jump();
        } else if (!isOnGround(playerX, playerY + 1)) {
            playerY += 3; // Apply gravity
        }

        // Handle jump key press
        if (keyH.upPressed && (playerY == screenHeight - 148 || isOnGround(playerX, playerY + 1))) {
            jump();
        }

        // Handle left/right movement
        if (keyH.leftPressed && !isColliding(playerX - playerSpeed, playerY)) {
            suunta = 0;
            playerX -= playerSpeed;
        } else if (keyH.rightPressed && !isColliding(playerX + playerSpeed, playerY)) {
            suunta = 1;
            playerX += playerSpeed;
        }

        cameraX = playerX - screenWidth / 2;
        

        // Clamp camera position to the game world boundaries
        cameraX = Math.max(0, Math.min(cameraX, worldWidth - screenWidth));

        pipeBottonColliding(playerX,playerY);

        for (Goomba goomba : levelGoombas.get(currentLevel - 1)) { 
            goomba.GoombaMovement();
           
        }
        if (!GoombaJumpon(playerX, playerY)) {
            
        }
        if (!GoombaisColliding(playerX, playerY)) {
           
        }
        if(!GoalisColliding(playerX, playerY)){
            
        }
        List<Shellcreeper> toRemove = new ArrayList<>();
        for(Pipe pipe : levelPipes.get(currentLevel-1)){
            ShellcreeperisCollidingPipe2(pipe.x, pipe.y, pipe.width, pipe.height,toRemove);
        }
        removeshellCreeper(toRemove);
       
       /*  for(Shellcreeper shellcreeper : levelShellcreepers.get(currentLevel-1)){
            if (shellcreeper.state == 2) {
                GoombaisCollidingShell(shellcreeper.x, shellcreeper.y);
            }
            if (ShellcreeperisCollidingPipe(shellcreeper.x, shellcreeper.y) ) {
                System.out.println("putki");
                shellcreeper.collision += 1;
                System.out.println("collision"+ shellcreeper.collision);
                
                
               
            }
            
            
           
        }
        */
        if (!ShellcreeperJumpon(playerX,playerY)) {
            
        }
        if (!ShellcreeperisColliding(playerX, playerY)) {
            
        }
        
        for (Shellcreeper shellcreeper : levelShellcreepers.get(currentLevel - 1)) {
            if (shellcreeper.state == 2) {
                GoombaisCollidingShell(shellcreeper.x, shellcreeper.y);
            }
            shellcreeper.ShellcreeperMovement();
        }
        CoinisColliding(playerX, playerY);
        
    }
    private boolean ShellcreeperisCollidingPipe2(int x, int y,int height,int width,List<Shellcreeper> toRemove){
        Rectangle PipeBounds = new Rectangle(x, y, height, width);
        boolean collisionDetected = false;
        //List<Shellcreeper> toRemove = new ArrayList<>();
        for (Shellcreeper shellcreeper : levelShellcreepers.get(currentLevel-1) ) {
            Rectangle shellcreeperBounds = new Rectangle(shellcreeper.x, shellcreeper.y, 20,20);
            
            if (PipeBounds.intersects(shellcreeperBounds)) {
                    System.out.println("osu putkeen");
                    shellcreeper.shellspeed = shellcreeper.shellspeed * -1;
                    shellcreeper.collision += 1;
                    System.out.println(shellcreeper.collision);
                    if (shellcreeper.collision == 3) {
                        toRemove.add(shellcreeper);
                    }
                    collisionDetected = true;
                    
            }
            
        }
        
        return collisionDetected;
    }
    
    private void CoinisColliding(int x, int y) {
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        //System.out.println(playerBounds);
        for (Coin coin : coins) {

            Rectangle CoinBounds = new Rectangle(coin.x, coin.y, coin.width, coin.height);
            if (playerBounds.intersects(CoinBounds)) {
                System.out.println("osu kolikkoon " + coin);
                points += 100;
                cointoremove = coin;
                
            }
        }
       //System.out.println("coins " +coins);
       coins.remove(cointoremove);
        
    } 
    private void removeshellCreeper(List<Shellcreeper> toRemove){
        List<Shellcreeper> currentShellcreepers = levelShellcreepers.get(currentLevel - 1);
        currentShellcreepers.removeAll(toRemove);
    }
    private boolean ShellcreeperisColliding(int x, int y){
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        for (Shellcreeper shellcreeper : levelShellcreepers.get(currentLevel-1) ) {
            Rectangle shellcreeperBounds = new Rectangle(shellcreeper.x, shellcreeper.y, 20,20);
            if (playerBounds.intersects(shellcreeperBounds)) {
                System.out.println(shellcreeper.state);
                if (shellcreeper.state == 1 || shellcreeper.state == 2 ) {
                    lifes = lifes -1;
                    playerX = 100;
                    shellcreeper.state = 1;
                    shellcreeper.collision = 0;
                }
                if (shellcreeper.state == 0 && jumpSpeed == 0) {
                    
                    
                    if (suunta == 1) {
                        shellcreeper.shellspeed = Math.abs(shellcreeper.shellspeed); 
                        shellcreeper.x += 10;
                    } else if (suunta == 0) {
                        shellcreeper.shellspeed = -Math.abs(shellcreeper.shellspeed); 
                        shellcreeper.x -= 10;
                    }
                    shellcreeper.state = 2;
                }
                
                
                return true;
            }
        }
        return false;
    }
    
    private boolean ShellcreeperJumpon(int x,int y){
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        Shellcreeper shellcreeperToRemove = null;
        for (Shellcreeper shellcreeper : levelShellcreepers.get(currentLevel-1 ) ) {
           // Rectangle goombaBounds = new Rectangle(goomba.x, goomba.y, 20, 20);
            Rectangle shellTopBounds = new Rectangle(shellcreeper.x, shellcreeper.y, 20, 1);

            if (playerBounds.intersects(shellTopBounds) && jumpSpeed > 0) { 
                System.out.println("tappo!");
                if (shellcreeper.state == 1) {
                    shellcreeper.setState(0);
                    jumpSpeed = -15;
                }
                else{
                    jumpSpeed = -15;
                    shellcreeperToRemove = shellcreeper; 
                    points += 100;
                }
                
            }
            if (shellcreeperToRemove != null) {
                System.out.println(shellcreeperToRemove);
                //deletedGoombas.add(shellcreeperToRemove);
               
                levelShellcreepers.get(currentLevel - 1).remove(shellcreeperToRemove);
                jumpSpeed = 15; 
                //shellcreeper.setState(500);
                return true;
            }
        }
        return false;
    }
    private boolean isOnGround(int x, int y) {
        if (y >= screenHeight - 148) {
            return true;
        }
        for (Pipe pipe : levelPipes.get(currentLevel - 1)) {
            Rectangle pipeBounds = new Rectangle(pipe.x, pipe.y, pipe.width+5, pipe.height);
            if (new Rectangle(x+tileSize/2-10, y + tileSize, 20, 1).intersects(pipeBounds)) {
                
                return true;
            }
        }
        for (Block block : levelBlocks.get(currentLevel - 1)) {
            //toimii mutta alko se pohjaan pomppaaminen bugaan
            Rectangle blockBounds = new Rectangle(block.x, block.y, block.width+5, block.height);
            if (new Rectangle(x+tileSize/2-10, y , 20, 50).intersects(blockBounds)) {
               //System.out.println("blockki");
                return true;
            }
        }
      
        return false;
    }

    private int getGroundLevel(int x, int y) {
        if (y >= screenHeight - 148) {
            return screenHeight - 148;
        }
        
        // Check collision with pipes
        for (Pipe pipe : levelPipes.get(currentLevel - 1)) {
            Rectangle pipeBounds = new Rectangle(pipe.x, pipe.y, pipe.width-10, pipe.height);
            if (new Rectangle(x, y + tileSize, tileSize, 1).intersects(pipeBounds)) {
                System.out.println("Pipe collision detected");
                return pipe.y - tileSize;
            }
        }
        
        for (Block block : levelBlocks.get(currentLevel - 1)) {
            Rectangle blockBounds = new Rectangle(block.x, block.y, block.width-10, block.height);
            if (new Rectangle(x, y + tileSize, tileSize, 1).intersects(blockBounds)) {
               //System.out.println("testi blcok");
                return block.y - tileSize;
            }
        }
        
        return y;
    }
    
    private int pipeBottonColliding(int x, int y){

        for (Block block : levelBlocks.get(currentLevel - 1)) {
            Rectangle blockBounds = new Rectangle(block.x, block.y+block.width+20, block.width, block.height);
            //muokkaaa
            if (new Rectangle(x+tileSize/2, y + tileSize, 20, 1).intersects(blockBounds)) {
                
                if (block instanceof QuestionMarkBlock questionMarkBlock) {
                    System.out.println(questionMarkBlock.x);
                    System.out.println("QuestionMarkBlock");
                    if (questionMarkBlock.state == 0) {
                        questionMarkBlock.state = 1;
                        Coin coin = new Coin(questionMarkBlock.x+5,questionMarkBlock.y-30);
                        coins.add(coin);
                        System.out.println("coin: " + coin);
                    }
                    
                }else if (block instanceof Brick) {
                    System.out.println("Brick");
                    levelBlocks.get(currentLevel - 1).remove(block);
                }
                playerY += 20;
                isJumping = false;
                return block.y - tileSize;
            }
        }

        return y;
    }
    private boolean isColliding(int x, int y) {
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        
        for (Pipe pipe : levelPipes.get(currentLevel - 1)) {
            Rectangle pipeBounds = new Rectangle(pipe.x+10, pipe.y, pipe.width-14, pipe.height-30);
            if (playerBounds.intersects(pipeBounds)) {

                return true;
            }
        }
        for (Block block : levelBlocks.get(currentLevel - 1)) {
            Rectangle blockBounds = new Rectangle(block.x, block.y, block.width-5, block.height);
            if (playerBounds.intersects(blockBounds)) {
                
                return true;
            }
        }
        return false;
    }
    
    
    private boolean GoombaisColliding(int x, int y) {
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        for (Goomba goomba : levelGoombas.get(currentLevel-1) ) {
            Rectangle goombaBounds = new Rectangle(goomba.x, goomba.y, 20,20);
            if (playerBounds.intersects(goombaBounds)) {
                lifes = lifes -1;
                playerX = 100;
                
                if (lifes != 0) {
                    deletedGoombas.clear();
                }
                
                return true;
            }
        }
        return false;
    }
    private boolean GoombaisCollidingShell(int x, int y) {
        Rectangle shellRectangle = new Rectangle(x, y, 20, 20);
        Goomba goombaToRemove = null;
        for (Goomba goomba : levelGoombas.get(currentLevel-1) ) {
            Rectangle goombaBounds = new Rectangle(goomba.x, goomba.y, 20,20);
            if (shellRectangle.intersects(goombaBounds)) {
                System.out.println("osu");
                goombaToRemove = goomba;

                //break;
            }
            if (goombaToRemove != null) {
                System.out.println(goombaToRemove);
                deletedGoombas.add(goombaToRemove);
               
                levelGoombas.get(currentLevel - 1).remove(goombaToRemove);
                return true;
            }
        }
        return false;
    }
    private boolean GoombaJumpon(int x, int y) {
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
        Goomba goombaToRemove = null;
        for (Goomba goomba : levelGoombas.get(currentLevel-1) ) {
           // Rectangle goombaBounds = new Rectangle(goomba.x, goomba.y, 20, 20);
            Rectangle goombaTopBounds = new Rectangle(goomba.x, goomba.y, 20, 1);

            if (playerBounds.intersects(goombaTopBounds) && jumpSpeed > 0) { 
                System.out.println("tappo!");
                points += 100;
                goombaToRemove = goomba;
                break;
            }
            if (goombaToRemove != null) {
                System.out.println(goombaToRemove);
                deletedGoombas.add(goombaToRemove);
               
                levelGoombas.get(currentLevel - 1).remove(goombaToRemove);
                return true;
            }
            return false;
        }
        if (goombaToRemove != null) {
            System.out.println(goombaToRemove);
            deletedGoombas.add(goombaToRemove);
           
            levelGoombas.get(currentLevel - 1).remove(goombaToRemove);
            jumpSpeed = -15; 
            return true;
        }
        return false;
    }
    private boolean GoalisColliding(int x, int y) {
        Rectangle playerBounds = new Rectangle(x, y, tileSize, tileSize);
            Goal goal = levelGoals.get(currentLevel -1);
            Rectangle GoalBounds = new Rectangle(goal.x, goal.y, goal.width, goal.height);
            if (playerBounds.intersects(GoalBounds)) {
                System.out.println("maali");
                System.out.println(coins);
                coins.clear();
                System.out.println(coins);
                levelpassed = true;
                return true;
            }
        
        return false;
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.translate(-cameraX, 0);
        
            
        g2.setColor(Color.blue);
        g2.fillRect(0,0,worldWidth*tileSize,maxScreenRow*tileSize);
            
        // Draw pipes
        for (Pipe pipe : levelPipes.get(currentLevel - 1)){
            g2.setColor(Color.orange);
            g2.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
            
            
            g2.drawImage(putki, pipe.getX()-75, pipe.getY()-8, pipe.getWidth()*4, pipe.getHeight()+20, null);
            
        }
               
        
        for(Goomba goomba : levelGoombas.get(currentLevel-1)){
            //g2.setColor(Color.red);
           // g2.fillRect(goomba.x ,nollakohta+29,20,20);
            g2.drawImage(goombaimage, goomba.getX()-4, nollakohta+20, 30, 30, null);
            //goombaimage
    
        }

        
            for(Shellcreeper shellcreeper  : levelShellcreepers.get(currentLevel-1)){
               
                    
                
                if (shellcreeper.state == 1) {
                    g2.setColor(Color.green);
                }
                else{
                g2.setColor(Color.yellow);
                }
                
                g2.fillRect(shellcreeper.x,nollakohta+29,20,20);
                
            }
        
        g2.setColor(Color.orange);
        g2.fillRect(ykkösmaali.x, screenHeight - 100 - ykkösmaali.height, ykkösmaali.width, ykkösmaali.height);
        
        for(Block block : levelBlocks.get(currentLevel-1)){
           Image image;
            if (block instanceof QuestionMarkBlock) {
                if (((QuestionMarkBlock) block).state == 1) {
                    image = emptyblock;
                }else{
                    image = block.image;
                }
            }
            else{
                image = block.image;
            }
            
            g2.drawImage(image, block.getX(), block.getY(), block.getWidth(), block.getHeight(), null);
        }
        
        for(Coin coin : coins){
            Image img = coin.image;
            g2.drawImage(img, coin.getX(), coin.getY(), coin.width, coin.height, null);
        }
        
       //draw playera
        
        g2.setColor(Color.white);
       // g2.fillRect(playerX, playerY, tileSize, tileSize);
        //g2.translate(cameraX, 0);

        if (suunta == 1) {
           // g2.setColor(Color.black);
            //g2.fillRect(playerX+40, playerY+10, 4, 4);
            g2.drawImage(testikuva, playerX, playerY, tileSize, tileSize, null);
           // g2.translate(-cameraX, 0);
        }else{
            g2.setColor(Color.black);
            //g2.fillRect(playerX+5, playerY+10, 4, 4);
            g2.drawImage(testikuva2, playerX, playerY, tileSize, tileSize, null);
            //g2.translate(-cameraX, 0);
        }

        if (isJumping == true) {
            g2.setColor(Color.blue);
            g2.fillRect(playerX+20, playerY+43, 4, 4);
        }
       /* 
        g2.setColor(Color.BLUE);
        g2.fillRect(playerX+tileSize/2-10, playerY, 20, 50);
        */
        
        // Draw ground
        Color darkGreen = new Color(0, 100, 0);
        g2.setColor(darkGreen);
        g2.fillRect(0, screenHeight - 100, worldWidth, 100);
        g2.translate(cameraX, 0);
        if (levelpassed == true) {
            Font font = new Font("Arial", Font.BOLD, 24);
            g2.setFont(font);
            g2.setColor(Color.red);
            g2.drawString("Level complited " + currentLevel + ". Press space to continue", screenWidth/4 , 100);
        }else if(lifes == 0){
            Font font = new Font("Arial", Font.BOLD, 24);
            g2.setFont(font);
            g2.setColor(Color.red);
            g2.drawString("Game over. Press space to continue", screenWidth/4 , 100);
        }
        else{
            
            g2.setColor(Color.red);
            g2.drawString("Lifes: "+lifes, screenWidth/2 , 100);

            g2.setColor(Color.red);
            g2.drawString("Points: "+points, 100 , 100);
        }

        
    // Translate back before drawing UI elements
    
   

   
 }
}


