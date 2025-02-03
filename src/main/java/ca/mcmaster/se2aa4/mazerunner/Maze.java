package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {

	private int[][] maze; // 2D array representing the maze grid
	private int X; // Number of columns in the maze
	private int Y; // Number of rows in the maze

	// Constructor: Takes a list representing the maze and transforms it into a 2D array
	public Maze(ArrayList<Integer> mazeList, int totalLines){
		this.Y = totalLines; // Set the number of rows
		this.X = mazeList.size() / totalLines; // Calculate the number of columns
		this.maze = new int[X][Y]; // Initialize the 2D array

		// Populate the 2D array with values from the maze list
		for(int i = 0; i < Y; i++){
			for(int j = 0; j < X; j++){
				maze[j][i] = mazeList.get(X * i + j); // Map list elements to 2D array
			}
		}
	}

	// Sets the value of a specific position in the maze
	public void set(int x, int y, int value){
		if(x < X && y < Y && x >= 0 && y >= 0){ // Check if the position is within bounds
			maze[x][y] = value;
		}else{
			throw new IllegalArgumentException("Invalid position"); // Throw error for invalid positions
		}
	}

	// Returns the value at a specific position in the maze
	public int get(int x, int y){
		if(x < X && y < Y && x >= 0 && y >= 0){ // Check if the position is within bounds
			return maze[x][y];
		}else{
			throw new IllegalArgumentException("Invalid position"); // Throw error for invalid positions
		}
	}

	// Returns the number of rows in the maze
	public int getY(){
		return Y;
	}

	// Returns the number of columns in the maze
	public int getX(){
		return X;
	}

}