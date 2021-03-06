package testbench.gui;

import bench.Snake.SnakeCount;
import bench.database.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class finishFrame {
    static JFrame frame;
    long time;
    Data data;

    public finishFrame(Data score, long time) throws IOException {
        this.time = time;
        this.data = score;
        frame = new JFrame("The End");
        frame.setSize(1800, 1000);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(21, 127, 147));
        frame.getContentPane().setLayout(null);

        Font font = new Font("Jokerman", Font.PLAIN, 50);


        try {
            font= Font.createFont(Font.TRUETYPE_FONT, SnakeCount.class.getResourceAsStream("/Dea.ttf")).deriveFont(Font.BOLD,53f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        JLabel scorelbl = new JLabel(Integer.toString(data.score()),SwingConstants.RIGHT);
        JLabel text1 = new JLabel("Score:");
        JLabel timelbl = new JLabel(Long.toString(time/1000000) + "ms",SwingConstants.RIGHT);
        JLabel text2 = new JLabel("Runtime:");
        JLabel cpu = new JLabel(data.CPU(),SwingConstants.RIGHT);
        JLabel cores = new JLabel(data.cores()+"",SwingConstants.RIGHT);
        JLabel clock = new JLabel(data.clock()+"",SwingConstants.RIGHT);
        JLabel ram = new JLabel(data.ram()+"",SwingConstants.RIGHT);
        JLabel os = new JLabel(data.OS()+"",SwingConstants.RIGHT);

        JLabel cputxt = new JLabel("CPU:");
        JLabel corestxt = new JLabel("Nr of cores:");
        JLabel clocktxt = new JLabel("Time(ns): ");
        JLabel ramtxt = new JLabel("RAM(bytes): ");
        JLabel ostxt = new JLabel("OS: ");

        scorelbl.setFont(font);
        text1.setFont(font);
        timelbl.setFont(font);
        text2.setFont(font);
        cpu.setFont(font.deriveFont(Font.PLAIN,33f));
        cores.setFont(font.deriveFont(Font.PLAIN,33f));
        clock.setFont(font.deriveFont(Font.PLAIN,33f));
        ram.setFont(font.deriveFont(Font.PLAIN,33f));
        os.setFont(font.deriveFont(Font.PLAIN,33f));
        cputxt.setFont(font.deriveFont(Font.PLAIN,33f));
        corestxt.setFont(font.deriveFont(Font.PLAIN,33f));
        clocktxt.setFont(font.deriveFont(Font.PLAIN,33f));
        ramtxt.setFont(font.deriveFont(Font.PLAIN,33f));
        ostxt.setFont(font.deriveFont(Font.PLAIN,33f));

        scorelbl.setForeground(Color.white);
        text1.setForeground(Color.white);
        timelbl.setForeground(Color.white);
        text2.setForeground(Color.white);

        cpu.setForeground(Color.white);
        cores.setForeground(Color.white);
        clock.setForeground(Color.white);
        ram.setForeground(Color.white);
        os.setForeground(Color.white);

        cputxt.setForeground(Color.white);
        corestxt.setForeground(Color.white);
        clocktxt.setForeground(Color.white);
        ramtxt.setForeground(Color.white);
        ostxt.setForeground(Color.white);

        text1.setBounds(100,30,200,70);
        scorelbl.setBounds(490,30,240,70);
        text2.setBounds(100,130,200,70);
        timelbl.setBounds(490,130,240,70);

        cputxt.setBounds(40,280,200,70);
        corestxt.setBounds(40,330,200,70);
        clocktxt.setBounds(40,380,200,70);
        ramtxt.setBounds(40,430,200,70);
        ostxt.setBounds(40,480,200,70);

        cpu.setBounds(270,280,600,70);
        cores.setBounds(270,330,600,70);
        clock.setBounds(270,380,600,70);
        ram.setBounds(270,430,600,70);
        os.setBounds(270,480,600,70);


        JPanel result = new JPanel();
        result.setBackground(new Color(0,0,0,120));
        result.setLayout(null);
        result.setBounds(450,150,900,550);
        result.add(scorelbl);
        result.add(text1);
        result.add(timelbl);
        result.add(text2);

        result.add(cpu);
        result.add(cores);
        result.add(clock);
        result.add(ram);
        result.add(os);

        result.add(cputxt);
        result.add(corestxt);
        result.add(clocktxt);
        result.add(ramtxt);
        result.add(ostxt);

        JPanel email = new JPanel();
        email.setBounds(550, 750, 700, 100);
        email.setOpaque(false);
        email.setLayout(null);
        frame.getContentPane().add(email);
        // email.add(please);

        JLabel link = new JLabel("Please send us your results via email!",SwingConstants.CENTER);
        link.setFont(font.deriveFont(30f));
        link.setForeground(Color.white);
        link.setBounds(0,30,740,50);

        String text = data.CPU().replaceAll(" ","%20")+"%0A"+data.OS().replaceAll(" ","%20")+"%0A"+data.cores()+"%0A"+data.clock()+"%0A"+data.ram()+"%0A"+data.score();
        link.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("mailto:miriam.mironiuc@student.upt.ro?"+
                            "&subject=Fractal%20Score&body="+text));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
                link.setText("Thank you!");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                link.setForeground(Color.black);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                link.setForeground(Color.white);
            }

        });

        email.add(link);
        frame.getContentPane().add(result);

        JPanel tr = new JPanel();
        tr.setBounds(550, 750, 700, 100);
        tr.setBackground(new Color(0,0,0,120));
        frame.getContentPane().add(tr);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
