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
import java.util.LinkedList;
import java.util.Queue;
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
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) 
				bt[i][j].setBackground(cl[a[i][j]]);
		int MMM = -1111;
		Queue<Point> st = new LinkedList<>();
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
		
		st.add(p);
		boolean kt = false;
		while (!st.isEmpty() && !kt) {
			p = st.poll();
			int X = (int) p.getX();
			int Y = (int) p.getY();
			System.out.println(b[X][Y]);
			for (int i = X - 1; i <= X + 1; i++)
				for (int j = Y - 1; j <= Y + 1; j++)
					if (i >= 0 && i < M && j >= 0 && j < N &&Math.abs(i - X) + Math.abs(j - Y) == 1) {
						if (b[i][j] == MMM && (a[i][j] == 0 || a[i][j] == 3)) {
							b[i][j] = b[X][Y] + 1;
							st.add(new Point(i, j));
						}
							
					}
			
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				if (b[i][j] != MMM && a[i][j] == 3 && b[i][j] < b[(int) d.getX()][(int) d.getY()]) {
					d = new Point(i, j);
				}
		int X = (int) d.getX();
		int Y = (int) d.getY();
		if (b[X][Y] == MMM) {
			return;
		}
		System.out.println(X + " " + Y + " " + b[X][Y]);
		while (b[X][Y] > 1) {
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
		new Maze("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111131111111111111111111111111111101111111111111111111111111111101111111111111111111111113111101111111111111111111111110111101111111111111111111111110111101111111111111111111111112000001111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
	}
}
