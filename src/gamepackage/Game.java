package gamepackage;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Game extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Icon img;
	private JLabel healthbarLabelPlayer;
	private JLabel readyText, timePanel;
	private static PlayerCharacter player;
	private static EnemyCharacter enemy;
	private JFrame f;
	private int countDown;
	
	private boolean running = false;
	private Thread thread;
	
	public Game( int frameBoundX, int frameBoundY) {	
		super();
		
		f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//background
		img = new ImageIcon(getClass().getResource("ArkaPlan.jpg"));
		JLabel contentPane = new JLabel();
		contentPane.setIcon( img );
		contentPane.setLayout( null );
		f.setContentPane( contentPane );
		
		
		int g = (frameBoundX - 260)/2;
		//healthbar for Player
		healthbarLabelPlayer = new JLabel("HealthBar1");
		healthbarLabelPlayer.setBounds(50, 50, g, 50);
		healthbarLabelPlayer.setBackground(Color.red);
		healthbarLabelPlayer.setOpaque(true);
		f.add(healthbarLabelPlayer);
		
		//healtbar2
		JLabel  healthbarLabelEnemy = new JLabel("HealthBar2");
		healthbarLabelEnemy.setBounds(g + 210, 50, g, 50);
		healthbarLabelEnemy.setBackground(Color.red);
		healthbarLabelEnemy.setOpaque(true);
		f.add(healthbarLabelEnemy);
		
		
		//timePanel
		timePanel = new JLabel("Time");
		timePanel.setBounds(g + 80, 30, 100, 100);
		timePanel.setBackground(Color.white);
		timePanel.setOpaque(true);
		f.add(timePanel);
		
		//Player Label
		player = new PlayerCharacter(frameBoundX, frameBoundY);
		f.add(player);
		
		//Enemy Label
		enemy = new EnemyCharacter(frameBoundX, frameBoundY);
		f.add(enemy);
		
		player.setEnemy(enemy);
		
		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.addKeyListener(this);
		f.setVisible(true);
		
		//get ready panel
		int i = frameBoundY - 520;
		readyText = new JLabel();
		readyText.setLayout(null);
		readyText.setText("READY");
		//readyText.setOpaque(true);
		readyText.setBounds(frameBoundX / 2 - 200, i / 2 + 310, 400, 100);
	  
		f.add(readyText);

		
		thread = new Thread(this);
		thread.start();
		
	}
	
	@Override
	public void run() {
		// wait for 3 seconds
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//get ready panel disappears
		f.remove(readyText);
		
		//Count Down
		countDown = 100;
		Timer timer = new Timer();
		timePanel.setText(""+countDown);
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (countDown <= 0) {
					timer.cancel();
					timePanel.setText("END");
				} else {
					countDown = countDown - 1;
					timePanel.setText(""+countDown);
				}
			}
		}, 1, 1000);
		
		
		running = true;
		while (running) {
			player.update();
			enemy.update();
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
		if(keyCode == KeyEvent.VK_ESCAPE) {
			setVisible(false);
			f.getContentPane().removeAll();
			f.setVisible(false);
			new Menu();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
