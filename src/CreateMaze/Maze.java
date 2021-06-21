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
	int M = 30, N = 30;
	JButton bt[][] = new JButton[100][100];
	int a[][] = new int [M][N];
	Container cn;
	Color cl[] = {Color.lightGray, Color.black, Color.red, Color.green};
	
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
		
		cn.add(pn);
		cn.addKeyListener(this);
		
		this.setVisible(true);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Maze("111111111111111111111111111111100000000000000000000000000001101111111111111111111111111101101000000000000000000000000101101011111011111111111111110101101010000000000000000000010101101010111111111111111111010101101010100000000000000001010101101010101111111111011101010101101010101000000000000101010101101010101011111111110101010101101010101010000000010101010101101010101010111011010101010101101010101010100001010101010001101010101010101101010100010101101010101010101101010101010101101010101010130001010101010101101010101010111111010101010101101010101010000000010101010101101010101011101111110101010101101010101000000000000101010101101010101111111111111101010101101010100000000000000001010101101010111111111111111111010101101010000000000000000000010101101011111011111111111111110101101000000000000000000000000101101111111111111111111111111101100000000000002000000000000001111111111111111111111111111111");
	}

}
