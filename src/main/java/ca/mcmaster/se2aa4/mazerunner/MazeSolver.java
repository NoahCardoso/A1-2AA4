package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Stack;

abstract class MazeSolver {
	abstract boolean solve();
	abstract boolean checkPath(String path);
	abstract void printCanonicalPath();
	abstract void printFactorizedPath();

}