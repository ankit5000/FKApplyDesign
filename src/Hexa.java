public class Hexa {

    public static final int ROWS = 7;
    public static final int COLS = 13;

    Cell[][] hexaGrid;

    public Hexa(){
        hexaGrid = new Cell[ROWS][COLS];
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                hexaGrid[row][col] = new Cell(row,col);
            }
        }
    }

    public void init(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                hexaGrid[row][col].clear();
            }
        }
    }

    public void printGrid(){
        for(int row=0; row<ROWS; ++row){
            for(int col=0; col<COLS; ++col){
                if(row%2==0 && col%2 == 1){
                    hexaGrid[row][col].printCell();
                }
                else if(row%2 == 1 && col%2 == 0){
                    hexaGrid[row][col].printCell();
                }
                if(col < COLS-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(row< ROWS-1){
                System.out.println("-----------------------------------");
            }
        }
    }

    public Boolean check(State curr){
        if(hexaGrid[0][0].content == State.CROSS){
            return true;
        }
        return  false;
    }

//    public static void main(String[] args) {
//        Hexa hexaTest = new Hexa();
//        hexaTest.init();
//        hexaTest.printGrid();
//    }

}
