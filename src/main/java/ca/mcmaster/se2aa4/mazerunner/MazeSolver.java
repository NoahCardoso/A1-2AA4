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
				
				while(cx < x-1){
					int dx = cx - px;
					int dy = cy - py;
					int rx = -1*dy;
					int ry = dx;
					if(rx+cx< x && ry+cy < y && rx+cx >= 0 && ry+cy >= 0){
						if(maze.get(rx+cx,ry+cy) == 0){
							px = cx;
							py = cy;
							cx += rx;
							cy += ry;
							
							path.add("R");
							path.add("F");
							continue;
							
						}
					}
					if(dx+cx< x && dy+cy < y && dx+cx >= 0 && dy+cy >= 0){
						if(maze.get(dx+cx,dy+cy) == 0){
							px = cx;
							py = cy;
							cx += dx;
							cy += dy;
							path.add("F");
							continue;
						}
					}
					if(-1*rx+cx < x && -1*ry+cy < y && -1*rx+cx >= 0 && -1*ry+cy >= 0){
						if(maze.get(-1*rx+cx,-1*ry+cy) == 0){
							px = cx;
							py = cy;
							cx += -1*rx;
							cy += -1*ry;
							path.add("L");
							path.add("F");
							continue;
							
						}
					}
					if(dx-cx < x && -dy-cy < y && dx+cx >= 0 && dy+cy >= 0){
						px = cx;
						py = cy;
						cx -= dx;
						cy -= dy;
						path.add("R");
						path.add("R");
						path.add("F");
					}
					//System.out.println(cx+ " " + cy + " " +dx+ " " + dy);
					

					
				}
				
				return true;
            }
		}
		//no entrance found
        return false;

	}
	public boolean checkPath(String path){
		int y = maze.getY();
		int x = maze.getX();
	

        for(int j = 0; j < y; j++){
			//looks for entrance on left side
			
			if(maze.get(0, j) == 0){
				
				int cx = 0;
				int cy = j;
				int dx = 1;
				int dy = 0;
				
				for(int i = 0; i < path.length(); i++){
					
					String value = "";
					while(Character.isDigit(path.charAt(i))){
						value = value + path.charAt(i);
						i++;
					}
					int v = 0;
					char dir = path.charAt(i);
					if(!(value.equals(""))){
						v = Integer.valueOf(value);
					}
					
					do{
						//System.out.println(cx + " " + cy + " " + dx + " " + dy);
						if(dir == 'F'){
							if(dx+cx < x && dy + cy < y){
								if(maze.get(dx+cx,dy+cy) == 0){
									cx += dx;
									cy += dy;
								}
							}
						}
						else if(dir == 'R'){
							int temp = dx;
							dx = -1*dy;
							dy = temp;
						}
						else if(dir == 'L'){
							int temp = dx;
							dx = dy;
							dy = -1*temp;
						}
						v--;
					}while(v > 0);
					

				}
				if(cx == x-1){
					return true;
				}
			}
			
		}
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
	public void printFactorizedPath(){
		System.out.print("Factorized Path: ");
		//prints each step taken through the maze
		for(int i = 0; i < path.size(); i++){
			int c = 1;
			if(i+1<path.size()){
				while(path.get(i).equals(path.get(i+1))){
					c++;
					i++;
					if(i+1>=path.size()){
						break;
					}
				}
				if(c>1){
					System.out.print(c+path.get(i)+" ");
					continue;
				}
				
			}
			
			System.out.print(path.get(i)+" ");
				
			
		}
		
		System.out.println(" ");
	}

}