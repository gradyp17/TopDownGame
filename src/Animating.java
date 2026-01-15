/**
 * @author  Grady Pattison
 * @version 1.60, 04/05/2024
 */
import javax.swing.*;
import java.awt.*;

public class Animating extends JFrame {
    int numRows = 136; // Change the number of rows as needed
    int numCols = 140; // Change the number of columns as needed
    int[][] map = new int[numRows][numCols];

    public Animating(){
        map[2][2] = 3;
        this.setTitle("animation");
        setSize(1440,900);
        JPanel myPanel = new DrawingPanel(Color.RED, map, numRows, numCols); // Pass numRows and numCols
        add(new DrawingPanel(Color.RED, map, numRows, numCols)); // Pass numRows and numCols
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Animating();
            }
        });
    }
}
