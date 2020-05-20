package bench.Snake;

import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;

public class Snake {

    Screen  screen;

    public Snake(Screen screen) {

        this.screen = screen;

    }
    public static void main(String[] args) {
        new Snake(new Screen());
    }
}