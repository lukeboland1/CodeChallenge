package codeChallengeServer;

public class BoardState {
	  
	  private int width, height;
	  private int lastRow = -1;
	  private int lastColumn = -1;
	  private String[][] grid;

	  public BoardState(int h, int w) 
	  {
	    width = w;
	    height = h;
	    grid = new String[h][w];

	    
	    for (int i = 0; i < h; i++) 
	    {
	    	for (int j = 0; j < w; j++)
	    	{
	    		grid[i][j] = "[ ]";
	    	}
	    }
	      
	   }
	  
	  
	  public String toString() 
	  {
		  String string = new String();
		  for(int i =0;i< height; i++){
			    for (int j = 0; j < width; j++) 
			    {
			        string += (grid[i][j]);
			    }  
			    string += ("\n");
		  
		  
		  }
		  return string;

	}
	  
	  
	 public String checkHorizontal(int row)
	 {
		 StringBuilder sb = new StringBuilder();
		 for (int j = 0; j < width; j++) 
		    {
		        sb.append(grid[row][j]);
		    }
		 return sb.toString();
	 }
	 
	 public String checkVertical(int column)
	 {
		 StringBuilder sb = new StringBuilder();
		 for (int j = 0; j < height; j++) 
		    {
		        sb.append(grid[j][column]);
		    }
		 return sb.toString();
	 }
	 
	 public String checkNorthWestDiagonal(int row, int column)
	 {
		 StringBuilder sb = new StringBuilder();
		 int r = 0, c = 0;
		 for (int j = column, i = row; j >= 0 && i >= 0; j--, i--) 
		    {
		        r = i;
		        c = j;
		    }
		 
		 for (int j = c, i = r; j < width && i < height; j++, i++) 
		    {
		        sb.append(grid[i][j]);
		    }
		 return sb.toString();
	 }
	 
	 public String checkNorthEastDiagonal(int row, int column)
	 {
		 StringBuilder sb = new StringBuilder();
		 int r = 0, c = 0;
		 for (int j = column, i = row; j <width && i >= 0; j++, i--) 
		    {
			 	r = i;
		        c = j;
		    }
		 for (int j = c, i = r; i <height && j >= 0; j--, i++) 
		   {
		        sb.append(grid[i][j]);
		   }
		 
		 return sb.toString();
	 }
	 
	 public boolean makeTurn(int column, String mark)
	 {
		 boolean found = false;
		 for (int j = column, i = height - 1; i >=0 && j >= 0 && found == false; i--) 
		   {
		        if(grid[i][j].equals("[ ]"))
		        {
		        	found = true;
		        	
		        	lastRow = i;
		        	lastColumn = j;
		        	
		        	grid[i][j] = "["+mark+"]";
		        	return true;
		        		
		        }
		        else
		        {
		        	//continue
		        }
		   }
		 
		 return false;
		 
		 
	 }
	 


	public boolean checkWin()
	 {
		 boolean win = false;
		 String horizontal = this.checkHorizontal(lastRow);
		 String vertical = this.checkVertical(lastColumn);
		 String eastDiagonal = this.checkNorthEastDiagonal(lastRow, lastColumn);
		 String westDiagonal = this.checkNorthWestDiagonal(lastRow, lastColumn);
		 if (horizontal.contains("[x][x][x][x][x]") || horizontal.contains("[o][o][o][o][o]")) 
		 {
		     win = true;
		 }
		 else if(vertical.contains("[x][x][x][x][x]") || vertical.contains("[o][o][o][o][o]")) 
		 {
			 win = true;
		 }
		 else if(eastDiagonal.contains("[x][x][x][x][x]")|| eastDiagonal.contains("[o][o][o][o][o]")) 
		 {
			 win = true;
		 }
		 else if(westDiagonal.contains("[x][x][x][x][x]")|| westDiagonal.contains("[o][o][o][o][o]"))  
		 {
			 win = true;
		 }
		 
		 
		 return win;
	 }
	
	public boolean checkDraw()
	 {
		 String s = this.checkHorizontal(0);
		 if(s.contains("[ ]"))
		 {
			 return false;
		 }
		 else
		 {
			 return true;
		 }
	 }


	public String[][] getGrid() {
		return grid;
	}
	
	public void fillGrid() 
	  {
	    //fill grid for testing purposes
	    for (int i = 0; i < height; i++) 
	    {
	    	for (int j = 0; j < width; j++)
	    	{
	    		grid[i][j] = "[A]";
	    	}
	    }
	      
	   }
	
	
	
	 
}