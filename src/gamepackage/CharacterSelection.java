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

class CharacterSelection extends JPanel {

	private static final long serialVersionUID = 1L;
	private Font font, sizedFont = null;

	public CharacterSelection(JFrame f, int frameBoundX, int frameBoundY,int difficulty) {
		
		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}
		
		//Main Panel of Character  Selection Section created here
		JPanel charPanel = new JPanel();
		charPanel.setLayout(null);
		setBounds(0,0,frameBoundX, frameBoundY);
		
		//Logo
		JLabel logoLabel = new JLabel("GEOTRIX", SwingConstants.CENTER);
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);
		logoLabel.setBounds(frameBoundX / 2 - 200, (frameBoundY-520)/ 2, 400, 190);
		charPanel.add(logoLabel);
		
		JButton charSelectButton = new JButton("Select Character");
		charSelectButton.setBounds(10, 10, 200, 100);
		// when charSelectButton clicked
		charSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				f.setVisible(false);
				f.dispose();
				new Game(frameBoundX, frameBoundY);
			}
		});
		
		
		//Background Icon
		String img = "background_revision.jpg";
		ImageIcon imgIc = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(imgIc);
		bgLabel.setBounds(0, 0, frameBoundX, frameBoundY);

		charPanel.add(charSelectButton);
		charPanel.add(logoLabel);
		charPanel.add(bgLabel);
		
		f.add(charPanel);
		f.revalidate();
		f.setVisible(true);
	}

}
