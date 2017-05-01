package gamepackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private int frameBoundX = 960;// frame x axis
	private int frameBoundY = 540;// frame y axis
	private Font sizedFont = null;
	private Font font;

	public Menu() {
		// TODO Auto-generated constructor stub
		
		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Login Panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);

		//Background Icon
		String img = "ArkaPlan.jpg";
		ImageIcon i = new ImageIcon(this.getClass().getResource(img));
		
		//Backgroud Label
		JLabel bgLabel = new JLabel(i);
		
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

		// username
		JTextField usernameTextField = new JTextField("Username", 15);
		usernameTextField.setOpaque(false);
		sizedFont = font.deriveFont(30f);
		usernameTextField.setFont(sizedFont);

		// password
		JTextField passwordTextField = new JPasswordField("123456", 15);
		passwordTextField.setOpaque(false);
		sizedFont = font.deriveFont(30f);
		passwordTextField.setFont(sizedFont);

		// sign in
		JButton signInButton = new JButton("Sign In");
		signInButton.setBorderPainted(false);
		signInButton.setFocusPainted(false);
		signInButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		signInButton.setFont(sizedFont);

		// log in
		JButton logInButton = new JButton("Log In");
		logInButton.setBorderPainted(false);
		logInButton.setFocusPainted(false);
		logInButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 30f);
		logInButton.setFont(sizedFont);
		// when login button clicked
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove login panel
				loginPanel.setVisible(false);
				f.remove(loginPanel);
				// call main menu panel
				new MainMenu(f, frameBoundX, frameBoundY);
			}
		});

		// settings button
		JButton settingsButton = new JButton("S");
		settingsButton.setBorderPainted(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 25f);
		settingsButton.setFont(sizedFont);
		settingsButton.setForeground(Color.RED);
		// when settings button clicked
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//resolution selection dialog box
				JDialog.setDefaultLookAndFeelDecorated(true);
				Object[] selectionValues = { "1920x1080", "1600x900",
						"1366x768", "1280x720",
						"960x540" };
				Object selection = JOptionPane.showInputDialog(null,
						"Choose resolution",
						"Settings",
						JOptionPane.QUESTION_MESSAGE,
						null, selectionValues,
						selectionValues[4]);

				if (Objects.equals(selectionValues[0], selection)) {
					frameBoundX = 1920;
					frameBoundY = 1080;
				} else if (Objects.equals(selectionValues[1], selection)) {
					frameBoundX = 1600;
					frameBoundY = 900;
				} else if (Objects.equals(selectionValues[2], selection)) {
					frameBoundX = 1366;
					frameBoundY= 768;
				} else if (Objects.equals(selectionValues[3], selection)) {
					frameBoundX = 1280;
					frameBoundY = 720;
				} else if (Objects.equals(selectionValues[4], selection)) {
					frameBoundX = 960;
					frameBoundY = 540;
				}
				resetLoginUI(frameBoundX, frameBoundY, f, loginPanel, logoLabel,
						usernameTextField,
						passwordTextField,
						signInButton,
						logInButton,
						settingsButton,
						bgLabel);

			}
		});

		// adding labels and textfields to login panel
		loginPanel.add(logoLabel);
		loginPanel.add(usernameTextField);
		loginPanel.add(passwordTextField);
		loginPanel.add(signInButton);
		loginPanel.add(logInButton);
		loginPanel.add(settingsButton);
		loginPanel.add(bgLabel);
		// adding login panel to main frame
		f.add(loginPanel);
		
		//reset the UI to set the bounds of the components
		resetLoginUI(frameBoundX, frameBoundY, f, loginPanel, logoLabel, usernameTextField,
				passwordTextField, signInButton, logInButton,
				settingsButton, bgLabel);

		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.setVisible(true);

	}

	//resets the login UI
	private void resetLoginUI(int x, int y, JFrame f, JPanel loginPanel, JLabel logoLabel,
			JTextField usernameTextField, JTextField passwordTextField,
			JButton signInButton, JButton logInButton, JButton settingsButton, JLabel bgLabel) {

		int i = y - 520;

		f.remove(loginPanel);
		f.setSize(x, y);
		f.revalidate();

		loginPanel.setBounds(0, 0, x, y);
		logoLabel.setBounds(x / 2 - 200, i / 2, 400, 190);
		usernameTextField.setBounds(x / 2 - 200, i / 2 + 200, 400, 100);
		passwordTextField.setBounds(x / 2 - 200, i / 2 + 310, 400, 100);
		signInButton.setBounds(x / 2 - 200, i / 2 + 420, 195, 100);
		logInButton.setBounds(x / 2 + 5, i / 2 + 420, 195, 100);
		settingsButton.setBounds(x - 70, 10, 60, 60);
		bgLabel.setBounds(0, 0, x, y);

		f.add(loginPanel);
		f.revalidate();
		f.repaint();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();
	}

}