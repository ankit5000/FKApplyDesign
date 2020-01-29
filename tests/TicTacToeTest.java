import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    @Test
    public void isDrawBigBoardTest() {

        TicTacToe ticTacToeTest = new TicTacToe();

        Cell[][] bigBoardTest;

        bigBoardTest = new Cell[Board.ROWS][Board.COLS];
        for(int row = 0; row< Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                bigBoardTest[row][col] = new Cell(row, col);
            }
        }

        assertFalse(ticTacToeTest.isDrawBigBoard(bigBoardTest));
    }

}