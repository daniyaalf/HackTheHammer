import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class endMethods extends JPanel implements ActionListener {

	public static JFrame codeFrame;
	public static JPanel codePanel;
	public static JLabel instructLabel;
	public static JTextArea codeField;
	public static JButton submitButton, nextButton;

	Image codeBgSubmit, codeBgNext;

	static int levelCode = 0;

	public endMethods() {
		codeBgSubmit = Toolkit.getDefaultToolkit().createImage("Javos/codeBgSubmit.jpg");
		codeBgNext = Toolkit.getDefaultToolkit().createImage("Javos/codeBgNext.jpg");

		codeFrame = new JFrame("CODE CHALLENGES");
		codeFrame.setPreferredSize(new Dimension(600, 500));
		codeFrame.setLocation(100, 200);

		codePanel = new JPanel();
		codePanel.setLayout(new GridLayout(0, 1, 10, 5));
		codePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		codePanel.setBackground(Color.BLACK);

		instructLabel = new JLabel("Question " + levelCode + ": ", JLabel.CENTER);
		instructLabel.setFont(new Font("Arial", Font.BOLD, 48));
		instructLabel.setForeground(Color.WHITE);

		codeField = new JTextArea();
		codeField.setBackground(Color.gray);

		submitButton = new JButton("");
		submitButton.setActionCommand("Submit");
		submitButton.setIcon(new ImageIcon(codeBgSubmit));

		nextButton = new JButton("");
		nextButton.setActionCommand("Next");
		nextButton.setIcon(new ImageIcon(codeBgNext));
		nextButton.setEnabled(false);
		

		codePanel.add(instructLabel);
		codePanel.add(codeField);
		codePanel.add(submitButton);
		codePanel.add(nextButton);

		codeFrame.add(codePanel);
		codeFrame.pack();
		codeFrame.setVisible(true);

		final int scoreOneTotal = 5;
		final int scoreTwoTotal = 6;
		final int scoreThreeTotal = 9;

		int[] totalScores = {scoreOneTotal, scoreTwoTotal, scoreThreeTotal};

		submitButton.addActionListener(new ActionListener() {
			String userInput = "";

			public void actionPerformed(ActionEvent e) {
				userInput = codeField.getText().toString().trim();
//				if(levelCode == 0)
//					nextButton.setEnabled(true);
				if (codeChecker(userInput) == totalScores[levelCode-1]) {
					instructLabel.setText("Question " + levelCode + " Correct!");
					System.out.println("Lvl " + levelCode + " Correct");
					nextButton.setEnabled(true);
				} else
					instructLabel.setText("Incorrect! Try Again: ");

			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (levelCode < 3)
					levelCode++;

				nextButton.setEnabled(false);
				instructLabel.setText("Question " + levelCode + ": ");
				codeFrame.dispose();
			}
		});

	}

	public static String removeSpaces(String phrase) {
		while (phrase.indexOf(" ") != -1)
			phrase = phrase.substring(0, phrase.indexOf(" "))
					+ phrase.substring(phrase.indexOf(" ") + 1, phrase.length());

		return phrase;
	}
	
	public static int levelOne(String userInput) {
		int userScore = 0;
		String lvlOne = "int x=400, y=400;";

		if (lvlOne.equalsIgnoreCase(userInput)) {
			return 5;
		} else {
			if (userInput.indexOf("int") != -1)
				userScore++;
			if (userInput.indexOf("=") != -1 && userInput.indexOf("y") != -1 && userInput.indexOf("x") != -1)
				userScore++;
			if (userInput.indexOf("400") != -1)
				userScore++;
			if (userInput.indexOf(";") != -1)
				userScore++;
			if (userScore == 4) {
				if (userInput.indexOf("int") != userInput.lastIndexOf("int"))
					if (userInput.indexOf(";") == userInput.lastIndexOf(";"))
						userScore--;
					else if (userInput.indexOf("int") == userInput.lastIndexOf("int"))
						if (userInput.indexOf(",") == -1)
							userScore--;

				if (userInput.indexOf("int") < userInput.indexOf("=")
						&& userInput.indexOf("x") < userInput.indexOf("400")
						&& userInput.indexOf("400") < userInput.indexOf(";"))
					userScore++;
			}
		}
		return userScore;
	}

	public static int levelTwo(String userInput) {
		int userScore = 0;
		String lvlTwo = "if(students>30)\n\tSystem.out.println(\"No\");";
		int indexPrint = 0;
		System.out.println(removeSpaces(userInput));
		if (lvlTwo.equalsIgnoreCase(userInput)) {
			return 6;
		} else {
			if (userInput.indexOf("if") != -1)
				userScore++;
			if (removeSpaces(userInput).indexOf("students>30") != -1) {
				userScore++;
			}
			if (userInput.indexOf("System.out.println") != -1 || userInput.indexOf("System.out.print") != -1) {
				if (userInput.indexOf("System.out.println") != -1)
					indexPrint = userInput.indexOf("System.out.println");
				if (userInput.indexOf("System.out.print") != -1)
					indexPrint = userInput.indexOf("System.out.print");
				userScore++;
			}
			if (userInput.toLowerCase().indexOf("('no')") != -1)
				userScore++;
			if (userInput.indexOf(";") != -1)
				userScore++;
			if (userScore == 5)
				if (userInput.indexOf("if") < removeSpaces(userInput).indexOf("(students>30)")
						&& removeSpaces(userInput).indexOf("(students>30)") < indexPrint
						&& indexPrint < userInput.toLowerCase().indexOf("(\"no\")")
						&& userInput.toLowerCase().indexOf("(\"no\")") < userInput.indexOf(";"))
					userScore++;

		}
		return userScore;

	}

	public static int levelThree(String userInput) {
		int userScore = 0;
		String lvlThree = "int num=100;\nint sum=0;\nwhile(num>0){\n\tsum+=num;\n\tnum--;\n}";
		if (lvlThree.equalsIgnoreCase(userInput)) {
			return 9;
		} else {
			if (userInput.indexOf("int") != -1)
				userScore++;
			if (userInput.indexOf("=") != -1 && (userInput.indexOf("=") != userInput.lastIndexOf("=")))
				userScore++;
			if (userInput.indexOf(";") != -1)
				userScore++;
			if (removeSpaces(userInput.toLowerCase()).indexOf("while") != -1)
				userScore++;
			if (userInput.indexOf("{") != -1)
				userScore++;
			if (removeSpaces(userInput.toLowerCase()).indexOf("sum+=num;") != -1)
				userScore++;
			if (userInput.toLowerCase().indexOf("num--;") != -1 || userInput.toLowerCase().indexOf("num++;") != -1)
				userScore++;
			if (userInput.indexOf("}") != -1)
				userScore++;
			if (userScore == 8)
				if (userInput.indexOf("int") < userInput.indexOf("=") && userInput.indexOf("=") < userInput.indexOf(";")
						&& userInput.indexOf(";") < removeSpaces(userInput.toLowerCase()).indexOf("while(num>0)")
						&& removeSpaces(userInput.toLowerCase()).indexOf("while") < userInput.indexOf("{")
						&& userInput.indexOf("{") < removeSpaces(userInput.toLowerCase()).indexOf("sum+=num;")
						&& removeSpaces(userInput.toLowerCase()).indexOf("sum+=num;") < userInput.indexOf("}"))
					userScore++;

		}
		return userScore;
	}

	public static int codeChecker(String userInput) {
		// index of int, indexes of 400, indexes of =, index of ;
		int score = 0;
		if (levelCode == 0)
			score = 0;
		
		if (levelCode == 1)
			score = levelOne(userInput);
		if (levelCode == 2)
			score = levelTwo(userInput);
		if (levelCode == 3)
			score = levelThree(userInput);

		return score;
	}

	public static void main(String[] args) {
		new endMethods();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}