package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {

	private int[][] maze;
	private int rows;
	private int columns;

	//Takes a list of a maze and transforms it into a 2D array
	public Maze(ArrayList<Integer> mazeList, int totalLines){
		this.rows = totalLines;
		//calcuates number of columns needed
		this.columns = mazeList.size() / totalLines;
		this.maze = new int[rows][columns];

		//initalizes the 2D array
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				maze[i][j] = mazeList.get(columns * i + j);

			}
		}
	}

	//Sets the value of a position in the 2D array to value
	public void set(int x, int y, int value){
		if(x < rows && y < columns && x >= 0 && y >= 0){
			maze[x][y] = value;
		}
		else{
			throw new IllegalArgumentException("Invalid position");
		}
		
	}

	//Returns the value at a position in the maze
	public int get(int x, int y){
		if(x < rows && y < columns && x >= 0 && y >= 0){
			return (maze[x][y]);
		}else{
			throw new IllegalArgumentException("Invalid position");
		}

		
	}

	public int getColumns(){
		return columns;
	}

	public int getRows(){
		return rows;
	}
   
}
