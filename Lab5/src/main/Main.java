package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		
	//Declare and Initialize GUI Components	
		JFrame myFrame = new JFrame("Color Grid");
		JButton restartButton = new JButton("Restart");
		JButton closeButton = new JButton("Close");
		MyPanel myPanel = new MyPanel();
		JPanel buttonPanel = new JPanel();
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		
	//Set Frame Configuration Settings
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 700);
	
	//Set Layout Manager for Frame
		myFrame.setLayout( new BoxLayout(myFrame.getContentPane(), BoxLayout.Y_AXIS) );
		
	//Set Layout Manager for Button Panel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
	//Add Preferred Sizes to Buttons 
		restartButton.setPreferredSize(new Dimension(100, 70));
		closeButton.setPreferredSize(new Dimension(70, 70));
		
	//Add Event Listeners to Buttons with their Actions
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				myPanel.restartGame();
			}
		});
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				myFrame.dispose();
			}
		});
		
	//Add Buttons to Button Panel	
		buttonPanel.add(restartButton);
		buttonPanel.add(closeButton);
		
	//Add Panel and Button Panel to Frame	
		myFrame.add(myPanel);
		myFrame.add(buttonPanel);
		
	//Add Mouse Listener To Frame
		myFrame.addMouseListener(myMouseAdapter);
		
	//Make the GUI Visible	
		myFrame.setVisible(true);
	}
}