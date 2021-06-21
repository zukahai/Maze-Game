package CreateMaze;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Maze extends JFrame implements ActionListener, KeyListener{
	int key = 0;
	int M = 30, N = 30;
	JButton bt[][] = new JButton[100][100];
	int a[][] = new int [M][N];
	Container cn;
	Color cl[] = {Color.lightGray, Color.black, Color.red, Color.green};
	JButton type_bt, Result_bt, bfs_bt;
	
	public Maze() {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = 1;
		cn = init();
	}
	
	public Maze(String s) {
		int index = 0;
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				a[i][j] = s.charAt(index++) - 48;
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setBackground(cl[a[i][j]]);
				bt[i][j].setActionCommand(i * N + j + "");
				pn.add(bt[i][j]);
			}
		
		JPanel pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		
		type_bt = new JButton("    ");
		type_bt.setActionCommand("type_bt");
		type_bt.addActionListener(this);
		type_bt.setBackground(cl[key]);
		
		Result_bt = new JButton("Result");
		Result_bt.addActionListener(this);
		Result_bt.setBackground(Color.black);
		Result_bt.setForeground(Color.white);
		
		bfs_bt = new JButton("BFS");
		bfs_bt.addActionListener(this);
		bfs_bt.setBackground(Color.black);
		bfs_bt.setForeground(Color.white);
		
		pn2.add(type_bt);
		pn2.add(Result_bt);
		pn2.add(bfs_bt);
		
		
		cn.add(pn);
		cn.add(pn2, "South");
		cn.addKeyListener(this);
		
		this.setVisible(true);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}

	public void BFS() {
		int MMM = 111111;
		Stack<Point> st = new Stack<>();
		int x[] = new int [M + 1];
		int y[] = new int [N + 1];
		int b[][] = new int [M + 1][N + 1];
		Point p = new Point();
		Point d = new Point();
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				b[i][j] = MMM;
				if (a[i][j] == 2) {
					p = new Point(i, j);
					b[i][j] = 0;
				}
				if (a[i][j] == 3) {
					d = new Point(i, j);
				}
			}
		
		st.push(p);
		boolean kt = false;
		while (!st.empty() && !kt) {
			p = st.pop();
			int X = (int) p.getX();
			int Y = (int) p.getY();
			System.out.println(b[X][Y]);
			for (int i = X - 1; i <= X + 1; i++)
				for (int j = Y - 1; j <= Y + 1; j++)
					if (i >= 0 && i < M && j >= 0 && j < N &&Math.abs(i - X) + Math.abs(j - Y) == 1) {
						if (b[i][j] == MMM && (a[i][j] == 0 || a[i][j] == 3)) {
							b[i][j] = b[X][Y] + 1;
							st.push(new Point(i, j));
							if (a[i][j] == 3) {
								kt = true;
							}
						}
							
					}
			
		}
		
		int X = (int) d.getX();
		int Y = (int) d.getY();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}
		if (b[X][Y] == MMM) {
			return;
		}
//		System.out.println(X + " " + Y + " " + b[X][Y]);
		while (a[X][Y] != 2) {
			System.out.println(X + " " + Y);
			for (int i = X - 1; i <= X + 1; i++)
				for (int j = Y - 1; j <= Y + 1; j++)
					if (Math.abs(i - X) + Math.abs(j - Y) == 1 && b[i][j] - b[X][Y] == -1) {
						if (a[i][j] != 2) {
							bt[i][j].setBackground(Color.orange);
						}
						X = i;
						Y = j;
					}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(type_bt.getActionCommand())) {
			key ++;
			if (key == 4)
				key = 0;
			type_bt.setBackground(cl[key]);
		} else if (e.getActionCommand().equals(Result_bt.getActionCommand())) {
			print();
			new Message(print2());
		} else if (e.getActionCommand().equals(bfs_bt.getActionCommand())) {
			BFS();
		} else {
			int K = Integer.parseInt(e.getActionCommand());
			int J = K % N;
			int I = K / N;
			a[I][J] = key;
			if (key < 4)
				bt[I][J].setBackground(cl[key]);
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void print() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(a[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public String print2() {
		String s = "";
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				s = s + a[i][j];
		}
		return s;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		key = e.getKeyCode() - 48;
		if (key == 5) {
//			print();
//			System.out.println(print2());
			BFS();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Maze("111111111111111111111111111111111111111111111111111111111111110000110000110100000010000011110110000110000101111010111011110111111111101101111010111011110000101000001100001110001111110111101011111101100000101111110100001010111101111111100011110101001000110001111111111011110101011010110111111110000011110111011011110111000000111111110000001011000100011110001111110111101011010111011111100011110100001011211111011111101011110111111011110000011111111011110000111000110111110000011011110110111110110000100111010011110010100000011110111111011011111010101111011100111000011011110010101000001101111111000011110110101111111101111111111011110110100000000001100000011011110010111111111111101111000011111010111000000011101111111111111010000011111010000000000011110011111000111110111111110111110111011110111100110000000111110000000110031111111101110111111111111111111111111111111111111111111111111111111111111111");
	}

}
