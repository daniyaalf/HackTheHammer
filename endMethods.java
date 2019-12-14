package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class endMethods extends JPanel implements ActionListener{

	public static JFrame codeFrame;
	public static JPanel codePanel;
	public static JLabel instructLabel;
	public static JTextArea codeField;
	public static JButton submitButton;
	
	Image codeBg;
	
	static int levelCode = 1;

	public endMethods () {
		codeBg = Toolkit.getDefaultToolkit().createImage("codeBg2.jpg");
		
		codeFrame = new JFrame ("CODE CHALLENGE: LEVEL " + levelCode);
		codeFrame.setPreferredSize(new Dimension(600, 400));
		codeFrame.setLocation(200, 200);
		
		
		codePanel = new JPanel();
		codePanel.setLayout(new GridLayout(0, 1, 10, 5));
		codePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		codePanel.setBackground(Color.BLACK);
		
		instructLabel = new JLabel ("Enter the code: ", JLabel.CENTER);
		instructLabel.setFont(new Font("Arial", Font.BOLD, 48));
		instructLabel.setForeground(Color.WHITE);
				
		codeField = new JTextArea();
		codeField.setBackground(Color.gray);
		
		submitButton = new JButton ("");
		submitButton.setActionCommand ("Submit");
		submitButton.setIcon(new ImageIcon(codeBg));
		
	
		codePanel.add(instructLabel);
		codePanel.add(codeField);
		codePanel.add(submitButton);
	
		
		codeFrame.add(codePanel);
		codeFrame.pack();
		codeFrame.setVisible(true);
		
		final int scoreOneTotal = 5;
		submitButton.addActionListener(new ActionListener() {
			String userInputOne = "";
			public void actionPerformed(ActionEvent e) {
				userInputOne = codeField.getText().toString().trim();
				if(codeChecker(userInputOne) == scoreOneTotal) {
					instructLabel.setText("Correct!");
					System.out.println("Lvl 1 Correct");
//					levelCode++;
				}
				else
					instructLabel.setText("Incorrect! Try Again: ");
					
			}
		});
		
	}
	
	
	public static String removeSpaces(String phrase) {
		while(phrase.indexOf(" ") != -1)
			phrase = phrase.substring(0, phrase.indexOf(" ")) + phrase.substring(phrase.indexOf(" ")+1, phrase.length());
			
		return phrase;
	}
	
	public static int codeChecker(String userInput) {
		//index of int, indexes of 400, indexes of =, index of ;
		int userScore = 0;
		
		String lvlOne = "int x = 400, y = 400;";
		if(levelCode == 1) {
			if(lvlOne.equalsIgnoreCase(userInput)) {
				return 5;
				
			}
			else {
				if(userInput.indexOf("int") != -1)
					userScore++;
				if(userInput.indexOf("=") != -1 && userInput.indexOf("y") != -1)
					userScore++;
				if(userInput.indexOf("400") != -1)
					userScore++;
				if(userInput.indexOf(";") != -1)
					userScore++;
				if(userScore == 4)
					if(userInput.indexOf("int") < userInput.indexOf("=") && userInput.indexOf("x") < userInput.indexOf("400") && 
							userInput.indexOf("400") < userInput.indexOf(";"))
						userScore++;
			}

			return userScore;
		}
		
		return 0;
	}
	
	public static void main(String[] args){
		new endMethods();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	

}
