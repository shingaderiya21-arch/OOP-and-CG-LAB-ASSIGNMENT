
import java.awt.*;
import javax.swing.*;

public class CohenSutherlandShort extends JPanel {

    final int x_min = 100, y_min = 100, x_max = 300, y_max = 300;
    final int INSIDE = 0, LEFT = 1, RIGHT = 2, BOTTOM = 4, TOP = 8;

    int code(int x, int y) {
        int c = INSIDE;
        if (x < x_min) {
            c |= LEFT;
        } else if (x > x_max) {
            c |= RIGHT;
        }
        if (y < y_min) {
            c |= BOTTOM;
        } else if (y > y_max) {
            c |= TOP;
        }
        return c;
    }

    void clip(Graphics g, int x1, int y1, int x2, int y2) {
        int c1 = code(x1, y1), c2 = code(x2, y2);
        while (true) {
            if ((c1 | c2) == 0) {
                g.setColor(Color.RED);
                g.drawLine(x1, y1, x2, y2);
                break;
            } else if ((c1 & c2) != 0) {
                break; 
            }else {
                int c = (c1 != 0) ? c1 : c2, x = 0, y = 0;
                if ((c & TOP) != 0) {
                    x = x1 + (x2 - x1) * (y_max - y1) / (y2 - y1);
                    y = y_max;
                } else if ((c & BOTTOM) != 0) {
                    x = x1 + (x2 - x1) * (y_min - y1) / (y2 - y1);
                    y = y_min;
                } else if ((c & RIGHT) != 0) {
                    y = y1 + (y2 - y1) * (x_max - x1) / (x2 - x1);
                    x = x_max;
                } else if ((c & LEFT) != 0) {
                    y = y1 + (y2 - y1) * (x_min - x1) / (x2 - x1);
                    x = x_min;
                }
                if (c == c1) {
                    x1 = x;
                    y1 = y;
                    c1 = code(x1, y1);
                } else {
                    x2 = x;
                    y2 = y;
                    c2 = code(x2, y2);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(x_min, y_min, x_max - x_min, y_max - y_min);

        g.setColor(Color.BLUE);
        g.drawLine(50, 50, 250, 250);
        g.drawLine(150, 50, 350, 350);
        g.drawLine(120, 120, 180, 180);
        g.drawLine(350, 50, 400, 80);

        clip(g, 50, 50, 250, 250);
        clip(g, 150, 50, 350, 350);
        clip(g, 120, 120, 180, 180);
        clip(g, 350, 50, 400, 80);
    }

    public static void main(String[] a) {
        JFrame f = new JFrame("Cohen-Sutherland");
        f.add(new CohenSutherlandShort());
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
