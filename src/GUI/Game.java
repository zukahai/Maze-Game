package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Class.Figure;
import Class.Point;


public class Game extends JFrame implements KeyListener{
	int M = 15;
	int N = 29;
	int II = 500;
	int JJ = 500;
	String data[] = {"1111111111111111111111111111111111111111111111011011100001111110000010111010111111101010101000100111111010111011101011111110001100011010111111101011010000000111111010110101111101111110120001010001011111101111111101010111111010000300000001111110111111111110011111100000000010100111111111111110101001111110101010101011011111101010101010110111111000000000000001111111111111111111111111111111111111111111",
					""};
	Container cn;
	Figure F = new Figure(108, 114);
	JButton bt[][] = new JButton[M][N];
	int a[][] = new int[200][200];
	public Game() {
		super("Tank Game - HaiZuka");
		ininMaxtrix();
		cn = init();
	}
	
	public void ininMaxtrix() {
		int k = 0;
		
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
					bt[i][j].setBackground(Color.cyan);
				else
					bt[i][j].setBackground(Color.black);
		Vector vv = F.getVec();
//		F.getTt().Display();
		for (int i = 0; i < vv.size(); i++) {
			Point p = (Point) vv.elementAt(i);
			bt[p.getX() - II][p.getY() - JJ].setBackground(Color.green);
		}
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setBorder(null);
				pn.add(bt[i][j]);
			}
		
		cn.add(pn);
		
		this.setVisible(true);
		this.setSize(1300, 700);
		this.setLocationRelativeTo(null);
		setResizable(false);
		update();
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
			if (F.getDirection() == 1)
				II --;
			F = F.next(1);
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			if (F.getDirection() == 3)
				II++;
			F = F.next(3);
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			if (F.getDirection() == 4)
				JJ--;
			F = F.next(4);
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			if (F.getDirection() == 2)
				JJ++;
			F = F.next(2);
			update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
