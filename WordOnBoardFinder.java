
import java.util.*;

/**
 * Finds whether and where a given word occurs on the board.
 * 
 * @author Nguyen Thu Huyen
 * @author Nguyen Hoang Nam Anh
 * 
 * Time Spent: 2.5 hours
 */

public class WordOnBoardFinder implements IWordOnBoardFinder {

    /**
     * Verifies whether a word can be formed by using some combination of
     * cells on the board. 
     * 
     * Words can only be formed from sequentially adjacent cells - two cells 
     * are considered, or neighboring, if they lie next to each other 
     * horizontally, verticaly, or diagonally. 
     * 
     * If possible, returns a list of cells on board following the order of  
     * the letters in the word.
     * If not, returns an empty list.
     * 
     * @param board the Boggle board.
     * @param word the word needs verification.
     * 
     * @return a list of cells if the word can be formed, or an empty list 
     * if not. 
     */
    public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
        List<BoardCell> list = new ArrayList<BoardCell>();
        if (word.length() <= board.size() * board.size()){
            for(int row = 0; row < board.size(); row++) { 
                for(int col = 0; col < board.size(); col++){ 
                    if (foundWord(board, word, list, 0, row, col)) {
                        return list; 
                    }
                }
            }
        }
        return list;
    }
    
    /**
     * Returns whether or not the word can be formed using some combination 
     * of cells on the board.
     * 
     * @param board the Boggle board.
     * @param word the word being verified.
     * @param list the list of cells that match the letters. The order of the 
     * cells in list follows the order of the letters in word.
     * @param int the index of the letter.
     * @param row the row index of the cell.
     * @param col the col index of the cell.
     * 
     * @return true iff the word can be formed.
     */
    public static boolean foundWord(BoggleBoard board, String word, 
    List<BoardCell> list, int i, int row, int col){
        if (i == word.length()){    
            return true;
        }
        BoardCell cell = new BoardCell(row, col);
        if (list.contains(cell)){
            return false;
        }
        if (!matchedCell(board, word, i, cell)){
            return false;
        }
        list.add(cell); 
        List<BoardCell> neighborList = validNeighbors(board, row, col);
        if (word.charAt(i) == 'q'){
            i+= 2;
        } else{
            i++;
        }
        for (BoardCell neighborCell: neighborList){
            if (foundWord(board, word, list, i, neighborCell.getRow(), 
            neighborCell.getCol())){
                return true;
            }
        }
        list.remove(cell);
        return false;
    }
    /** Returns whether or not the cell is in the board.
     * 
     * @param board the Boggle board.
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * 
     * @return true if the specified cell is in the board.
     */
    public static boolean validCell(BoggleBoard board, int row, int col){
        return row >= 0 && row < board.size() && col >= 0 && 
        col < board.size();
    }
    /** Returns the list of valid neighbors of a specified cell.
     * 
     * @param board the Boggle board.
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * 
     * @return the list of valid neighbors.
     */
    public static List<BoardCell> validNeighbors(BoggleBoard board, int row,
    int col){
        List<BoardCell> neighborsList = new ArrayList<BoardCell>();
        
        // explores south 
        if (validCell(board, row + 1, col)){
            neighborsList.add(new BoardCell(row + 1, col));
        }
        // explores north
        if (validCell(board, row - 1, col)){
            neighborsList.add(new BoardCell(row - 1, col));
        }
        // explores east
        if (validCell(board, row, col + 1)){
            neighborsList.add(new BoardCell(row, col + 1));
        }
        // explores west
        if (validCell(board, row, col - 1)){
            neighborsList.add(new BoardCell(row, col - 1));
        }
        // explores south east
        if (validCell(board, row + 1, col + 1)){
            neighborsList.add(new BoardCell(row + 1, col + 1));
        }
        // explores north west
        if (validCell(board, row - 1, col - 1)){
            neighborsList.add(new BoardCell(row - 1, col - 1));
        }
        // explores north east
        if (validCell(board, row - 1, col + 1)){
            neighborsList.add(new BoardCell(row - 1, col + 1));
        }
        // explores south west 
        if (validCell(board, row + 1, col - 1)){
            neighborsList.add(new BoardCell(row + 1, col - 1));
        }
        
        return neighborsList;
    }
    /** Returns whether or not the face of the cell matches the letter of 
     * the specified word.
     * 
     * @param board the Boggle board.
     * @param word the word being verified.
     * @param i the index of the letter in the word.
     * @param cell the cell whose face is being compared.
     * 
     * @return true if the face of the cell matches the letter of a given word.
     */
    public static boolean matchedCell(BoggleBoard board, String word, int i, 
    BoardCell cell){
        String cellLetter = board.getFace(cell);
        if (word.charAt(i) == 'q'){
            return cellLetter.equals("qu");
        }
        return cellLetter.equals(word.charAt(i)+ "");
    }  
}