import javafx.util.Pair;

import java.util.Scanner;
import java.util.Stack;

public class TicTacToe {

    boolean start = true;
    private Board board;
    private StatusOfGame gameStatus;
    private State curr;
    private GameMode gameMode;
    int xScore = 0;
    int zeroScore = 0;
    int drawScore = 0;

    private static Scanner in = new Scanner(System.in);

    int boardNumber = 0;
    Cell[][] bigBoard;

    Stack<Pair<Integer,Integer>> moves = new Stack<>();

    public TicTacToe(){

        initBigBoard();
        board = new Board();
        initGame();

        boolean turnPlayer1 = true;

        do {

            if(!start) {
                System.out.println("Type u for undo else, press any key: ");
                String isUndo = in.next();
                if(isUndo.equals("u")){
                    undo();
                    curr = (curr == State.CROSS)? State.ZERO : State.CROSS;
                    turnPlayer1 = !turnPlayer1;
                }
            }
            else{
                start = false;
            }

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
                System.out.println("'X' won in this subBoard");
                bigBoard[(boardNumber/3)][(boardNumber%3)].content = State.CROSS;
                xScore++;
                nextBoard(State.CROSS);
            } else if (gameStatus == StatusOfGame.ZERO_WON){
                System.out.println("'0' won in this subBoard");
                bigBoard[(boardNumber/3)][(boardNumber%3)].content = State.ZERO;
                zeroScore++;
                nextBoard(State.ZERO);
            } else if(gameStatus == StatusOfGame.DRAW){
                System.out.println("It's draw in this subBoard");
                bigBoard[(boardNumber/3)][(boardNumber%3)].content = State.EMPTY;
                drawScore++;
                nextBoard(State.EMPTY);
            }
            curr = (curr == State.CROSS)? State.ZERO : State.CROSS;
            turnPlayer1 = !turnPlayer1;

        }while(gameStatus == StatusOfGame.STILL_PLAYING);
    }

    private void undo() {
        if(!moves.empty()){
            Pair<Integer,Integer>pair = moves.lastElement();
            moves.pop();
            board.cells[pair.getKey()][pair.getValue()].content = State.EMPTY;
        }
    }

    private void nextBoard(State whoWon) {

        System.out.println("Type \"stat\" for the current leaderboard else, press any key: ");
        String showLeaderBoard = in.next();
        if(showLeaderBoard.equals("stat")){
            System.out.println("X's score: " + xScore);
            System.out.println("0's score: " + zeroScore);
            System.out.println("No. of draws: " + drawScore);
        }

        gameStatus = StatusOfGame.STILL_PLAYING;
        if(isDrawBigBoard()){
            gameStatus = StatusOfGame.DRAW;
            System.out.println("It's a draw in the entire board.");
            return;
        }
        if(checkBoard(whoWon)){
            gameStatus = (whoWon == State.CROSS)? StatusOfGame.CROSS_WON:StatusOfGame.ZERO_WON;
            if(gameStatus == StatusOfGame.CROSS_WON){
                System.out.println("X won!! in the entire board.");
            }
            else if(gameStatus == StatusOfGame.ZERO_WON) {
                System.out.println("0 won!! in the entire board.");
            }
            return;
        }
        boardNumber++;
        System.out.println("switching to board no. " + (boardNumber + 1));
        initGame();
    }

    private boolean isDrawBigBoard() {
        for(int row = 0; row< Board.ROWS; ++row){
            for(int col = 0; col< Board.COLS; ++col){
                if(bigBoard[row][col].content == State.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkBoard(State currState) {
        int currentRow = (boardNumber/3);
        int currentCol = (boardNumber%3);
        return(bigBoard[currentRow][0].content == currState
                && bigBoard[currentRow][1].content == currState
                && bigBoard[currentRow][2].content == currState
                || bigBoard[0][currentCol].content == currState
                && bigBoard[1][currentCol].content == currState
                && bigBoard[2][currentCol].content == currState
                || currentRow == currentCol
                && bigBoard[0][0].content == currState
                && bigBoard[1][1].content == currState
                && bigBoard[2][2].content == currState
                || currentRow + currentCol == 2
                && bigBoard[0][2].content == currState
                && bigBoard[1][1].content == currState
                && bigBoard[2][0].content == currState);
    }

    private void initBigBoard() {
        bigBoard = new Cell[Board.ROWS][Board.COLS];
        for(int row = 0; row< Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                bigBoard[row][col] = new Cell(row, col);
            }
        }
    }

    private void turnOfComputer(State currState) {
        for(int row = 0; row< Board.ROWS; row++){
            for(int col = 0; col< Board.COLS; col++){
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
            if(currState == State.CROSS){
                System.out.print("Player 'X', enter your move(row[1-3] col[1-3]): ");
            } else {
                System.out.print("Player '0', enter your move(row[1-3] col[1-3]): ");
            }
            int row = in.nextInt()-1;
            int col = in.nextInt()-1;

            Pair<Integer,Integer> pair = new Pair<>(row,col);
            moves.push(pair);

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
        moves.clear();
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
