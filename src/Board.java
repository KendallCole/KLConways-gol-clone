public class Board {
    Cell Grid[][];
    public int width;
    public int height;
    Board(int width, int height){
        this.width = width;
        this.height = height;
        this.Grid = new Cell[width][height];
        for(int i = 0; i < this.Grid.length; i++){
            for(int j = 0; j < this.Grid[0].length; j++){
                this.Grid[i][j] = new Cell();
            }
        }
    }
    public void setGrid(Cell[][] newGrid){
        this.Grid = newGrid;
    }
    public void setCell(int x, int y, Cell.State toState){
        this.Grid[x][y].state = toState;
    }
    public int getSize() {return this.width * this.height;};
    public Cell[][] getGrid(){return this.Grid;}
}


