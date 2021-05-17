import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Practice extends JFrame implements ActionListener{
	JLabel lblTime;
	JLabel lblPower;
	JLabel lblscore;
	JLabel lblhighScore;
	JButton btnReturn;
	Menu menu;
	int time;
    DrawPractice bd;
    Timer t;
    JLabel lblpicture;
	
	
	public Practice(JFrame jfMenu,int Power,Color col){
		jfMenu.setVisible(false);
        getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1150, 700);
		setResizable(false);
		setLayout(null);
		
		t=new Timer(1000,this);
		t.start();
		
	    lblpicture=new JLabel(new ImageIcon("resim5.jpg"));
		lblpicture.setBounds(50, 50, 1039, 570);
		
		
		
		
		lblPower=new JLabel("POWER:");
		lblPower.setBounds(300,10,300,20);
		lblPower.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblPower.setForeground(Color.white);
		
		lblscore=new JLabel("SCORE:");
		lblscore.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblscore.setBounds(630,10,300,20);
		lblscore.setForeground(Color.white);
		
		lblhighScore=new JLabel("HIGH SCORE:");
		lblhighScore.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblhighScore.setBounds(800,10,300,20);
		lblhighScore.setForeground(Color.white);
	   
	    lblTime=new JLabel("Time:"+time);
	    lblTime.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
	    lblTime.setForeground(Color.white);
	    lblTime.setBounds(10,10,100,20);
	    
	    btnReturn=new JButton("MENU");
		btnReturn.setBounds(900, 600, 100, 50);
		btnReturn.setForeground(Color.red);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		bd=new DrawPractice(this,Power,col,lblTime,lblPower,lblscore,lblhighScore);
		bd.setBounds(80,80,980,510);
		bd.setBackground(new Color(32,111,79));
		add(lblTime);
		add(bd);
		add(lblPower);
		add(lblscore);
		add(lblhighScore);
		add(btnReturn);
		add(lblpicture);
		setVisible(true);
		
		
		btnReturn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			setVisible(false);
			menu=new Menu();
			
		}
		});
	   
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
         time++;
         lblTime.setText("Time:"+time);
	}

	
	
}
