package asteroids;

/*
CLASS: Asteroids
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of the program,
      it is the control center.
Original code by Dan Leyzberg and Art Simon
*/
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



class Asteroids extends Game{
	static int counter = 0;
	public Ship myShip; private boolean collide = false; private int lives = 10; private int points = 0;
	private int numBullets = 15;
	private Asteroid myAsteroid, myAsteroid2, myAsteroid3, myAsteroid4, myAsteroid5; Point[] bulletPoints;
	Asteroid[] astArr = null; long timeHit=System.currentTimeMillis();; 
	AudioInputStream aIs; Clip cSound; Bullets myBullets;
  
  public Asteroids() {
    super("Asteroids!",800,600);
    this.setFocusable(true);
	this.requestFocus();
	
	Point[] shipPoints = new Point[7];
	shipPoints[0] = new Point(13,-6);
	shipPoints[1] = new Point(30,-15);
	shipPoints[2] = new Point(50,-6);
	shipPoints[3] = new Point(30,-50);
	shipPoints[4] = new Point(30,-15);
	shipPoints[5] = new Point(13,-6);
	shipPoints[6] = new Point(30,-50);
	
	Point shipLocation = new Point(400,300);
	myShip = new Ship(shipPoints, shipLocation, 0);
	this.addKeyListener(myShip);
	
	Point[] asteroidPoints = new Point[7];
	asteroidPoints[0] = new Point(13,-6);
	asteroidPoints[1] = new Point(30,-30);
	asteroidPoints[2] = new Point(45,-50);
	asteroidPoints[3] = new Point(55,-30);
	asteroidPoints[4] = new Point(75,-6);
	asteroidPoints[5] = new Point(45, 0);
	asteroidPoints[6] = new Point(13,-6);
	
	Point asteroidLocation = new Point(0, 400);
	Point asteroidLocation2 = new Point(800, 100);
	Point asteroidLocation3 = new Point(400, 600);
	Point asteroidLocation4 = new Point(250, 0);
	Point asteroidLocation5 = new Point(650, 600);
	myAsteroid = new Asteroid(asteroidPoints, asteroidLocation, 0);
	myAsteroid2 = new Asteroid(asteroidPoints, asteroidLocation2, 0);
	myAsteroid3 = new Asteroid(asteroidPoints, asteroidLocation3, 0);
	myAsteroid4 = new Asteroid(asteroidPoints, asteroidLocation4, 0);
	myAsteroid5 = new Asteroid(asteroidPoints, asteroidLocation5, 0);
	astArr =new Asteroid[]{myAsteroid, myAsteroid2, myAsteroid3,myAsteroid4,myAsteroid5};
	bulletPoints = new Point[5];
	bulletPoints[0] = new Point(10,-10);
	bulletPoints[1] = new Point(15,-15);
	bulletPoints[2] = new Point(20,-10);
	bulletPoints[3] = new Point(15,-5);
	bulletPoints[4] = new Point(10,-10);

	Point out = new Point(-10,-10);
	myBullets = new Bullets(bulletPoints, out, 0);
	this.addKeyListener(myBullets);
  }
  
  	public void player()
  	{
  		try {
  	        aIs = AudioSystem.getAudioInputStream(new File("CollisionSound.wav").getAbsoluteFile());
  	        Clip cSound = AudioSystem.getClip();
  	        cSound.open(aIs);
  	        cSound.start();
  	    } catch(Exception ex) {
  	        System.out.println("Error with playing sound.");
  	        ex.printStackTrace();
  	    }
  	}
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
   
    	brush.setColor(Color.red);
    	if (counter == 0)
    	{
    		brush.drawString("Press W to move forward, A to move left, D to move right and F to fire. Get Ready!", 250, 200);
    		try {
        		TimeUnit.SECONDS.sleep(5);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
    		counter++;
    	}
    	myShip.paint(brush);
    	myShip.move();
    	brush.setColor(Color.cyan);
    	for (Asteroid a: astArr)
    	{
    		a.paint(brush);
    		a.move();
    	}   	
    	collide = myShip.checkCollision(astArr);
    	brush.drawString("Lives: " + lives, 10, 10);
    	brush.drawString("Points: " + points, 400, 10);
    	brush.drawString("Bullets Left: " + numBullets, 700, 10);
    	if(collide==true && System.currentTimeMillis()-timeHit>2000)
    	{
    		timeHit = System.currentTimeMillis();
    		player();
    		lives--;
    		if(lives <= 0)
    		{
    			
    			lives = 0;
    			brush.drawString("Game Over!", 400, 300);
    			try {
            		TimeUnit.SECONDS.sleep(3);
            	} catch(InterruptedException ex) {
            	    Thread.currentThread().interrupt();
            	}
    			System.exit(0);
    		}
    	}
    	this.addKeyListener(myBullets);
    	if(myBullets.shots()==true && numBullets>0)
    	{
    		myBullets = new Bullets(bulletPoints, myShip.position, myShip.rotation);
    		numBullets--;
    	}
    	myBullets.paint(brush);
    	myBullets.move();
    	if(myBullets.checkCollision(astArr)==true)
    	{
    		points+= 100;
    	}
    	if(numBullets<=0)
    	{
    		brush.drawString("You are out of Bullets", 400, 300);
    		try {
        		TimeUnit.SECONDS.sleep(3);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
			System.exit(0);
    	}
  }
  
	public static void main (String[] args) {
   		Asteroids a = new Asteroids();
		a.repaint();
  }
}
