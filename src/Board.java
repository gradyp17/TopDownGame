import java.awt.*;

public class Board {
    private int x;
    private int y;
    private boolean move;
    private int moveNums;

    private int level;
    private int currentLevel;
    private boolean gameOver = false;
    private boolean gameComplete = false;

    public Board() {
        level = 1;
        currentLevel = 1;

    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        move = false;
        moveNums = 0;
        x = 0;
        y = 0;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public int getMoveNums() {
        return moveNums;
    }

    public void setMoveNums(int moveNums) {
        this.moveNums = moveNums;
    }

    public void addLevel() {
        level++;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void boardChange(Player player) {
        if (player.getX() < -32) {
            move = true;
            if (moveNums == 0) {
                moveNums = 1440;
                currentLevel++;
            }
        }
        move = false;
    }

    public void subtractNum() {
        moveNums = moveNums - 5;
    }

    public void addX() {
        x = x + 5;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void gameOver(Graphics g) {
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1440, 900);
            g.setColor(Color.black);
            Font myFont = new Font("TimesRoman", Font.BOLD, 30);
            g.setFont(myFont);
            g.drawString("Game Over", 650, 430);
        }
    }

    public void gameComplete(Graphics g) {
        if (gameComplete) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1440, 900);
            g.setColor(Color.black);
            Font myFont = new Font("TimesRoman", Font.BOLD, 30);
            g.setFont(myFont);
            g.drawString("You Win!", 650, 430);
        }
    }

    public void draw(Graphics g) {
//        if (level == 1){
//            g.setColor(Color.GRAY);
//            g.fillRect(0,0,1440,900);
//            g.setColor(Color.lightGray);
//            g.fillRect(100,100,1240,700);
//            g.fillRect(0,400,1440,100);
//        }

        //level 1
        g.setColor(Color.GRAY);
        g.fillRect(x, 0, 1440, 900);
        g.setColor(Color.lightGray);
        g.fillRect(100 + x, 100 + y, 1240, 700);
        g.fillRect(x, 400, 1440, 100);
        g.setColor(Color.GRAY);
        g.fillRect(x+1340, 400, 100, 100);

        //level 2
        g.setColor(Color.BLUE);
        g.fillRect(-1440 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-1330 + x, 100, 1240, 700);
        g.fillRect(-1440 + x, 400, 1440, 100);

        // level 3
        g.setColor(Color.GREEN);
        g.fillRect(-2880 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-2770 + x, 100, 1240, 700);
        g.fillRect(-2880 + x, 400, 1440, 100);

        // level 4
        g.setColor(Color.RED);
        g.fillRect(-4320 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-4210 + x, 100, 1240, 700);
        g.fillRect(-4320 + x, 400, 1440, 100);

        // level 5
        g.setColor(Color.YELLOW);
        g.fillRect(-5760 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-5650 + x, 100, 1240, 700);
        g.fillRect(-5760 + x, 400, 1440, 100);

        // level 6
        g.setColor(Color.ORANGE);
        g.fillRect(-7200 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-7090 + x, 100, 1240, 700);
        g.fillRect(-7200 + x, 400, 1440, 100);

        // level 7
        g.setColor(Color.PINK);
        g.fillRect(-8640 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-8530 + x, 100, 1240, 700);
        g.fillRect(-8640 + x, 400, 1440, 100);

        // level 8
        g.setColor(Color.MAGENTA);
        g.fillRect(-10080 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-9980 + x, 100, 1240, 700);
        g.fillRect(-10080 + x, 400, 1440, 100);

        // level 9
        g.setColor(Color.CYAN);
        g.fillRect(-11520 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-11410 + x, 100, 1240, 700);
        g.fillRect(-11520 + x, 400, 1440, 100);

        // level 10
        g.setColor(Color.DARK_GRAY);
        g.fillRect(-12960 + x, 0, 1440, 900);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-12850 + x, 100, 1240, 700);
        g.fillRect(-12960 + x, 400, 1440, 100);
    }
}
