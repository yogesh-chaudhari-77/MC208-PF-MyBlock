/**
 * @author 		: Yogeshwar Girish Chaudhari 
 * Email		: s3828116@student.rmit.edu.au
 * Date			: 15-03-2020
 * Description 	: MyBlock is a simple tool which can help one design a block of land.  
 */
  
  
import java.util.InputMismatchException;
import java.util.Scanner;
/*
class MyBlock {
	// Changing type to Byte as block would be of size 3-10
	private byte[][] block;

	// Minimizing need of repetitively calculating len(rows) & len(cols) of block
	private byte blockRows;											
	private byte blockCols;		
	
	private boolean vacant;		
	
	// Maintains the number of house allocated in all blocks
	// Assumed it to be incremental at system level and not object specific so its static. Otherwise can be made private
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
		
		for(int i = 0; i < this.blockRows; i++) {
			for( int j = 0; j < this.blockCols ; j++) {
				
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
		MyBlock.currentHouse = 1;			// House numbering will start with 1 again on clearing block
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

}*/

/**
 * @author 		: Yogeshwar Girish Chaudhari 
 * Email		: s3828116@student.rmit.edu.au
 * Date			: 15-03-2020
 * Description 	: This is the driver code for the program  
 */

public class ProgFunAssignment1 {
	
	private MyBlock newBlock = null;		
	private Scanner console = null;
	
	// Exclusive boundary
	private static final byte minRowColStart = 2;		

	// Inclusive boundary 
	private static final byte maxRowColEnd = 10;		
	
	// Constants that will be used as menu options
	private static final byte optAddHouse = 1;		 
	
	// Menu option for Displaying block
	private static final byte optDisplayBlk = 2;		
	
	// Menu option for Clearing Block
	private static final byte optClearBlk = 3;		
	
	// Menu option for terminating the program
	private static final byte optQuit = 4;		
	
	// This variable is set when user selects quit option
	private static boolean quit	= false;
		
	// Toggle debugging messages logging
	private static final boolean debugging_mode = false;	
	
	
	public ProgFunAssignment1() {
		console = new Scanner(System.in);
	}
	
	
	public static void main (String[] args) {		
		
		ProgFunAssignment1 driver = new ProgFunAssignment1();
		
		int rows = driver.acceptRows();
		int cols = driver.acceptCols();
		
		driver.newBlock = new MyBlock(rows,cols);
		
		driver.run();
		
		driver.closeResources();
		
		System.out.println("Program terminated successfully.");
		System.exit(0);
	}
	// End of main()
	
	
	// Number of Rows for the block
	public int acceptRows() {
		int rows = 0;
		System.out.println("House Allocation Program");
		System.out.println("=================================");
		
		// Separating inputs to show errors right away for better UX
		do {
			try {
				System.out.print("Please Enter Rows : ");
				rows = console.nextInt();
			} catch (InputMismatchException ime) {
				console.nextLine();
			}
		} while (! validateColsAndRows(rows, "Rows must be greater than "+minRowColStart+" and less than equal to "+maxRowColEnd+". Please try again"));
		
		return rows;
	}
	
	
	// Number of Columns for the block
	public int acceptCols() {
		int cols = 0;
		do {
			try {
				System.out.print("Please Enter Cols : ");
				cols = console.nextInt();
			} catch (InputMismatchException ime) {
				console.nextLine();
			}
		} while (! validateColsAndRows(cols, "Columns must be greater than "+minRowColStart+" and less than equal to "+maxRowColEnd+". Please try again.") );
		
		return cols; 
		
	}

	
	public void run() {
		
		// Keep program running until user selects quit option
		do {
			try {
				this.printMenu();
				byte userChoice = (byte) this.console.nextInt();
				this.loadOption(userChoice);
			} 
			catch ( InputMismatchException nse ) {
				
				//Log this event but keep the loop running
				ProgFunAssignment1.console_log("Invalid input. User entered something other than a number");
				System.out.println("Please enter choice from given option");
						
				// Advance scanner / flush previous non-int input
				this.console.nextLine();						
			}
			
		} while (ProgFunAssignment1.quit == false);
		
	}
	
	
	// Helper function for validating rows and columns for the block
	static boolean validateColsAndRows(int val, String errorMsg) {
		if ( val <= minRowColStart || val > maxRowColEnd ) {
			System.out.println(""+errorMsg);
			return false;
		}
		return true;
	}
	
	
	public void printMenu() {	
		
		System.out.println("----------------------\n\tMenu\n----------------------");
		
		System.out.println("1. Add A House");
		System.out.println("2. Display The Block");
		System.out.println("3. Clear Block");
		System.out.println("4. Quit");
		System.out.println("----------------------");
		
		System.out.print("Enter Your Choice : ");
	}
	

	// Association of number with the functionality
	public void loadOption(byte userChoice) {
		
		switch (userChoice) {
		
			// Add House
			case optAddHouse : {
				
				System.out.print("Enter Row Position 	: ");
				byte rowPos = (byte) console.nextInt();
				
				System.out.print("Enter Column Position   : ");
				byte colPos = (byte) console.nextInt();
				
				System.out.print("Enter Number Of Rows    : ");
				byte rows = (byte) console.nextInt();
				
				System.out.print("Enter Number Of Columns : ");
				byte columns = (byte) console.nextInt();
				
				if (! this.newBlock.addHouse(rowPos, colPos, rows, columns) ){
					System.out.println("Unable to add house. Please try again.\n");
				}
			}
			break;
	
			// Display block
			case optDisplayBlk :{ 
				this.newBlock.displayBlock();
			}
			break;
	
			// Clear Block
			case optClearBlk :{
				this.newBlock.clearBlock();
				System.out.println("The Block Has Been Cleared");
			}
			break;
	
			// Terminate the program
			case optQuit :{ 
				ProgFunAssignment1.quit = true;
				System.out.println("Terminating Program");
			}
			break;
	
			default: 
				System.out.println("Incorrect Choice. Please try again from below options.");
		}
		
	}
	

	// Helper Method - Method for logging error messages
	public static void console_log(String msg) {	
		if (debugging_mode) {
			System.out.println(msg);
		}
	}
	
	
	// Method for closing all opened resources before existing program
	public void closeResources(){
		try {
			this.console.close();
		}
		catch ( Exception e ) {
			console_log("Error occured while closing console input");
		}
	}

}
