import java.awt.*;

public class Shockwave extends GameObject {
    private int expansionRate;
    private boolean active;

    public Shockwave(int x, int y, int initialSize, int expansionRate) {
        super(x, y, initialSize, initialSize);
        this.expansionRate = expansionRate;
        this.active = true;
    }

    public void update() {
        if (active) {
            setWidth(getWidth() + expansionRate);
            setHeight(getHeight() + expansionRate);
            setxLocation(getxLocation() - expansionRate / 2);
            setyLocation(getyLocation() - expansionRate / 2);
            if (getWidth() > 800) {
                active = false;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void draw(Graphics g) {
        if (active) {
            g.setColor(Color.YELLOW);
            g.drawOval(getxLocation(), getyLocation(), getWidth(), getHeight());
        }
    }
}
