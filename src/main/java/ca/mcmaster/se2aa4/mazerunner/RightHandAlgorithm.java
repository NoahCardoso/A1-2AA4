package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Stack;

public class RightHandAlgorithm extends MazeSolver {
	private Maze maze;
	private ArrayList<String> path; // Stores the path taken through the maze

	public RightHandAlgorithm(Maze maze){
		this.maze = maze;
		this.path = new ArrayList<String>();
	}

	// Only solves a straight maze, otherwise cannot solve the maze
	public boolean solve(){
		int y = maze.getY(); // Height of the maze
		int x = maze.getX(); // Width of the maze
	
        for(int j = 0; j < y; j++){
			// Looks for entrance on the left side
			if(maze.get(0, j) == 0){
				path.add("F"); // Move forward from the entrance
				int px = 0; // Previous x-coordinate
				int py = j; // Previous y-coordinate
				int cx = 1; // Current x-coordinate
				int cy = j; // Current y-coordinate
				
				while(cx < x-1){ // Continue until the right edge of the maze is reached
					int dx = cx - px; // Direction of movement in x
					int dy = cy - py; // Direction of movement in y
					int rx = -1*dy; // Right turn x-direction
					int ry = dx; // Right turn y-direction

					// Check right-hand side for open path
					if(rx+cx< x && ry+cy < y && rx+cx >= 0 && ry+cy >= 0){
						if(maze.get(rx+cx,ry+cy) == 0){
							px = cx;
							py = cy;
							cx += rx;
							cy += ry;
							path.add("R"); // Add right turn to path
							path.add("F"); // Move forward after turn
							continue;
						}
					}

					// Check forward for open path
					if(dx+cx< x && dy+cy < y && dx+cx >= 0 && dy+cy >= 0){
						if(maze.get(dx+cx,dy+cy) == 0){
							px = cx;
							py = cy;
							cx += dx;
							cy += dy;
							path.add("F"); // Move forward
							continue;
						}
					}

					// Check left-hand side for open path
					if(-1*rx+cx < x && -1*ry+cy < y && -1*rx+cx >= 0 && -1*ry+cy >= 0){
						if(maze.get(-1*rx+cx,-1*ry+cy) == 0){
							px = cx;
							py = cy;
							cx += -1*rx;
							cy += -1*ry;
							path.add("L"); // Add left turn to path
							path.add("F"); // Move forward after turn
							continue;
						}
					}

					// Turn around if no other paths are available
					if(dx-cx < x && -dy-cy < y && dx+cx >= 0 && dy+cy >= 0){
						px = cx;
						py = cy;
						cx -= dx;
						cy -= dy;
						path.add("R");
						path.add("R"); // Two right turns equal a 180-degree turn
						path.add("F"); // Move forward after turning around
					}
				}
				return true; // Maze solved
            }
		}
		return false; // No entrance found
	}

	// Verifies if the provided path leads to the maze exit
	public boolean checkPath(String path){
		int y = maze.getY();
		int x = maze.getX();
	
        for(int j = 0; j < y; j++){
			// Looks for entrance on the left side
			if(maze.get(0, j) == 0){
				int cx = 0;
				int cy = j;
				int dx = 1;
				int dy = 0;
				
				for(int i = 0; i < path.length(); i++){
					String value = "";
					// Extracts numbers indicating repeated moves
					while(Character.isDigit(path.charAt(i))){
						value += path.charAt(i);
						i++;
					}
					int v = 0;
					char dir = path.charAt(i);
					if(!value.equals("")){
						v = Integer.valueOf(value);
					}
					
					do{
						// Move forward if possible
						if(dir == 'F'){
							if(dx+cx < x && dy + cy < y){
								if(maze.get(dx+cx,dy+cy) == 0){
									cx += dx;
									cy += dy;
								}
							}
						}
						// Turn right
						else if(dir == 'R'){
							int temp = dx;
							dx = -1*dy;
							dy = temp;
						}
						// Turn left
						else if(dir == 'L'){
							int temp = dx;
							dx = dy;
							dy = -1*temp;
						}
						v--;
					} while(v > 0);
				}
				// Check if exit is reached
				if(cx == x-1){
					return true;
				}
			}
		}
		return false; // Path does not lead to exit
	}

	// Prints the canonical path taken through the maze
	public void printCanonicalPath(){
		for(int i = 0; i < path.size(); i++){
			System.out.print(path.get(i));
		}
		System.out.println(" ");
	}

	// Prints the factorized (condensed) version of the path
	public void printFactorizedPath(){
		for(int i = 0; i < path.size(); i++){
			int c = 1;
			// Condenses repeated characters
			if(i+1 < path.size()){
				while(path.get(i).equals(path.get(i+1))){
					c++;
					i++;
					if(i+1 >= path.size()){
						break;
					}
				}
				if(c > 1){
					System.out.print(c + path.get(i) + " ");
					continue;
				}
			}
			System.out.print(path.get(i) + " ");
		}
		System.out.println(" ");
	}
}