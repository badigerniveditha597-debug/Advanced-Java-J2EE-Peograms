/*
 * 4c. Develop a Swing program in Java to display a message “Digital Clock
 *  is pressed” or “Hour Glass is pressed” depending upon the Jbutton with 
 *  image either Digital Clock or Hour Glass is pressed by implementing the
 *   event handling mechanism with addActionListener( ).
 */
package Swings;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;

	public class ClockButtonExample implements ActionListener {

	    JFrame frame;
	    JLabel label;
	    JButton btnClock, btnHourGlass;

	    ClockButtonExample() {
	        // Create Frame
	        frame = new JFrame("Button Image Example");
	        frame.setSize(500, 300);
	        frame.setLayout(new FlowLayout());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Load Images (Make sure images are in same folder or give full path)
	        ImageIcon clockIcon = new ImageIcon("clock.png");
	        ImageIcon hourglassIcon = new ImageIcon("hourglass.png");

	        // Create Buttons with Images
	        btnClock = new JButton(clockIcon);
	        btnHourGlass = new JButton(hourglassIcon);

	        // Create Label
	        label = new JLabel("Press a button");

	        // Add Action Listener
	        btnClock.addActionListener(this);
	        btnHourGlass.addActionListener(this);

	        // Add components to frame
	        frame.add(btnClock);
	        frame.add(btnHourGlass);
	        frame.add(label);

	        frame.setVisible(true);
	    }

	    // Event Handling
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == btnClock) {
	            label.setText("Digital Clock is pressed");
	        } else if (e.getSource() == btnHourGlass) {
	            label.setText("Hour Glass is pressed");
	        }
	    }

	    public static void main(String[] args) {
	        new ClockButtonExample();
	    }
	}


