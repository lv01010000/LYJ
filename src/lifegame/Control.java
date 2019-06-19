package lifegame;


import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Control {
	
	final static public int X=25;
    final static public int Y=25;
    final static public int WIDTH = 22;  
    final static public int HEIGHT = 22;
	
    Control() {
    	
    	JFrame jframe=new JFrame();
    	jframe.setSize(600,640);
    	JPanel jpanel=new JPanel();
    	jpanel.setBounds(0,0,580,620);
    	int count=0;
    	jframe.setTitle("���ܴ���"+count);
    	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jframe.setLocationRelativeTo(null);
    	jframe.add(jpanel);
    	jframe.setVisible(true);
    	
    	Cell [][]cell=new Cell[X][Y];//����ϸ������
    	cell=Cell.initial();//��ʼ��ϸ��״̬
    	Cell.getLiving(cell);//ͳ��ÿ��ϸ����Χ���ŵ�ϸ������
    	
    	
    	Graphics g=jpanel.getGraphics();
    	for(int i=0;i<X;i++) {
    		for(int j=0;j<Y;j++) {
    			g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);
    		}
    	}
    	
    	for(int i=0;i<X;i++) {
    		for(int j=0;j<Y;j++) {
    			if(cell[i][j].getStatus()==1) {
    				g.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//��ʵ�ķ��򣬼�ϸ�����
    			}
    			else {
    				g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//�����ķ��򣬼�ϸ������
    			}
    		}
    	}
    	
    	long d1= System.currentTimeMillis();
    	while(true) {
    		long d2= System.currentTimeMillis();//��ʱ��Ч��
    		if(d2-d1>1500) {
    			int change;
    			d1=d2;
    			jframe.repaint();
    			change=Cell.update(cell);//ͳ�Ƹ���ϸ��������
    			Cell.getLiving(cell);//ͳ��ÿ��ϸ����Χ���ŵ�ϸ������
    			
    			for(int i=0;i<X;i++) {
    	    		for(int j=0;j<Y;j++) {
    	    			g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//�����ķ���
    	    		}
    	    	}
    			
    			for(int i=0;i<X;i++) {
    				for(int j=0;j<Y;j++) {
    					if(cell[i][j].getStatus()==1) {
    	    				g.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//��ʵ�ķ��򣬼�ϸ�����
    	    			}
    	    			else {
    	    				g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//�����ķ��򣬼�ϸ������
    				    }
    				}
    			}
    		    count++;
    		    jframe.setTitle("���ܴ���"+count);
    			if(change==X*Y) 
    				break;
    			if(count>1000) 
    				break;
    	    }	    	
    	}
    	JOptionPane.showMessageDialog(jframe, "�ڵ�"+count+"�η��ܴﵽƽ��", "��ʾ", JOptionPane.PLAIN_MESSAGE);
    }
}


