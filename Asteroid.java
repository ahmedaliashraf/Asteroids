package asteroids;

import java.awt.Graphics;
import java.util.Random;

public class Asteroid extends Polygon{
	public Asteroid(Point[] inShape, Point inPosition, double inRotation) 
	{
		super(inShape, inPosition, inRotation);
	}
	
	public void paint(Graphics brush)
	{
		Point[] shape = getPoints();
		int[] xPoints = new int[shape.length];
		int[] yPoints = new int[shape.length];
		for (int i = 0; i < shape.length; i++)
		{
			xPoints[i] = (int)(shape[i].x);
			yPoints[i] = (int)shape[i].y;
 		}
		brush.drawPolygon(xPoints, yPoints, shape.length); 
	}
	
	public void move()
	{
		Random ranGen = new Random();
		double x = super.position.x;
		double y = super.position.y;
		
		if(x > 800)
		{
			x = 0;
		}
		if(x<0)
		{
			x = 800;
		}
		if(y < 0)
		{
			y = 600;
		}
		if(y > 600)
		{
			y = 0;
		}
		int speed = ranGen.nextInt(6);
		int rot = ranGen.nextInt(10);
		x+= speed;
		y+= speed;
		super.position = new Point(x, y);
		super.rotate(rot);
	}

}
