package gamepackage;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	private Icon img;

	public Game( int frameBoundX, int frameBoundY) {		
		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//background
		img = new ImageIcon(getClass().getResource("bg.jpg"));
		JLabel contentPane = new JLabel();
		contentPane.setIcon( img );
		contentPane.setLayout( null );
		f.setContentPane( contentPane );
		
		
		int g = (frameBoundX - 260)/2;
		//healthbar for Player
		JLabel  healtbarLabelPlayer = new JLabel();
		healtbarLabelPlayer.setBounds(50, 50, g, 50);
		healtbarLabelPlayer.setBackground(Color.black);
		healtbarLabelPlayer.setOpaque(true);
		f.add(healtbarLabelPlayer);
		
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
		
		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.setVisible(true);
	}

}
