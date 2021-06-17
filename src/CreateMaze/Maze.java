package CreateMaze;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Maze extends JFrame implements ActionListener, KeyListener{
	int key = 0;
	int M = 20, N = 20;
	JButton bt[][] = new JButton[100][100];
	int a[][] = new int [M][N];
	Container cn;
	Color cl[] = {Color.white, Color.black, Color.GRAY, Color.green};
	public Maze() {
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
				bt[i][j].setBackground(Color.black);
				bt[i][j].setActionCommand(i * N + j + "");
				a[i][j] = 1;
				pn.add(bt[i][j]);
			}
		
		cn.add(pn);
		cn.addKeyListener(this);
		
		this.setVisible(true);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Maze();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int K = Integer.parseInt(e.getActionCommand());
		int J = K % N;
		int I = K / N;
		a[I][J] = key;
		if (key < 4)
			bt[I][J].setBackground(cl[key]);
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
	
	public void print2() {
		String s = "";
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				s = s + a[i][j];
		}
		System.out.println(s);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		key = e.getKeyCode() - 48;
		if (key == 5) {
			print();
			print2();
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
