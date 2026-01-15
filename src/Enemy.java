import java.awt.*;

public class Enemy extends GameObject {
    private int x;
    private int y;
    private int speed;
    private int veloX;
    private int veloY;
    private int health;

    public Enemy(int x, int y, int speed) {
        super(x, y, 32, 32);
        this.x = x;
        this.y = y;
        this.speed = speed;
        veloX = 0;
        veloY = 0;
        health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void dropHealth() {
        health = health - 10;
    }

    public void updateBullets() {
    }

    public void addY(int add) {
        y += add;
    }

    public void addX(int add) {
        x += add;
    }

    public int getVeloY() {
        return veloY;
    }

    public void setVeloY(int veloY) {
        this.veloY = veloY;
    }

    public int getVeloX() {
        return veloX;
    }

    public void setVeloX(int veloX) {
        this.veloX = veloX;
    }

    //    public void move(int dx, int dy) {
//        x += dx * speed;
//        y += dy * speed;
//        setxLocation(x);
//        setyLocation(y);
//    }
    public void moveTo(Player player) {
        //lightly based off of wikis line algorithms
        //https://en.wikipedia.org/wiki/Line_drawing_algorithm

        //when wall collision happens default to second axis of movement.
        int wallCollision = testForWallCollision();

        int x2 = player.getX();
        int y2 = player.getY();
        int x1 = x;
        int y1 = y;
        int dirX = 0;
        int dirY = 0;

        if (x2 - x1 >= 0) {
            dirX = 1;
        } else {
            dirX = -1;
        }

        if (y2 - y1 >= 0) {
            dirY = 1;
        } else {
            dirY = -1;
        }

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx >= dy) {
            if (wallCollision == 1 && dirX < 0) {
                y += dirY;
            } else if (wallCollision == 2 && dirX > 0) {
                y += dirY;
            } else {
                x += dirX;
            }
        } else {
            if (wallCollision == 3 && dirY < 0) {
                x += dirX;
            } else if (wallCollision == 4 && dirY > 0) {
                x += dirX;
            } else {
                y += dirY;
            }
        }
        setxLocation(x);
        setyLocation(y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 32, 32);
    }

    public int testForWallCollision() {
        if (x < 100) { //left wall
            return 1;
        } else if (x > 1340 - 33) { //right wall
            return 2;
        } else if (y < 100) { //top wall
            return 3;
        } else if (y > 800 - 33) { //bottom wall
            return 4;
        }
        return 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        setxLocation(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        setyLocation(y);
    }
}
