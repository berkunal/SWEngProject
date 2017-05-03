package gamepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Difficulty extends JPanel {

	private static final long serialVersionUID = 1L;

	public Difficulty(JFrame f, int frameBoundX, int frameBoundY) {
		int i = frameBoundY - 520;

		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(null);
		setBounds(0,0,frameBoundX, frameBoundY);
		//Adding title to frame
		JLabel titleLabel = new JLabel();
		titleLabel.setLayout(null);
		titleLabel.setText("DIFFICULTY SELECTION");
		titleLabel.setOpaque(true);
		titleLabel.setBounds(frameBoundX/2-100, i / 2, 400, 190);
		difficultyPanel.add(titleLabel);
		
		
		//Adding first difficulty button
		JButton firstDifficulty = new JButton("First Difficulty ");
		
		firstDifficulty.setBounds(frameBoundX / 2 - 200, i / 2 + 200, 400, 100);
		firstDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				//'1' is sent to the Character selection for difficulty level information
				JPanel charSelectPanel = new CharacterSelection(f, frameBoundX, frameBoundY,1);
				charSelectPanel.setLayout(null);
				f.add(charSelectPanel);
			}
		});

		difficultyPanel.add(firstDifficulty);
		//Adding second difficulty
		JButton secondDifficulty = new JButton("Second Difficulty ");
		secondDifficulty.setBounds(frameBoundX / 2 - 200, i / 2 + 310, 400, 100);
		secondDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				JPanel charSelectPanel = new CharacterSelection(f, frameBoundX, frameBoundY,2);
				charSelectPanel.setLayout(null);
				f.add(charSelectPanel);
			}
		});
		difficultyPanel.add(secondDifficulty);
		//Adding third Difficulty
		JButton thirdDifficulty = new JButton("Third Difficulty ");
		thirdDifficulty.setBounds(frameBoundX / 2 - 200, i / 2 + 420, 400, 100);
		thirdDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				JPanel charSelectPanel = new CharacterSelection(f, frameBoundX, frameBoundY,3);
				charSelectPanel.setLayout(null);
				f.add(charSelectPanel);
			}
		});
		difficultyPanel.add(thirdDifficulty);
		

		f.add(difficultyPanel);
		f.setVisible(true);

	}

}