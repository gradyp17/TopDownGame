import java.awt.*;

public class HealthBar {
    private int health;
    public HealthBar(){
        health = 480;
    }
    public void update(Player player){
        double playerHealth = (double) player.getHealth() /100;
        health = (int) (480 * playerHealth);
//        System.out.println(health);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(920, 20, 500, 60);
        g.setColor(Color.RED);
        g.fillRect(930, 30, 480, 40);
        g.setColor(Color.GREEN);
        g.fillRect(930, 30, health, 40);
    }
}
