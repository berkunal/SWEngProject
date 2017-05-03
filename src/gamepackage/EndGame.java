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

class EndGame extends JPanel{

	private static final long serialVersionUID = 1L;
	private Font font, sizedFont = null;
	
	public EndGame(JFrame f, int frameBoundX, int frameBoundY, boolean win) {
		
		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}
		
		//Main Panel of End Game Section created here
		JPanel endPanel = new JPanel();
		endPanel.setLayout(null);
		setBounds(0,0,frameBoundX, frameBoundY);
		
		//Logo
		JLabel logoLabel;
		if (win){
			logoLabel = new JLabel("You Won!", SwingConstants.CENTER);
		} else {
			logoLabel = new JLabel("You Lost!", SwingConstants.CENTER);
		}
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);
		logoLabel.setBounds(frameBoundX / 2 - 300, (frameBoundY-520)/ 2, 600, 190);
		endPanel.add(logoLabel);
		
		//Back Button
		JButton backButton = new JButton("Back to Main Menu");
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		backButton.setFont(sizedFont);
		backButton.setBounds(frameBoundX / 2 - 300, (frameBoundY-520)/ 2 + 200, 600, 200);
		// when backButton clicked
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove panel
				endPanel.setVisible(false);
				f.remove(endPanel);
				new MainMenu(f, frameBoundX, frameBoundY);
			}
		});
		
		
		//Background Icon
		String img = "background_revision.jpg";
		ImageIcon imgIc = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(imgIc);
		bgLabel.setBounds(0, 0, frameBoundX, frameBoundY);

		endPanel.add(backButton);
		endPanel.add(logoLabel);
		endPanel.add(bgLabel);
		
		f.add(endPanel);
		f.revalidate();
		f.setVisible(true);
		
	}

}
