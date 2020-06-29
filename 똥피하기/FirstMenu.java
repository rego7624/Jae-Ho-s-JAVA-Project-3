package notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;


public class FirstMenu extends JFrame {
	Container c1=getContentPane();
	ImageIcon right_icon=new ImageIcon("keyboard_right.png");
	ImageIcon left_icon=new ImageIcon("keyboard_left.png");
	JLabel la_right=new JLabel(right_icon);
	JLabel la_left=new JLabel(left_icon);
	JButton b1=new JButton("게임 방법");
	JButton b2=new JButton("게임 시작");
	JButton b3=new JButton("점수 보드");
	JButton b4=new JButton("뒤로 가기");
	JLabel ins_right=new JLabel(": 오른쪽으로 이동");
	JLabel ins_left=new JLabel(": 왼쪽으로 이동");
	
	String header[]= {"순위","이름", "점수"};
	String contents[][]= new String[20][3];
	public JTable table=new JTable(contents, header);
	JScrollPane scrollpane=new JScrollPane(table);
	
	int row;

	public FirstMenu() {
		la_right.setLocation(80,160);
		la_left.setLocation(80,50);
		la_right.setVisible(false);
		la_left.setVisible(false);
		la_right.setSize(200,150);
		la_left.setSize(200,150);
		c1.add(la_right);
		c1.add(la_left);
		
		ins_left.setLocation(240,120);
		ins_right.setLocation(240,220);
		ins_left.setVisible(false);
		ins_right.setVisible(false);
		ins_left.setSize(100,20);
		ins_right.setSize(100,20);
		c1.add(ins_left);
		c1.add(ins_right);
		
		b1.setSize(100,50);
		b1.setVisible(true);
		b1.setLocation(150,100);
		b2.setSize(100,50);
		b2.setVisible(true);
		b2.setLocation(150,170);
		b3.setSize(100,50);
		b3.setVisible(true);
		b3.setLocation(150,240);
		b4.setSize(100,50);
		b4.setVisible(false);
		b4.setLocation(10,10);
		c1.setBackground(Color.WHITE);
		c1.setLayout(null);
		c1.setFocusable(true);
		c1.requestFocus();
		
		Dimension dim=new Dimension(400,500);
		setTitle("똥 피하기 ver.민성");
//		setSize(400,500);
//		setSize(dim);
		setPreferredSize(dim);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		c1.add(b1);
		c1.add(b2);
		c1.add(b3);
		c1.add(b4);
		
		MyActionListener myActionListener=new MyActionListener();
		b1.addActionListener(myActionListener);
		b2.addActionListener(myActionListener);
		b3.addActionListener(myActionListener);
		b4.addActionListener(myActionListener);
		
		c1.add(scrollpane);
		scrollpane.setVisible(false);
		scrollpane.setSize(270,500);
		scrollpane.setLocation(130,0);
	}
	
	void hideButtons() {
		b1.setVisible(false);
		b2.setVisible(false);
		b3.setVisible(false);
	}
	void showButtons() {
		b1.setVisible(true);
		b2.setVisible(true);
		b3.setVisible(true);
	}
	void showHowToPlay() {
		ins_left.setVisible(true);
		ins_right.setVisible(true);
		la_right.setVisible(true);
		la_left.setVisible(true);
		b4.setVisible(true);
	}
	void hideHowToPlay() {
		ins_left.setVisible(false);
		ins_right.setVisible(false);
		la_right.setVisible(false);
		la_left.setVisible(false);
		b4.setVisible(false);
	}
	void showTable() {
		scrollpane.setVisible(true);
		b4.setVisible(true);
	}
	void hideTable() {
		scrollpane.setVisible(false);
		b4.setVisible(false);
	}
	public void sortTable(JTable table) {
		table.setRowSorter(null);
	}
	void readTable() {
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;

	    
	    String[][] arr = new String[20][3];
	    
	    try {
	    	fis=new FileInputStream("scoreboard.txt");
			isr = new InputStreamReader (fis);
		    br = new BufferedReader (isr);
		    
		    String data;

		    for(int i=0;;i++) {
		    	data=br.readLine();
		    	if(data==null)
		    		break;
		    	String[] dataset=data.split(",");

//		    	arr[i][0]=String.valueOf(dataset[0]);
		    	arr[i][0]=dataset[0];
		    	arr[i][1]=dataset[1];
		    	arr[i][2]=dataset[2];
		    	table.setValueAt(arr[i][0], i, 0);
		    	table.setValueAt(arr[i][1], i, 1);
		    	table.setValueAt(arr[i][2], i, 2);
		    	
		    	row++;
		    }
		    fis.close();isr.close();br.close();
	    }catch(IOException e) {
		   	e.printStackTrace();
		}
	}
	void writeTable(String name, int score) {
		OutputStreamWriter osw;
	    BufferedWriter bw;
	    FileOutputStream fos;
	    
	    String readData="";
	    contents[row][0]=String.valueOf(row+1);
	    contents[row][1]=name;
	    contents[row][2]=String.valueOf(score);
	    for(int i=0;i<row+1;i++) {
	    	for(int j=0;j<3;j++) {
	    		readData+=contents[i][j];
	    		if(j!=3)
	    			readData+=',';
	    	}
	    	readData+="\n";
	    }
	    
		try {
			fos=new FileOutputStream("scoreboard.txt");
			osw=new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);

			bw.write(readData);
			bw.close();osw.close();fos.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b=(JButton)e.getSource();
			if(b.getText().equals("게임 방법")) {
				hideButtons();
				showHowToPlay();
			}
			else if(b.getText().equals("게임 시작")) {
				MyFrame mf=new MyFrame();
			}
			else if(b.getText().equals("뒤로 가기")) {
				showButtons();
				hideHowToPlay();
				hideTable();
			}
			else if(b.getText().equals("점수 보드")) {
				hideButtons();
				readTable();
				showTable();
			}
		}
	}
}

