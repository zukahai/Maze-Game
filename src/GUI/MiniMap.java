package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Class.Point;

public class MiniMap extends JFrame{
	int M = 30;
	int N = 30;
	JButton bt[][] = new JButton[M][N];
	Color cl[] = {Color.GRAY, Color.black, Color.red, Color.green, Color.LIGHT_GRAY, Color.white};
	Container cn;
	public MiniMap() {
		// TODO Auto-generated constructor stub
		super("Mini Map");
		cn = init();
	}

	public Container init() {
		Container cn = this.getContentPane();
		
		JPanel pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				bt[i][j] = new JButton();
				bt[i][j].setBackground(Color.black);
				bt[i][j].setActionCommand(i * N + j + "");
				bt[i][j].setBorder(null);
				pn.add(bt[i][j]);
			}
		
		cn.add(pn);
		
		this.setVisible(true);
		this.setSize(400, 400);
//		this.setLocationRelativeTo(null);
//		setResizable(false);
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public void Update(int a[][], Point p) {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				bt[i][j].setBackground(cl[a[i][j]]);
		bt[p.getX() / 10][p.getY() / 10].setBackground(Color.blue);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MiniMap();
	}

}
