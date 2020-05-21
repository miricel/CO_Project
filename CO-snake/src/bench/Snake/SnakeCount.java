package bench.Snake;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SnakeCount extends JLabel {
    private int cnt = 0;
    private String str;
    private Font font;

    public SnakeCount(String str){
        this.str = str;
        setForeground(Color.white);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, SnakeCount.class.getResourceAsStream("/DarkSeed.otf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFont(font.deriveFont(25f));
    }

    public void add() {
        this.cnt++;
        setText(str+cnt);
    }

    public int getCnt() {
        return cnt;
    }
}
