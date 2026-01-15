import java.awt.*;

public class GameObject {
    private int xLocation;
    private int yLocation;
    private int height;
    private int width;

    public GameObject(int x, int y, int w, int h){
        setxLocation(x);
        setyLocation(y);
        setWidth(w);
        setHeight(h);
    }
    public Rectangle getHitBox(){
        return new Rectangle(getxLocation(), getyLocation(), getWidth(), getHeight());
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
