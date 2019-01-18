package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Selector extends JPanel implements KeyListener, ActionListener {
	private boolean one = false;
	private Timer timer;
	private int delay = 75;
	public Selector() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 905, 700);
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 50));
		graphics.drawString("Press 1 to start", 250, 300);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1) {
			Main.changemode(true);
			timer.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
