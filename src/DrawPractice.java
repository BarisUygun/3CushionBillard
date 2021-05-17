import java.awt.*;
import java.awt.color.CMMException;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;


public class DrawPractice extends JPanel implements MouseListener,MouseMotionListener,ActionListener{

	double ball_1X;
	double ball_1Y;
	double ball_2X;
	double ball_2Y;
	double ball_mainX;
	double ball_mainY;
	
	
	JLabel lblPower;
	JLabel lblscore;
	JLabel lblHighScore;
	
	Timer t;
	
	int ballRadius=50;
	Ellipse2D ball_1;
	Ellipse2D ball_2;
	Ellipse2D ball_main;
	Ellipse2D MovingBall;
	Ellipse2D PreviousMovingBall;
	boolean collided=false;
	
	Line2D cue;
	double cueBallSideX=0;
	double cueBallSideY=0;
	double cueMouseSideX=0;
	double cueMouseSideY=0;
	
	double cueSlopeX=0;
	double cueSlopeY=0;
	
	double velocityX=0;
    double velocityY=0;
    
    double PreviousvelocityX=0;
    double PreviousvelocityY=0;
    
	Random rnd=new Random();
	boolean line_drawing=false;
	boolean first_time_playing=true;
	
	
	boolean hitTheWall=false;
	boolean hitThewallPrevious=false;
	
	boolean Zone1=false;
	boolean Zone2=false;
	boolean Zone3=false;
	boolean Zone4=false;
	
	boolean ZonePrevious1=false;
	boolean ZonePrevious2=false;
	boolean ZonePrevious3=false;
	boolean ZonePrevious4=false;
	
	double previouscueBallSideX=0;
	double previouscueBallSideY=0;
	double previouscueMouseSideX=0;
	double previouscueMouseSideY=0;
	double middlePointX=0;
	double middlePointY=0;
	
	double movingballLocationX=0;
	double movingballLocationY=0;
	
	double power=0;
	int cueSpeed;
	Color cueColor;
	
	int score=0;
	int highScore=0;
	int wallHitCounter=0;
	boolean wallHitPass=false;
	boolean hitball_1=false;
	boolean hitball_2=false;
	Ellipse2D scoreManagementBall;
	JFrame jf;
	
	
	
	DrawPractice(JFrame jfr ,int Power,Color col,JLabel lblTime,JLabel pow,JLabel scor,JLabel hghscore){
		jf=jfr;
		
			
		velocityX=0;
		velocityY=0;
		PreviousvelocityX=0;
		PreviousvelocityY=0;
	
		lblHighScore=hghscore;
		lblPower=pow;
		lblscore=scor;
		lblscore.setText("SCORE:0");
		lblHighScore.setText("HIGH SCORE:0");
		lblPower.setText("POWER:0");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		scoreManagementBall=ball_main;
		ball_1=new Ellipse2D.Double(200,200,ballRadius,ballRadius);
        ball_2=new Ellipse2D.Double(400,200,ballRadius,ballRadius);
        ball_main=new Ellipse2D.Double(300,200,ballRadius,ballRadius);
        cue=new Line2D.Double(cueBallSideX, cueBallSideY, cueMouseSideX, cueMouseSideY);
        MovingBall=ball_main;
        
        cueSpeed=Power;
        cueColor=col;
       
        
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g.create();
		g2d.setStroke(new BasicStroke(5));
		
		if(line_drawing==true){
			g2d.setPaint(cueColor);
			cue.setLine(cueBallSideX, cueBallSideY, cueMouseSideX, cueMouseSideY);
			g2d.draw(cue);
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setPaint(Color.YELLOW);
	    g2d.draw(ball_1);
	    g2d.fill(ball_1);
		
	    g2d.setPaint(Color.RED);
	    g2d.draw(ball_2);
	    g2d.fill(ball_2);
	    
	    g2d.setPaint(Color.white);
	    g2d.draw(ball_main);
	    g2d.fill(ball_main);
	    
		
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		power=Math.sqrt(Math.pow(cueBallSideX-MovingBall.getCenterX(),2)+Math.pow(cueBallSideY-MovingBall.getCenterY(),2));
	        lblPower.setText("POWER:+"+power);
		
		if(cueMouseSideX>MovingBall.getCenterX() && cueMouseSideY<MovingBall.getCenterY()){
		    if(e.getX()>cueMouseSideX){	
		       cueBallSideX=MovingBall.getCenterX()+((e.getX()-cueMouseSideX)/5);
		       cueBallSideY=MovingBall.getCenterY()+(((e.getX()-cueMouseSideX)*(cueSlopeY/cueSlopeX))/5);
		       repaint();
		    }
		    
		    
		}
	    else if(cueMouseSideX<MovingBall.getCenterX() && cueMouseSideY<MovingBall.getCenterY()){
		    if(e.getX()<cueMouseSideX){
		    	 cueBallSideX=MovingBall.getCenterX()+((e.getX()-cueMouseSideX)/5);
			     cueBallSideY=MovingBall.getCenterY()+(((e.getX()-cueMouseSideX)*(cueSlopeY/cueSlopeX))/5);
			     repaint();
		    }
		    
		    
		}
	 
	    else if(cueMouseSideX<MovingBall.getCenterX() && cueMouseSideY>MovingBall.getCenterY()){
		    if(e.getX()<cueMouseSideX){
		    	cueBallSideX=MovingBall.getCenterX()+((e.getX()-cueMouseSideX)/5);
			    cueBallSideY=MovingBall.getCenterY()+(((e.getX()-cueMouseSideX)*(cueSlopeY/cueSlopeX))/5);
			    repaint();
		    }
		    
		    
		}
	 
	    else if(cueMouseSideX>MovingBall.getCenterX() && cueMouseSideY>MovingBall.getCenterY()){
		    if(e.getX()>cueMouseSideX){
		    	cueBallSideX=MovingBall.getCenterX()+((e.getX()-cueMouseSideX)/5);
			    cueBallSideY=MovingBall.getCenterY()+(((e.getX()-cueMouseSideX)*(cueSlopeY/cueSlopeX))/5);
			    repaint();
		    }
		    
		    
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		collided=false;
		PreviousMovingBall=null;
		line_drawing=true;
		hitTheWall=false;
		
		
		scoreManagementBall=ball_main;
		MovingBall=ball_main;
		
		
		if(!first_time_playing){
		t.stop();
		}
	
	    first_time_playing=false;
	    
		cueMouseSideX=e.getX();
		cueMouseSideY=e.getY();
		cueBallSideX=MovingBall.getCenterX();
		cueBallSideY=MovingBall.getCenterY();
		
		previouscueMouseSideX=cueMouseSideX;
		previouscueMouseSideY=cueMouseSideY;
		previouscueBallSideX=cueBallSideX;
		previouscueBallSideY=cueBallSideY;
		
		
		cueSlopeX=cueMouseSideX-cueBallSideX;
		cueSlopeY=cueMouseSideY-cueBallSideY;
	
		hitball_1=false;
		hitball_2=false;
		wallHitCounter=0;
		
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	
		line_drawing=false;
		
	     velocityX=Math.abs((cueBallSideX-MovingBall.getCenterX())*cueSpeed);
	     velocityY=Math.abs(velocityX*(cueSlopeY/cueSlopeX));
	   	 t=new Timer(0,this);
		 t.start();
		 repaint();
	}
	
	
		
		
public void otherBallNormalMovement() {
	
	if (velocityX>0 && velocityY>0) {
			if (cueMouseSideX>cueBallSideX && cueMouseSideY<cueBallSideY) {
				
				Zone1 = true;
				MovingBall.setFrame(MovingBall.getX() - 1, MovingBall.getY() + (1 *Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
				repaint();

			}
			else if (cueMouseSideX<cueBallSideX && cueMouseSideY<cueBallSideY) {
				 
				Zone2 = true;
				MovingBall.setFrame(MovingBall.getX() + 1, MovingBall.getY() + (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
				repaint();


			}

        	else if (cueMouseSideX<cueBallSideX && cueMouseSideY>cueBallSideY) {
        	
				Zone3 = true;
				MovingBall.setFrame(MovingBall.getX() + 1, MovingBall.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
				repaint();

			}

			else if (cueMouseSideX>cueBallSideX && cueMouseSideY>cueBallSideY) {
				 
				Zone4 = true;
				MovingBall.setFrame(MovingBall.getX() - 1, MovingBall.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
				repaint();


			}
		}
	}
	
public void PreviousBallMovementMovement() {
		
		if (PreviousvelocityX>0 && PreviousvelocityY>0) {
			if (middlePointX>=movingballLocationX && middlePointY<=movingballLocationY) { //sað üste
	             ZonePrevious1=true; 
	            
				PreviousMovingBall.setFrame(PreviousMovingBall.getX() + 1, PreviousMovingBall.getY() - (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
				 FindMovingBall(PreviousMovingBall, ball_main, ball_1, ball_2);
				repaint();

			}
			else if (middlePointX<=movingballLocationX && middlePointY<=movingballLocationY) {  //sol üste
				 ZonePrevious2=true; 
				
			    PreviousMovingBall.setFrame(PreviousMovingBall.getX() - 1, PreviousMovingBall.getY() - (1 *Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
			    FindMovingBall(PreviousMovingBall, ball_main, ball_1, ball_2);
			    repaint();


			}

        	else if (middlePointX<=movingballLocationX && middlePointY>=movingballLocationY) { //sol alta 
        		
        		 ZonePrevious3=true;
        		
				PreviousMovingBall.setFrame(PreviousMovingBall.getX() - 1, PreviousMovingBall.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
				 FindMovingBall(PreviousMovingBall, ball_main, ball_1, ball_2);
				repaint();

			}

			else if (middlePointX>=movingballLocationX && middlePointY>=movingballLocationY) {  //sað alta
				 ZonePrevious4=true; 
				
				PreviousMovingBall.setFrame(PreviousMovingBall.getX() + 1, PreviousMovingBall.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
				 FindMovingBall(PreviousMovingBall, ball_main, ball_1, ball_2);
				repaint();


			}
		}
	}
	
	
	
	
	public void WallHitOther(Ellipse2D ball,double velocityY,double velocityX) {
    
		if(scoreManagementBall==ball){
			wallHitCounter++;
		}
		
		
		if (velocityX>0 && velocityY>0) {

			if (ball.getY() <= 0) {  //UPPER WALL
				if (Zone4) {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() - 1, ball.getY() + (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = 10000;
					cueMouseSideY = -10000;
				}
				else {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() + 1, ball.getY() + (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = -10000;
					cueMouseSideY = -10000;
				}

			}
			else if (ball.getY() + 50 >= 510) {  //LOWER WALL
				if (Zone2) {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() + 1, ball.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = -10000;
					cueMouseSideY = 10000;

				}
				else {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() - 1, ball.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = 10000;
					cueMouseSideY = 10000;
				}


			}

			else if (ball.getX() <= 0) {  //LEFT WALL
				if (Zone4) {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() + 1, ball.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = -10000;
					cueMouseSideY = 10000;

				}
				else {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() + 1, ball.getY() + (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = -10000;
					cueMouseSideY = -10000;

				}
			}
			else if (ball.getX() >= 900) {  //RIGHT WALL
				if (Zone2) {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() - 1, ball.getY() + (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = 10000;
					cueMouseSideY = -10000;

				}
				else {
					Zone1 = false;
					Zone2 = false;
					Zone3 = false;
					Zone4 = false;
					ball.setFrame(ball.getX() - 1, ball.getY() - (1 * Math.atan(velocityY / velocityX)), ballRadius, ballRadius);
					hitTheWall = false;
					cueMouseSideX = 10000;
					cueMouseSideY = 10000;

				}
			}






		}

	}
	
	
	
	
	public void WallHitPrevious(Ellipse2D ball) {
		
		if(scoreManagementBall==ball ){
			wallHitCounter++;
		}
	
		if (PreviousvelocityX>0 && PreviousvelocityY>0) {

			if (ball.getY() <= 0) {  //UPPER WALL 
				if (ZonePrevious2) {
					
				    ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=-10000;
					middlePointY=10000;
					ball.setFrame(ball.getX() - 1, ball.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					
				}
				else {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=10000;
					middlePointY=10000;
					ball.setFrame(ball.getX() + 1, ball.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					
				}

			}
			else if (ball.getY() + 50 >= 510) {  //LOWER WALL
				if (ZonePrevious4) {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=10000;
					middlePointY=-10000;
					ball.setFrame(ball.getX() + 1, ball.getY() - (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					

				}
				else {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=-10000;
					middlePointY=-10000;
					ball.setFrame(ball.getX() - 1, ball.getY() - (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					
				}


			}

			else if (ball.getX() <= 0) {  //LEFT WALL
				if (ZonePrevious3) {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=10000;
					middlePointY=10000;
					ball.setFrame(ball.getX() + 1, ball.getY() - (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					

				}
				else {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=10000;
					middlePointY=-10000;
					ball.setFrame(ball.getX() + 1, ball.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					

				}
			}
			else if (ball.getX() >= 900) {  //RIGHT WALL
				if (ZonePrevious1) {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=-10000;
					middlePointY=-10000;
					ball.setFrame(ball.getX() - 1, ball.getY() + (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					

				}
				else {
					ZonePrevious1=false;
					ZonePrevious2=false;
					ZonePrevious3=false;
					ZonePrevious4=false;
					middlePointX=-10000;
					middlePointY=10000;
					ball.setFrame(ball.getX() - 1, ball.getY() - (1 * Math.atan(PreviousvelocityY / PreviousvelocityX)), ballRadius, ballRadius);
					hitThewallPrevious = false;
					

				}
			}






		}

	}
	
	public void scoreBallHit(){
		
			double distanceX_case1=scoreManagementBall.getCenterX()-ball_1.getCenterX();
			double distanceY_case1=scoreManagementBall.getCenterY()-ball_1.getCenterY();
			
			double distanceX_case2=scoreManagementBall.getCenterX()-ball_2.getCenterX();
			double distanceY_case2=scoreManagementBall.getCenterY()-ball_2.getCenterY();
			
			if(Math.sqrt(Math.pow(distanceX_case1,2)+Math.pow(distanceY_case1, 2))<(54)){
				hitball_1=true;
			}
			if(Math.sqrt(Math.pow(distanceX_case2,2)+Math.pow(distanceY_case2, 2))<(54)){
				hitball_2=true;
			}
			
				
		
		
	}
	
	public void FindMovingBall(Ellipse2D moving,Ellipse2D Ball_main,Ellipse2D Ball_1,Ellipse2D Ball_2){
		scoreBallHit();
		if(moving==Ball_main){
			double distanceX_case1=Ball_main.getCenterX()-Ball_1.getCenterX();
			double distanceY_case1=Ball_main.getCenterY()-Ball_1.getCenterY();
			
			double distanceX_case2=Ball_main.getCenterX()-Ball_2.getCenterX();
			double distanceY_case2=Ball_main.getCenterY()-Ball_2.getCenterY();
			
			if(Math.sqrt(Math.pow(distanceX_case1,2)+Math.pow(distanceY_case1, 2))<(54)){
				
				MovingBall=ball_1;
				collided=true;
				PreviousMovingBall=ball_main;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    middlePointX=Math.abs(Ball_main.getCenterX()+Ball_1.getCenterX())/2;
			    middlePointY=Math.abs(Ball_main.getCenterY()+Ball_1.getCenterY())/2;
			}
			else if(Math.sqrt(Math.pow(distanceX_case2,2)+Math.pow(distanceY_case2, 2))<(54)){
				MovingBall=ball_2;
				collided=true;
				PreviousMovingBall=ball_main;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    middlePointX=Math.abs(Ball_main.getCenterX()+Ball_2.getCenterX())/2;
			    middlePointY=Math.abs(Ball_main.getCenterY()+Ball_2.getCenterY())/2;
			    
			}
			
			
		}
		else if(moving==Ball_1){
			double distanceX_case3=Ball_1.getCenterX()-Ball_2.getCenterX();
			double distanceY_case3=Ball_1.getCenterY()-Ball_2.getCenterY();
			
			double distanceX_case1=Ball_main.getCenterX()-Ball_1.getCenterX();
			double distanceY_case1=Ball_main.getCenterY()-Ball_1.getCenterY();
			
			if(Math.sqrt(Math.pow(distanceX_case3,2)+Math.pow(distanceY_case3, 2))<(54)){
				MovingBall=ball_2;
				collided=true;
				PreviousMovingBall=ball_1;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    middlePointX=Math.abs(Ball_1.getCenterX()+Ball_2.getCenterX())/2;
			    middlePointY=Math.abs(Ball_1.getCenterY()+Ball_2.getCenterY())/2;
			    
			}
			else if(Math.sqrt(Math.pow(distanceX_case1,2)+Math.pow(distanceY_case1, 2))<(54)){
				MovingBall=ball_main;
				collided=true;
				PreviousMovingBall=ball_1;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    middlePointX=Math.abs(Ball_main.getCenterX()+Ball_1.getCenterX())/2;
			    middlePointY=Math.abs(Ball_main.getCenterY()+Ball_1.getCenterY())/2;
			}
			
		}
		else if(moving==Ball_2){
			double distanceX_case2=Ball_main.getCenterX()-Ball_2.getCenterX();
			double distanceY_case2=Ball_main.getCenterY()-Ball_2.getCenterY();
			
			double distanceX_case3=Ball_1.getCenterX()-Ball_2.getCenterX();
			double distanceY_case3=Ball_1.getCenterY()-Ball_2.getCenterY();
			
			if(Math.sqrt(Math.pow(distanceX_case2,2)+Math.pow(distanceY_case2, 2))<(54)){
				MovingBall=ball_main;
				collided=true;
				PreviousMovingBall=ball_2;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    middlePointX=Math.abs(Ball_main.getCenterX()+Ball_2.getCenterX())/2;
			    middlePointY=Math.abs(Ball_main.getCenterY()+Ball_2.getCenterY())/2;
			     
			}
			else if(Math.sqrt(Math.pow(distanceX_case3,2)+Math.pow(distanceY_case3, 2))<(54)){
				MovingBall=ball_1;
				collided=true;
				PreviousMovingBall=ball_2;
				PreviousvelocityX=velocityX/2;
			    PreviousvelocityY=Math.abs(PreviousvelocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    velocityX=velocityX/2;
			    velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
			    
			    movingballLocationX=MovingBall.getCenterX();
			    movingballLocationY=MovingBall.getCenterY();
			   
			    middlePointX=Math.abs(Ball_1.getCenterX()+Ball_2.getCenterX())/2;
			    middlePointY=Math.abs(Ball_1.getCenterY()+Ball_2.getCenterY())/2;
			    
			}
			
			
		}
		
		
		
	}
	
	
	boolean OutOfBounds(Ellipse2D ball){
	     if((ball.getCenterX()-25>0 && ball.getCenterX()+25<980)&&(ball.getCenterY()-25>0 && ball.getCenterY()+25<510)){

			return false;
		}
		
		return true;
	}
	
	public void timing(){
	
		if(velocityX>=250 && velocityY>=250){
			t.stop();
			
			t=new Timer(0,this);
			t.start();}
	  else if((velocityX<250 && velocityX>=200)&&(velocityY<250 && velocityY>=200)){
			t.stop();
			t=new Timer(1,this);
			t.start();
		}
	  else if((velocityX<200 && velocityX>=150)&&(velocityY<200 && velocityY>=150)){
			t.stop();
			t=new Timer(2,this);
			t.start();
		}
	  else if((velocityX<150 && velocityX>=100) && (velocityY<150 && velocityY>=100)){
		  
			t.stop();
			t=new Timer(3,this);
			t.start();
		}
	  else if(velocityX<100 && velocityY<100){
	    t.stop();
		t=new Timer(4,this);
		t.start();
		}   
	  
	 
	  
	  
		
	}
   
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	 if((velocityX>0 && velocityY>0) ||(PreviousvelocityX>0 && PreviousvelocityY>0)){
		
		timing();
		
		FindMovingBall(MovingBall, ball_main, ball_1, ball_2);
		
		 if(!OutOfBounds(MovingBall)&& !hitTheWall){
		    	
		    	velocityX--;
		        velocityY=Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX));
		       
		        otherBallNormalMovement();
		       
		     }
		       
		 else{
			     velocityX--;
			     velocityY=(Math.abs(velocityX*Math.atan(cueSlopeY/cueSlopeX)));
			     hitTheWall=true;
			     WallHitOther(MovingBall,velocityY,velocityX);
			    
		        }
		     
		 
		 if(collided && !OutOfBounds(PreviousMovingBall) && !hitThewallPrevious){
			 PreviousvelocityX--;
			 PreviousvelocityY=Math.abs(PreviousvelocityX*(cueSlopeY/cueSlopeX));
			 PreviousBallMovementMovement();
	        	
	        }
		 
		 else{
		    	 if(collided){
		    		 PreviousvelocityX--;
					 PreviousvelocityY=Math.abs(PreviousvelocityX*(cueSlopeY/cueSlopeX));
		    		 hitThewallPrevious=false;
			    	 WallHitPrevious(PreviousMovingBall);
			    	 
			     }
		     }
		
	  
		
	}
     
      
      if((velocityX<=0 || velocityY<=0) &&(PreviousvelocityX<=0 || PreviousvelocityY<=0)){
    	
    	if(hitball_1 && hitball_2 && wallHitCounter>=3){ 
    		score=score+25;
    		int high=-99999;
    		if(score>highScore){
    			lblHighScore.setText("HIGH SCORE:"+score);
    		}
    		lblscore.setText("SCORE:"+score);
    		try{
    			File file=new File("highScorePractice.txt");
    			Scanner input=new Scanner(new File("highScorePractice.txt"));
    			String hgh="";
    			while(input.hasNext()){
    				hgh=input.next();
    			}
    			high=Integer.parseInt(hgh);
    			input.close();
    			System.out.println(high);
    			if(score>high){
    				System.out.println("writin");
    			    FileWriter fwrite=new FileWriter("highScorePractice.txt");
    			    fwrite.write(Integer.toString(score));
    			    fwrite.close();
    			}
    			
    			
    		}
    		catch(Exception e){
    		   System.out.println( e.getMessage());
    		}
    		
    		
    		t.stop();
        }
    	else{
    		
    		score=score-25;
    		lblscore.setText("SCORE:"+score);
    		t.stop();
    		
    	}
    
  
      }
		
	
	}
	
	
	
	
	
	
	
	
		}
      
    
		
 
	
  
	
	
	
	
	
	
