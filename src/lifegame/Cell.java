package lifegame;

import java.util.Random;

public class Cell {
	
	private int Status;   //0-死亡 1-存活
	private int Living;   //周围活细胞数目
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
	
	//细胞获取状态
	public int getStatus() {
		
		return Status;
		
	}
	
	//设置细胞状态
	public void setStatus(int m_Status) {
		Status=m_Status;
	}
	
	//获取当前周围活细胞数目
	public int getLiving() {
		return Living;
	}
	
	//设置当前周围活细胞数目
	public void setLiving(int m_Living) {
		Living=m_Living;
	}
	
	//设置细胞下一时期的状态
	public void NextStatus() {
		
		if(this.getLiving()>3||this.getLiving()<2) 
			this.setStatus(0);
		else 
			if(this.getLiving()==3) this.setStatus(1);
		else 
			this.setStatus(this.Status);
		
	}
	
	//初始化细胞状态
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
    
    //统计每个细胞周围活着的细胞个数
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
    
    //统计更新细胞的数量
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

