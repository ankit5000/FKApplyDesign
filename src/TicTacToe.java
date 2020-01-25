import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private StatusOfGame gameStatus;
    private State curr;

    private static Scanner in = new Scanner(System.in);


    public TicTacToe(){
        board = new Board();
        initGame();

        do {
            turnOfPlayer(curr);
            board.printGrid();
            check(curr);

            if(gameStatus == StatusOfGame.CROSS_WON){
                System.out.println("'X' won!!");
            } else if (gameStatus == StatusOfGame.ZERO_WON){
                System.out.println("'0' won!!");
            } else if(gameStatus == StatusOfGame.DRAW){
                System.out.println("It's draw!!");
            }

            curr = (curr == State.CROSS)? State.ZERO : State.CROSS;

        }while(gameStatus == StatusOfGame.STILL_PLAYING);
    }

    public void initGame(){
        board.init();
        curr = State.CROSS;
        gameStatus = StatusOfGame.STILL_PLAYING;
    }

    public void turnOfPlayer(State currState){
        boolean validInput = false;
        do {
            if(currState == currState.CROSS){
                System.out.print("Player 'X', enter your move(row[1-3] col[1-3]): ");
            } else {
                System.out.print("Player '0', enter your move(row[1-3] col[1-3]): ");
            }
            int row = in.nextInt()-1;
            int col = in.nextInt()-1;

            if(row>=0 && row<Board.ROWS && col>=0 && col<Board.COLS &&
                    board.cells[row][col].content == State.EMPTY){
                board.cells[row][col].content = currState;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            } else {
                System.out.println("This move at (" +(row-1)+","+(col+1)+") is not"
                        + "valid. Try again...");
            }
        } while(!validInput);
    }

    public void check(State currState){
        if(board.hasWon(currState)){
            gameStatus = (currState == State.CROSS)? StatusOfGame.CROSS_WON:StatusOfGame.ZERO_WON;
        } else if(board.isDraw()){
            gameStatus = StatusOfGame.DRAW;
        }
    }

    public static void main(String[] args) {

        new TicTacToe();
    }

}
