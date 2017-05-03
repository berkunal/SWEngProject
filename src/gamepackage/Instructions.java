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

class Instructions extends JPanel{

	private static final long serialVersionUID = 1L;
	private Font font, sizedFont = null;
	
	public Instructions(JFrame f, int frameBoundX, int frameBoundY) {
		
		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}
		
		//Main Panel of Instructions Section created here
		JPanel instrPanel = new JPanel();
		instrPanel.setLayout(null);
		setBounds(0,0,frameBoundX, frameBoundY);
		
		//Logo
		JLabel logoLabel = new JLabel("GEOTRIX", SwingConstants.CENTER);
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);
		logoLabel.setBounds(frameBoundX / 2 - 200, (frameBoundY-520)/ 2, 400, 190);
		
		JButton backButton = new JButton("Back");
		backButton.setBorderPainted(false);
		backButton.setFocusPainted(false);
		backButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		backButton.setFont(sizedFont);
		backButton.setForeground(Color.RED);
		backButton.setBounds(10, 10, 200, 100);
		// when backButton clicked
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove panel
				instrPanel.setVisible(false);
				f.remove(instrPanel);
				new MainMenu(f, frameBoundX, frameBoundY);
			}
		});
		
		//Logo
		JLabel instructionLabel = new JLabel("Arrow keys + Space button", SwingConstants.CENTER);
		sizedFont = font.deriveFont(40f);
		instructionLabel.setFont(sizedFont);
		instructionLabel.setBounds(25, frameBoundY/2 - 100, frameBoundX - 50, 200);
		instrPanel.add(instructionLabel);

		//Background Icon
		String img = "background_revision.jpg";
		ImageIcon imgIc = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(imgIc);
		bgLabel.setBounds(0, 0, frameBoundX, frameBoundY);

		instrPanel.add(backButton);
		instrPanel.add(logoLabel);
		instrPanel.add(bgLabel);
		
		f.add(instrPanel);
		f.revalidate();
		f.setVisible(true);
		
		
	}

}
