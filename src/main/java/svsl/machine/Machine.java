package svsl.machine;

import svsl.*;
import svsl.world.StardewValley;

import java.util.Arrays;

public abstract class Machine<M extends Machine<?>> extends Container {
    public final MachineType type;
    public final int pos;
    protected final MachineSet<M> machineSet;
    protected MachineState state = MachineState.Standby;
    protected int daysLeft;
    protected ESlot[] inv;

    public Machine(int pos, MachineSet<M> set, MachineType type) {
        super(set, type.id.withSuffix("_" + pos), type.name);
        this.pos = pos;
        this.machineSet = set;
        this.type = type;
        inv = new ESlot[type.slots];
        for (int i = 0; i < type.slots; i++) {
            inv[i] = registerSlot(EStack.NULL);
        }
    }

    public abstract boolean accept(EStack input);
    protected abstract void onFinished();
    protected abstract void onStarting(EStack input);
    protected abstract int getDuration(EStack input);

    public void start(EStack input) {
        if (!accept(input)) {
            throw new IllegalArgumentException("can't brew: " + input);
        }
        if (state == MachineState.Standby) {
            onStarting(input);
            daysLeft = getDuration(input);
            setState(MachineState.Working);
        }
    }

    public EStack collect() {
        if (state != MachineState.Finished || inv[0].isNull()) {
            return EStack.NULL;
        }
        onFinished();
        var out = inv[0].getStack();
        inv[0].setStack(EStack.NULL);
        setState(MachineState.Standby);
        return out;
    }

    public void update() {
        if (state == MachineState.Working) {
            if (--daysLeft == 0) {
                setState(MachineState.Finished);
            }
        }
    }

    protected void setState(MachineState state) {
        if (state != this.state) {
            machineSet.onStateChanged((M)this, this.state, state);
            this.state = state;
        }
    }

    @Override
    public String toString() {
        return switch (state) {
            case Standby -> "就绪";
            case Working -> "工作中:" + Arrays.toString(inv) + "(" + daysLeft + "d)";
            case Finished -> "完成:" + Arrays.toString(inv);
        };
    }
}
