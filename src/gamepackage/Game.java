package gamepackage;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Game extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Icon img;
	private JLabel healthbarLabelPlayer;
	private PlayerCharacter player;
	private JFrame f;
	
	private boolean running = false;
	private Thread thread;
	
	public Game( int frameBoundX, int frameBoundY) {	
		super();
		
		f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//background
		img = new ImageIcon(getClass().getResource("bg.jpg"));
		JLabel contentPane = new JLabel();
		contentPane.setIcon( img );
		contentPane.setLayout( null );
		f.setContentPane( contentPane );
		
		
		int g = (frameBoundX - 260)/2;
		//healthbar for Player
		healthbarLabelPlayer = new JLabel();
		healthbarLabelPlayer.setBounds(50, 50, g, 50);
		healthbarLabelPlayer.setBackground(Color.black);
		healthbarLabelPlayer.setOpaque(true);
		f.add(healthbarLabelPlayer);
		
		//healtbar2
		JLabel  healtbarLabelEnemy = new JLabel();
		healtbarLabelEnemy.setBounds(g + 210, 50, g, 50);
		healtbarLabelEnemy.setBackground(Color.black);
		healtbarLabelEnemy.setOpaque(true);
		f.add(healtbarLabelEnemy);
		
		
		//timePanel
		JLabel  timePanel = new JLabel();
		timePanel.setBounds(g + 80, 30, 100, 100);
		timePanel.setBackground(Color.black);
		timePanel.setOpaque(true);
		f.add(timePanel);
		
		player = new PlayerCharacter(frameBoundX, frameBoundY);
		f.add(player);
		
		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.addKeyListener(this);
		f.setVisible(true);
		
		thread = new Thread(this);
		thread.start();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		running = true;
		while (running) {
			player.update();
			f.revalidate();
			f.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(keyCode == KeyEvent.VK_UP){
			player.setUp(true);
		}
		if(keyCode == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		if(keyCode == KeyEvent.VK_SPACE){
			player.setAttacking(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(keyCode == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(keyCode == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(keyCode == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		if(keyCode == KeyEvent.VK_SPACE){
			player.setAttacking(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
