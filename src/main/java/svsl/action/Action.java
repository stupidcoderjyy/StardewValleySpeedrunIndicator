package svsl.action;

public abstract class Action {
    public final ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public abstract boolean perform();
}
