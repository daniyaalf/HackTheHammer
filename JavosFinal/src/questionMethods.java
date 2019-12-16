import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class questionMethods extends JPanel {

	static int levelCode = endMethods.levelCode;

	public static String questionZero = String.format("<html>Welcome to Javos! Move around using A, S, D, and W, avoid walls, and beat the codevengers to obtain all 6 Max_VALUE stones. Press next to continue. </html>");
	
	public static String questionOne = String.format("<html>A \"variable\" in Java is something that can store another value. One type of a variable is an \"integer\"" + 
			"and an integer can store numbers without decimal places. Simply declare two integers named \"x\" and" + 
			"\"y\", and set them equal to 400. Here is an example of how to make a variable that is an integer:" + 
			"<p>int var = 1000;" + 
			"<p>In Java, remember that you must type a semicolon at the end of each line to indicate that the code " +
			"stops there.</html>");

	public static String questionTwo = String.format("<html>An \"if statement\" can be used to tell the computer to make a decision in Java. The idea is that \"if\"\n" + 
			"some condition is met, certain code will be executed. An if statement uses curly braces around the code\n" + 
			"in it. Note that to compare numbers, you can use the greater than or less than signs. Here's an example \n" + 
			"of an if statement:\n" + 
			"<p>	if(someNumber < anotherNumber) {\n" + 
			"<p>		someNumber = anotherNumber+1;\n" + 
			"<p>}\n" + 
			"An example of code that can be executed is making words appear on the console window. The \"syntax\", \n" + 
			"or the specific words and symbols that must be typed in for this to happen, are \"System.out.println(text)\".\n" + 
			"This will print a variable of words, called a String, onto the console. Here's a scenario: A programmer\n" + 
			"declared a variable called \"students\". It is an integer, and it is set equal to some value that you don't know. \n" + 
			"Make an if statement that checks if this number is more than 30, and if it is, print out the word \"No\", \n" + 
			"as if someone is asking whether that number of students can fit in one classroom. For example, if the \n" + 
			"variable is equal to 25, nothing  will happen. However, if it is equal to 40, \"No\" is printed to the console.\n" + 
			"</html>");

	public static String questionThree = String.format("<html>A loop can be used to repeat code. This makes a program more efficient, rather than having to type code\n" + 
			"again and again. One useful type of loop is a while loop. In a while loop, code repeats while a certain\n" + 
			"condition is true. The code that repeats is inside curly braces, just like the if statement. For example:\n" + 
			"	while(someNumber > 5) {\n" + 
			"		someNumber--; //someNumber-- means that the integer will decrease by one each time.\n" + 
			"	}\n" + 
			"Always make sure an infinite loop is not created. The loop's conditions should eventually not be met for\n" + 
			"the loop to stop. Your task is to use a while loop to find the sum of the numbers 1 to 100 (inclusive).\n" + 
			"Refrain from including spaces between assignment operators (int x=100, not x = 100), and between the \n" + 
			"conditions and the curly braces.\n" + 
			"HINT: Two variables are needed. One for the number being added, and one for the total sum. Finally,\n" + 
			"print the variable out at the end. MAKE THE NUMBER VARIABLE \"num\" AND THE OTHER ONE \"sum\".\n" + 
			"</html>");


	public static JFrame questionFrame;
	public static JPanel questionPanel;
	public static JLabel questionLabel;

	public questionMethods () {

		questionFrame = new JFrame ("Question");
		questionFrame.setPreferredSize(new Dimension(600, 500));
		questionFrame.setLocation(800, 200);


		questionPanel = new JPanel();
		questionPanel.setLayout(new GridLayout(0, 1, 10, 5));
		questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		questionPanel.setBackground(Color.BLACK);

		questionLabel = new JLabel ("Question " + levelCode + ": ", JLabel.CENTER);
		if(levelCode == 2)
			questionLabel.setFont(new Font("Arial", Font.BOLD, 15));
		else
			questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		questionLabel.setForeground(Color.WHITE);

		questionPanel.add(questionLabel);

		questionFrame.add(questionPanel);
		questionFrame.pack();
		questionFrame.setVisible(true);
		endMethods.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					questionFrame.dispose();
			}
		});
	}



	public static void main(String[] args) {
		new questionMethods();
	}

}
