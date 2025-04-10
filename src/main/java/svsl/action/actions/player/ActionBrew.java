package svsl.action.actions.player;

import svsl.Element;
import svsl.Tag;
import svsl.action.Action;
import svsl.action.ActionType;
import svsl.machine.MachineSet;
import svsl.machine.MachineState;
import svsl.machine.barrel.Barrel;
import svsl.player.Inventory;

public class ActionBrew extends Action {
    private final Inventory inv;
    private final Element e;
    private final MachineSet<Barrel> barrels;

    public ActionBrew(Inventory inv, Element e, MachineSet<Barrel> barrels) {
        super(ActionType.BREW);
        this.inv = inv;
        this.e = e;
        this.barrels = barrels;
    }

    @Override
    public boolean perform() {
        if (e.hasTag(Tag.CAN_BREW) && inv.has(e) && barrels.countMachine(MachineState.Standby) > 0) {
            inv.take(e, 1);
            barrels.process(e);
            return true;
        }
        return false;
    }
}
