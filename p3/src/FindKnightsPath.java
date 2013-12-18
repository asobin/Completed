///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p3
// Files:            FindKnightsPath.java; ListNode.java; QueueADT.java;
//					 SimpleQueue.java; SimpleStack.java; StackADT.java;
//					 StackADT.java; EmptyQueueException.java;
//					 EmptyStackException.java
// Semester:         CS367 Fall 2013
//
// Author:           David Maman
// Email:            dmaman@wisc.edu
// CS Login:         maman
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Alexander Sobin
// Email:            asobin@wisc.edu
// CS Login:         sobin
// Lecturer's Name:  Jim Skrentny
// Lab Section:      N/A
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * FindKnightsPath is an application designed to solve the classic chess problem
 * involved in moving the knight around the board without retracing steps.
 * In our modified version, the knight starts at a position specified in the 
 * command-line args and moves to an ending position also specified in the 
 * command-line args. The output is virtual representation of the knights path
 * which is solved using our implementation of recursive algorithms using 
 * queue and stack data structures.
 *  
 * <p>Bugs: none known, some bizarre command-line args perhaps.
 *
 * @authors maman, sobin
 */
public class FindKnightsPath 
{
	private static int [][] board; //2D array board represents board
	private static String [][] board2; //2D array board2 represents board
	private static String regex = ","; //String regex for parsing args
	private final static int[][] MOVES = //initializes board moves
	{ 
		{ -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, 
		{ -2, -1 },{ -2, 1 }, { 2, -1 }, { 2, 1 } 
		};
	private static int startLocRow; // represents starting row location 
	private static int startLocCol; // represents starting column location
	private static int endLocRow; // represents ending row location
	private static int endLocCol; // represents ending column location
	private static String startLocationArgs; // represents args for starting
											// location on board
	private static String endLocationArgs; //represents ending location args
	//initializes the queue kn that holds all the positions of the neighbors
	//it is used to enqueue and check the locations of the neighbors
	private static SimpleQueue<String> kn = new SimpleQueue<String>();
	//initializes the stack path that holds the locations of the path that
	//the knight takes
	private static SimpleStack<String> path = new SimpleStack<String>();
	//boolean done used to determine if findPath is done
	private static boolean done = false;

	/**
	 * main method for FindKnightsPath will initialize, solve, and display the 
	 * knights path. The application gets the beginning and ending locations 
	 * via command-line arguments. It then determines and outputs a valid path.
	 * @param args
	 * @throws EmptyStackException
	 * @throws EmptyQueueException
	 */
	public static void main (String[] args) throws EmptyStackException, 
	EmptyQueueException
	{
		board = new int[8][8]; //creates 2D board 8 by 8 in size
		board2 = new String [8][8]; //creates 2D board 8 by 8 in size
		if(args.length == 0 || args == null || args.length == 1)
		{
			System.out.println("Please enter valid starting and ending " +
					"locations between 0 and 7");
			System.exit(-1);
		}
		if(args.length > 3)
		{
			System.out.println("You may only enter up to three arguments.");
			System.exit(-1);
		}

		String [] startlocation = null; //the starting location specified in 
									   //commmand-line args
		String [] endlocation = null; //the ending location specified in 
									 //command-line args
		startlocation = args[0].split(regex);
		endlocation = args[1].split(regex);
		try
		{
			startLocRow = Integer.parseInt(startlocation[0]);
			startLocCol = Integer.parseInt(startlocation[1]);
			endLocRow = Integer.parseInt(endlocation[0]);
			endLocCol = Integer.parseInt(endlocation[1]);
			if(checkPos(startLocRow, startLocCol, endLocRow,endLocCol) == true)
			{
				startLocationArgs = args[0];
				endLocationArgs = args[1];
				initilizeBoard(startLocRow, startLocCol);
				findPath(endLocRow,endLocCol);
				if(args.length == 2)
				{
					printPath();
				}
				else if(args[2].equals("-n"))
				{
					printBoard();
					System.out.println("");
					printPath();
				}
				else
				{
					System.out.println("To see the numbered board, " +
							"the last argument should be '-n'");
					System.exit(-1);
				}

			}
			else
			{
				System.out.println("Positions must be between 0 and 7");
				System.exit(-1);
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please enter valid starting and ending " +
					"locations between 0 and 7");
			System.exit(-1);
		}
	}
	/**
	 * findPath method is done in two steps. The first step numbers the squares
	 * on the board from the starting location using the queue algorithm.
	 * ... if the '-n' option is provided in the command-line argument, the the
	 * program displays the numbered board determined by the queue algorithm
	 * before displaying the knight's path that is found.
	 * The second step requires the stack algorithm, which determines the path
	 * by popping the locations off of the stack from start to finish.
	 * 
	 * @param row
	 * @param col
	 */
	private static void findPath(int row, int col)
	{
		board2[startLocRow][startLocCol] = "1";
		path.push(row+""+col);
		int currNum; //current value assigned to a position on board
		currNum = board[row][col];
		int tempRow; //the temporary row whose value will be looked at for path
		int tempCol;//temporary column whose value will be looked at for path
		for(int i = 0; i < MOVES.length && done != true; i++)
		{
			/*tempRow and tempCol represent the possible legal moves
    used to not overwrite currLocR and currLocC
			 */
			tempRow = row + MOVES[i][0];
			tempCol = col + MOVES[i][1];

			if(neighborIsValid(tempRow,tempCol)== true)
			{
				if(tempRow == startLocRow && tempCol == startLocCol)
				{
					done = true;
					return;
				}
				else if(board[tempRow][tempCol] == currNum-1)
				{
					//recursively to find the rest of the path
					findPath(tempRow,tempCol);
				} //end else if 
			} //end if (neighborIsValid...)
		} //end for
	} //end find path
	/**
	 * printPath will print out the numeric outputs of the findPath method
	 * onto the board.
	 * @throws EmptyStackException
	 */
	private static void printPath() throws EmptyStackException   
	{
		System.out.println("A path from " + startLocationArgs + " to "  
				+ endLocationArgs + " is:");
		clearZeros(board2);
		for(int k = path.size(); k > 0; k--) 
		{
			String pos = path.pop();
			int row2 = Character.getNumericValue(pos.charAt(0));
			int col2 = Character.getNumericValue(pos.charAt(1));
			board2[row2][col2] = Integer.toString(board[row2][col2]);
		}
		System.out.println("    0   1   2   3   4   5   6   7");
		for(int i = 0; i < board[0].length; i++) 
		{
			System.out.println("   ------------------------------- " );
			for(int j = 0; j < board[0].length; j++) 
			{
				if(j == 0) System.out.print(i + " | " );
				System.out.print( board2[i][j] + " | " );
			}
			System.out.print( "\n" );
		}
		System.out.println( "   ------------------------------- " );
	}
	/**
	 * initializeBoard method sets up the design of the board, and initializes
	 * the values of all the positions on the board.
	 * @param row
	 * @param col
	 * @throws EmptyQueueException
	 */
	private static void initilizeBoard(int row, int col)
	throws EmptyQueueException 
	{
		kn.enqueue(row +""+ col);
		boolean done = false;
		board[startLocRow][startLocCol] = 1;
		int currNum = 1; //starting position is at 1
		while(!done)
		{
			String pos = kn.dequeue();
			int row2 = Character.getNumericValue(pos.charAt(0));
			int col2 = Character.getNumericValue(pos.charAt(1));
			if(board[row2][col2] != currNum)
			{
				currNum++;
			}
			if(row2 == endLocRow && col2 == endLocCol)
			{
				done = true;
			}
			else
			{
				int tempRow = 0; //initialize temp row to zero for board layout 
				int tempCol = 0; //initialize temp col to zero for board layout
				for(int i = 0; i < MOVES.length; i++)
				{
					tempRow = row2 + MOVES[i][0];
					tempCol = col2 + MOVES[i][1];
					//make sure neighbor is a valid move
					if(neighborIsValid(tempRow,tempCol)==true)
					{
						//check if position on board has already been visited
						if(board[tempRow][tempCol] < currNum &&
								board[tempRow][tempCol] != 0 )
						{
							//do not change currNum
						}
						else
						{
							board[tempRow][tempCol] = currNum +1;
							kn.enqueue(tempRow + "" +tempCol);

						} //end else
					} //end if(neighborIsValid..)
				} //end for
			} // end else
		} //end while
	} //end initializeBoard
	/**
	 * clearZeros method is designed to take the zeros out for when the -n 
	 * command is called in the command-line arguments. This results in the
	 * board position whose value is zero being blank when the board is printed
	 * out.
	 * @param a
	 */
	private static void clearZeros(String[][] a)
	{
		for(int i = 0; i < a[0].length; i++)
		{
			for(int j = 0; j < a[0].length; j++ )
			{
				if(a[i][j] == null)
				{
					a[i][j] = " ";
				}
				if(a[i][j] == "0")
				{
					a[i][j]= " ";
				} //end if
			} //end for 
		} //end for
	} //end clearZeros
	/**
	 * checkPos is a method that makes sure that the path taken by the knight
	 * is valid ie. within the perimeter of the board.
	 * @param sRow
	 * @param sCol
	 * @param eRow
	 * @param eCol
	 * @return true if the pos is within board perimeter, false if not.
	 */
	private static boolean checkPos(int sRow, int sCol,
			int eRow, int eCol) 
	{
		if(sRow < 0 || sRow > 7)
		{
			return false;
		}
		if(sCol < 0 || sCol > 7)
		{
			return false;
		}
		if(eRow < 0 || eRow > 7)
		{
			return false;
		}
		if(eCol < 0 || eCol > 7)
		{
			return false;
		}
		return true;
	}
	/**
	 * neighborIsValid method will make sure the next knight move or neighbor
	 * as we call it in our program is within the perimeter of the board.
	 * @param row
	 * @param col
	 * @return true iff pos is within board perimeter, else false.
	 */
	public static boolean neighborIsValid(int row, int col)
	{
		if(row < 0 || row > 7)
		{
			return false;
		}
		if(col < 0 || col > 7)
		{
			return false;
		}
		return true;  
	}
	/**
	 * printBoard method is used to print out the board 
	 */
	public static void printBoard()
	{
		System.out.println("The numbered board for " + startLocationArgs + 
				" to "  + endLocationArgs + " is:");
		System.out.println("    0   1   2   3   4   5   6   7");
		for(int i = 0; i < board[0].length; i++) 
		{
			System.out.println("   ------------------------------- " );
			for(int j = 0; j < board[0].length; j++) 
			{
				if(j == 0) System.out.print(i + " | " );
				if(board[i][j] == 0)
				{
					System.out.print("  | " );
				}
				else
				{
					System.out.print( board[i][j] + " | " );
				} //end else
			} //end for
			System.out.print( "\n" );
		} //end for
		System.out.println( "   ------------------------------- " );
	} //end printBoard
} //end FindKnightsPath
