package gamepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuItem item1 = new JMenuItem("Credits");
	private JMenuItem item2 = new JMenuItem("Exit");

	public Menu() {
		// TODO Auto-generated constructor stub
		JFrame f = new JFrame("Geotrix");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("File");
		JMenu menu2 = new JMenu("Help");
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		
		menu2.add(item1);
		menu1.add(item2);
		
		menubar.add(menu1);
		menubar.add(menu2);
		
		f.setJMenuBar(menubar);
		
		f.setSize(360, 480);
		f.setResizable(false);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Menu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == item1 ) {
			JOptionPane.showMessageDialog(null, "Berk Ünal\n20140702009");
		}
		if ( e.getSource() == item2 ) {
			System.exit(0);
		}
	}

}
