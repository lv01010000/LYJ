package lifegame;

import java.util.Random;

public class Cell {
	
	private int Status;   //0-���� 1-���
	private int Living;   //��Χ��ϸ����Ŀ
	final static public int x=25;
    final static public int y=25;

	Cell(){ 	
		Status=0;
		Living=0;
		
	}
	
	Cell(int m_Status,int m_Living){
		
		Status=m_Status;
		Living=m_Living;
		
	}
	
	//ϸ����ȡ״̬
	public int getStatus() {
		
		return Status;
		
	}
	
	//����ϸ��״̬
	public void setStatus(int m_Status) {
		Status=m_Status;
	}
	
	//��ȡ��ǰ��Χ��ϸ����Ŀ
	public int getLiving() {
		return Living;
	}
	
	//���õ�ǰ��Χ��ϸ����Ŀ
	public void setLiving(int m_Living) {
		Living=m_Living;
	}
	
	//����ϸ����һʱ�ڵ�״̬
	public void NextStatus() {
		
		if(this.getLiving()>3||this.getLiving()<2) 
			this.setStatus(0);
		else 
			if(this.getLiving()==3) this.setStatus(1);
		else 
			this.setStatus(this.Status);
		
	}
	
	//��ʼ��ϸ��״̬
    public static  Cell[][] initial() {
    	
    	Cell [][]cell=new Cell[x][y];
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			cell[i][j]=new Cell();
    			Random random=new Random();
    			cell[i][j].setStatus(random.nextInt(2));
    		}
    	}
    	return cell;
    } 
    
    //ͳ��ÿ��ϸ����Χ���ŵ�ϸ������
    public static void getLiving(Cell [][]cell){
    	
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			int living=0;
    			if(i>0&&j>0) 
    				living+=cell[i-1][j-1].getStatus();
    			if(i>0) 
    				living+=cell[i-1][j].getStatus();
    			if(j>0) 
    				living+=cell[i][j-1].getStatus();
    			if(i<x-1&&j<x-1) 
    				living+=cell[i+1][j+1].getStatus();
    			if(i<x-1) 
    				living+=cell[i+1][j].getStatus();
    			if(j<x-1) 
    				living+=cell[i][j+1].getStatus();
    			if(i<x-1&&j>0) 
    				living+=cell[i+1][j-1].getStatus();
    			if(i>0&&j<x-1) 
    				living+=cell[i-1][j+1].getStatus();
    			
    			cell[i][j].setLiving(living);
    			
    		}
    	}
    }
    
    //ͳ�Ƹ���ϸ��������
    public static int update(Cell [][]cell){
    	
    	int count=0;
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			int status=cell[i][j].getStatus();
    			cell[i][j].NextStatus();
    			if(status==cell[i][j].getStatus()) 
    				count++;
    		}
    	}
    	return count;
    }

}

