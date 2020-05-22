package bench.database;

import testbench.gui.finishFrame;
import testbench.gui.myPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SnakeDB {
	public void doAll(long time,JFrame frame)
	{
		DatabaseSnake database = DatabaseSnake.getInstance();
		database.readFromFile();
		
		Data newData = new Data();
		long bestTime=37000000000L;
		newData.setTime(time);
		newData.setScore((int)(100*newData.clock()/bestTime));
		
		database.getData().add(newData);	
		database.writeToFile();

		JPanel contentPane = (JPanel) frame.getContentPane();
		JPanel panel = null;
		try {
			panel = new myPanel(time, newData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
		frame.repaint();

		frame.setVisible(true);

	}
}
