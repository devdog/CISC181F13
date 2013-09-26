package DL1;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class DL1_Tests extends TestCase {
    // Some sample DNA sequences
    public static final char[] TEST1 = "AGTA".toCharArray();
    public static final char[] TEST2 = "AAGT".toCharArray();
    public static final char[] TEST3 = "AATA".toCharArray();

    public static final char[] SAMPLE1 = "AGTGCTGAAAGTTGCGCCAGTGAC".toCharArray();
    public static final char[] SAMPLE2 = "AGTGCTGAAGTTCGCCAGTTGACG".toCharArray();
       
	/**
     * A JUnit test for your DotMatrix constructor.
     * 
     * You do not need to modify this method.
     */
    public void test_construct() {
        // Tests if constructing a DotMatrix generates correct result when given TEST1 and TEST2
        boolean[][] expected = {{true, true, false, false},
                                {false, false, true, false},
                                {false, false, false, true},
                                {true, true, false, false}};
        DotMatrix dm = new DotMatrix(TEST1, TEST2);
        
        assertTrue("expected:" + Arrays.deepToString(expected) + " but was:" +
                   Arrays.deepToString(dm.getMatrix()),
                   Arrays.deepEquals(expected, dm.getMatrix()));
        
        // print the 3 dot matrix that will be tested later
        DotMatrix dm1 = new DotMatrix(TEST1, TEST2);
        System.out.println(dm1.toString());
        DotMatrix dm2 = new DotMatrix(TEST1, TEST3);
        System.out.println(dm2.toString());
        DotMatrix dm3 = new DotMatrix(SAMPLE1, SAMPLE2);
        System.out.println(dm3.toString());
    }
    
    /**
     * A JUnit test for your diagonalMatchScore method.
     * 
     * You do not need to modify this method.
     */
    public void test_diagonalMatchScore() {
        DotMatrix dm1 = new DotMatrix(TEST1, TEST2);
        assertEquals(1, dm1.diagonalMatchScore(0, 0));
        assertEquals(3, dm1.diagonalMatchScore(0, 1));
        
        DotMatrix dm2 = new DotMatrix(TEST1, TEST3);
        assertEquals(2, dm2.diagonalMatchScore(0, 0));
        assertEquals(1, dm2.diagonalMatchScore(2, 0));
        assertEquals(0, dm2.diagonalMatchScore(0, 2));

        DotMatrix dm3 = new DotMatrix(SAMPLE1, SAMPLE2);
        assertEquals(9, dm3.diagonalMatchScore(0, 0));
        assertEquals(5, dm3.diagonalMatchScore(0, 7));
        assertEquals(3, dm3.diagonalMatchScore(15, 0));
        assertEquals(0, dm3.diagonalMatchScore(0, 23));
    }
    
    /**
     * A JUnit test for your maxDiagonalAlignment method.
     * 
     * You do not need to modify this method.
     */
    public void test_maxDiagonalAlignment() {
    	DotMatrix dm1 = new DotMatrix(TEST1, TEST2);
        assertEquals(3, dm1.maxDiagonalAlignment());
        
        DotMatrix dm2 = new DotMatrix(TEST1, TEST3);
        assertEquals(2, dm2.maxDiagonalAlignment());

        DotMatrix dm3 = new DotMatrix(SAMPLE1, SAMPLE2);
        assertEquals(9, dm3.maxDiagonalAlignment());
    }
    
    /**
     * A JUnit test for your findClosestMatch method.
     * 
     * You do not need to modify this method.
     */
    public void test_findClosestMatch() {
        H1N1Virus mystery = new H1N1Virus("TEST1", TEST1);
        ArrayList<H1N1Virus> possibleSources = new ArrayList<H1N1Virus>();
        possibleSources.add(new H1N1Virus("TEST2", TEST2));
        possibleSources.add(new H1N1Virus("TEST3", TEST3));
        
        H1N1Virus closest = mystery.findClosestMatch(possibleSources);
        assertEquals("TEST2", closest.getLocation());
    }
}
