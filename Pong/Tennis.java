package Pong;

import java.applet.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;





public class Tennis extends Applet implements Runnable, KeyListener{
	//Fields
	
	//Global Variable 
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    //Creates an object of the other classes
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    Board b;
    boolean gameStarted;
    //Double Buffering
    Graphics gfx;
    Image img;
    //starts off variables 
	public void init() {
		this.resize(WIDTH, HEIGHT);
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		b1 = new Ball();
		p2 = new AIPaddle(2, b1);
		b = new Board();
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
		
	}
	//paints background, and object from other classes on the screen
	public void paint(Graphics g) {
	
		
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		if (b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.GREEN);
			gfx.setFont(new Font("Apple Casual", Font.PLAIN, 70)); 
			
			gfx.drawString(" Game     						 over", 110, 150);
			gfx.drawString("You  							  Lose", 190, 350);
		}
			else {
				
			
		p1.draw(gfx);
		b1.draw(gfx);
		p2.draw(gfx);
		
		
	}
		if(!gameStarted) {
			gfx.setColor(Color.WHITE);
			gfx.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			gfx.drawString("UNBEATABLE PONG", 150, 80);
			
			gfx.drawString("Press The Enter Key To Start", 120, 150);
		}
		
		else {
			b.draw(gfx);
		}
		
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g) {
 		paint(g);
	}


	public void run() {
		for(;;) {
			if(gameStarted) {
			p1.move();
			p2.move();
			b1.move();
			b1.checkPaddleCollision(p1, p2);
			}
			
			repaint();
				
			
	
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		

	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
	}
	else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
		p1.setDownAccel(true);
	} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		gameStarted = true;
	}
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
	}
		
	
	
	public void keyTyped(KeyEvent e) {
	}
		
	}
	

