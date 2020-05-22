package testbench.gui;

import bench.Snake.SnakeCount;
import bench.database.Data;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class myPanel extends JPanel {
    long time;
    Data data;


    public myPanel(long time, Data score) throws IOException {
        setBackground(Color.pink);
        setBounds(0, 0, 1000, 700);
        setLayout(null);

        this.time = time;
        this.data = score;

        Font font = new Font("Jokerman", Font.PLAIN, 50);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, SnakeCount.class.getResourceAsStream("/DarkSeed.otf")).deriveFont(53f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        JLabel scorelbl = new JLabel(Integer.toString(data.score()), SwingConstants.RIGHT);
        JLabel text1 = new JLabel("Score:");
        JLabel timelbl = new JLabel(Long.toString(time / 1000000) + "  ms", SwingConstants.RIGHT);
        JLabel text2 = new JLabel("Runtime:");
        JLabel cpu = new JLabel(data.CPU(), SwingConstants.RIGHT);
        JLabel cores = new JLabel(data.cores() + "", SwingConstants.RIGHT);
        JLabel clock = new JLabel(data.clock() + "", SwingConstants.RIGHT);
        JLabel ram = new JLabel(data.ram() + "", SwingConstants.RIGHT);
        JLabel os = new JLabel(data.OS() + "", SwingConstants.RIGHT);

        JLabel cputxt = new JLabel("CPU:");
        JLabel corestxt = new JLabel("Nr of cores:");
        JLabel clocktxt = new JLabel("Time(ns): ");
        JLabel ramtxt = new JLabel("RAM(bytes): ");
        JLabel ostxt = new JLabel("OS: ");

        scorelbl.setFont(font);
        text1.setFont(font);
        timelbl.setFont(font);
        text2.setFont(font);
        cpu.setFont(font.deriveFont(Font.PLAIN, 30f));
        cores.setFont(font.deriveFont(Font.PLAIN, 30f));
        clock.setFont(font.deriveFont(Font.PLAIN, 30f));
        ram.setFont(font.deriveFont(Font.PLAIN, 30f));
        os.setFont(font.deriveFont(Font.PLAIN, 30f));
        cputxt.setFont(font.deriveFont(Font.PLAIN, 33f));
        corestxt.setFont(font.deriveFont(Font.PLAIN, 33f));
        clocktxt.setFont(font.deriveFont(Font.PLAIN, 33f));
        ramtxt.setFont(font.deriveFont(Font.PLAIN, 33f));
        ostxt.setFont(font.deriveFont(Font.PLAIN, 33f));

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

        text1.setBounds(100, 30, 200, 50);
        scorelbl.setBounds(390, 30, 240, 50);
        text2.setBounds(100, 130, 200, 50);
        timelbl.setBounds(390, 130, 240, 50);

        cputxt.setBounds(40, 280, 150, 50);
        corestxt.setBounds(40, 330, 150, 50);
        clocktxt.setBounds(40, 380, 150, 50);
        ramtxt.setBounds(40, 430, 150, 50);
        ostxt.setBounds(40, 480, 150, 50);

        cpu.setBounds(270, 280, 440, 50);
        cores.setBounds(270, 330, 440, 50);
        clock.setBounds(270, 380, 440, 50);
        ram.setBounds(270, 430, 440, 50);
        os.setBounds(270, 480, 440, 50);


        JPanel result = new JPanel();
        result.setBackground(new Color(0, 0, 0, 75));
        result.setLayout(null);
        result.setBounds(100, 35, 740, 550);
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
        email.setBounds(100, 595, 740, 50);
        email.setOpaque(false);
        email.setLayout(null);
        add(email);
        // email.add(please);

        JLabel link = new JLabel("Please send us your results via email!",SwingConstants.CENTER);
        link.setFont(font.deriveFont(17f));
        link.setForeground(Color.white);
        link.setBounds(0,10,740,20);

        String text = data.CPU().replaceAll(" ","%20")+"%0A"+data.OS().replaceAll(" ","%20")+"%0A"+data.cores()+"%0A"+data.clock()+"%0A"+data.ram()+"%0A"+data.score();
        link.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("mailto:miriam.mironiuc@student.upt.ro?"+
                            "&subject=Snake%20Score&body="+text));
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
        add(result);

        JPanel tr = new JPanel();
        tr.setBounds(100, 595, 740, 50);
        tr.setBackground(new Color(0,0,0,75));
        add(tr);

    }

}
