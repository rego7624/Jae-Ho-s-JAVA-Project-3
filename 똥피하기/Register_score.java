package notepad;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register_score extends JFrame {
	JLabel la_type_name=new JLabel("Your name :");
	JLabel la_your_score=new JLabel("Your score :");
	JTextField scan_name=new JTextField();
	JButton b_ok=new JButton("µî·Ï");
	//String name;
	
	Register_score(){		//constructor
		Container c=getContentPane();
		setTitle("Á¡¼ö µî·Ï");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(250,150);
		setVisible(true);
		c.setLayout(null);
		c.setBackground(Color.WHITE);
		c.setFocusable(true);
		c.requestFocus();
		

		la_type_name.setLocation(20,20);
		la_type_name.setSize(100,20);
		la_type_name.setVisible(true);
		c.add(la_type_name);
		
		la_your_score.setLocation(20,40);
		la_your_score.setSize(100,20);
		la_your_score.setVisible(true);
		c.add(la_your_score);
		
		scan_name.setLocation(100,20);
		scan_name.setSize(70,20);
		scan_name.setVisible(true);
		c.add(scan_name);
		
		b_ok.setLocation(90,70);
		b_ok.setSize(60,20);
		b_ok.setVisible(true);
		
		Font font=new Font("¸¼Àº °íµñ",Font.PLAIN,10);
		b_ok.setFont(font);
		
		c.add(b_ok);
		
		b_ok.addActionListener(new MyActionListener());
		
	}
	
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name=scan_name.getText();
			File file=new File("scoreboard.txt");
			try {
				FileReader fr=new FileReader(file);
				FileWriter fw=new FileWriter(file, true);
				int idx=0;
				int c;
				while((c=fr.read())!=-1) {
					if(c==10)
						idx++;
				}
				fw.write("\n"+(idx+1)+","+name);
				fr.close();
				fw.close();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
