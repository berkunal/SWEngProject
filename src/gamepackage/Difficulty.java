package gamepackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Difficulty extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font font, sizedFont = null;

	public Difficulty(JFrame f, int frameBoundX, int frameBoundY) {
		int i = frameBoundY - 520;

		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}
		
		//Main Panel of Difficulty Section created here
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(null);
		difficultyPanel.setBounds(0,0,frameBoundX, frameBoundY);
		
		//Logo
		JLabel logoLabel = new JLabel("GEOTRIX", SwingConstants.CENTER);
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);
		logoLabel.setBounds(frameBoundX / 2 - 200, (frameBoundY-520)/ 2, 400, 190);
		difficultyPanel.add(logoLabel);
		
		//Mentioning that we are at the difficulty screen here
		JLabel difficultyLabel = new JLabel("How much you can handle?", SwingConstants.CENTER);
		sizedFont = font.deriveFont(Font.BOLD, 16f);
		difficultyLabel.setFont(sizedFont);
		difficultyLabel.setForeground(Color.red);
		difficultyLabel.setBounds(frameBoundX / 2 -200, (frameBoundY - 520)/2 + 150, 400, 40);
		difficultyPanel.add(difficultyLabel);
		
		//Adding first difficulty button - easy: 1
		JButton firstDifficulty = new JButton("+ Fundamentals of Geomety");
		//Setting visuals of first button
		firstDifficulty.setBorderPainted(false);
		firstDifficulty.setFocusPainted(false);
		firstDifficulty.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 17f);
		firstDifficulty.setFont(sizedFont);		
		
		firstDifficulty.setBounds(frameBoundX / 2 - 250, i / 2 + 200, 500, 100);
		firstDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				//'1' is sent to the Character selection for difficulty level information
				 new CharacterSelection(f, frameBoundX, frameBoundY,1);
			}
		});
		
		//Adding second difficulty - medium: 2
		JButton secondDifficulty = new JButton("++ Complex Geometry ");
		//Setting Visuals of Second Button
		secondDifficulty.setBorderPainted(false);
		secondDifficulty.setFocusPainted(false);
		secondDifficulty.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 17f);
		secondDifficulty.setFont(sizedFont);
		
		secondDifficulty.setBounds(frameBoundX / 2 - 250, i / 2 + 310, 500, 100);
		secondDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				new CharacterSelection(f, frameBoundX, frameBoundY,2);
			}
		});
		
		//Adding third Difficulty - hard: 3
		JButton thirdDifficulty = new JButton("+++ Quantum Geometry ");
		//Setting Visuals of Third Button
		thirdDifficulty.setBorderPainted(false);
		thirdDifficulty.setFocusPainted(false);
		thirdDifficulty.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 17f);
		thirdDifficulty.setFont(sizedFont);	
		
		thirdDifficulty.setBounds(frameBoundX / 2 - 250, i / 2 + 420, 500, 100);
		thirdDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				new CharacterSelection(f, frameBoundX, frameBoundY,3);
			}
		});
		
		//Background Icon
		String img = "background_revision.jpg";
		ImageIcon imgIc = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(imgIc);
		bgLabel.setBounds(0, 0, frameBoundX, frameBoundY);
		
		difficultyPanel.add(firstDifficulty);
		difficultyPanel.add(secondDifficulty);
		difficultyPanel.add(thirdDifficulty);
		difficultyPanel.add(bgLabel);

		
		f.add(difficultyPanel);
		f.revalidate();
		f.setVisible(true);

	}

}
