package gamepackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
	private ArrayList<User> userList = new ArrayList<>();
	private File userDataFile;
	private BufferedWriter bw;

	public Menu() {
		// TODO Auto-generated constructor stub

		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Open the data file here
		String currentDir = System.getProperty("user.home");
		
		userDataFile = new File(currentDir + "//GeotrixUserData.txt");
		if (!userDataFile.exists()) {
			try {
				userDataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					userDataFile));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				String[] seperator = line.split("-");
				userList.add(new User( seperator[0], seperator[1], Integer.parseInt(seperator[2]) ));
			}
			bufferedReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Login Panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);

		// Background Icon
		String img = "background_revision.jpg";
		ImageIcon i = new ImageIcon(this.getClass().getResource(img));

		// Backgroud Label
		JLabel bgLabel = new JLabel(i);

		// Font
		InputStream is = Menu.class.getResourceAsStream("Cheap Potatoes.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch
			// block
			e1.printStackTrace();
		}

		// Logo Label
		JLabel logoLabel = new JLabel("GEOTRIX", SwingConstants.CENTER);
		sizedFont = font.deriveFont(68f);
		logoLabel.setFont(sizedFont);

		// username
		JTextField usernameTextField = new JTextField("Username", 15);
		usernameTextField.setOpaque(false);
		sizedFont = font.deriveFont(26f);
		usernameTextField.setFont(sizedFont);
		usernameTextField.addMouseListener(new MouseAdapter() {
			boolean firstClick = true;

			@Override
			public void mouseClicked(MouseEvent e) {
				if (firstClick) {
					usernameTextField.setText("");
					firstClick = false;
				}
			}
		});

		// password
		JPasswordField passwordTextField = new JPasswordField("123456", 15);
		passwordTextField.setOpaque(false);
		sizedFont = font.deriveFont(35f);
		passwordTextField.setFont(sizedFont);
		passwordTextField.setEchoChar('*');
		passwordTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordTextField.setText("");
			}
		});

		// sign in
		JButton signInButton = new JButton("Sign Up");
		signInButton.setBorderPainted(false);
		signInButton.setFocusPainted(false);
		signInButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 26f);
		signInButton.setFont(sizedFont);
		signInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User newUser = null;

				for (User u : userList) {

					if (u.username.compareTo(usernameTextField.getText()) == 0) {
						newUser = u;
						break;
					}		
				}
				if (newUser != null) {
					usernameTextField.setText("Failed!");
					
				} else {
					newUser = new User( usernameTextField.getText(), new String(passwordTextField.getPassword()) );
					userList.add(newUser);
					try {
						bw = new BufferedWriter(new FileWriter(userDataFile, true));
						bw.write( newUser.username + "-" + newUser.password + "-" + newUser.score);
						bw.newLine();
						bw.flush();
						bw.close();
						usernameTextField.setText("Succesful!");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		// log in
		JButton logInButton = new JButton("Log In");
		logInButton.setBorderPainted(false);
		logInButton.setFocusPainted(false);
		logInButton.setContentAreaFilled(false);
		sizedFont = font.deriveFont(Font.BOLD, 26f);
		logInButton.setFont(sizedFont);
		// when login button clicked
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User newUser = null;
				for (User u : userList) {

					if (u.username.compareTo(usernameTextField.getText()) == 0
							&& u.password.compareTo(new String(passwordTextField.getPassword())) == 0) {
						newUser = u;
						break;
					}
					
				}
				if (newUser == null) {
					usernameTextField.setText("Failed!");
				} else {	
					// remove login panel
					loginPanel.setVisible(false);
					f.remove(loginPanel);
					// call main menu panel
					new MainMenu(f, frameBoundX, frameBoundY);
				}

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
				// resolution selection dialog box
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
					frameBoundY = 768;
				} else if (Objects.equals(selectionValues[3], selection)) {
					frameBoundX = 1280;
					frameBoundY = 720;
				} else if (Objects.equals(selectionValues[4], selection)) {
					frameBoundX = 960;
					frameBoundY = 540;
				}
				resetLoginUI(frameBoundX, frameBoundY, f, loginPanel,
						logoLabel,
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

		// reset the UI to set the bounds of the components
		resetLoginUI(frameBoundX, frameBoundY, f, loginPanel, logoLabel, usernameTextField,
				passwordTextField, signInButton, logInButton,
				settingsButton, bgLabel);

		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.setVisible(true);

		
		f.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				try {

					bw = new BufferedWriter(new FileWriter(
							userDataFile));

					for (User user : userList) {
						bw.write(user.username + "-" + user.password
								+ "-"
								+ user.score);
						bw.newLine();
						bw.flush();
						bw = new BufferedWriter(new FileWriter(
								userDataFile,
								true));
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if (bw != null) {
						try {
							bw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}

			}
		});
	}

	// resets the login UI
	private void resetLoginUI(int x, int y, JFrame f, JPanel loginPanel, JLabel logoLabel,
			JTextField usernameTextField, JTextField passwordTextField,
			JButton signInButton, JButton logInButton, JButton settingsButton,
			JLabel bgLabel) {

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