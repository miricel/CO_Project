package testbench.gui;

import testbench.TestFractal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class finishFrame {
    static JFrame frame;
    long time;
    int score;

    public finishFrame(int score, long time) throws IOException {
        this.time = time;
        this.score = score;
        frame = new JFrame("The End");
        BImage bi = new BImage();

        JLabel s = new JLabel(Integer.toString(score));
        JLabel text1 = new JLabel("Score:");
        JLabel t = new JLabel(Long.toString(time/1000000) + " s");
        JLabel text2 = new JLabel("Runtime:");
        s.setFont(new Font("Jokerman", Font.PLAIN, 50));
        text1.setFont(new Font("Jokerman", Font.PLAIN, 50));
        t.setFont(new Font("Jokerman", Font.PLAIN, 50));
        text2.setFont(new Font("Jokerman", Font.PLAIN, 50));
        s.setForeground(Color.white);
        text1.setForeground(Color.white);
        t.setForeground(Color.white);
        text2.setForeground(Color.white);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Jokerman", Font.PLAIN, 25));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(new EmptyBorder(8, 20, 8, 20));

        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.setBackground(new Color(0,0,0,75));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(text1, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        newPanel.add(s, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(text2, constraints);
        constraints.gridx = 1;
        newPanel.add(t, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(backButton, constraints);

        backButton.addActionListener(new backToMain());

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

    static class backToMain implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            String[] args = {};

            try {
                Main.main(args);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //frame.setVisible(true);

        }
    }

}
