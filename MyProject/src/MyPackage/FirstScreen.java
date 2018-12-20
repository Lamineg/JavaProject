package MyPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class FirstScreen {

	
	private Object lock = new Object();
	private static String username;
	private static int difficulty;
	private static int input_skills;
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	
	//this method will launch the first GUI window that will ask some info to the player,
	// the username, the difficulty and what skills he favors
	public void run() throws InterruptedException {
		
		//creates a panel and sets his layout as a BoxLayout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//add the panel to the frame
		frame.add(panel);

		//creates a field for the player to write his username
		JTextField text = new JTextField(15);
		text.setSize(20, 10);
		
		//label 'username' for the text field
		JLabel labeluser = new JLabel("Username: ");
		
		//add both to the panel
		panel.add(labeluser);
		panel.add(text);

		//creates a slider for the difficulty
		JSlider diff = new JSlider(0, 10, 5);

		//creates a title for the difficulty slider
		JLabel diff_label = new JLabel("Difficulty: ");
		
		//attach the title to the difficulty slider
		diff_label.setLabelFor(diff);
		
		//add both to the panel
		panel.add(diff_label);
		panel.add(diff);
		
		//creates a slider for the skills
		JSlider skill = new JSlider(0, 10, 5);
		
		//create a title for the skill slider
		JLabel skill_label = new JLabel("Skills: ");
		
		//attach the title to the skill slider
		skill_label.setLabelFor(skill);
		
		//add both to the panel
		panel.add(skill_label);
		panel.add(skill);

		//true because we want to display a label for each value of the sliders as well
		diff.setPaintLabels(true);
		skill.setPaintLabels(true);
		
		//create a table to store the number that will be setup under each value
		//the difficulty slider
		Hashtable<Integer, JLabel> table1 = new Hashtable<Integer, JLabel>();

		for (int i = 0; i < 11; i++) {

			table1.put(i, new JLabel(String.valueOf(i)));

		}
		
		//attach the numbers to the slider
		diff.setLabelTable(table1);
		
		
		//creates a second table for the labels under each of the values of 
		//the skill slider. We don't put anything for the labels except from the extremities 
		Hashtable<Integer, JLabel> table2 = new Hashtable<Integer, JLabel>();
		for (int i = 0; i < 11; i++) {

			table2.put(i, new JLabel(""));

		}
		
		//extremities of the slider 
		table2.put(10, new JLabel("Dancing Skills"));
		table2.put(0, new JLabel("Looks"));

		//attach the labels to the slider
		skill.setLabelTable(table2);
		
		//new button
		JButton enter = new JButton("ENTER");
		
		//label for the enter button instructions for the information of the player 
		//to be validated
		JLabel instructions = new JLabel("Click ENTER and exit the window to validate");
		instructions.setLabelFor(enter);

		//attach the label to the enter button
		panel.add(instructions);
		
		//add the enter button to the panel
		panel.add(enter);

		//size of the frame
		frame.setSize(400, 220);
		frame.setVisible(true);
		
		
		//creates an actionlistener on the enter button so that we perform some actions when pressed
		enter.addActionListener((new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//saves the username written in the text field 
				setUsername(text.getText().toString());
				//displays an error message if certain conditions are not met
				if (validUserName(username) == true) {
					JOptionPane.showMessageDialog(frame, "Invalid username: at least 3 characters and no spaces!");
				}
				//saves the difficulty
				setDifficulty(diff.getValue());
				//saves the skills
				setInput_skills(skill.getValue());
			}

		}));
	}
	
	//method to make the program wait until the Firstscreen window is closed
	public void waitScreen() throws InterruptedException {
		
		//creates a new thread that waits as long as the window FirstScreen is open
		Thread t = new Thread() {
			public void run() {
				synchronized (getLock()) {
					while (frame.isVisible())
						try {
							getLock().wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		};
		//starts the thread
		t.start();
		
		//sets the frame as 
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent x) {
				synchronized (getLock()) {
					frame.setVisible(false);
					getLock().notify();
				}
			}

		});
		
		//waits for the thread to close
		t.join();

	}
	
	//determines if the username is valid, returns true if invalid, false if valid
	public boolean validUserName(String username) {
		if (username != null) {
			for (int i = 0; i < username.length(); i++) {
				if (Character.isWhitespace(username.charAt(i))) {
					return true;
				}
			}
			if (username.length() < 3) {
				return true;
			}

		}
		return false;
	}

	// getters and setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		FirstScreen.username = username;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		FirstScreen.difficulty = difficulty;
	}

	public int getInput_skills() {
		return input_skills;
	}

	public void setInput_skills(int input_skills) {
		FirstScreen.input_skills = input_skills;
	}

	public Object getLock() {
		return lock;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}
}