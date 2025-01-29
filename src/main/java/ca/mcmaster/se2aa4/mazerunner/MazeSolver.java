package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver {
	private Maze maze;
	private ArrayList<String> path; //stores the path taken through the maze

	public MazeSolver(Maze maze){
		this.maze = maze;
		this.path = new ArrayList<String>();
	}

	//Only solves the straight maze
	//Otherwise cannot solve the maze
	public boolean Solve(){
		int y = maze.getY();
		int x = maze.getX();
	

        for(int j = 0; j < y; j++){
			//looks for entrance on left side
			
			if(maze.get(0, j) == 0){
				path.add("F");
				int px = 0;
				int py = j;
				int cx = 1;
				int cy = j;
				
				
				maze.set(cx,cy,2);
				while(cx < x-1){
					int dx = cx - px;
					int dy = cy - py;
					int rx = -1*dy;
					int ry = dx;
					
					if(maze.get(rx+cx,ry+cy) == 0){
						px = cx;
						py = cy;
						cx += rx;
						cy += ry;
						
						path.add("R");
						path.add("F");
						
					}
					else if(maze.get(dx+cx,dy+cy) == 0){
						px = cx;
						py = cy;
						cx += dx;
						cy += dy;
						path.add("F");
					}
					else if(maze.get(-1*rx+cx,-1*ry+cy) == 0){
						px = cx;
						py = cy;
						cx += -1*rx;
						cy += -1*ry;
						path.add("L");
						path.add("F");
						
					}
					else{
						px = cx;
						py = cy;
						cx -= dx;
						cy -= dy;
						path.add("R");
						path.add("R");
						path.add("F");
					}

					
				}
				maze.set(cx,cy,2);
				return true;
            }
		}
		//no entrance found
        return false;

	}

	public void printCanonicalPath(){
		System.out.print("Canonical Path: ");
		//prints each step taken through the maze
		for(int i = 0; i < path.size(); i++){
			System.out.print(path.get(i));
		}
		System.out.println(" ");
	}

}