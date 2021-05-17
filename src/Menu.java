import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.*;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;




public class Menu extends JFrame {
	JFrame jf;
	Multiplayer multiplayer;
	Offline_1v1 offline_1v1;
	Practice practice;
	JLabel lblWelcome;
	JLabel lblhighScoreMultiplayer;
	JLabel lblhighScorePractice;
	JLabel lblhighscores;
	JCheckBox cbxCue_1;
	JCheckBox cbxCue_2;
	JCheckBox cbxCue_3;
	JButton jbuttonSinglePlayerOnePC;
	JButton jbuttonPractise;
	JButton jbuttonTwoPlayerOffline;
    JRadioButton jrbtServer;
    JRadioButton jrbtClient;
	JTextField txtIP;
    JTextField txtCheat;
	
	cues cue1;
	cues cue2;
	cues cue3;
	String Client_Server;
	
	
	Menu(){
		
		jf=new JFrame("Menu");
		jf.setLayout(null);
		jf.setSize(600, 600);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(new JLabel(new ImageIcon("resim3.jpg")));
		jf.setVisible(true);
		
		lblhighscores=new JLabel("HIGH SCORE");
		lblhighscores.setBounds(220,480,200,40);
		lblhighscores.setForeground(Color.white);
		lblhighscores.setBackground(Color.BLACK);
		lblhighscores.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		lblhighScorePractice=new JLabel();
	    lblhighScorePractice.setBounds(190,510,100,40);
	    lblhighScorePractice.setForeground(Color.white);
	    lblhighScorePractice.setBackground(Color.BLACK);
	    lblhighScorePractice.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	    
	    lblhighScoreMultiplayer=new JLabel();
	    lblhighScoreMultiplayer.setBounds(292,510,130,40);
	    lblhighScoreMultiplayer.setForeground(Color.white);
	    lblhighScoreMultiplayer.setBackground(Color.BLACK);
	    lblhighScoreMultiplayer.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
	    
	    try {
	    	int high=-9999;
	    	File file=new File("highScore.txt");
			Scanner input=new Scanner(new File("highScorePractice.txt"));
			String hgh="";
			while(input.hasNext()){
				hgh=input.next();
			}
			high=Integer.parseInt(hgh);
			input.close();
			lblhighScorePractice.setText("Practice:"+high);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    
	    try {
	    	int high=-9999;
	    	File file=new File("highScoreMultiplayer.txt");
			Scanner input=new Scanner(new File("highScoreMultiplayer.txt"));
			String hgh="";
			while(input.hasNext()){
				hgh=input.next();
			}
			high=Integer.parseInt(hgh);
			input.close();
			lblhighScoreMultiplayer.setText("Multiplayer:"+high);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		txtCheat=new JTextField("CHEATS");
		txtCheat.setBounds(190,440,200,30);
		txtCheat.setForeground(Color.white);
		txtCheat.setBackground(Color.black);
		txtCheat.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		txtCheat.setHorizontalAlignment(JTextField.CENTER);
		
		txtIP=new JTextField("127.0.0.1");
		txtIP.setBounds(190,400,200,30);
		txtIP.setBackground(Color.black);
		txtIP.setForeground(Color.white);
		txtIP.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		txtIP.setHorizontalAlignment(JTextField.CENTER);
		
		jrbtClient=new JRadioButton("Client");
		jrbtClient.setBounds(220,350,70,50);
		jrbtClient.setBackground(Color.black);
		jrbtClient.setForeground(Color.white);
		jrbtServer=new JRadioButton("Server");
		jrbtServer.setBounds(290,350,70,50);
		jrbtServer.setBackground(Color.BLACK);
		jrbtServer.setForeground(Color.white);
		
		ButtonGroup bgClient_Server = new ButtonGroup();
		bgClient_Server.add(jrbtClient);
		bgClient_Server.add(jrbtServer);
		
		cue1=new cues(Color.white, 10);
		cue2=new cues(Color.orange,20);
		cue3=new cues(Color.PINK, 30);
		
		cbxCue_1=new JCheckBox("Cue_1->Power=10");
		cbxCue_1.setBounds(190, 280,200, 20);
		cbxCue_1.setBackground(Color.BLACK);
		cbxCue_1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		cbxCue_1.setForeground(Color.white);
		
		cbxCue_2=new JCheckBox("Cue_2->Power=20");
		cbxCue_2.setBounds(190, 300, 200, 20);
		cbxCue_2.setBackground(Color.BLACK);
		cbxCue_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		cbxCue_2.setForeground(Color.orange);
		
		cbxCue_3=new JCheckBox("Cue_3->Power=30");
		cbxCue_3.setBounds(190, 320, 200, 20);
		cbxCue_3.setBackground(Color.BLACK);
		cbxCue_3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		cbxCue_3.setForeground(Color.pink);
		
		
		
		jbuttonSinglePlayerOnePC=new JButton("Multiplayer");
		jbuttonSinglePlayerOnePC.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		jbuttonSinglePlayerOnePC.setBackground(Color.BLACK);
		jbuttonSinglePlayerOnePC.setForeground(Color.white);
		jbuttonSinglePlayerOnePC.setBounds(90,180,140,60);
		
		jbuttonPractise=new JButton("Practice");
		jbuttonPractise.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		jbuttonPractise.setBackground(Color.BLACK);
		jbuttonPractise.setForeground(Color.white);
		jbuttonPractise.setBounds(230,180,140,60);
		
		jbuttonTwoPlayerOffline=new JButton("1v1");
		jbuttonTwoPlayerOffline.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		jbuttonTwoPlayerOffline.setBackground(Color.BLACK);
		jbuttonTwoPlayerOffline.setForeground(Color.white);
		jbuttonTwoPlayerOffline.setBounds(370,180,140,60);
		
		
		jbuttonTwoPlayerOffline.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					String cheats=txtCheat.getText();
					
					switch (cheats){
						case "cheat1":
							System.out.println("CHEAT1");
							cue1.cueSpeed=50;
							cue2.cueSpeed=50;
							cue3.cueSpeed=50;
							break;
							
						case "cheat2":
							System.out.println("CHEAT2");
							cue1.cueColor=Color.yellow;
							cue2.cueColor=Color.yellow;
							cue3.cueColor=Color.yellow;
							break;
					}
					
					
						if(cbxCue_1.isSelected()){
							offline_1v1=new Offline_1v1(jf,cue1.cueSpeed,cue1.cueColor);
						}
						else if(cbxCue_2.isSelected()){
							
							offline_1v1=new Offline_1v1(jf,cue2.cueSpeed,cue2.cueColor);
						}
						else if(cbxCue_3.isSelected()){
							
							offline_1v1=new Offline_1v1(jf,cue3.cueSpeed,cue3.cueColor);
						}
						else{
							JOptionPane.showMessageDialog(null, "PLEASE ENTER A CUE TYPE");
						}
					
					
					
					
					
					
				}
				
			});
		
		
		
		
        jbuttonPractise.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String cheats=txtCheat.getText();
				
				switch (cheats){
					case "cheat1":
						System.out.println("CHEAT1");
						cue1.cueSpeed=50;
						cue2.cueSpeed=50;
						cue3.cueSpeed=50;
						break;
						
					case "cheat2":
						System.out.println("CHEAT2");
						cue1.cueColor=Color.yellow;
						cue2.cueColor=Color.yellow;
						cue3.cueColor=Color.yellow;
						break;
				}
				
				
					if(cbxCue_1.isSelected()){
						practice=new Practice(jf,cue1.cueSpeed,cue1.cueColor);
					}
					else if(cbxCue_2.isSelected()){
						
						practice=new Practice(jf,cue2.cueSpeed,cue2.cueColor);
					}
					else if(cbxCue_3.isSelected()){
						
						practice=new Practice(jf,cue3.cueSpeed,cue3.cueColor);
					}
					else{
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CUE TYPE");
					}
				
				
				
				
				
				
			}
			
		});
		
		
		jbuttonSinglePlayerOnePC.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String cheats=txtCheat.getText();
				
				switch (cheats){
					case "cheat1":
						System.out.println("CHEAT1");
						cue1.cueSpeed=50;
						cue2.cueSpeed=50;
						cue3.cueSpeed=50;
						break;
						
					case "cheat2":
						System.out.println("CHEAT2");
						cue1.cueColor=Color.yellow;
						cue2.cueColor=Color.yellow;
						cue3.cueColor=Color.yellow;
						break;
				}
				
				
				if(jrbtClient.isSelected()){
					Client_Server="Client";
					if(cbxCue_1.isSelected()){
						multiplayer=new Multiplayer(jf,cue1.cueSpeed,cue1.cueColor,Client_Server,txtIP.getText());
					}
					else if(cbxCue_2.isSelected()){
						
						multiplayer=new Multiplayer(jf,cue2.cueSpeed,cue2.cueColor,Client_Server,txtIP.getText());
					}
					else if(cbxCue_3.isSelected()){
						
						multiplayer=new Multiplayer(jf,cue3.cueSpeed,cue3.cueColor,Client_Server,txtIP.getText());
					}
					else{
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CUE TYPE");
					}
				}
				else if(jrbtServer.isSelected()){
					Client_Server="Server";
					if(cbxCue_1.isSelected()){
						multiplayer=new Multiplayer(jf,cue1.cueSpeed,cue1.cueColor,Client_Server,txtIP.getText());
					}
					else if(cbxCue_2.isSelected()){
						
						multiplayer=new Multiplayer(jf,cue2.cueSpeed,cue2.cueColor,Client_Server,txtIP.getText());
					}
					else if(cbxCue_3.isSelected()){
						
						multiplayer=new Multiplayer(jf,cue3.cueSpeed,cue3.cueColor,Client_Server,txtIP.getText());
					}
					else{
						JOptionPane.showMessageDialog(null, "PLEASE ENTER A CUE TYPE");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER SERVER OR CLIENT");
				}
				
				
				
			}
			
		});
		
		cbxCue_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cbxCue_2.setSelected(false);
				cbxCue_3.setSelected(false);
				
			}
		});
		cbxCue_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cbxCue_1.setSelected(false);
				cbxCue_3.setSelected(false);
				
			}
		});
		cbxCue_3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				cbxCue_2.setSelected(false);
				cbxCue_1.setSelected(false);
				
			}
		});
		
txtIP.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIP.setBackground(Color.DARK_GRAY);
				txtIP.setForeground(Color.black);
				txtIP.setText("");
				
			}
		});
		
txtCheat.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		txtCheat.setBackground(Color.DARK_GRAY);
		txtCheat.setForeground(Color.black);
		txtCheat.setText("");
		
	}
});
		
		jf.add(jbuttonTwoPlayerOffline);
		jf.add(cbxCue_3);
    	jf.add(cbxCue_2);
		jf.add(cbxCue_1);
		jf.add(jbuttonSinglePlayerOnePC);
		jf.add(jrbtClient);
		jf.add(jrbtServer);
	    jf.add(txtIP);	
	    jf.add(txtCheat);
	    jf.add(jbuttonPractise);
		jf.add(lblhighScorePractice);
		jf.add(lblhighScoreMultiplayer);
		jf.add(lblhighscores);
		
		
	}



}
