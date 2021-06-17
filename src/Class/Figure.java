package Class;

import java.util.Vector;
import Class.Point;

public class Figure {
	Point tt;
	Vector<Point> Vec = new Vector();
	int direction = 1;
	
	int xx[] = {-1, 0, 1, 0};
	int yy[] = {0, 1, 0, -1};
	public Figure() {
		// TODO Auto-generated constructor stub
		this.tt = new Point();
		Vec = new Vector();
		direction = 1;
	}
	
	public Figure(int X, int Y) {
		this.tt = new Point(X, Y);
		Vec = initVec(direction);
	}
	
	public Vector<Point> initVec(int direction) {
		Vector<Point> v = new Vector();
		if (direction == 1) {
			for (int i = (int) tt.getX(); i <= tt.getX() + 1; i++)
				for (int j = (int) (tt.getY() - 1); j <= tt.getY() + 1; j++)
					v.add(new Point(i, j));
			v.add(new Point((int)tt.getX() - 1, (int)tt.getY()));
		} else if (direction == 3) {
			for (int i = (int)tt.getX() - 1; i <= tt.getX(); i++)
				for (int j = (int)tt.getY() - 1; j <= tt.getY() + 1; j++)
					v.add(new Point(i, j));
			v.add(new Point((int)tt.getX() + 1, (int)tt.getY()));
		} else if (direction == 2) {
			for (int i = (int)tt.getX() - 1; i <= tt.getX() + 1; i++)
				for (int j = (int)tt.getY() - 1; j <= tt.getY(); j++)
					v.add(new Point(i, j));
			v.add(new Point((int)tt.getX(), (int)tt.getY() + 1));
		} else if (direction == 4) {
			for (int i = (int)tt.getX() - 1; i <= tt.getX() + 1; i++)
				for (int j = (int)tt.getY(); j <= tt.getY() + 1; j++)
					v.add(new Point(i, j));
			v.add(new Point((int)tt.getX(), (int)tt.getY() - 1));
		}
		return v;
	}
	
	public Figure next(int N) {
		Figure F = new Figure(tt.getX(), tt.getY());
		if (N == this.direction) {
//			System.out.println("OK");
			F.setTt(new Point(tt.getX() + xx[N - 1], tt.getY() + yy[N - 1]));
		}
		F.setDirection(N);
		F.setVec(F.initVec(N));
		return F;
	}
	
	public Point getTt() {
		return tt;
	}

	public void setTt(Point tt) {
		this.tt = tt;
	}

	public Vector<Point> getVec() {
		return Vec;
	}

	public void setVec(Vector<Point> vec) {
		Vec = vec;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
