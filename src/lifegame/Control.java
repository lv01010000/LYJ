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
    	jframe.setTitle("繁衍代数"+count);
    	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jframe.setLocationRelativeTo(null);
    	jframe.add(jpanel);
    	jframe.setVisible(true);
    	
    	Cell [][]cell=new Cell[X][Y];//创建细胞矩阵
    	cell=Cell.initial();//初始化细胞状态
    	Cell.getLiving(cell);//统计每个细胞周围活着的细胞个数
    	
    	
    	Graphics g=jpanel.getGraphics();
    	for(int i=0;i<X;i++) {
    		for(int j=0;j<Y;j++) {
    			g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);
    		}
    	}
    	
    	for(int i=0;i<X;i++) {
    		for(int j=0;j<Y;j++) {
    			if(cell[i][j].getStatus()==1) {
    				g.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//画实心方框，即细胞存活
    			}
    			else {
    				g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//画空心方框，即细胞死亡
    			}
    		}
    	}
    	
    	long d1= System.currentTimeMillis();
    	while(true) {
    		long d2= System.currentTimeMillis();//定时器效果
    		if(d2-d1>1500) {
    			int change;
    			d1=d2;
    			jframe.repaint();
    			change=Cell.update(cell);//统计更新细胞的数量
    			Cell.getLiving(cell);//统计每个细胞周围活着的细胞个数
    			
    			for(int i=0;i<X;i++) {
    	    		for(int j=0;j<Y;j++) {
    	    			g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//画空心方框
    	    		}
    	    	}
    			
    			for(int i=0;i<X;i++) {
    				for(int j=0;j<Y;j++) {
    					if(cell[i][j].getStatus()==1) {
    	    				g.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//画实心方框，即细胞存活
    	    			}
    	    			else {
    	    				g.drawRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);//画空心方框，即细胞死亡
    				    }
    				}
    			}
    		    count++;
    		    jframe.setTitle("繁衍代数"+count);
    			if(change==X*Y) 
    				break;
    			if(count>1000) 
    				break;
    	    }	    	
    	}
    	JOptionPane.showMessageDialog(jframe, "在第"+count+"次繁衍达到平衡", "提示", JOptionPane.PLAIN_MESSAGE);
    }
}


