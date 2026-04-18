/*5d. Develop a Swing program in Java to create a Tabbed Pan of Cyan,
 *  Magenta and Yellow and display the concerned color whenever the specific 
 *  tab is selected in the Pan
 * 
 */
package Swings;
	

	import javax.swing.*;
	import javax.swing.event.*;
	import java.awt.*;

	public class TabbedPaneExample1 {

	    JFrame frame;
	    JTabbedPane tabbedPane;

	    TabbedPaneExample1() {

	        // Create Frame
	        frame = new JFrame("Tabbed Pane Example");
	        frame.setSize(400, 300);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Create TabbedPane
	        tabbedPane = new JTabbedPane();

	        // Create Panels with Colors
	        JPanel cyanPanel = new JPanel();
	        cyanPanel.setBackground(Color.CYAN);

	        JPanel magentaPanel = new JPanel();
	        magentaPanel.setBackground(Color.MAGENTA);

	        JPanel yellowPanel = new JPanel();
	        yellowPanel.setBackground(Color.YELLOW);

	        // Add Tabs
	        tabbedPane.addTab("Cyan", cyanPanel);
	        tabbedPane.addTab("Magenta", magentaPanel);
	        tabbedPane.addTab("Yellow", yellowPanel);

	        // Add Change Listener
	        tabbedPane.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                int index = tabbedPane.getSelectedIndex();
	                String tabName = tabbedPane.getTitleAt(index);
	                System.out.println(tabName + " tab is selected");
	            }
	        });

	        // Add to Frame
	        frame.add(tabbedPane);

	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        new TabbedPaneExample();
	    }
	}

