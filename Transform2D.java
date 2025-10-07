
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class Transform2D extends JPanel {

    private int choice;

    public Transform2D(int choice) {
        this.choice = choice;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw original shape (a rectangle)
        int[] x = {50, 150, 150, 50};
        int[] y = {50, 50, 100, 100};
        g2.setColor(Color.BLUE);
        g2.fillPolygon(x, y, 4);

        // Apply transformation
        AffineTransform transform = new AffineTransform();

        switch (choice) {
            case 1: // Translation
                transform.translate(100, 50);
                break;
            case 2: // Scaling about X and Y
                transform.scale(1.5, 0.5);
                break;
            case 3: // Rotation about origin (X,Y axis concept)
                transform.rotate(Math.toRadians(45), 100, 75);
                break;
            case 4: // Shearing
                transform.shear(0.5, 0.0);
                break;
        }

        Shape rect = new Polygon(x, y, 4);
        Shape newShape = transform.createTransformedShape(rect);

        g2.setColor(Color.RED);
        g2.fill(newShape);
    }

    public static void main(String[] args) {
        String[] options = {"Translation", "Scaling", "Rotation", "Shearing"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose a transformation", "2D Transformations",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        JFrame frame = new JFrame("2D Transformations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Transform2D(choice + 1));
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
