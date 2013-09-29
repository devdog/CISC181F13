package DL1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class H1N1Virus {
    private String location;
    private char[] sequence;
    
    public H1N1Virus(String location, char[] sequence) {
        this.location = location;
        this.sequence = sequence;
    }
    
    public String getLocation() {
        return location;
    }

    public char[] getSequence() {
        return sequence;
    }
    
    public String toString() {
        return getLocation();
    }
     
    /**
     * Compares this GenomeSequence to each of the given possible sources
     * by constructing a DotMatrix for this sequence and each possible source,
     * and then using the maxDiagonalAlignment() method to find the closest
     * match. The closest match is the possible source with the highest
     * maxDiagonalAlignment score with this sequence.
     * 
     * Make sure you only compute the alignment score once for each
     * possible source.
     * 
     * @param sequence
     * @param sequences
     * @return
     */
    public H1N1Virus findClosestMatch(ArrayList<H1N1Virus> possibleSources) {
    	int score = 0; 

        int maxScore = 0;

        H1N1Virus closest = null;

        for (H1N1Virus p : possibleSources) {

                        DotMatrix dm = new DotMatrix(this.getSequence(), p.getSequence());

                        score = dm.maxDiagonalAlignment();

                        if (score > maxScore) {

                                        maxScore = score;

                                        closest = p;

                        }

        }

        return closest;
    }
    
    /**
     * Reads a specific genome sequence file format.  Scans comment lines for information
     * about locations, and parses sequences into the result.
     * 
     * @param fileName
     * @throws Exception
     */
    public static ArrayList<H1N1Virus> loadSequencesFromFile(String fileName) throws Exception {
        ArrayList<H1N1Virus> result = new ArrayList<H1N1Virus>();
        
        Scanner in = new Scanner(new File(fileName));
        in.useDelimiter("\r\n|[\n\r]"); // parse each line in the file
        String location = null;
        while (in.hasNext()) {
            String line = in.next();
            if (line.startsWith(";")) {
                // it is a comment, check to see if it marks a location
                if (line.startsWith(";A")) {
                    // get the location information
                    location = line.split("/")[1];
                }
            }
            else if (line.length() != 0) { // it is not empty
                // convert the lower case format to upper case and remove blanks
                result.add(new H1N1Virus(
                    location, line.toUpperCase().replace(" ", "").toCharArray()));
            }
        }
        in.close();
        return result;
    }
      
    public static void main(String[] args) throws Exception {
        ArrayList<H1N1Virus> sequences = loadSequencesFromFile("./src/DL1/influenza.txt");
        // assume the first sequence is the test, we want to find which 
        //  other sequence is its closest match
        H1N1Virus mystery = sequences.remove(0);
        
        System.out.println("Determining which sequence is the most likely known source of the " +
            mystery + " influenza strain...");

        H1N1Virus closestMatch = mystery.findClosestMatch(sequences);
        
        System.out.println("The closest match and most likely known source of the " + 
            mystery + " influenza strain is " + closestMatch);
        
    }
}
