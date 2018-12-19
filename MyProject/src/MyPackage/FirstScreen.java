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
	
	
	
	public void run() throws InterruptedException {
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		frame.add(panel);
		
		
		
		JTextField text = new JTextField(15);
		text.setSize(20, 10);
		JLabel labeluser = new JLabel("Username: ");
		panel.add(labeluser);
		panel.add(text);

		JSlider diff = new JSlider(0, 10, 5);
		JSlider skill = new JSlider(0, 10, 5);
		
		JLabel diff_label = new JLabel("Difficulty: ");
		diff_label.setLabelFor(diff);
		panel.add(diff_label);
		panel.add(diff);
		
		JLabel skill_label = new JLabel("Skills: ");
		skill_label.setLabelFor(skill);
		panel.add(skill_label);
		panel.add(skill);
		
		diff.setPaintLabels(true);
		skill.setPaintLabels(true);
		
		Hashtable<Integer, JLabel> table1 = new Hashtable<Integer, JLabel>();
	    
		for(int i = 0; i<11; i++) {
			
		table1.put (i, new JLabel(String.valueOf(i)));
		
		}
	    
		
		diff.setLabelTable (table1);	
		
		Hashtable<Integer, JLabel> table2 = new Hashtable<Integer, JLabel>();
		for(int i = 0; i<11; i++) {
			
			table2.put (i, new JLabel(String.valueOf(i)));
			
			}
		
		table2.put(10, new JLabel("Dancing Skills"));
		table2.put(0, new JLabel("Looks"));
				
		skill.setLabelTable(table2);

		JButton enter = new JButton("ENTER");
		JLabel instructions = new JLabel("Click ENTER and exit the window to validate");
		instructions.setLabelFor(enter);

		panel.add(instructions);
		panel.add(enter);
		
		frame.setSize(400, 220);
		frame.setVisible(true);

		enter.addActionListener((new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setUsername(text.getText().toString());
				setDifficulty(diff.getValue());
				setInput_skills(skill.getValue());
			}
			
			
	}));
	}
	
	public static void Wait(FirstScreen first) throws InterruptedException {
		
		Thread t = new Thread() {
	        public void run() {
	            synchronized(first.getLock()) {
	                while (first.frame.isVisible())
	                    try {
	                        first.getLock().wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	            }
	        }
	    };
	    t.start();

	    first.frame.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	            synchronized (first.getLock()) {
	                first.frame.setVisible(false);
	                first.getLock().notify();
	            }
	        }

	    });

	    t.join();
			
	}			
	
	//getters and setters
	
	public static String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		FirstScreen.username = username;
	}

	public static int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		FirstScreen.difficulty = difficulty;
	}

	public static int getInput_skills() {
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