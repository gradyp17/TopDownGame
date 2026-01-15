import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends GameObject {
    private Rectangle swordHitBox;
    private int x;
    private int y;
    private int speed;
    private int veloX;
    private int veloY;
    private int health;
    private int facing;
    private boolean swing;
    private BufferedImage imageLeft, imageRight, imageUp, imageDown;
    private boolean invincible = false;
    private Timer invincibilityTimer;
    public Player(int x, int y, int speed) {
        super(x, y, 32, 32);
        this.x = x;
        this.y = y;
        this.speed = speed;
        veloX = 0;
        veloY = 0;
        health = 100;
        facing = 1;
        swing = false;

        try {
            imageLeft = ImageIO.read(new File("left.png"));
            imageRight = ImageIO.read(new File("right.png"));//right
            imageUp = ImageIO.read(new File("up.png"));//up
            imageDown = ImageIO.read(new File("down.png"));//down
        } catch (IOException e) {
            e.printStackTrace();
        }
        invincibilityTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invincible = false;
                invincibilityTimer.stop();
            }
        });
    }

    public Rectangle getSwordHitBox() {
        if (facing == 1) { //left
            swordHitBox = new Rectangle(getxLocation() - 40, getyLocation(), 56, getHeight());
        }
        if (facing == 2) { //right
            swordHitBox = new Rectangle(getxLocation() + 16, getyLocation(), 56, getHeight());
        }
        if (facing == 3) { //up
            swordHitBox = new Rectangle(getxLocation(), getyLocation() - 16, getWidth(), 56);
        }
        if (facing == 4) { //down
            swordHitBox = new Rectangle(getxLocation(), getyLocation() + 40, getWidth(), 56);
        }

        swordHitBox = new Rectangle(getxLocation(), getyLocation(), getWidth(), getHeight());
        return swordHitBox;
    }

    public void setSwordHitBox(Rectangle swordHitBox) {
        this.swordHitBox = swordHitBox;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getVeloY() {
        return veloY;
    }

    public void setVeloY(int veloY) {
        this.veloY = veloY;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isSwing() {
        return swing;
    }

    public void setSwing() {
        swing = !swing;
    }

    public int getVeloX() {
        return veloX;
    }

    public void setVeloX(int veloX) {
        this.veloX = veloX;
    }

    public void checkCollisionsWithEnemies(ArrayList<Enemy> enemies) {
        if (!invincible) {
            for (Enemy enemy : enemies) {
                if (getHitBox().intersects(enemy.getHitBox())) {
                    subHealth();
                    invincible = true;
                    invincibilityTimer.start();
                    break;
                }
            }
        }
    }

    public void move(int dx, int dy, ArrayList<Enemy> enemies) {
        if (enemies.size() == 0) {
            x += dx * speed;
            setxLocation(x);
        } else {
            if (checkForWallCollisions(dx, dy, true) == 1) { //x axis
                if (dx > 0) {
                    x += dx * speed;
                    setxLocation(x);
                }
            } else if (checkForWallCollisions(dx, dy, true) == 2) {
                if (dx < 0) {
                    x += dx * speed;
                    setxLocation(x);
                }
            } else {
                x += dx * speed;
                setxLocation(x);
            }
        }


        if (checkForWallCollisions(dx, dy, false) == 1) { //y axis
            if (dy > 0) {
                y += dy * speed;
                setyLocation(y);
            }
        } else if (checkForWallCollisions(dx, dy, false) == 2) {
            if (dy < 0) {
                y += dy * speed;
                setyLocation(y);
            }
        } else {
            y += dy * speed;
            setyLocation(y);
        }
//        x += dx * speed;
//        y += dy * speed;
//        setxLocation(x);
//        setyLocation(y);
    }

    public int checkForWallCollisions(int dx, int dy, boolean val) {
        if (val) {
            if (x < 100) { //left wall
                return 1;
            } else if (x > 1340 - 33) { //right wall
                return 2;
            } else {
                return 0;
            }
        } else {
            if (y < 100) { //top wall
                return 1;
            } else if (y > 800 - 33) { //bottom wall
                return 2;
            } else {
                return 0;
            }
        }
    }

    public void swingSword(Graphics g) {
        g.setColor(Color.BLACK);
        if (swing) {
            if (facing == 1) { //left
                g.fillRect(x - 35, y + 12, 40, 10);
            }
            if (facing == 2) { //right
                g.fillRect(x + 30, y + 12, 40, 10);
            }
            if (facing == 3) { //up
                g.fillRect(x + 12, y - 32, 10, 40);
            }
            if (facing == 4) { //down
                g.fillRect(x + 12, y + 30, 10, 40);
            }
        }
    }

    public void draw(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillOval(x, y, 32, 32);
        switch (facing) {
            case 1: // left
                g.drawImage(imageLeft, x, y, 32, 32, null);
                break;
            case 2: // right
                g.drawImage(imageRight, x, y, 32, 32, null);
                break;
            case 3: // up
                g.drawImage(imageUp, x, y, 32, 32, null);
                break;
            case 4: // down
                g.drawImage(imageDown, x, y, 32, 32, null);
                break;
        }
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void subHealth() {
        if (invincible == false) {
            invincible = true;
            health = health - 5;

            invincibilityTimer.start();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addX() {
        x = x + 5;
    }
}

