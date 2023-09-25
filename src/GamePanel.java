import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    static final int TIME_BETWEEN_FRAMES = 25;
    static final int WIDTH = 800;
    static final int HEIGHT = 800;

    static final int COLS = 200;
    static final int ROWS = 200;
    static final int UNIT_X = WIDTH / COLS;
    static final int UNIT_Y = WIDTH / ROWS;

    private Board board;
    private Gol gol;
    GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.gol = new Gol();
        this.board = new Board(COLS, ROWS);
        this.board.setGrid(this.gol.randomize(this.board.getGrid()));
        run();
    }
    public void run(){
        Timer timer = new Timer(TIME_BETWEEN_FRAMES, this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.board.setGrid(this.gol.step(this.board.getGrid()));
        repaint();

    }
    public void draw(Graphics g){

        int x = 0;
        int y = 0;
        for (Cell[] thisRow : this.board.getGrid()){
            for(Cell thisCell : thisRow){
                if (thisCell.state == Cell.State.DEAD){
                    g.setColor(Color.black);
                }else{
                    g.setColor(Color.white);
                }
                g.fillRect((UNIT_Y * y) , (UNIT_X * x), (UNIT_Y), (UNIT_X));
                y = (++y ) % thisRow.length;
            }

            ++x;
        }
        //draw grid
        g.setColor(Color.gray);
        for(int i=UNIT_Y; i < HEIGHT; i+=UNIT_Y){
            g.fillRect(i, 0, 1, HEIGHT);
        }
        for(int i=UNIT_X; i < WIDTH; i+=UNIT_X){
            g.fillRect(0, i, WIDTH, 1);
        }

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}