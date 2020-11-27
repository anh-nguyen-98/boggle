
import java.util.*;

/** Represents the computer player in the Boggle game. 
 *  Finds all legal word combinations on the Boggle board.
 * 
 * @author Nguyen Thu Huyen
 * @author Nguyen Hoang Nam Anh
 * 
 * Time spent: 2.5 hours
*/
public class AutoPlayer extends AbstractAutoPlayer {
    
    public List<String> findAllValidWords(BoggleBoard board, ILexicon lex) {
        List<String> listWords = new ArrayList<String>();

        String sequence = "";
        List<BoardCell> listCells = new ArrayList<BoardCell>();

        for (int row = 0; row < board.size(); row++){
            for (int col = 0; col < board.size(); col++){
                explore(board, lex, listWords, sequence, listCells, row, col);
            }
        } 
        return listWords;
    }

    /**
     * Helper method for findAllValidWords.
     * 
     * Explores all sequences on the Boggle board in order to find as many
     * legal words as possible.
     * 
     * @param board the Boggle board.
     * @param listWords the list of legal words found on the Boggle board.
     * @param sequence the sequence of letters that is being built, which may 
     * be growing into either a word, a prefix, or neither of them.
     * @param listCells the list of cells currently being chosen to form 
     * the word.
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     */
    private static void explore(BoggleBoard board, ILexicon lex,
            List<String> listWords, String sequence, List<BoardCell> listCells,
            int row, int col){
        if (cellExists(board, row, col)){
            BoardCell cell = new BoardCell(row, col);
            if (!listCells.contains(cell)){
                String newSequence = sequence + board.getFace(cell);
                LexStatus sequenceStatus = lex.wordStatus(newSequence);
                if (!sequenceStatus.equals(LexStatus.NOT_WORD)){
                    listCells.add(cell);
                    if (sequenceStatus.equals(LexStatus.WORD)){
                        listWords.add(newSequence);
                    }
                    // explores north
                    explore(board, lex, listWords, newSequence, listCells, 
                            row-1, col);

                    // explores south
                    explore(board, lex, listWords, newSequence, listCells, 
                            row+1, col);

                    // explores west
                    explore(board, lex, listWords, newSequence, listCells, 
                            row, col-1);

                    // explores east
                    explore(board, lex, listWords, newSequence, listCells, 
                            row, col+1);

                    // explores northwest
                    explore(board, lex, listWords, newSequence, listCells, 
                            row-1, col-1);

                    // explores northeast
                    explore(board, lex, listWords, newSequence, listCells, 
                            row-1, col+1);

                    // explores southwest
                    explore(board, lex, listWords, newSequence, listCells, 
                            row+1, col-1);

                    // explores southeast
                    explore(board, lex, listWords, newSequence, listCells, 
                            row+1, col+1);

                    listCells.remove(cell);
                }             
            }
        }
    }

    /**
     * Returns whether or not the cell exists.
     * 
     * A cell is considered to exist if it exists on the Boggle
     * board (i.e. in-bound indexed cell).
     * 
     * @param board the Boggle board.
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * 
     * @return true iff the cell exists.
     */
    private static boolean cellExists(BoggleBoard board,
    int row, int col){                                    
        return row >= 0 && row < board.size() &&
                                            col >= 0 && col < board.size(); 
    }
}