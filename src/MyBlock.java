/**
 * @author 		: Yogeshwar Girish Chaudhari 
 * Email		: s3828116@student.rmit.edu.au
 * Date			: 15-03-2020
 * Description 	: MyBlock is a simple tool which can help one design a block of land.  
 */
  
class MyBlock {
	// Changing type to Byte as block would be of size 3-10
	private byte[][] block;

	// Minimizing need of repetitively calculating len(rows) & len(cols) of block
	private byte blockRows;											
	private byte blockCols;		
	
	private boolean vacant;		
	
	// Maintains the number of house allocated in all blocks
	// Assumed it to be incremental at system level and not object specific so its static. Otherwise can be made private only
	private static byte currentHouse 					= 1;		
	
	// Constant that indicates availability of space
	private static final byte emptyChar 				= 0;		
	
	// Constant that determines minimum distance between houses
	private static final byte minDistanceInHouses 		= 1;		
	
	// Leaves these many rows and columns from all edges
	private static final byte minDistanceFromBlockEdges = 1;		
	
	// Mandatory minimum rows of house, house can be 1x?
	private static final byte minHouseRows 				= 1;		
	
	// Mandatory minimum columns of house, house can be ?x1
	private static final byte minHouseCols 				= 1;			

	
	// Initialize the block, each cell is set to the character '0' 
	public MyBlock (int maxRows, int maxColumns) {
		this.block 	   = new byte[maxRows][maxColumns];
		this.blockRows = (byte) maxRows;
		this.blockCols = (byte) maxColumns;
		this.vacant	   = true;
		
		this.clearBlock();
	}


	// Display the entire block as a matrix 
	public void displayBlock() {
		
		for (int i = 0; i < this.blockRows; i++) {
			for ( int j = 0; j < this.blockCols ; j++) {
				
				System.out.print(this.block[i][j]+" ");
				if (j == (this.blockCols - 1)) 
				{
					System.out.print("\n");
				}
			
			}
		}
	}


	// Clear out the block. This involves setting each cell value to '0'
	public void clearBlock() {
		
		// Minimizing array iteration for repetitive clear operation 
		if (!this.vacant) {
			for (int i = 0; i < this.blockRows; i++) {
				for (int j = 0; j < this.blockCols; j++) {
					this.block[i][j] = MyBlock.emptyChar;			
				}
			}
		}else {
			ProgFunAssignment1.console_log("Block is already empty");
		}
		
		this.vacant = true;	
		System.out.println("Value of vacant : "+this.vacant+"\n");
		
		// House numbering will start with 1 again on clearing block
		MyBlock.currentHouse = 1;			
	}


	// Build a house on the block
	public boolean addHouse (byte rowPos, byte colPos, byte rows, byte columns) {
		
		// Add house if the requested block ..
		boolean rulesValidated = leavesMinDistanceFromStart(rowPos, colPos, rows, columns) 
								&& withInEndLimits(rowPos, colPos, rows, columns) 
								&& minSpaceBtwHouses(rowPos, colPos, rows, columns) 
								&& hasMinRowsCols(rows, columns);
		
		if (rulesValidated) {
			
			byte refColPos = colPos;
			
			// Securing house as all rules has been validated
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					this.block[rowPos][colPos] = currentHouse;
					colPos += 1;
				}
				rowPos += 1;
				
				// Start with starting column for next row
				colPos = refColPos;
			}
			
			currentHouse += 1;
			this.vacant = false;
			
			this.displayBlock();

			// Secured the block 
			return true;
			
		} else {
			
			return false;
		}
	}
	
	
	// Rule 1 - House can start from (1,1) ensuring house leaves an empty row & column from start
	public boolean leavesMinDistanceFromStart (byte rowPos, byte colPos, byte rows, byte columns) {
		
		if ( (rowPos >= minDistanceFromBlockEdges) && (colPos >= minDistanceFromBlockEdges) ) {
			return true;
		 } else { 
			System.out.println("House should leave "+minDistanceFromBlockEdges+" rows and "+minDistanceFromBlockEdges+" cols from block start"); 
		 	return false; 
		 }
	}
	
	
	// Rule 2 - It checks if the block is available to be occupied
	public boolean minSpaceBtwHouses (byte rowPos, byte colPos, byte rows, byte columns) {
		
		byte refColPos = (byte) (colPos - minDistanceInHouses);
		colPos = (byte) (colPos - minDistanceInHouses);
		rowPos = (byte) (rowPos - minDistanceInHouses);

		//  Validate there is at least 1 empty row and column between houses if it gets occupied
		for (int i = 0; i < ( rows + (minDistanceInHouses * 2) ); i++) {
			for (int j = 0; j < (columns + (minDistanceInHouses * 2) ); j++) {
				if (this.block[rowPos][colPos] != emptyChar){
					
					ProgFunAssignment1.console_log("This is occupied - rowPos : "+rowPos+" colPos : "+colPos);
					System.out.println("Minimum Space of "+minDistanceInHouses+" is required between houses from all it's edges");
					return false;
				}
				colPos += 1;
			}
			rowPos += 1;
			
			// Start with starting column for next row
			colPos = refColPos;
		}
		
		return true;
	}
	
	
	// Rule 3 - Essentially no house will go beyond availableRows - 1 and availableColumns - 1 limits. Making sure that Rule 1 is still observed
	public boolean withInEndLimits (byte rowPos, byte colPos, byte rows, byte columns) {
		
		if ( (rowPos + rows) <= (blockRows - minDistanceFromBlockEdges) && (colPos + columns) <= (blockCols - minDistanceFromBlockEdges))
		{
			return true;
		}
		else 
		{
			System.out.println("Part of the house at ending is going out of the block");
			return false;
		}
		
	}
	
	
	// Rule 4 - validation : Minimum size of house should be 1x1
	public boolean hasMinRowsCols (int rows, int columns) {
		
		if (rows >= minHouseRows && columns >= minHouseCols) {
			return true;
		}else {
			System.out.println("Min house size should be "+minHouseRows+"x"+minHouseCols);
			return false;
		}
	}

}