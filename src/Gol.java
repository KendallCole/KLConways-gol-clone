import java.util.ArrayList;
import java.util.Random;

public class Gol {
    public static void gol(){

    }
    public Cell[][] step(Cell[][] grid){
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];

        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {

                int nCount = CountNeighbors(grid, i, j);

                Cell.State toState = Cell.State.DEAD;
                if (grid[i][j].state == Cell.State.DEAD){
                    if (nCount == 3){
                        toState = Cell.State.LIVE; // Reproduction
                    }else{
                        toState = Cell.State.DEAD; // Stasis
                    }
                } else if (grid[i][j].state == Cell.State.LIVE){
                    if(nCount < 2){
                        toState = Cell.State.DEAD; // Death by underpopulation
                    }else if (nCount == 2 || nCount == 3){
                        toState = Cell.State.LIVE; // Live on to the next generation
                    }else{
                        toState = Cell.State.DEAD; // Death by overpopulation
                    }
                }

                newGrid[i][j] = new Cell(toState);
            }
        }
        return newGrid;
    }
    public int CountNeighbors(Cell[][] grid, int x, int y){
        int sum = 0;
        int cols = grid.length;
        int rows = grid[0].length;
        for (int xx = -1; xx <= 1; xx++) {
            for (int yy = -1; yy <= 1; yy++) {
                if (xx == 0 && yy == 0) {
                    continue;
                }
                if (x+xx >= 0 && y+yy >= 0 && x + xx < rows && y + yy < cols) {
                    if (grid[x+xx][y+yy].state == Cell.State.LIVE) {
                        ++sum;
                    }
                }
            }
        }
        return sum;
    }

    public void PrintGrid(Cell[][] arr){
        int rows = arr.length;
        int columns = arr[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j].state + " ");
            }
            System.out.println();
        }
    }
    public Cell[][] randomize(Cell[][] grid){
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];

        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                if(new Random().nextBoolean() == true){

                    newGrid[i][j] = new Cell(Cell.State.LIVE);
                }else{
                    newGrid[i][j] = new Cell();
                }
            }
        }
        return newGrid;
    }


}
