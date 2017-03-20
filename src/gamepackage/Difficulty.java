package gamepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Difficulty extends JPanel {

	private static final long serialVersionUID = 1L;

	public Difficulty(JFrame f, int frameBoundX, int frameBoundY) {

		setBounds(0,0,frameBoundX, frameBoundY);
		
		JButton difficultyButton = new JButton("Select Difficulty");
		difficultyButton.setBounds(10, 10, 200, 100);
		// when difficultyButton clicked
		difficultyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				JPanel charSelectPanel = new CharacterSelection(f, frameBoundX, frameBoundY);
				charSelectPanel.setLayout(null);
				f.add(charSelectPanel);
			}
		});

		add(difficultyButton);
	}

}
