package svsl.action;

import svsl.Id;

public enum ActionType {
    PLOW,
    BREW
    ;
    public final Id id;

    ActionType() {
        this.id = Id.ofAction(name().toLowerCase());
    }
}
