package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {

	private int[][] maze;
	private int X;
	private int Y;

	//Takes a list of a maze and transforms it into a 2D array
	public Maze(ArrayList<Integer> mazeList, int totalLines){
		this.Y = totalLines;
		//calcuates number of columns needed
		this.X = mazeList.size() / totalLines;
		this.maze = new int[X][Y];

		//initalizes the 2D array
		for(int i = 0; i < Y; i++){
			for(int j = 0; j < X; j++){

				maze[j][i] = mazeList.get(Y * i + j);
				
			}
			
		}
		
	}

	//Sets the value of a position in the 2D array to value
	public void set(int x, int y, int value){
		if(x < X && y < Y && x >= 0 && y >= 0){
			maze[x][y] = value;
		}else{
			throw new IllegalArgumentException("Invalid position");
		}
		
	}

	//Returns the value at a position in the maze
	public int get(int x, int y){
		if(x < X && y < Y && x >= 0 && y >= 0){
			return (maze[x][y]);
		}else{
			throw new IllegalArgumentException("Invalid position");
		}
		
	}

	public int getY(){
		return Y;
	}

	public int getX(){
		return X;
	}
   
}
