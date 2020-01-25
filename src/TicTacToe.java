import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private StatusOfGame gameStatus;
    private State curr;
    private GameMode gameMode;

    private static Scanner in = new Scanner(System.in);


    public TicTacToe(){
        board = new Board();
        initGame();

        Boolean turnPlayer1 = true;

        do {

            if(gameMode == GameMode.HUMAN_TO_COMPUTER && turnPlayer1 == false){
                System.out.println("Computer's turn");
                turnOfComputer(curr);
            }
            else{
                turnOfPlayer(curr);
            }

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
            turnPlayer1 = !turnPlayer1;

        }while(gameStatus == StatusOfGame.STILL_PLAYING);
    }

    private void turnOfComputer(State currState) {
        for(int row = 0; row<board.ROWS; row++){
            for(int col = 0; col<board.COLS; col++){
                if(board.cells[row][col].content == State.EMPTY) {
                    board.cells[row][col].content = currState;
                    board.currentRow = row;
                    board.currentCol = col;
                    return;
                }
            }
        }
    }

    private void turnOfPlayer(State currState){
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

    public void initGame(){
        board.init();
        curr = State.CROSS;
        gameStatus = StatusOfGame.STILL_PLAYING;
        System.out.println("Type h for Human to Human game and c for Human to Computer game: ");
        String input = in.next();
        gameMode = (input.equals("h"))? GameMode.HUMAN_TO_HUMAN:GameMode.HUMAN_TO_COMPUTER;
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
