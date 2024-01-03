import java.awt.Graphics;

public class Rectangle extends GeometricObject {
	private Point begin;
	private Point end;
	
	public Rectangle() {
		begin = new Point(0, 0);
		end = new Point(1, 1);
	}
	
	public Rectangle(Point b, Point e) {
		begin = new Point(b.getX(), b.getY());
		end = new Point(e.getX(), e.getY());
	}
	
	/**
	 * Draws a rectangle
	 * @param g graphic context
	 */
	@Override
	public void draw(Graphics g) {
		int x = (int)smallestX();
		int y = (int)smallestY();
		int w = (int)width();
		int h = (int)height();
		
		g.setColor(getInteriorColor());
		g.fillRect(x, y, w, h);
		
		g.setColor(getBoundaryColor());
		g.drawRect(x, y, w, h);
	}
	
	/**
	 * Returns the first point defining this rectangle.
	 * 
	 * @return a point (corner) defining this rectangle.
	 */
	public Point getBegin() {
		return new Point(begin.getX(), begin.getY());
	}
	
	/**
	 * Returns the second defining this rectangle.
	 * 
	 * @return a point (corner) defining this rectangle.
	 */
	public Point getEnd() {
		return new Point(end.getX(), end.getY());
	}
	
	/**
	 * Determines the greatest x.
	 * 
	 * @return the greatest x.
	 */
	public double greatestX() {
		return begin.getX() > end.getX() ? begin.getX() : end.getX();
	}
	
	public double greatestY() {
		return begin.getY() > end.getY() ? begin.getY() : end.getY();
	}
	
	public double height() {
		return Math.abs(begin.getY()-end.getY());
	}
	
	public void setBegin(Point p) {
		begin = new Point(p.getX(), p.getY());
	}
	
	public void setEnd(Point p) {
		end = new Point(p.getX(), p.getY());
	}
	
	public double smallestX() {
		return begin.getX() < end.getX() ? begin.getX() : end.getX();
	}
	
	public double smallestY() {
		return begin.getY() < end.getY() ? begin.getY() : end.getY();
	}
	
	@Override
	public String toString() {
		String str = "Rectangle " + super.toString() + "\n";
		str += begin + "\n" + end;
		return str;
	}
	
	public void translate(Vector v) {
		begin.translate(v);
		end.translate(v);
	}
	
	public double width() {
		return Math.abs(begin.getX() - end.getX());
	}
	
	public Rectangle sum(Rectangle r) {
		Vector x1 = new Vector(smallestX(), smallestY());
		Vector y1 = new Vector(greatestX(), greatestY());
		
		Vector x2 = new Vector(r.smallestX(), r.smallestY());
		Vector y2 = new Vector(r.greatestX(), r.greatestY());
		
		Vector sumX = x1.add(x2);
		Vector sumY = y1.add(y2);
		
		
		return new Rectangle(new Point(sumX.getX(), sumX.getY()), new Point(sumY.getX(), sumY.getY()));
	}
	
	/**
	 * This code has been added to this class.
	 * Returns the all the edges of the current rectangle object.
	 * @return
	 */
	public LineSegment[] rectangleSegments() {
		LineSegment[] rectangleEdges = new LineSegment[4];
		
		rectangleEdges[0] = new LineSegment(new Point(begin.getX(), begin.getY()), new Point(end.getX(), begin.getY()));
		rectangleEdges[1] = new LineSegment(new Point(end.getX(), begin.getY()), new Point(end.getX(), end.getY()));
		rectangleEdges[2] = new LineSegment(new Point(end.getX(), end.getY()), new Point(begin.getX(), end.getY()));
		rectangleEdges[3] = new LineSegment(new Point(begin.getX(), end.getY()), new Point(begin.getX(), begin.getY()));
		
		return rectangleEdges;
	}
	
	/**
	 * Checks if a polygon passed as a parameter intersects this rectangle
	 * using a brute force technique.
	 * @param pol
	 * @return
	 */
	public boolean intersects(Polygon pol) {
		LineSegment[] polygonEdges = pol.getEdges();
		LineSegment[] rectangleEdges = rectangleSegments();
		
		for(int i = 0; i < polygonEdges.length; i++) {
			for(int j = 0; j < rectangleEdges.length; j++) {
				if(polygonEdges[i].intersectsLineSegment(rectangleEdges[j])) {
					return true;
				}
			}
		}
		
		return false;
	}
}
