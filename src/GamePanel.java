import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    int TIME_BETWEEN_FRAMES = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public static final int COLS = 50; // Ensure that width/height are evenly dividable by this
    public static final int ROWS = 50;
    public static final int UNIT_X = WIDTH / COLS;
    public static final int UNIT_Y = WIDTH / ROWS;

    private Board board;
    private Gol gol;
    private Timer timer;

    private InputController inputController;
    public ArrayList<Vec2> userSetLive;
    public ArrayList<Vec2> userSetDead;
    GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.gol = new Gol();
        this.board = new Board(COLS, ROWS);
        InputController controller = new InputController(this);
        this.board.setGrid(this.gol.randomize(this.board.getGrid()));
        this.addMouseListener(controller);
        this.addMouseMotionListener(controller);
        this.inputController = controller;
        this.userSetLive = new ArrayList<>();
        this.userSetDead = new ArrayList<>();
        this.timer = runTimer();
    }
    public Timer runTimer(){
        Timer timer = new Timer(TIME_BETWEEN_FRAMES, this);
        timer.start();
        return timer;
    }

    public void setSpeed(int speed){
        this.timer.setDelay(speed);
        timer.restart();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.board.setGrid(this.gol.step(this.board.getGrid()));


        while(!this.userSetLive.isEmpty()){
            Vec2 setAlive = this.userSetLive.remove(this.userSetLive.size()-1);
            this.board.setCell(setAlive.x, setAlive.y, Cell.State.LIVE);
        }
        while(!this.userSetDead.isEmpty()){
            Vec2 setAlive = this.userSetDead.remove(this.userSetDead.size()-1);
            this.board.setCell(setAlive.x, setAlive.y, Cell.State.DEAD);
        }

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