
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;


public class Line extends GeometricObject
{

    private double a;   //x coefficient
    private double b;   //y coefficient
    private double c;   //constant term

    /**
     * Instantiates a line object as -x + y = 0 or y = x.
     */
    public Line()
    {
        a = -1;
        b = 1;
        c = 0;
    }

    /**
     * Instantiates a line object as ax + by + c = 0 or y = (-a/b) x + (-c/b).
     */
    public Line(double a, double b, double c) throws IllegalArgumentException
    {
        //a=0 and b=0 does not define a line
        if (a==0 && b==0) throw new IllegalArgumentException();
        else
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    /**
     * Constructor that takes in two Point parameters and generates the constants.
     * @param p
     * @param q
     * @throws IllegalArgumentException
     */
    
    public Line(Point p, Point q) throws IllegalArgumentException {
        if (p.getX() == q.getX() && p.getY() == q.getY()) throw new IllegalArgumentException();
        else {
        	if(p.getX() == q.getX()) {	// Checks if its a vertical line
        		a = 1;
        		b = 0;
        		c = p.getX() * (-1);
        	}
        	else {	// All other types of lines.
        		a = (q.getY()-p.getY()) / (q.getX()-p.getX());
        		b = -1;
        		c = p.getY() - a * p.getX();
        	}
        }
	}
    /**
     * Calculates the distance of a point from a line.
     * @param p
     * @return
     */
    public double distanceFromPoint(Point p) {
    	double absVal = Math.abs(a*p.getX() + b*p.getY()+c);
    	double sqrt = Math.sqrt(a*a + b*b);
    	double distance = absVal/sqrt;
    	return distance;
    }
    /**
     * Returns -1, 0, 1, depending on the relative
     * position of the given point and this line.
     * 
     * Line is not vertical:
     * 	-1: if point is below the line
     * 	 0: if point is in the line
     * 	 1: if point is over the line
     * 
     * Line is vertical:
     * 	-1: if point is left of the line
     * 	 0: if point is in the line
     * 	 1: if point is right of the line
     * 
     * @param p
     * @return
     */
    public int pointRelativePosition(Point p) {
    	
    	int relativePosition = 0;
    	
    	if(!isVertical()) {
    		double yCoord = getY(p.getX());
    		if(yCoord > p.getY()) relativePosition = -1;
    		else {
    			if(yCoord== p.getY()) relativePosition = 0;
    			else relativePosition = 1;
    		}
    	}
    	else {
    		double xCoord = -c/a;
    		if(xCoord > p.getX()) relativePosition = -1;
    		else {
    			if(xCoord == p.getX()) relativePosition = 0;
    			else relativePosition = 1;
    		}
    	}
    	return relativePosition;
    }
    
    /**
     * Draws this line.
     * 
     * @param g graphic context 
     */
    @Override
    public void draw(Graphics g)
    {
        //finds the window size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double maxX = screenSize.width;
        double maxY = screenSize.height;

        //obtain two points on this line, (x1, y1) and (x2, y2)
        double x1, y1, x2, y2;
        
        if (!isVertical()) //line is not vertical
        {
            x1 = 0.0;
            y1 = getY(x1);
            x2 = maxX;
            y2 = getY(x2);
        }
        else //line is vertical
        {
            y1 = 0.0;
            x1 = getX(y1);
            y2 = maxY;
            x2 = getX(y2);
        }

        g.setColor(getBoundaryColor());
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }    
    
    /**
     * Returns the coefficient of the X variable in the equation of this line
     * object.
     * 
     * @return coefficient of the X variable in the equation of this line object
     */
    public double getA()
    {
        return a;
    }

    /**
     * Returns the coefficient of the Y variable in the equation of this line
     * object.
     * 
     * @return coefficient of the Y variable in the equation of this line object
     */
    public double getB()
    {
        return b;
    }

    /**
     * Returns the independent term of the equation of this line object.
     * 
     * @return independent term of the equation of this line object
     */
    public double getC()
    {
        return c;
    }

    /**
     * Given x, calculates the value of y.
     *
     * @param x x value
     * @return value of y
     * @throws IllegalStateException 
     */
    public double getY(double x) throws IllegalStateException
    {
        if (b != 0)
            return (-a / b) * x + (-c / b);
        else
            throw new IllegalStateException("Vertical line cannot be used here.");
    }

    /**
     * Given y, calculates the value of x.
     *
     * @param y y value
     * @return value of x
     * @throws IllegalStateException 
     */
    public double getX(double y) throws IllegalStateException
    {
        if (a != 0)
            return (-b / a) * y + (-c / a);
        else throw new IllegalStateException("Horizontal line cannot be used here.");
    }
    
    /**
     * Determines if this line is vertical.
     * 
     * @return true if this line is vertical, false otherwise
     */
    boolean isVertical()
    {
        return b == 0;
    }
        
    /**
     * Sets the coefficient of the X variable in the equation of this line
     * object.
     * 
     * @param a value of the coefficient
     * 
     * @throws IllegalStateException if a=0 and b=0
     */
    public void setA(double a) throws IllegalStateException
    {
        //a=0 and b=0 does not define a line
        if (a==0 && this.b==0) throw new IllegalStateException();
        else
        {
            this.a = a;
        }
    }

    /**
     * Sets the coefficient of the Y variable in the equation of this line
     * object
     * 
     * @param b value of the coefficient
     * 
     * @throws IllegalStateException if a=0 and b=0
     */
    public void setB(double b) throws IllegalStateException
    {
        //a=0 and b=0 does not define a line
        if (this.a==0 && b==0) throw new IllegalStateException();
        else
        {
            this.b = b;
        }
    }

    /**
     * Sets the independent term of the equation of this line object.
     * 
     * @param c independent term
     */
    public void setC(double c)
    {
        this.c = c;
    }

    /**
     * Sets this line object as ax + by + c = 0 or y = (-a/b) x + (-c/b).
     */
    public void setLine(double a, double b, double c) throws IllegalStateException
    {
        //a=0 and b=0 does not define a line
        if (a==0 && b==0) throw new IllegalStateException();
        else
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    
    /**
     * Constructs a String description of this line.
     * 
     * @return String containing a description of this line
     */
    @Override
    public String toString()
    {
        String str = "LINE " + a + " " + b + " " + c + " " + super.toString();

        return str;
    }
    
    /**
     * Checks if the given line segments intersects the line.
     * @param ls - LineSegment Object
     * @return - a boolean value
     */
    public boolean intersectsLineSegment(LineSegment ls) {
    	if(pointRelativePosition(ls.getBegin()) == pointRelativePosition(ls.getEnd())
    			&& pointRelativePosition(ls.getEnd()) != 0
    			&& pointRelativePosition(ls.getBegin()) != 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    /**
     * Checks if the polygon entered is intersected by this line at any given position.
     * @param pol
     * @return - boolean value, false if it does not get intersected, true if it does.
     */
    public boolean intersectsPolygon(Polygon pol) {
    	LineSegment[] edges = pol.getEdges();
    	
    	for(int i = 0; i < edges.length; i++) {
    		if(intersectsLineSegment(edges[i])) return true;
    	}
    	
    	return false;
    }
}
