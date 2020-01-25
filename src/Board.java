public class Board {

    public static final int ROWS = 3;
    public static final int COLS = 3;

    Cell[][] cells;
    int currentRow, currentCol;

    public Board(){
        cells = new Cell[ROWS][COLS];
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                cells[row][col] = new Cell(row,col);
            }
        }
    }

    public void init(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                cells[row][col].clear();
            }
        }
    }

    public boolean hasWon(State currState){
        return(cells[currentRow][0].content == currState
                && cells[currentRow][1].content == currState
                && cells[currentRow][2].content == currState
                || cells[0][currentCol].content == currState
                && cells[1][currentCol].content == currState
                && cells[2][currentCol].content == currState
                || currentRow == currentCol
                && cells[0][0].content == currState
                && cells[1][1].content == currState
                && cells[2][2].content == currState
                || currentRow + currentCol == 2
                && cells[0][2].content == currState
                && cells[1][1].content == currState
                && cells[2][0].content == currState);
    }

    public boolean isDraw(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                if(cells[row][col].content == State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                cells[row][col].printCell();
                if(col < COLS-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(row< ROWS-1){
                System.out.println("-------------");
            }
        }
    }
}
