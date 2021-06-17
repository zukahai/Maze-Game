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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Class.Figure;
import Class.Point;


public class Game extends JFrame implements KeyListener{
	int M = 15;
	int N = 29;
	int II = 100;
	int JJ = 100;
	String data[] = {"1111111111111111111111111111111111111111111111011011100001111110000010111010111111101010101000100111111010111011101011111110001100011010111111101011010000000111111010110101111101111110120001010001011111101111111101010111111010000300000001111110111111111110011111100000000010100111111111111110101001111110101010101011011111101010101010110111111000000000000001111111111111111111111111111111111111111111",
					"1111111111111111111111111111111111111111111000000001100001111110111111011011011111101000110000010111111010101111011101111110101011111110011111101010111111101111111010101000001011111110101012111010111111101110111310100111111000001100101011111111111111011010111111100000000100101111111011111111011011111110110000000000011111101101111111010111111000000001000001111111111111111111111111111111111111111111",
					""};
	
	Color cl[] = {Color.lightGray, Color.black, Color.red, Color.green};
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
		int k = 1;
		String dt = data[k];
		int index = 0;
		
		for (int i = 0; i < 200; i += 10)
			for (int j = 0; j < 200; j += 10) {
				int value = dt.charAt(index++) - 48;
				if (value == 2) {
					II = i + 4;
					JJ = j + 5;
					F = new Figure(II, JJ);
					II -= 8;
					JJ -= 14;
				}
				for (int I = i; I < i + 10; I++)
					for (int J = j; J < j + 10; J++)
						a[I][J] = value;
			}
	}
	
	public void update() {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				bt[i][j].setBackground(cl[a[i + II][j + JJ]]);
		Vector vv = F.getVec();
//		F.getTt().Display();
		for (int i = 0; i < vv.size(); i++) {
			Point p = (Point) vv.elementAt(i);
			bt[p.getX() - II][p.getY() - JJ].setBackground(Color.blue);
			if (a[p.getX()][p.getY()] == 3) {
				JOptionPane.showMessageDialog(null, "Win");
				System.exit(0);
			}
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

	public boolean check(int X, int Y, int t) {
		if (t == 1) {
			if (a[X - 2][Y] == 1 || a[X - 1][Y - 1] == 1 || a[X - 1][Y + 1] == 1)
				return false;
		}
		if (t == 2) {
			if (a[X][Y + 2] == 1 || a[X - 1][Y + 1] == 1 || a[X + 1][Y + 1] == 1)
				return false;
		}
		if (t == 3) {
			if (a[X + 2][Y] == 1 || a[X + 1][Y - 1] == 1 || a[X + 1][Y + 1] == 1)
				return false;
		}
		if (t == 4) {
			if (a[X][Y - 2] == 1 || a[X - 1][Y - 1] == 1 || a[X + 1][Y - 1] == 1)
				return false;
		}
		
		return true;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Point Ptemp = F.getTt();
		int X = Ptemp.getX();
		int Y = Ptemp.getY();
		if (e.getKeyCode() == e.VK_UP) {
			if (F.getDirection() != 1) {
				F = F.next(1);
			} else if (check(X, Y, 1)) {
				II --;
				F = F.next(1);
			}
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			if (F.getDirection() != 3) {
				F = F.next(3);
			} else if (check(X, Y, 3)) {
				II ++;
				F = F.next(3);
			}
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			if (F.getDirection() != 4) {
				F = F.next(4);
			} else if (check(X, Y, 4)) {
				JJ --;
				F = F.next(4);
			}
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			if (F.getDirection() != 2) {
				F = F.next(2);
			} else if (check(X, Y, 2)) {
				JJ ++;
				F = F.next(2);
			}
			update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
