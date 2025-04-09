package svsl.machine;

import svsl.*;
import svsl.world.StardewValley;
import svsl.world.World;

import java.util.Arrays;

public abstract class Machine extends Container {
    protected boolean isWorking;
    protected int daysLeft;
    protected ESlot[] inv;

    public Machine(World parent, String id, String name, int slots) {
        super(parent, new Id("me", id), name);
        inv = new ESlot[slots];
        for (int i = 0; i < slots; i++) {
            inv[i] = registerSlot(EStack.NULL);
        }
    }

    public Machine(World parent, String id, String name) {
        this(parent, id, name, 1);
    }

    public abstract boolean accept(EStack input);
    protected abstract void onFinished();
    protected abstract void onStarting(EStack input);
    protected abstract int getDuration(EStack input);

    public void start(EStack input) {
        if (!accept(input)) {
            throw new IllegalArgumentException("can't brew: " + input);
        }
        if (!isWorking) {
            onStarting(input);
            daysLeft = getDuration(input);
            isWorking = true;
        }
    }

    public EStack collect() {
        if (isWorking || inv[0].isNull()) {
            return EStack.NULL;
        }
        onFinished();
        var out = inv[0].getStack();
        inv[0].setStack(EStack.NULL);
        return out;
    }

    public void update() {
        if (isWorking) {
            if (--daysLeft == 0) {
                isWorking = false;
                StardewValley.INSTANCE.player.inv.store(collect());
            }
        }
    }

    @Override
    public String toString() {
        String res = id.toString();
        if (isWorking) {
            res += Arrays.toString(inv) + "(" + daysLeft + "d)";
        }
        return res;
    }
}
