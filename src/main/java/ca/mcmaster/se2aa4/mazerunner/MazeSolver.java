package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Stack;

abstract class MazeSolver {
	// Abstract method to solve the maze
	// Implementing classes must define the logic to solve the maze
	abstract boolean solve();

	// Abstract method to check if a given path is valid within the maze
	// Implementing classes must define the validation logic
	abstract boolean checkPath(String path);

	// Abstract method to print the canonical (step-by-step) path through the maze
	// Implementing classes must define how the path is formatted and displayed
	abstract void printCanonicalPath();

	// Abstract method to print a factorized (condensed) version of the path
	// Implementing classes must define the logic to condense and display the path
	abstract void printFactorizedPath();
}