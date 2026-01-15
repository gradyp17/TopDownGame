import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// How to Play:
// move with arrow keys and swing sword with space
// the sword attacks will face the direction the player will face
// the enemys have a health of 10 hits
// you must clear the enemys on each level to move to the next
// the boss is on level 10
// if you beat level 10 you win!
public class DrawingPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private Timer timer;
    private int[][] spriteMap;
    private BufferedImage spriteSheet;
    private int tileSize;
    private int numRows;
    private int numCols;

    private int points;
    private Boss boss;
    private Player player;

    private Board board;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private HealthBar healthBar;
    private long lastShootTime;
    private final long shootInterval = 2000;
    private boolean l2 = false;
    private boolean l3 = false;
    private boolean l4 = false;
    private boolean l5 = false;
    private boolean l6 = false;
    private boolean l7 = false;
    private boolean l8 = false;
    private boolean l9 = false;
    private boolean l10 = false;
    private boolean bossSpwaned = false;
    private long lastColTime;
    private boolean invincible;
    private Timer invincibilityTimer;


    public DrawingPanel(Color color, int[][] spriteMap, int numRows, int numCols) { // Accept numRows and numCols as parameters
        points = 0;
        this.spriteMap = spriteMap;
        this.numRows = numRows;
        this.numCols = numCols;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(10, this);
        timer.start();

        // Create the player at the center of the panel
        int playerX = 500;
        int playerY = 500;
        player = new Player(playerX, playerY, 2); // speed
        board = new Board();
        healthBar = new HealthBar();
        healthBar.update(player);
        enemies.add(new Slime(200,200,1));
//        enemies.add(new Shooter(400,200,1));
        lastShootTime = System.currentTimeMillis();
        lastColTime = System.currentTimeMillis();
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        drawSprites(g);
        board.draw(g);
        player.move(player.getVeloX(), player.getVeloY(), enemies);
        player.draw(g); // Draw the player
//        enemy.move(enemy.getVeloX(), enemy.getVeloY());
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
            if (enemies.get(i) instanceof Shooter) {
                ((Shooter) enemies.get(i)).drawBullets(g);
            }
        }

        if (bossSpwaned){
            boss.draw(g);
            boss.drawShockwaves(g);
        }


        drawPoints(g);
        healthBar.draw(g);
        player.swingSword(g);
        board.gameOver(g);
        board.gameComplete(g);



    }




    private void drawPoints(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Level " + board.getCurrentLevel(), 10, 50);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // You can add animation logic here
//        enemy.moveTo(player);
        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).moveTo(player);
            if (enemies.get(i) instanceof Shooter) {
                enemies.get(i).updateBullets();
            }
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime > shootInterval) {
            for (Enemy enemy : enemies) {
                if (enemy instanceof Shooter) {
                    ((Shooter) enemy).shoot(player);
                }
            }
            lastShootTime = currentTime;
        }

        if (bossSpwaned){
            boss.attack();
            boss.updateShockwaves();
        }

        healthBar.update(player);

        //board move
        board.boardChange(player);
        if (board.getMoveNums() > 0){
            board.addX();
            player.addX();
            board.subtractNum();
        }

        levelChecker();
        checkLevel();
        checkForCollisions();
        repaint();
    }

    private void levelChecker() {
        if (board.getMoveNums() == 0 && l2 == false && board.getCurrentLevel() == 2){//level2
            l2 = true;
            enemies.add(new Slime(200, 200, 1));
        }
        if (board.getMoveNums() == 0 && l3 == false && board.getCurrentLevel() == 3){//level2
            l3 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Slime(600, 200, 1));
        }
        if (board.getMoveNums() == 0 && l4 == false && board.getCurrentLevel() == 4){//level2
            l4 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Slime(600, 200, 1));
        }
        if (board.getMoveNums() == 0 && l5 == false && board.getCurrentLevel() == 5){//level2
            l5 = true;
            enemies.add(new Shooter(200, 200, 1));
        }
        if (board.getMoveNums() == 0 && l6 == false && board.getCurrentLevel() == 6){//level2
            l6 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Shooter(600, 200, 1));
        }
        if (board.getMoveNums() == 0 && l7 == false && board.getCurrentLevel() == 7){//level2
            l7 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Slime(600, 200, 1));
        }
        if (board.getMoveNums() == 0 && l8 == false && board.getCurrentLevel() == 8){//level2
            l8 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Shooter(600, 200, 1));
            enemies.add(new Shooter(400, 200, 1));
        }
        if (board.getMoveNums() == 0 && l9 == false && board.getCurrentLevel() == 9){//level2
            l9 = true;
            enemies.add(new Slime(200, 200, 1));
            enemies.add(new Slime(600, 200, 1));
        }
        if (board.getMoveNums() == 0 && l10 == false && board.getCurrentLevel() == 10){//level2
            l10 = true;
            bossSpwaned = true;
            boss = new Boss(300, 300, 1);
        }
        if (l10 == true && boss.getHealth() <= 0){
            //game completed
            board.setGameComplete(true);
        }
        if (player.getHealth() <= 0){
            board.setGameOver(true);
        }
    }


    public void checkLevel(){
        if (enemies.size() == 0){
            if (board.getCurrentLevel() == board.getLevel()){
                board.addLevel();
                System.out.println(board.getLevel());
            }
        }
    }

//    private void checkForSwordCollision() { //sword collisions
//        Rectangle swordSolid = player.getSwordHitBox();
//        Rectangle enemySolid = enemy.getHitBox();
//        if(swordSolid.intersects(enemySolid)){
//            enemy.dropHealth();
//        }
//    }
    private void checkForSwordCollisionSlime() { //sword collisions with slimes
        Rectangle swordSolid = player.getSwordHitBox();
        for (int i = 0; i < enemies.size(); i++){
            Rectangle slimeSolid = enemies.get(i).getHitBox();
            if(swordSolid.intersects(slimeSolid)){
                enemies.get(i).dropHealth();
            }
        }

        if (bossSpwaned) {
            Rectangle bossSolid = boss.getHitBox();
            if (bossSolid.intersects(swordSolid)) {
                boss.dropHealth();
            }
        }


        updateSlimes();
    }
    public void updateSlimes(){
        for (int i = 0; i < enemies.size(); i++){
            if (enemies.get(i).getHealth() == 0){
                enemies.remove(i);
                i--;
            }
        }
    }

    private void checkForCollisions() {
//        Rectangle enemySolid = enemy.getHitBox();
//        Rectangle playerSolid = player.getHitBox();
//
//        if (enemySolid.intersects(playerSolid)){
//            points++;
//        }


//        player.checkCollisionsWithEnemies(enemies);
        Rectangle playerSolid = player.getHitBox();

        for (int i = 0; i < enemies.size(); i++) {
            Rectangle enemySolid = enemies.get(i).getHitBox();
            if (enemySolid.intersects(playerSolid)) {
//                long currentTime = System.currentTimeMillis();
//                if (currentTime - lastColTime > shootInterval){
//                    player.subHealth();
//                    lastColTime = System.currentTimeMillis();
//                }

                    player.subHealth();
//                System.out.println(healthBar.getHealth());


            }

            if (enemies.get(i) instanceof Shooter) {
                ArrayList<Bullet> bullets = ((Shooter) enemies.get(i)).getBullets();
                for (Bullet bullet : bullets) {
                    if (bullet.getHitBox().intersects(playerSolid)) {
//                        System.out.println("Player hit by bullet");
                        player.subHealth();
                        bullets.remove(bullet);
                        break;
                    }
                }
            }
        }


        if (bossSpwaned){
            ArrayList<Shockwave> shockwaves = boss.getShockwaves();
            for (Shockwave shockwave : shockwaves) {
                if (shockwave.getHitBox().intersects(playerSolid)) {
                    player.subHealth();
//                    System.out.println("Player hit by shockwave");
                }
            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // You can add mouse click logic here
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                player.setVeloX(-1);
                player.setFacing(1);
                board.boardChange(player);
                break;
            case KeyEvent.VK_RIGHT:
                player.setVeloX(1);
                player.setFacing(2);
                break;
            case KeyEvent.VK_UP:
                player.setVeloY(-1);
                player.setFacing(3);
                break;
            case KeyEvent.VK_DOWN:
                player.setVeloY(1);
                player.setFacing(4);
                break;
            case KeyEvent.VK_SPACE:
                player.setSwing();
//                checkForSwordCollision();
                checkForSwordCollisionSlime();
                break;
        }
        repaint();
    }

    // Implement other overridden methods

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key presses to move the player
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                player.setVeloX(0);
                break;
            case KeyEvent.VK_RIGHT:
                player.setVeloX(0);
                break;
            case KeyEvent.VK_UP:
                player.setVeloY(0);
                break;
            case KeyEvent.VK_DOWN:
                player.setVeloY(0);
                break;
            case KeyEvent.VK_SPACE:
                player.setSwing();
                break;
        }
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
