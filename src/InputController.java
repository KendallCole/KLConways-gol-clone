import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputController extends MouseAdapter {
    private GamePanel game;
    private Cell.State paintState;
    public boolean held = false;

    InputController(GamePanel game){
        this.game = game;
        this.held = false;
        this.paintState = Cell.State.LIVE;
    }
    public int roundToNext(int num, int roundTo){
        return (int) Math.ceil(num / roundTo) * roundTo;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        userPaint(e);
    }

    public void userPaint(MouseEvent e){
        Point pos = e.getPoint();
        Vec2 gridPos = new Vec2(roundToNext(pos.y, game.UNIT_Y) / game.UNIT_Y, roundToNext(pos.x, game.UNIT_X) / game.UNIT_X);
        if(this.paintState== Cell.State.LIVE){
            this.game.userSetLive.add(gridPos);
        }else{
            this.game.userSetDead.add(gridPos);
        }
    }
    public void mousePressed(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.paintState = Cell.State.LIVE;
        }else{
            this.paintState = Cell.State.DEAD;
        }
        this.held = true;
        super.mousePressed(e);
    }
    public void mouseDragged(MouseEvent e){
        if(this.held == true){
            userPaint(e);
        }
    }
    public void mouseReleased(MouseEvent e){
        this.held = false;
        System.out.println("Hold ended");
    }
}
