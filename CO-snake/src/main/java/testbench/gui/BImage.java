package testbench.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BImage extends JPanel {
    Image pic;

    public BImage() throws IOException {
        BufferedImage img = ImageIO.read(new File("Resources/wall.jpg"));
        ImageIcon icon = new ImageIcon(img);

        //ImageIcon i = new ImageIcon("C:\\Users\\Renata\\Documents\\Year2\\2-CO\\CO-project\\CO_Project\\CO-snake\\resources/wallpaper.jpg");
        pic = icon.getImage();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(pic, 0, 0, null);
    }
}
