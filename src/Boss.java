import java.awt.*;
import java.util.ArrayList;

public class Boss extends Enemy {
    private final long attackInterval = 5000;
    private ArrayList<Shockwave> shockwaves;
    private long lastAttackTime;

    public Boss(int x, int y, int speed) {
        super(x, y, speed);
        shockwaves = new ArrayList<>();
        lastAttackTime = System.currentTimeMillis();
    }

    public ArrayList<Shockwave> getShockwaves() {
        return shockwaves;
    }

    public void setShockwaves(ArrayList<Shockwave> shockwaves) {
        this.shockwaves = shockwaves;
    }

    public void attack() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAttackTime > attackInterval) {
            shockwaves.add(new Shockwave(getX(), getY(), 50, 5));
            lastAttackTime = currentTime;
        }
    }

    public void updateShockwaves() {
        for (int i = 0; i < shockwaves.size(); i++) {
            shockwaves.get(i).update();
            if (!shockwaves.get(i).isActive()) {
                shockwaves.remove(i);
                i--;
            }
        }
    }

    public void drawShockwaves(Graphics g) {
        for (Shockwave shockwave : shockwaves) {
            shockwave.draw(g);
        }
    }
}
