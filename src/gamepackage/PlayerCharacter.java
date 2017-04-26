package gamepackage;

import java.awt.Color;

import javax.swing.JLabel;

class PlayerCharacter extends JLabel {

	private static final long serialVersionUID = 1L;
	private int positionX, positionY;
	private int frameBoundX, frameBoundy;
	private int speed;
	private boolean left = false;
	private boolean up = false;
	private boolean right = false;
	private boolean down = false;
	private boolean attacking = false;
	private int health = 100;

	public PlayerCharacter(int frameBoundX, int frameBoundY) {
		// TODO Auto-generated constructor stub
		this.positionX = 50;
		this.positionY = frameBoundY-250;
		this.frameBoundX = frameBoundX;
		this.frameBoundy = frameBoundY;
		setBounds(positionX, positionY, 150, 200);//the heigh will differ, can get as parameters
		setBackground(Color.black);
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
	
	public void update() {
		//player moving right
		if (right) {
			if(positionX < frameBoundX -25 - 150){
				positionX += speed;
				setBounds(positionX, positionY, 150, 200);
			}	
		}
		//player moving left
		if (left) {
			if(positionX > 25){
				positionX -= speed;
				setBounds(positionX, positionY, 150, 200);
			}
				
		}

	}
}
