package gamepackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Menu extends JFrame{

	private static final long serialVersionUID = 1L;
	private int frameBoundX = 960;//frame x axis
	private int frameBoundY = 540;//frame y axis

	public Menu() {
		// TODO Auto-generated constructor stub
		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Login Panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setBounds(0, 0, frameBoundX, frameBoundY);
		
		//Logo Label
		JLabel logoLabel = new JLabel();
		logoLabel.setLayout(null);
		int i = frameBoundY - 520;
		logoLabel.setBounds(frameBoundX/2 - 200, i/2, 400, 190);
		logoLabel.setBackground(Color.black);
		logoLabel.setOpaque(true);
		
		//username
		JTextField usernameTextField = new JTextField("Username", 15);
		usernameTextField.setBounds(frameBoundX/2 - 200, i/2 + 200, 400, 100);
		
		//password
		JTextField passwordTextField = new JPasswordField("Passwd", 15);
		passwordTextField.setBounds(frameBoundX/2 - 200, i/2 + 310, 400, 100);
		
		//sign in
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(frameBoundX/2 - 200, i/2 + 420, 195, 100);
		
		//log in
		JButton logInButton = new JButton("Log In");
		logInButton.setBounds(frameBoundX/2 + 5, i/2 + 420, 195, 100);
		//when login button clicked
		logInButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove login panel
				loginPanel.setVisible(false);
				f.remove(loginPanel);
				//call game panel
				JPanel gamePanel = new Game();
				f.add(gamePanel);
				
			}
		});

		//adding labels and textfields to login panel
		loginPanel.add(logoLabel);
		loginPanel.add(usernameTextField);
		loginPanel.add(passwordTextField);
		loginPanel.add(signInButton);
		loginPanel.add(logInButton);
		//adding login panle to main frame
		f.add(loginPanel);
		
		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();
	}

}
