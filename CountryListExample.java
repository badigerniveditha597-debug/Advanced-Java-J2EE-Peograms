/*
5c. Develop a Swing program in Java to add the countries USA, India,
 Vietnam, Canada, Denmark, France, Great Britain, Japan, Africa, Greenland,
  Singapore into a JList and display the capital of the countries on console
 whenever the countries are selected on the list.
 * 
 */

package Swings;
import javax.swing.*;
	import java.awt.*;
	import javax.swing.event.*;
	import java.util.HashMap;

	public class CountryListExample {

	    JFrame frame;
	    JList<String> countryList;
	    HashMap<String, String> capitalMap;

	    CountryListExample() {

	        // Create Frame
	        frame = new JFrame("Country List");
	        frame.setSize(300, 300);
	        frame.setLayout(new FlowLayout());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Countries array
	        String countries[] = {
	            "USA", "India", "Vietnam", "Canada", "Denmark",
	            "France", "Great Britain", "Japan", "Africa",
	            "Greenland", "Singapore"
	        };

	        // Create JList
	        countryList = new JList<>(countries);
	        countryList.setVisibleRowCount(5);

	        // Create Scroll Pane
	        JScrollPane scrollPane = new JScrollPane(countryList);

	        // Create HashMap for Capitals
	        capitalMap = new HashMap<>();
	        capitalMap.put("USA", "Washington D.C.");
	        capitalMap.put("India", "New Delhi");
	        capitalMap.put("Vietnam", "Hanoi");
	        capitalMap.put("Canada", "Ottawa");
	        capitalMap.put("Denmark", "Copenhagen");
	        capitalMap.put("France", "Paris");
	        capitalMap.put("Great Britain", "London");
	        capitalMap.put("Japan", "Tokyo");
	        capitalMap.put("Africa", "No single capital"); // continent
	        capitalMap.put("Greenland", "Nuuk");
	        capitalMap.put("Singapore", "Singapore");

	        // Add List Selection Listener
	        countryList.addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	                String selectedCountry = countryList.getSelectedValue();

	                if (selectedCountry != null) {
	                    String capital = capitalMap.get(selectedCountry);
	                    System.out.println("Capital of " + selectedCountry + " is " + capital);
	                }
	            }
	        });

	        // Add to Frame
	        frame.add(scrollPane);

	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        new CountryListExample();
	    }
	}

