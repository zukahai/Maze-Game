package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game extends JFrame implements KeyListener{
	int M = 15;
	int N = 29;
	int II = 500;
	int JJ = 500;
	Container cn;
	JButton bt[][] = new JButton[M][N];
	int a[][] = new int[1000][1000];
	public Game() {
		super("Tank Game - HaiZuka");
		cn = init();
		ininMaxtrix();
	}
	
	public void ininMaxtrix() {
		for (int i = 6; i < 1000; i += 13)
			for (int j = 6; j < 1000; j += 12) {
				for (int I = i - 5; I < i; I++)
					for (int J = j - 5; J < j; J++)
						a[I][J] = 1;
			}
	}
	
	public void update() {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				if (a[i + II][j + JJ] == 1)
					bt[i][j].setBackground(Color.black);
				else
					bt[i][j].setBackground(Color.white);
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				pn.add(bt[i][j]);
			}
		
		cn.add(pn);
		
		this.setVisible(true);
		this.setSize(1300, 700);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == e.VK_UP) {
			II --;
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			II++;
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			JJ--;
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			JJ++;
			update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
