package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

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
		int rows = maze.getRows();
		int columns = maze.getColumns();

        for(int i = 0; i < rows; i++){
			//looks for entrance on left side
			if(maze.get(i, 0) == 0){
				//keeps moving towards the right untill it hits a wall
				// or till it reachs the exit
                for(int j = 0; j < columns; j++){
					//check if it can move toward the right
                    if(maze.get(i, j) == 0){
                        path.add("R");
                        continue;
                    }
					//if it is blocked then no straight path exists
                    else{
                        return false;
                    }
                }
				//has reached the end of the maze without hitting a wall
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