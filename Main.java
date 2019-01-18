package snake;


import javax.swing.JFrame;

public class Main {
	public static JFrame Window = new JFrame();
	public static Gameplay game = new Gameplay();
	public static Selector selector = new Selector();
	public static void main (String[] args) {
		Window.setBounds(0, 0, 905, 700);	
		Window.add(selector);
		Window.setVisible(true);		
		Window.setResizable(false);
		Window.setTitle("Snake Game");
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void changemode(boolean a) {
		if(a) {
			Window.setVisible(false);
			Window.remove(selector);
			Window.add(game);
			Window.setVisible(true);
		}
		
	}
}
