package src.main.jp.ac.ksu.mori;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    private int rectX = 50;
    private int rectY = 100;
    private final int rectWidth = 150;
    private final int rectHeight = 80;

    private int dx = 2;

    public Main() {
        // for文の代わりにタイマーを使う
        Timer timer = new Timer(16, e -> {
            updatePosition();
            repaint();
        });
        timer.start();
    }

    private void updatePosition() {
        rectX += dx;

        if (rectX + rectWidth > getWidth() || rectX < 0) {
            dx = -dx;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLUE);
        g2.fillRect(rectX, rectY, rectWidth, rectHeight);

        g2.setColor(Color.WHITE);
        String text = "動いてる！";
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int textX = rectX + (rectWidth - textWidth) / 2;
        int textY = rectY + (rectHeight + textHeight) / 2;
        g2.drawString(text, textX, textY);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("手作りアニメーション");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            Main panel = new Main();
            frame.add(panel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
