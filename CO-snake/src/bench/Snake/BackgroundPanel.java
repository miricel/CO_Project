package bench.Snake;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private final int width;
    private final int height;
    private BufferedImage background;

    public BackgroundPanel(String image, int width, int height) {

        this.width = width;
        this.height = height;
        try {
            background = ImageIO.read(new File(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public BackgroundPanel(BufferedImage backgroundimg, int width, int height) {
        this.width = width;
        this.height = height;
        this.background = backgroundimg;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = getWidth() - background.getWidth();
            int y = getHeight() - background.getHeight();
            g2d.drawImage(background, x, y, this);
            g2d.dispose();
        }
    }

}
