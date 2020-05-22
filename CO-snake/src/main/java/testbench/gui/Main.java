package testbench.gui;

import bench.Snake.Screen;
import bench.Snake.SnakeCount;
import testbench.SnakeWithFixedPoint;
import testbench.TestFractal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static JFrame frame;

    public static BufferedImage resise(BufferedImage img, int neww, int newh){
        Image temp = img.getScaledInstance(neww, newh, Image.SCALE_SMOOTH);
        BufferedImage i = new BufferedImage(neww, newh, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = i.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();
        return i;
    }

    public static void main(String[] args) throws IOException, NullPointerException{
        frame = new JFrame("Benchmarking Application");
        BImage bi = new BImage();

        JButton snakeButton = new JButton("Snake");
        JButton fractalButton = new JButton("Fractal");
        snakeButton.setFont(new Font("Jokerman", Font.PLAIN, 25));
        fractalButton.setFont(new Font("Jokerman", Font.PLAIN, 25));
        snakeButton.setBackground(Color.BLACK);
        snakeButton.setForeground(Color.WHITE);
        snakeButton.setBorder(new EmptyBorder(8, 20, 8, 20));
        fractalButton.setBackground(Color.BLACK);
        fractalButton.setForeground(Color.WHITE);
        fractalButton.setBorder(new EmptyBorder(8, 20, 8, 20));

        snakeButton.addActionListener(new startSnakes());
        fractalButton.addActionListener(new startFractal());



        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.setBackground(new Color(0,0,0,75));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
       // newPanel.add(lbls, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
       // newPanel.add(lblf, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(snakeButton, constraints);
        constraints.gridx = 1;
        newPanel.add(fractalButton, constraints);

        newPanel.setBounds(300, 100, 800, 500);
        bi.setLayout(null);
        bi.add(newPanel);
        frame.add(bi);
        frame.setSize(365, 395);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    static class startSnakes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            String[] args = {};

            try {
                SnakeWithFixedPoint.main(args);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            frame.setVisible(true);

        }
    }

    static class startFractal implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            String[] args = {};

            try {
                TestFractal.main(args);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //frame.setVisible(true);

        }
    }
}
