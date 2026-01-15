import java.awt.*;
import java.util.ArrayList;

public class Shooter extends Enemy{
    private ArrayList<Bullet> bullets = new ArrayList<>();
    public Shooter(int x, int y, int speed) {
        super(x, y, speed);
    }
    public void shoot(Player player) {
        int x1 = getX();
        int y1 = getY();
        int x2 = player.getX();
        int y2 = player.getY();
        int dirX = 0;
        int dirY = 0;

        int dx = x2 - x1;
        int dy = y2 - y1;

        long slope = gcd(dy, dx);

        if (dx >= 0){
            dirX = 1;
        } else {
            dirX = -1;
        }
        if (dy >= 0){
            dirY = 1;
        } else {
            dirY = -1;
        }


//        dirX /= length;
//        dirY /= length;

        bullets.add(new Bullet(x1, y1, dirX, dirY, dx, dy, 5)); // Adjust the speed as needed
    }
    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.move();
        }
    }

    public void drawBullets(Graphics g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void moveTo(Player player){
        int wallCollision = testForWallCollision();

        int x2 = player.getX();
        int y2 = player.getY();
        int x1 = getX();
        int y1 = getY();
        int dirX = 0;
        int dirY = 0;

        if (x2 - x1 >= 0){
            dirX = 1;
        }else{
            dirX = -1;
        }

        if (y2 - y1 >= 0){
            dirY = 1;
        }else{
            dirY = -1;
        }

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx >= dy && dx > 200){
            if (wallCollision == 1 && dirX < 0){
                addY(dirY);
            }else if (wallCollision == 2 && dirX > 0) {
                addY(dirY);
            }else{
                addX(dirX);
            }
        }else if (dy > 200){
            if (wallCollision == 3 && dirY < 0){
                addX(dirX);
            }else if (wallCollision == 4 && dirY > 0) {
                addX(dirX);
            }else{
                addY(dirY);
            }
        }
        setxLocation(getX());
        setyLocation(getY());
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawBullets(g);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
