package CreateMaze;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	JButton type_bt, Result_bt;
	
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
		
		pn2.add(type_bt);
		pn2.add(Result_bt);
		
		
		cn.add(pn);
		cn.add(pn2, "South");
		cn.addKeyListener(this);
		
		this.setVisible(true);
		this.setSize(700, 800);
		this.setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
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
			print();
			System.out.println(print2());
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Maze("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111112111111111111111111111111111112111111111111111111111111111111111111111111111111111111010110110001111111111111111111010101011011111111111111111111000100011011111111111111111111010101011011111111111111111111010101010001111111111111111111111111111111111111111111111111111113111111111111111111111111111113111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
	}

}
