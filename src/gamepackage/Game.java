package gamepackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class Game extends JFrame implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Icon img;
	private JLabel healthbarLabelPlayer;
	private JLabel  healthbarLabelEnemy;
	private JLabel readyText, timePanel;
	private static PlayerCharacter player;
	private static EnemyCharacter enemy;
	private int frameBoundX, frameBoundY;
	private JFrame f;
	private int countDown;
	
	
	private boolean running = false;
	private Thread thread;
	private Font font, sizedFont = null;
	
	public Game( int frameBoundX, int frameBoundY) {	
		super();
		this.frameBoundX = frameBoundX;
		this.frameBoundY = frameBoundY;
		
		
		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}
		
		
		f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//background
		img = new ImageIcon(getClass().getResource("background_revision.jpg"));
		JLabel contentPane = new JLabel();
		contentPane.setIcon( img );
		contentPane.setLayout( null );
		f.setContentPane( contentPane );
		
		
		int g = (frameBoundX - 260)/2;
		//healthbar for Player
		healthbarLabelPlayer = new JLabel("Health: 100", SwingConstants.CENTER);
		healthbarLabelPlayer.setBounds(50, 50, g, 50);
		sizedFont = font.deriveFont(Font.BOLD, 28f);
		healthbarLabelPlayer.setFont(sizedFont);
		healthbarLabelPlayer.setForeground(Color.red);
		f.add(healthbarLabelPlayer);
		
		//healtbar2
		healthbarLabelEnemy = new JLabel("Health: 100", SwingConstants.CENTER);
		healthbarLabelEnemy.setBounds(g + 210, 50, g, 50);
		sizedFont = font.deriveFont(Font.BOLD, 28f);
		healthbarLabelEnemy.setFont(sizedFont);
		healthbarLabelEnemy.setForeground(Color.red);
		f.add(healthbarLabelEnemy);
		
		
		//timePanel
		timePanel = new JLabel("99", SwingConstants.CENTER);
		sizedFont = font.deriveFont(30f);
		timePanel.setFont(sizedFont);
		timePanel.setBounds(g + 80, 30, 100, 100);
		f.add(timePanel);
		
		//Player Label
		player = new PlayerCharacter(frameBoundX, frameBoundY);
		f.add(player);
		
		//Enemy Label
		enemy = new EnemyCharacter(frameBoundX, frameBoundY);
		f.add(enemy);
		
		player.setEnemy(enemy);
		enemy.setPlayer(player);
		
		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.addKeyListener(this);
		f.setVisible(true);
		
		//get ready panel
		int i = frameBoundY - 520;
		readyText = new JLabel("Ready!", SwingConstants.CENTER);
		readyText.setLayout(null);
		sizedFont = font.deriveFont(45f);
		readyText.setFont(sizedFont);
		readyText.setForeground(Color.red);
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
		
		
		int enemyHP = 100, playerHP = 100;
		enemy.enemyBrain();
		running = true;
		while (running) {
			
			//update the act of player and check if it is hitting the enemy
			player.update();
			if (player.getPlayerHit()) {
				enemyHP -= 10;
				enemy.setHealth(enemyHP);
				player.setPlayerHit(false);
				healthbarLabelEnemy.setText("Health: " + enemy.getHealth());
				if (enemyHP<=0) {
					player.setWin(true);
				}
			}
			//update the act of enemy and check if it is hitting the player
			enemy.update();
			if (enemy.getEnemyHit()) {
				playerHP -= 10;
				player.setHealth(playerHP);
				enemy.setEnemyHit(false);
				healthbarLabelPlayer.setText("Health: " + player.getHealth());
				if (playerHP<=0) {
					enemy.setWin(true);
				}
			}
			
			//check if player or enemy wins
			if(player.getWin() || enemy.getWin()){
				f.setVisible(false);
				f.dispose();
				//dispose current frame, create new one
				JFrame newFrame = new JFrame("Geotrix");
				newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				newFrame.setBounds(0, 0, frameBoundX, frameBoundY );
				//pass the value of player.win to new frame
				new EndGame(newFrame, frameBoundX, frameBoundY, player.getWin());
				running = false;
				break;
			}
			
			
			
			
			
			
			f.revalidate();
			f.repaint();
			
			try {
				Thread.sleep(3);
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
