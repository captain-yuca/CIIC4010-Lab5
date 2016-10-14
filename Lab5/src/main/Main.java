package main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import mineSweeperObjects.MineSweeperObject;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		JButton restartButton = new JButton("Restart");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 700);

		MyPanel myPanel = new MyPanel();		
		myFrame.setLayout( new BoxLayout(myFrame.getContentPane(), BoxLayout.Y_AXIS) );      // set the layout manager

		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				myPanel.restartGame();
			}
		});

		restartButton.setPreferredSize(new Dimension(70, 70));

		myFrame.add(myPanel);
		myFrame.add(restartButton);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);

		//Experimento para imprimir cosas en el Board
		JPanel panel = new JPanel();
		JLabel jlabel = new JLabel("");
		//antes de esto tiene q ir el loop q recorre por las posiciones
		
		//panel.setLocation(this.xPosition, this.yPosition);
		panel.add(jlabel);
		myFrame.add(panel);
		panel.setVisible(true);
	}
}