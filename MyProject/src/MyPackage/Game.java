package MyPackage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame{
	
	private final int WIDTH = 250;
    private final int HEIGHT = 290;

    private JLabel statusbar;

	public Game() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Minesweeper");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Grid(5,5,2,2,2,2));

        setResizable(false);
        setVisible(true);
    }
}
