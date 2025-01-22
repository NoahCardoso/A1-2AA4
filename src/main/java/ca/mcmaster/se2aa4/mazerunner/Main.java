package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();

        options.addOption("i", true, "takes file");

        CommandLineParser parser = new DefaultParser();
        try {
            
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {

                String inputFile = cmd.getOptionValue("i");

                try {
                    
                    logger.info("**** Reading the maze from file " + inputFile);
                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        for (int idx = 0; idx < line.length(); idx++) {
                            if (line.charAt(idx) == '#') {
                                System.out.print("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                System.out.print("PASS ");
                            }
                        }
                        System.out.print(System.lineSeparator());
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
        
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }


}
