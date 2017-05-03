package gamepackage;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

class PlayerCharacter extends JLabel {

	private static final long serialVersionUID = 1L;
	private int positionX, positionY;
	private int frameBoundX, frameBoundy;
	private int speed;
	private int verticalSpeed = 0;
	private boolean left = false;
	private boolean up = false;
	private boolean right = false;
	private boolean down = false;
	private boolean attacking = false;
	private boolean jumping = false;
	private boolean win = false;
	private int health = 100;
	private EnemyCharacter enemy;
	private boolean playerHit = false;

	public PlayerCharacter(int frameBoundX, int frameBoundY) {
		// TODO Auto-generated constructor stub
		this.positionX = 50;
		this.positionY = frameBoundY-250;
		this.frameBoundX = frameBoundX;
		this.frameBoundy = frameBoundY;
		setBounds(positionX, positionY, 150, 200);//the height will differ, can get as parameters
		setBackground(Color.cyan);
		setText("Player");
		setOpaque(true);
		speed = 3;
	}

	//Setter methods
	public void setLeft(boolean b){
		this.left = b;
	}
	public void setRight(boolean b){
		this.right = b;
	}
	public void setUp(boolean b){
		this.up = b;
	}
	public void setDown(boolean b){
		this.down = b;
	}
	public void setAttacking(boolean b){
		this.attacking = b;
	}
	public void setEnemy(EnemyCharacter enemy){
		this.enemy = enemy;
	}
	public void setPlayerHit(boolean b){
		this.playerHit = b;
	}
	public void setHealth(int hp){
		this.health = hp;
	}
	public void setWin(boolean b){
		this.win = b;
	}
	
	//Getter methods
	public int getpositionX(){
		return positionX;
	}
	public int getpositionY(){
		return positionY;
	}
	public boolean getWin(){
		return win;
	}
	public int getHealth() {
		return health;
	}
	public boolean  getAttacking() {
		return attacking;
	}
	public boolean getDown() {
		return down;
	}
	public	boolean getPlayerHit() {
		return playerHit;
	}

	private boolean collision () {
		int enemyX = enemy.getpositionX();
		int w = (down) ? 175 :150 ;
		if ( this.positionX + w > enemyX) {//depends on width
			return true;
		}
		return false;
	}
	
	//update method, used at moving the object
	public void update() {
		//player moving right
		
		if (right && !collision()) {
			int w = (down) ? 175 :150 ;
			if(positionX < frameBoundX -25 - 150){//depends on height
				for (int i = speed; i > 0; i--) {
					positionX++;
					setBounds(positionX, positionY, w, 200);//depends on height
				}
			}	
		}
		//player moving left
		if (left) {
			int w = (down) ? 175 :150 ;
			if(positionX > 25){
				for (int i = speed; i > 0; i--) {
					positionX--;
					setBounds(positionX, positionY, w, 200);//depends on height
				}
			}
				
		}
		//jumping
		if (up && !jumping) {
			jumping = true;//to make sure that there is only one jumping action
			//Jumping occurs in another thread
			Thread jumpingThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					//going upwards
					verticalSpeed = 100;
					int sleepTime=0;
					for (int i = verticalSpeed; i > 0; i--) {
						positionY--;
						setBounds(positionX, positionY, 150, 200);//depends on height
						//sleepTime changes by the position of the player for making accelerated movement
						if(i<=100-55-33){
							sleepTime = 150/11;
						}
						else if(i<=100-55){
							sleepTime = 150/33;
						}
						else if(i<100){
							sleepTime = 150/55;
						}
						try {
							TimeUnit.MILLISECONDS.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//falling			
					for (int i = verticalSpeed; i > 0; i--) {
						positionY++;
						setBounds(positionX, positionY, 150, 200);//depends on height
						
						if(i<=55){
							sleepTime = 150/55;
						}
						else if(i<=88){
							sleepTime = 150/33;
						}
						else if(i<100){
							sleepTime = 150/11;
						}
				
						try {
							TimeUnit.MILLISECONDS.sleep(sleepTime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						TimeUnit.MILLISECONDS.sleep(4);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jumping = false;
				}
			});
			jumpingThread.start();
			
		}
		//crouching
		if (down) {
			setBounds(positionX, positionY+100, 175, 100);//depends on height
		}
		if (!down) {
			setBounds(positionX, positionY, 150, 200);//depends on height
		}
		if (attacking && collision()) {
			playerHit = true;
			attacking = false;
		}
	}
}
