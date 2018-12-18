package MyPackage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame{
	
	private final int WIDTH = 834;
    private final int HEIGHT = 967;

    private JLabel statusbar;

	public Game() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Become a Star");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Grid(16,16,5,5,5,5,statusbar));

        setResizable(false);
        setVisible(true);
    }
}
