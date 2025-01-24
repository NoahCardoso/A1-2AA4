package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;
import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Define command-line options
        Options options = new Options();
        options.addOption("i", true, "takes file");
        CommandLineParser parser = new DefaultParser();

        //Tracks the number of lines in the maze file
        int lineCount = 0;
        ArrayList<Integer> mazeList = new ArrayList<>();

        try {
            // Parse command-line arguments
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {

                // Get the input file path
                String inputFile = cmd.getOptionValue("i");

                try {
                    
                    logger.info("**** Reading the maze from file " + inputFile);
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Convert each line of the maze to a list of integers (1 for walls, 0 for spaces)
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                mazeList.add(1); // Add WALL
                            } else if (line.charAt(idx) == ' ') {
                                mazeList.add(0); // Add PASS
                            }
                        }
                        lineCount++;
                    }
                    

                } catch(Exception e) {
                    logger.error("/!\\ An error has occured /!\\");
                }
                
            } else {
                logger.error("No input file provided.");
            }
            
        } catch (ParseException e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        // Create a Maze object and attempt to solve it
        Maze maze = new Maze(mazeList, lineCount);
        MazeSolver path = new MazeSolver(maze);
        
        logger.info("**** Computing path");
        if(path.Solve()){
            path.printCanonicalPath();
        }
        else{
            logger.warn("PATH NOT COMPUTED");
        }

        logger.info("** End of MazeRunner");
    }


}
