package DL1;


public class DotMatrix {
	private char[] sequence1;
	private char[] sequence2;
	private boolean[][] matrix;
	
	/**
     * Computes a Boolean matrix where a match between seq1 and seq2 at a position
     * would be true, otherwise false.
     * 
     * @param seq1
     * @param seq2
     */
	public DotMatrix(char[] sequence1, char[] sequence2) {
	    this.sequence1 = sequence1;
	    this.sequence2 = sequence2;
	    this.matrix= new boolean[sequence1.length][sequence2.length];
	    for(int i=0; sequence1.length*sequence2.length>i; i++){
	    	matrix[i/sequence2.length][i%sequence2.length]=sequence1[i/sequence2.length]==sequence2[i%sequence2.length];
	    }
	}
	
	public char[] getSequence1() {
		return sequence1;
	}

	public char[] getSequence2() {
		return sequence2;
	}

	public boolean[][] getMatrix() {
		return matrix;
	}
	
	/**
	 * Computes the maximum sequential number of matches in a diagonal
	 * path starting at the given row and column and going 
	 * "downwards to the right" in the matrix.
	 * 
	 * @param row
	 * @param column
	 * @return the max score
	 */
	public int diagonalMatchScore(int row, int column) {
		int countMax =0;
	    int count = 0;
	    for(int i=0; sequence1.length-row>i && sequence2.length-column>i; i++){
	    	count = 0;
	    	while(sequence1.length-row>i && sequence2.length-column>i && matrix[row+i][column+i]){
	    		count++;
	    		i++;
	    	}
	    	if(count>countMax){
	    		countMax=count;
	    	}
	    }
        return countMax; // change this too!
	}
	
	/**
	 * Computes the maximum diagonalMatchScore of any diagonal in
	 * the matrix (diagonals "start" at the left of each row
	 * and at the top of each column).
	 * 
	 * @return the max overall score
	 */
	public int maxDiagonalAlignment() {
		int maxOverallScore = 0;

        int maxScore = 0;

        for (int row = 0; row < matrix.length; row++) {

                        maxScore = diagonalMatchScore(row, 0);

                        if (maxScore > maxOverallScore) {

                                        maxOverallScore = maxScore;

                        }

        }

        for (int col = 1; col < matrix[0].length; col++) {

                        maxScore = diagonalMatchScore(0, col);

                        if (maxScore > maxOverallScore) {

                                        maxOverallScore = maxScore;

                        }

        }

        return maxOverallScore;
    }
    
    /**
     * Returns a String dot matrix representation of two sequences being aligned. Places the
     * seq1 value down the leftmost column, and seq2 above the topmost row. Places an
     * x at every place that is true in the dotMatrix.
     * 
     * @param seq1
     * @param seq2
     * @param dotMatrix
     */
    public String toString() {
    	StringBuilder b = new StringBuilder();
        b.append("  ");
        for (int i = 0; i < sequence2.length; i++) {
            b.append(sequence2[i]);
            b.append(" ");
        }
        b.append("\n");
        
        b.append(" +");
        for (int i = 0; i < sequence2.length; i++) {
            b.append("--");
        }
        b.append("\n");
        
        for (int i = 0; i < sequence1.length; i++) {
            b.append(sequence1[i]);
            b.append("|");
            for (int j = 0; j < sequence2.length; j++) {
                b.append(matrix[i][j] ? "x" : " ");
                b.append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }
}
