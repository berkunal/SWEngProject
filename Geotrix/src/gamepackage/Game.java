package gamepackage;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Game extends JPanel{

	private static final long serialVersionUID = 1L;

	public Game(JFrame f, int frameBoundX, int frameBoundY) {

		setBounds(0,0,frameBoundX, frameBoundY);
		
		JLabel logoLabel = new JLabel();
		logoLabel.setLayout(null);
		logoLabel.setBackground(Color.black);
		logoLabel.setOpaque(true);
		logoLabel.setBounds(10, 10, 200, 100);
		
		add(logoLabel);
	}

}
