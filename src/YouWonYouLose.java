import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class YouWonYouLose extends JFrame {

	JFrame jf;
	JLabel result;
	JButton btnReturn;
	Menu menu;
	
	public YouWonYouLose(int score){
		jf=new JFrame("Win or Lose");
		jf.setLayout(null);
		jf.setSize(1000, 1000);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setBackground(Color.BLACK);
		jf.setVisible(true);
		
		btnReturn=new JButton("MENU");
		btnReturn.setBounds(700, 700, 200, 200);
		btnReturn.setForeground(Color.red);
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnReturn.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			jf.setVisible(false);
			menu=new Menu();
			
		}
		});
		
		
		if(score >=50){
			result=new JLabel("YOU WON");
			result.setFont(new Font("Comic Sans MS", Font.BOLD, 90));
			result.setBounds(260, 240, 500, 500);
			result.setForeground(Color.red);
			
		}
		else{
			result=new JLabel("YOU LOSE");
			result.setFont(new Font("Comic Sans MS", Font.BOLD, 90));
			result.setBounds(260, 240, 500, 500);
			result.setForeground(Color.red);
		}
		jf.add(result);
		jf.add(btnReturn);
	}
	
}
