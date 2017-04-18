package gamepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class CharacterSelection extends JPanel {

	private static final long serialVersionUID = 1L;

	public CharacterSelection(JFrame f, int frameBoundX, int frameBoundY) {
		
		setBounds(0,0,frameBoundX, frameBoundY);

		JButton charSelectButton = new JButton("Select Character");
		charSelectButton.setBounds(10, 10, 200, 100);
		// when charSelectButton clicked
		charSelectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.getContentPane().removeAll();
				JPanel gamePanel = new Game(f, frameBoundX, frameBoundY);
				gamePanel.setLayout(null);
				f.add(gamePanel);
			}
		});

		add(charSelectButton);
	}

}
