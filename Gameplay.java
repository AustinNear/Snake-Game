package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean spacebar = false;
	private boolean gameover = false;
	
	private boolean enemyalive = true;
	
	private int lengthofsnake = 3;
	private int score = 0;
	private int moves = 0;
	
	private Timer timer;
	private int delay = 75;
	
	private Random rnum = new Random();
	
	private int enemyxlength = rnum.nextInt(905); //says length but should say position
	private int enemyylength = rnum.nextInt(700); //random coordinates for the first enemy spawn
	
	
	public Gameplay() {
		addKeyListener(this);					//Makes the key listener and timer when the game object is created
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	
	public void paint(Graphics graphics) {
		if(moves == 0) {
			snakexlength[2] = 50;		//Sets default coordinates for the starting snake segments
			snakexlength[1] = 60;
			snakexlength[0] = 70;
			snakeylength[2] = 70;
			snakeylength[1] = 70;
			snakeylength[0] = 70;
		}
		
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, 0, 905, 700);
		graphics.setColor(Color.GREEN);						//drawing the background and the score tracker
		graphics.setFont(new Font("arial", Font.BOLD, 10));
		graphics.drawString("Score: "+ score, 80, 50);
		
		for(int a = 0; a < lengthofsnake; a++) {		//drawing snake segments
				graphics.setColor(Color.getHSBColor(rnum.nextInt(200)+50, rnum.nextInt(200)+50, rnum.nextInt(200)+50));
				graphics.fillRect(snakexlength[a], snakeylength[a], 10, 10);
		}
		graphics.setColor(Color.WHITE);
		graphics.fillRect(enemyxlength, enemyylength, 10, 10);	//drawing first enemy
		
		if((enemyxlength >= 0 && enemyxlength <= 50)||(enemyxlength >= 855 && enemyxlength <= 905)||(enemyylength >= 0 && enemyylength <= 50)||(enemyylength >= 650 && enemyylength <= 700)) {
			graphics.setColor(Color.WHITE);
			enemyxlength = rnum.nextInt(905);		//corrects the enemy spawn if the coordinates are too close to edges
			enemyylength = rnum.nextInt(700);		//to prevent off screen enemies
			graphics.fillRect(enemyxlength - (enemyxlength%10), enemyylength - (enemyylength%10), 10, 10);
		}
		
		
		if(enemyalive == false) {
			graphics.setColor(Color.WHITE);	//respawns the enemy when one is eaten
			graphics.fillRect(enemyxlength = rnum.nextInt(905), enemyylength = rnum.nextInt(700), 10, 10);
			enemyalive = true;
		}
		if(((enemyxlength - 10 <= snakexlength[0]) && (snakexlength[0] <= enemyxlength + 10)) && ((enemyylength - 10 <= snakeylength[0]) && (snakeylength[0] <= enemyylength + 10))) {
			graphics.setColor(Color.DARK_GRAY);
			graphics.fillRect(enemyxlength, enemyylength, 10, 10);	//when the the snake's head block is within 10 pixels
			enemyalive = false;										//the enemy dies, the score goes up, and the snake grows
			lengthofsnake++;
			score++;
		}
		
		for(int i = 1; i < lengthofsnake; i++)
			if(((snakexlength[0] >= snakexlength[i] - 4) && (snakexlength[0] <= snakexlength[i] + 4)) && ((snakeylength[0] >= snakeylength[i] - 4) && (snakeylength[0] <= snakeylength[i] + 4)))
			{
				gameover = true;		//when the snake's head coords are within 4 pixels of any body segment's coords the game ends
			}
		if(gameover == true) {
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("arial", Font.BOLD, 50));		//text to inform the end of the game and how to restart
			graphics.drawString("GAME OVER PALSY-EYES", 250, 300);
			graphics.setFont(new Font("arial", Font.BOLD, 25));
			graphics.drawString("SPACE RESTART", 300, 600);
		}
			
		graphics.dispose(); //creates a blank canvas for the next tick to draw the next frame on
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	if(spacebar) {
			gameover = false;			//when spacebar is true it resets all changed variables back to default
			spacebar = false;
			score = 0;
			moves = 0;
			lengthofsnake = 3;
			repaint();
		}
	if(gameover == false) {	//if statements prevents movement when game is over
		if(up){
			for(int r = lengthofsnake - 1; r >= 0; r--) {
				snakexlength[r+1] = snakexlength[r];		//each segment's x coord takes the place of the one in front of it
			}
			
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r == 0)
					snakeylength[r] = snakeylength[r] - 10;		//if it is the first segment it moves 10 pixels in proper direction
				else
					snakeylength[r] = snakeylength[r-1];		//if it isn't the first segment the y coord becomes that of the one in front of it
				
				if(snakeylength[r] < 0)
					snakeylength[r] = 700;
			}
			repaint();
		}
		
		if(down){
			for(int r = lengthofsnake - 1; r >= 0; r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r == 0)
					snakeylength[r] = snakeylength[r] + 10;
				else
					snakeylength[r] = snakeylength[r-1];
				
				if(snakeylength[r] > 700)
					snakeylength[r] = 0;
			}
			repaint();
		}
		
		if(left){
			for(int r = lengthofsnake - 1; r >= 0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r == 0)
					snakexlength[r] = snakexlength[r] - 10;
				else
					snakexlength[r] = snakexlength[r-1];
				
				if(snakexlength[r] < 0)
					snakexlength[r] = 905;
			}
			repaint();
		}
		
		if(right){
			for(int r = lengthofsnake - 1; r >= 0; r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			
			for(int r = lengthofsnake; r >= 0; r--) {
				if(r == 0)
					snakexlength[r] = snakexlength[r] + 10;
				else
					snakexlength[r] = snakexlength[r-1];
				
				if(snakexlength[r] > 905)
					snakexlength[r] = 0;
			}
			repaint();
		}
	}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(gameover == true)	//pressing spacebar when the game is over sets spacebar to true and triggers the reset above
				spacebar = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			moves++;
			right = true;
			if(!left) {				//prevents turning left in one move while going right so you don't go into yourself too easily
				right = true;		
			}
			else {
				left = true;	
				right = false;
			}
			up = false;			//sets other directions back to false in case they were true before
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else{
				right = true;
				left = false;
			}
			up = false;
			down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else{
				down = true;
				up = false;
			}
			right = false;
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else{
				up = true;
				down = false;
			}
			right = false;
			left = false;
		}
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
