package MyPackage;

import java.awt.EventQueue;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.Component;

public class App_window {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_window window = new App_window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App_window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		frame.getContentPane().add(panel);
		
		
		
		JTextField text = new JTextField(15);
		text.setSize(20, 10);
		JLabel labeluser = new JLabel("Username: ");
		panel.add(labeluser);
		panel.add(text);

		JSlider diff = new JSlider(0, 10, 5);
		JSlider skill = new JSlider(0, 10, 5);
		
		JLabel diff_label = new JLabel("Difficulty: ");
		diff_label.setAlignmentX(Component.CENTER_ALIGNMENT);
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

	
	}

}
