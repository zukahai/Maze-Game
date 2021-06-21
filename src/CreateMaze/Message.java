package CreateMaze;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Message extends JFrame{
	Container cn;
	TextArea tf;
	public Message(String matrix) {
		// TODO Auto-generated constructor stub
		super("Message");
		cn = init(matrix);
	}
	
	public Container init(String matrix) {
		Container cn = this.getContentPane();
		
//		JPanel pn = new JPanel();
//		pn.setLayout(new GridLayout(M, N));
		tf = new TextArea(matrix);
		cn.add(tf);
		
		this.setVisible(true);
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
//		this.setLocation(390, 0);
		setResizable(false);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		
		return cn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Message("Hello");
	}

}
