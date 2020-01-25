public class Cell {

    State content;
    int row, col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        clear();
    }

    public void clear(){
        content = State.EMPTY;
    }

    public void printCell(){
        switch(content){
            case CROSS: System.out.print(" X "); break;
            case ZERO:System.out.print(" 0 "); break;
            case EMPTY: System.out.print("   ");break;
        }
    }
}
