package gamepackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private int frameBoundX = 960;// frame x axis
	private int frameBoundY = 540;// frame y axis

	public Menu() {
		// TODO Auto-generated constructor stub
		
		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Login Panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);

		// Logo Label
		JLabel logoLabel = new JLabel();
		logoLabel.setLayout(null);
		logoLabel.setBackground(Color.black);
		logoLabel.setOpaque(true);

		// username
		JTextField usernameTextField = new JTextField("Username", 15);

		// password
		JTextField passwordTextField = new JPasswordField("Passwd", 15);
		

		// sign in
		JButton signInButton = new JButton("Sign In");		
		// when signin button clicked
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove login panel
				loginPanel.setVisible(false);
				f.remove(loginPanel);
				//dssf
				 
				 String username = usernameTextField.getText();
				 String password = passwordTextField.getText();
				 
				 String checkname;
				 String checkpass;
				 int flag = 0;
				 
				 //check if data is already exist or not
				 try(BufferedReader br = new BufferedReader(new FileReader("File.txt"))){
					 
					 while ( ((checkname = br.readLine()) != null)  && (flag == 0)) {
					       
						 
						 if((username.equals(checkname))){
							 JOptionPane.showMessageDialog(null, "Username already exist.");
							 flag = 1;
							 checkpass = br.readLine();							 
								
								 
						}else if (br.readLine() == null)
							 {
								 
								 break;
								 
							 }
						 
					    }
					 
					 	// we have to call Menu() here but this way menu should be implement to ActionListener;
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				 
				 
				 if(flag==0){
								
					 try(FileWriter fw = new FileWriter("File.txt", true);
							 BufferedWriter bw = new BufferedWriter(fw);
							 PrintWriter pw = new PrintWriter(bw))
							 {
					        	//pw.println("");
					        	pw.println(username);						    
					        	pw.println(password);
						    
							 } catch (IOException e1) {}
						    
					 
				 
					 JOptionPane.showMessageDialog(null, "You are registered succesfully.");
				     }
				 
				
				
				
			}
		});


		// log in
		JButton logInButton = new JButton("Log In");
		// when login button clicked
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// remove login panel
				loginPanel.setVisible(false);
				f.remove(loginPanel);
				//dssf
				 
				 String username = usernameTextField.getText();
				 String password = passwordTextField.getText();
				 
				 String SavedUser;
				 String SavedPass;
				 
				 
				 try(BufferedReader br = new BufferedReader(new FileReader("File.txt"))){
										 
					 while ((SavedUser = br.readLine()) != null) {
					       
						 
						 if((username.equals(SavedUser))){
							 SavedPass = br.readLine();
							 if(( password.equals(SavedPass))){
								 
								// call game panel
									JPanel difficultyPanel = new Difficulty(f, frameBoundX, frameBoundY);
									difficultyPanel.setLayout(null);
									f.add(difficultyPanel);
									break;
								 
							 }else if(br.readLine() == null)
							 {
								 JOptionPane.showMessageDialog(null, "Wrong password"); 
								 break;
								 
								 
							 }	 
							 
								
								 
						}else if (br.readLine() == null)
							 {
								 JOptionPane.showMessageDialog(null, "Wrong username");
								 break;
								 
							 }
						 
					    }
					 
					 	// we have to call Menu() here but this way menu should be implement to ActionListener;
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 
				 
				
				
				
			}
		});

		// settings button
		JButton settingsButton = new JButton("S");
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
						settingsButton);

			}
		});

		// adding labels and textfields to login panel
		loginPanel.add(logoLabel);
		loginPanel.add(usernameTextField);
		loginPanel.add(passwordTextField);
		loginPanel.add(signInButton);
		loginPanel.add(logInButton);
		loginPanel.add(settingsButton);
		// adding login panel to main frame
		f.add(loginPanel);

		//reset the UI to set the bounds of the components
		resetLoginUI(frameBoundX, frameBoundY, f, loginPanel, logoLabel, usernameTextField,
				passwordTextField, signInButton, logInButton,
				settingsButton);

		f.setSize(frameBoundX, frameBoundY);
		f.setResizable(false);
		f.setVisible(true);

	}

	//resets the login UI
	private void resetLoginUI(int x, int y, JFrame f, JPanel loginPanel, JLabel logoLabel,
			JTextField usernameTextField, JTextField passwordTextField,
			JButton signInButton, JButton logInButton, JButton settingsButton) {

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
		settingsButton.setBounds(x - 60, 10, 50, 50);

		f.add(loginPanel);
		f.revalidate();
		f.repaint();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();
	}
}