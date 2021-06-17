package Class;

public class Point {
	int x, y;
	public Point() {
		// TODO Auto-generated constructor stub
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int X, int Y) {
		// TODO Auto-generated constructor stub
		this.x = X;
		this.y = Y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void Display() {
		System.out.println(this.x + " " + this.y);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
