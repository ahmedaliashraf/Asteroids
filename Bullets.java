package asteroids;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Bullets extends Polygon implements KeyListener{
	private boolean isFiring = false;

	public Bullets(Point[] inShape, Point inPosition, double inRotation) 
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

	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
		case KeyEvent.VK_F:
			isFiring = true;
			break;
		}
		
	}
	public void keyReleased(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
		case KeyEvent.VK_F:
			isFiring = false;
			break;
		}
	}
	public void keyTyped(KeyEvent e) 
	{
		
	}
	public void move()
	{
		double x = super.position.x;
		double y = super.position.y;

		x+= Math.cos(Math.toRadians(rotation-90))*3;
		y+= Math.sin(Math.toRadians(rotation-90))*3;
		super.position = new Point(x, y);
		
	}
	public boolean shots()
	{
		return isFiring;
	}

}
