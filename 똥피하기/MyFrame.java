package notepad;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;



public class MyFrame extends JFrame {
	Container c=getContentPane();
	public static int score=0;
	private static int high;
	private int end_flag=0;
	private static  JLabel la_score=new JLabel("Score : "+score);
	private static JLabel la_high_score=new JLabel("High : "+high);
	private static JLabel la_game_over=new JLabel("GAME OVER!!");
	private static JLabel la_start=new JLabel("Press <S> to play!!");
	private static JLabel la_restart=new JLabel("Press <R> to play again..");
	private static JLabel la_quit=new JLabel("Press <Q> to quit.");
	private static JLabel la_insert_score=new JLabel("Press <B> to register your score!");
	Man m=new Man();
	
	public int getScore() {
		return score;
	}
	

	
	public void drawMyFrame() {
		setTitle("¶Ë ÇÇÇÏ±â ver.¹Î¼º");
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		score=0;
		
		c.setBackground(Color.WHITE);
		c.setLayout(null);
		
		c.addKeyListener(new MainKeyListener());
		c.setFocusable(true);
		c.requestFocus();
		
		c.add(la_score);
		la_score.setSize(100,10);
		la_score.setLocation(10,10);
		la_score.setVisible(true);
		
		c.add(la_high_score);
		la_high_score.setSize(50,15);
		la_high_score.setLocation(10,20);
		la_high_score.setVisible(true);
		
		JPanel ground=new JPanel();
		c.add(ground);
		ground.setBackground(Color.GRAY);
		ground.setSize(400,130);
		ground.setLocation(0,370);
		
		c.add(la_start);
		la_start.setSize(150,20);
		la_start.setLocation(120, 150);
		la_start.setVisible(true);
		
		c.add(la_quit);
		la_quit.setSize(200,20);
		la_quit.setLocation(110,190);
		la_quit.setVisible(false);

		c.add(la_game_over);
		la_game_over.setSize(100,20);
		la_game_over.setLocation(150,150);
		la_game_over.setVisible(false);
		
		c.add(la_restart);
		la_restart.setSize(200,50);
		la_restart.setLocation(110,160);
		la_restart.setVisible(false);
		
		c.add(la_insert_score);
		la_insert_score.setSize(200,50);
		la_insert_score.setLocation(110,190);
		la_insert_score.setVisible(false);
	}
	void drawMan() {
		c.add(m.la);
		c.addKeyListener(m.new ManKeyListener());
	}
	void showGameOver() {
		la_game_over.setVisible(true);
		la_restart.setVisible(true);
		la_quit.setVisible(true);
		la_insert_score.setVisible(true);
	}
	void hideGameOver() {
		la_game_over.setVisible(false);
		la_restart.setVisible(false);
		la_quit.setVisible(false);
		la_insert_score.setVisible(false);
	}
	///////////////////////////////////////
	class Poop implements Runnable {
	private int speed;
	private int pos_x;	//0~400;
	private int pos_y;
	private JLabel label;
	private boolean hit_state=false;
	///////method
	
	Poop(){	//constructor
		Random ran=new Random();
		this.pos_x=ran.nextInt(400)+1;
		this.pos_y=0;
		this.speed=ran.nextInt(3)+4;
		
		ImageIcon icon1 = new ImageIcon("poop_image.png");
		this.label=new JLabel(icon1);
		this.label.setLocation(this.pos_x, this.pos_y);
		this.label.setVisible(true);
		this.label.setSize(20,20);
	}
	public int get_pos_x() {
		return this.pos_x;
	}
	public int get_pos_y() {
		return this.pos_y;
	}
	public boolean hit_man(Man m) {
		if(m.get_mpos_y()<=this.get_pos_y()&&this.get_pos_y()<=m.get_mpos_y()+50) {
			if(m.get_mpos_x()<=this.get_pos_x()&&this.get_pos_x()<=m.get_mpos_x()+50)
				this.hit_state=true;
		}
		else if(m.get_mpos_y()<=this.get_pos_y()+10&&this.get_pos_y()+10<=m.get_mpos_y()+50) {
			if(m.get_mpos_x()<=this.get_pos_x()+10&&this.get_pos_x()+10<=m.get_mpos_x()+50)
				this.hit_state=true;
		}
		else this.hit_state=false;
		
		return this.hit_state;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(30);
			
		}catch(InterruptedException e) {return;}
		if(end_flag==1)
			return;
		pos_y+=speed;
		label.setLocation(pos_x,pos_y);
		if(pos_y>370) {
			MyFrame.score++;
			if(score>high)
				high++;
			MyFrame.la_score.setText("Score : "+MyFrame.score);
			MyFrame.la_high_score.setText("High : "+MyFrame.high);
			c.remove(label);
			return;
			
		}
		if(this.hit_man(m)==true) {
			end_flag=1;
			m.setEnabled(false);
			return;
		}
		run();
		}
	}
	
	class PoopMaker extends Thread{
		public void run() {
			/*
			try {
				Thread.sleep(10);		//nÃÊ¸¶´Ù ¶Ë »ý¼º
			}catch(InterruptedException e) {return;}
			*/
			
			if(end_flag==1) {
				this.interrupt();
				showGameOver();
			}
			Poop p1=new Poop();
			c.add(p1.label);
			p1.run();
			
			run();
		}
	}
	
	public MyFrame() {	//constructor
		drawMyFrame();
		drawMan();
		hideGameOver();
	}
	public static void main(String[] args) {
		FirstMenu firstMenu=new FirstMenu();
	}
	
	class MainKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if(keyCode==KeyEvent.VK_S) {		//start
				la_start.setVisible(false);
				new PoopMaker().start();
			}
			else if(keyCode==KeyEvent.VK_R)	{	//restart
				end_flag=0;
				new MyFrame();
			}
			else if(keyCode==KeyEvent.VK_Q)		//quit
				System.exit(0);
			else if(keyCode==KeyEvent.VK_B) {	//register score
				Register_score my=new Register_score();
				my.la_your_score.setText("Your score :   "+score);
				
			}
		}
	}
	

}
