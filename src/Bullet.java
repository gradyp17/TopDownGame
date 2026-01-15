import java.awt.*;

public class Bullet extends GameObject {
    private int speed;
    private int directionX;
    private int directionY;
    private int dx;
    private int dy;
    private long slope;

    public Bullet(int x, int y, int directionX, int directionY, int dx, int dy, int speed) {
        super(x, y, 10, 10);
        this.speed = speed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.dx = dx;
        this.dy = dy;
        slope = gcd(dy, dx);
    }

    public void move() {
        slope = gcd(dy, dx);
        long slopeX = 0;
        long slopeY = 0;


        if (slope == 0){
            slopeX = Math.abs(dx);
            slopeY = Math.abs(dy);
        }else{
            slopeX = Math.abs(dx)/slope;
            slopeY = Math.abs(dy)/slope;
        }

        if (slopeX >= slopeY){
            setxLocation(getxLocation() + directionX);
            if (dx > 0){
                dx--;
            }else{
                dx++;
            }
        }else{
            setyLocation(getyLocation() + directionY);
            if (dy > 0){
                dy--;
            }else{
                dy++;
            }
        }
    }
    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(getxLocation(), getyLocation(), getWidth(), getHeight());
    }
}
