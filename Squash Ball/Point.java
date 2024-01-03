import java.awt.Graphics;


public class Point extends GeometricObject {
	private double x;
	private double y;
	
	public Point() {
		x = y = 0;
	}
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "POINT (" + x + ", " + y + ") " + super.toString();
	}
	
	public void draw(Graphics g) {
		int radius = 5;
		g.setColor(getInteriorColor());
		g.fillOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
		g.setColor(getBoundaryColor());
		g.drawOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
	}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow((int)(getX()-p.getX()), 2) + Math.pow((int)(getY()-p.getY()), 2));
	}
	
	public void translate(Vector v) {
		x += v.getX();
		y += v.getY();
	}
	
	public void rotate(double angle) {
		double angleRadians = Math.toRadians(angle);
		
		double sine = Math.sin(angleRadians);
		double cosine = Math.cos(angleRadians);
		
		double x1 = x*cosine - y*sine;
		double y1 = x*sine + y*cosine;
		
		x = x1;
		y = y1;
	}
}
