package notepad;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Man extends JLabel {
	int mspeed;
	private int mpos_x=175;
	private int mpos_y=320;
	public JLabel la;
	private ImageIcon icon2 = new ImageIcon("annonymous_left.png");
	private ImageIcon icon3 = new ImageIcon("annonymous_right.png");
	Man(){
		this.la=new JLabel(icon2);
		this.la.setLocation(this.mpos_x,this.mpos_y);
		this.la.setSize(50,50);
		this.la.setVisible(true);
	}
	public int get_mpos_x() {
		return this.mpos_x;
	}
	public int get_mpos_y() {
		return this.mpos_y;
	}
	class ManKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if(keyCode==KeyEvent.VK_RIGHT) {
				if(la.getX()<330) {
					la.setIcon(icon3);
					mpos_x+=10;
					la.setLocation(mpos_x,mpos_y);
				}
			}
			else if(keyCode==KeyEvent.VK_LEFT) {
				if(la.getX()>0)	{
					la.setIcon(icon2);
					mpos_x-=10;
					la.setLocation(mpos_x,mpos_y);
				}
			}
		}
	}
}