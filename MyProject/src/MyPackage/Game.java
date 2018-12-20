package MyPackage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {

	private final int WIDTH = 510;
	private final int HEIGHT = 560;

	private JLabel statusbar;

	public Game(int difficulty, String username, int input_skills) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Dance Battle 2D");

		statusbar = new JLabel("");
		add(statusbar, BorderLayout.SOUTH);

		add(new Grid(difficulty, username, input_skills, statusbar));

		setResizable(false);
		setVisible(true);
	}
}
