import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class HackGraphics extends JPanel implements KeyListener, MouseListener {
	
	public static JFrame frame;
	public static JLabel infoLabel;
	public static HackGraphics myPanel;
	public static Image bg;
	public static Image floor;
	public static Image character;
	public static Image boss;
	public static int levelCode = endMethods.levelCode;
	public static String [] levelArray = {"bg0.png", "bg1.png", "bg2.png", "bg3.png"};
	public static String currentBg;
	
	public static int screenWidth = 1200;
	public static int screenHeight = 480;
	public static int charWidth = 58;
	public static int charHeight = 108;
	
	String movementArrayLeft[] = {"Javos/LLL.png", "Javos/LNL.png", "Javos/LRL.png", "Javos/LNL.png"};
	String movementArrayRight[] = {"Javos/RRL.png", "Javos/RNL.png", "Javos/RLL.png", "Javos/RNL.png"};
	String movementArrayUp[] = {"Javos/URL.png", "Javos/UNL.png", "Javos/ULL.png", "Javos/UNL.png"};
	String movementArrayDown[] = {"Javos/DRL.png", "Javos/DNL.png", "Javos/DLL.png", "Javos/DNL.png"};
	int curPosLeft = 0;
	int curPosRight = 0;
	int curPosUp = 0;
	int curPosDown = 0;
    
    public static int xPos = 0;
    public static int yPos = screenHeight - charHeight;
    int bossXPos = 1100;
    int bossYPos = 220;
    int bossWidth = 100;
    int bossHeight = 100;
    
	int speed = 20;

	Rectangle hitbox;
	Rectangle bossHitBox;
	
	Rectangle [] walls = new Rectangle [(int)(Math.random() * (10 - 4 + 1) + 4)];
	static int wallWidth = 100;
	static int wallHeight = 200;
	static int wallX = 0;
	static int wallY = 0;
	
	int counter;
	static int [] randomRect = new int [4];
    
	public HackGraphics()
	{
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setVisible(true);
		
		infoLabel = new JLabel();
		infoLabel.setText(questionMethods.questionZero);
		
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		bg = Toolkit.getDefaultToolkit().getImage("Javos/" + levelArray[levelCode]);
		character = Toolkit.getDefaultToolkit().getImage("Javos/RSL.png");
		boss = Toolkit.getDefaultToolkit().getImage("Javos/boss" + levelCode + ".png");
		currentBg = levelArray[levelCode];
		if (currentBg.equals("bg0.png")) {
			bossXPos = 330;
			bossYPos = 10;
			bossWidth = 500;
			bossHeight = 500;
			
//			JLabel introText = new JLabel();
//			introText.setText("Welcome to Javos! Move around with W, A, S, and D. Try walking into obstacles!");
//			myPanel.add(introText);
			
		} else {
			for (int i = 0; i < walls.length; i++) {
				randomRect();
				walls [i] = new Rectangle (randomRect[0], randomRect[1], randomRect[2], randomRect[3]);
			}
		}
		setFocusable (true);
		addKeyListener (this);
		addMouseListener (this);
	}
	
	public void paint(Graphics g)
    {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
        g.drawImage(bg, 0, 0, this);
        g.drawImage(character, xPos, yPos, 58, 104, this);
        g.drawImage(boss, bossXPos, bossYPos, bossWidth, bossHeight, this);
        if (levelCode != 0) {
        	if (levelCode == 1) {
        		g2.setColor(new Color (150, 75, 0));
        	} else if (levelCode == 2) {
        		g2.setColor(new Color (173, 216, 230));
        	} else if (levelCode == 3) {
        		g2.setColor(new Color (212,175,55));
        	}
        	for (int i = 0; i < walls.length; i++)
        		g2.fill(walls[i]);
        }
    }
	
	public static void randomRect() {
		randomRect [0] = (int)(Math.random()* (800) + 100);
		randomRect [1] = (int)(Math.random()* (240));
		randomRect [2] = (int)(Math.random()* (100) + 50);
		randomRect [3] = (int)(Math.random()* (200) + 50);
	}
	
	void checkCollision(Rectangle wall) {
		//check if rect touches wall
		if(hitbox.intersects(wall)) {
			double left1 = hitbox.getX();
			double right1 = hitbox.getX() + hitbox.getWidth();
			double top1 = hitbox.getY();
			double bottom1 = hitbox.getY() + hitbox.getHeight();
			double left2 = wall.getX();
			double right2 = wall.getX() + wall.getWidth();
			double top2 = wall.getY();
			double bottom2 = wall.getY() + wall.getHeight();

			if(right1 > left2 && 
					left1 < left2 && 
					right1 - left2 < bottom1 - top2 && 
					right1 - left2 < bottom2 - top1)
			{
				xPos = wall.x - hitbox.width;
			}
			else if(left1 < right2 &&
					right1 > right2 && 
					right2 - left1 < bottom1 - top2 && 
					right2 - left1 < bottom2 - top1)
			{
				xPos = wall.x + wall.width;
			}
			else if(bottom1 > top2 && top1 < top2)
			{
				yPos = wall.y - hitbox.height;
			}
			else if(top1 < bottom2 && bottom1 > bottom2)
			{
				yPos = wall.y + wall.height;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		hitbox = new Rectangle (xPos, yPos, charWidth, charHeight);
		bossHitBox = new Rectangle (bossXPos, bossYPos, bossWidth, bossHeight);
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			xPos -= speed;
			if(xPos < 0)
				xPos = 0;
			curPosLeft += 1;
			if (curPosLeft > 3)
				curPosLeft = 0;
			character = Toolkit.getDefaultToolkit().getImage(movementArrayLeft[curPosLeft]);
			if (levelCode != 0) {
				for (int i = 0; i < walls.length; i ++)
					checkCollision(walls[i]);
			}
		} else if(key == KeyEvent.VK_D) {
			xPos += speed;
			if(xPos > screenWidth - charWidth)
				xPos -= speed;
			curPosRight += 1;
			if (curPosRight > 3)
				curPosRight = 0;
			character = Toolkit.getDefaultToolkit().getImage(movementArrayRight[curPosRight]);
			if (levelCode != 0) {
				for (int i = 0; i < walls.length; i ++)
					checkCollision(walls[i]);
			}
		}else if(key == KeyEvent.VK_W) {
			yPos -= speed;
			if(yPos < 0)
				yPos = 0;
			curPosUp += 1;
			if (curPosUp > 3)
				curPosUp = 0;
			character = Toolkit.getDefaultToolkit().getImage(movementArrayUp[curPosUp]);
			if (levelCode != 0) {
				for (int i = 0; i < walls.length; i ++)
					checkCollision(walls[i]);
			}
		}else if(key == KeyEvent.VK_S) {
			yPos += speed;
			if(yPos > screenHeight - charHeight)
				yPos -= speed;
			curPosDown += 1;
			if (curPosDown > 3)
				curPosDown = 0;
			character = Toolkit.getDefaultToolkit().getImage(movementArrayDown[curPosDown]);
			if (levelCode != 0) {
				for (int i = 0; i < walls.length; i ++)
					checkCollision(walls[i]);
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_A) {
			character = Toolkit.getDefaultToolkit().createImage("Javos/LSL.png");
		} else if(key == KeyEvent.VK_D) {
			character = Toolkit.getDefaultToolkit().createImage("Javos/RSL.png");
		}else if(key == KeyEvent.VK_W) {
			character = Toolkit.getDefaultToolkit().createImage("Javos/RSL.png");
		}else if(key == KeyEvent.VK_S) {
			character = Toolkit.getDefaultToolkit().createImage("Javos/RSL.png");
		}
		counter = 0;
		
			if (hitbox.intersects(bossHitBox)) {
				if (counter < 1) {
					counter++;
				checkCollision(bossHitBox);
				character = Toolkit.getDefaultToolkit().createImage("Javos/LSL.png");
				endMethods endFrame = new endMethods();
				endFrame.setVisible(true);
				questionMethods questionFrame = new questionMethods();
				questionFrame.setVisible(true);
				
				
				endMethods.nextButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(levelCode < 3)
							levelCode++;
						
						endMethods.nextButton.setEnabled(false);
						endMethods.instructLabel.setText("Question " + levelCode + ": ");
						myPanel.paint(myPanel.getGraphics()); 
						myPanel.repaint();
						xPos = 0;
						yPos = screenHeight - charHeight;
						bg = Toolkit.getDefaultToolkit().getImage("Javos/" + levelArray[levelCode]);
						character = Toolkit.getDefaultToolkit().getImage("Javos/RSL.png");
						boss = Toolkit.getDefaultToolkit().getImage("Javos/boss" + levelCode + ".png");
						randomRect();
					}
				});
			
//				if(levelCode == 0)
//					questionMethods.questionLabel.setText(questionMethods.questionZero);
				
				if(levelCode == 1)
					questionMethods.questionLabel.setText(questionMethods.questionOne);

				if(levelCode == 2)
					questionMethods.questionLabel.setText(questionMethods.questionTwo);

				if(levelCode == 3)
					questionMethods.questionLabel.setText(questionMethods.questionThree);
				}
			}
			repaint();
	}
	
//	private void delay (int milliSec)
//    {
//	try
//	{
//	    Thread.sleep (milliSec);
//	}
//	catch (InterruptedException e)
//	{
//	}
//    }
	
	public static void main(String[] args) {
		frame = new JFrame ("Javos");
		myPanel = new HackGraphics ();
		myPanel.add(infoLabel);
		frame.add(myPanel);
		frame.addKeyListener((KeyListener) myPanel);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		new HackGraphics();
	}
}
