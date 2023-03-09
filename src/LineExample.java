import javax.swing.*;
import java.awt.*;

public class LineExample extends JFrame {

    public LineExample() {
        setTitle("Düz Çizgi Örneği");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(50, 100, 250, 100);
    }

    public static void main(String[] args) {
        new LineExample();
    }
}