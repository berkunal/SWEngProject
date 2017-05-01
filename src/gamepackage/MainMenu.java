package gamepackage;

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

class MainMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private Font font, sizedFont = null;

	public MainMenu(JFrame f, int frameBoundX, int frameBoundY) {
		//Contents: Logo, new game, options, instructions, exit buttons
		
		//Main Panel of Main Menu created here
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(null);
		
		//Background Icon
		String img = "ArkaPlan.jpg";
		ImageIcon icon = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(icon);
		
		//Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Logo Label
		JLabel logoLabel = new JLabel("GEOTRIX", SwingConstants.CENTER);
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);
		
		//New Game Button
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBorderPainted(false);
		newGameButton.setFocusPainted(false);
		newGameButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		newGameButton.setFont(sizedFont);
		//on click configure game
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove main menu panel
				mainMenuPanel.setVisible(false);
				f.remove(mainMenuPanel);
				// call difficulty panel
				new Difficulty(f, frameBoundX, frameBoundY);
			}
		});
		
		
		//Options Button
		JButton optionsButton = new JButton("Options");
		optionsButton.setBorderPainted(false);
		optionsButton.setFocusPainted(false);
		optionsButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		optionsButton.setFont(sizedFont);
		
		//Instructions Button 
		JButton instrButton = new JButton("Instructions");
		instrButton.setBorderPainted(false);
		instrButton.setFocusPainted(false);
		instrButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		instrButton.setFont(sizedFont);
		//on click instructions game
		instrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove main menu panel
				mainMenuPanel.setVisible(false);
				f.remove(mainMenuPanel);
				// call instructions panel
				new Instructions(f, frameBoundX, frameBoundY);
			}
		});
		
		//Exit Button
		JButton exitButton = new JButton("Exit");
		exitButton .setBorderPainted(false);
		exitButton .setFocusPainted(false);
		exitButton .setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		exitButton .setFont(sizedFont);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//add components to main panel
		mainMenuPanel.add(logoLabel);
		mainMenuPanel.add(newGameButton);
		mainMenuPanel.add(optionsButton);
		mainMenuPanel.add(instrButton);
		mainMenuPanel.add(exitButton);
		mainMenuPanel.add(bgLabel);
		
		f.add(mainMenuPanel);
		
		resetUIMainMenu(frameBoundX, frameBoundY, f, mainMenuPanel, bgLabel, logoLabel,
				newGameButton, optionsButton, instrButton, exitButton);
		
		f.setVisible(true);
	}

	private void resetUIMainMenu(int x, int y, JFrame f, JPanel mainMenuPanel, JLabel bgLabel,
			JLabel logoLabel, JButton newGameButton, JButton optionsButton,
			JButton instrButton, JButton exitButton) {
		
		int i = y - 520;
		
		f.remove(mainMenuPanel);
		f.setSize(x, y);
		f.revalidate();
		
		mainMenuPanel.setBounds(0, 0, x, y);
		bgLabel.setBounds(0, 0, x, y);
		logoLabel.setBounds(x / 2 - 200, i / 2, 400, 190);
		newGameButton.setBounds(x / 2 - 200, i / 2 + 200, 400, 70);
		optionsButton.setBounds(x / 2 - 200, i / 2 + 280, 400, 70);
		instrButton.setBounds(x / 2 - 200, i / 2 + 360, 400, 70);
		exitButton.setBounds(x / 2 - 200, i / 2 + 440, 400, 70);		
		
		f.add(mainMenuPanel);
		f.revalidate();
		f.repaint();

	}

}
