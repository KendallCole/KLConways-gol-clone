
public class Cell {
    enum State {
        LIVE,
        DEAD,
    }
    public State state;
    public int id;
    public int IDS = 0;
    Cell(){
        this.state = State.DEAD;
    }
    Cell(State toState){
        this.state = toState;
    }
}
