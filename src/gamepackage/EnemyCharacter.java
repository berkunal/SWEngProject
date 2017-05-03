package gamepackage;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

class EnemyCharacter extends JLabel {

	private static final long serialVersionUID = 1L;
	private int positionX, positionY;
	private int frameBoundX, frameBoundy;
	private int speed;
	private int verticalSpeed = 0;
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;
	private boolean attacking = false;
	private boolean jumping = false;
	private boolean jumpingNow = false;
	private int health = 100;
	private boolean enemyHit = false;
	private boolean win = false;
	
	private PlayerCharacter player;

	public EnemyCharacter(int frameBoundX, int frameBoundY) {
		// TODO Auto-generated constructor stub
		this.positionX = frameBoundX - 150 - 50;//depends on height
		this.positionY = frameBoundY-250;
		this.frameBoundX = frameBoundX;
		this.frameBoundy = frameBoundY;
		setBounds(positionX, positionY, 150, 200);//the heigh will differ, can get as parameters
		setBackground(Color.cyan);
		setText("Enemy");
		setOpaque(true);
		speed = 2;
	}
	//Setter methods
	public void setLeft(boolean b){
		this.left = b;
	}
	public void setRight(boolean b){
		this.right = b;
	}
	public void setDown(boolean b){
		this.down = b;
	}
	public void setAttacking(boolean b){
		this.attacking = b;
	}
	public void setHealth(int hp){
		this.health = hp;
	}
	public void setPlayer(PlayerCharacter player){
		this.player = player;
	}
	public void setEnemyHit(boolean b){
		this.enemyHit = b;
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
	public int getHealth(){
		return health;
	}
	public boolean getEnemyHit(){
		return enemyHit;
	}
	public boolean getWin() {
		return win;
	}
	
	private boolean collision () {
		int playerX = player.getpositionX();
		int w = (player.getDown()) ? 175 :150 ;
		if ( this.positionX < playerX + w) {//depends on width
			return true;
		}
		return false;
	}
	
	public void update() {
		//Movements here
		if (left && !collision()) {
			if(positionX > 25){
				for (int i = speed; i > 0; i--) {
					positionX--;
					setBounds(positionX, positionY, 150, 200);//depends on height
				}
			}
				
		}
		if (right) {
			int w = (player.getDown()) ? 175 :150 ;
			if(this.positionX + w < frameBoundX-25){//depends on height
				for (int i = speed; i > 0; i--) {
					positionX++;
					setBounds(positionX, positionY, w, 200);//depends on height
				}
			}	
		}
		
		//jumping
		if (jumping && !jumpingNow) {
			jumpingNow = true;//to make sure that there is only one jumping action
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
					jumpingNow = false;
				}
			});
			jumpingThread.start();
			
		}
		if (down) {
			setBounds(positionX, positionY+100, 175, 100);//depends on height
		}
		if (!down) {
			setBounds(positionX, positionY, 150, 200);//depends on height
		}
		if (attacking && collision()) {
			enemyHit = true;
		}
	}
	
	public void enemyBrain() {
		//AI here
		Thread AIThread = new Thread(new Runnable() {
			boolean running = false;
			int movementRNG, attackRNG;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				running = true;
				while(running){
					
					movementRNG = ThreadLocalRandom.current().nextInt(0,4);
					attackRNG = ThreadLocalRandom.current().nextInt(0,2);
					left=false;
					right=false;
					down=false;
					jumping=false;
					attacking=false;
					
					switch (movementRNG) {
					case 0://moving left
						left = true;
						break;
					case 1://moving right
						right = true;
						break;
					case 2:
						jumping = true;
						break;
					case 3:
						down = true;
						break;
					default:
						break;
					}
					switch (attackRNG) {
					case 0:
						attacking = true;
						break;
					case 1:
						attacking = false;
						break;
					default:
						break;
					}
					
					try {
						TimeUnit.MILLISECONDS.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		AIThread.start();
		
		
	}

}
