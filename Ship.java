package asteroids;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.KeyEventDispatcher;

public class Ship extends Polygon implements KeyListener{
	private boolean isForward = false, isLeft = false, isRight = false;

	public Ship(Point[] inShape, Point inPosition, double inRotation) 
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
		case KeyEvent.VK_W:
			isForward = true;
			break;
		case KeyEvent.VK_A:
			isLeft = true;
			break;
		case KeyEvent.VK_D:
			isRight = true;
			break;
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
		case KeyEvent.VK_W:
			isForward = false;
			break;
		case KeyEvent.VK_A:
			isLeft = false;
			break;
		case KeyEvent.VK_D:
			isRight = false;
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
		if(isForward == true)
		{
			x+= Math.cos(Math.toRadians(rotation-90))*4;
			y+= Math.sin(Math.toRadians(rotation-90))*4;
			super.position = new Point(x, y);
		}
		if(isRight == true)
		{

			super.rotate(2);
		}
		if(isLeft == true)
		{
			super.rotate(-2);
		}
	}

}
