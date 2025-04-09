package svsl;

public class ESlot {
    private EStack stack;

    public ESlot(EStack stack) {
        this.stack = stack;
    }

    public ESlot() {
        this(EStack.NULL);
    }

    public void setStack(EStack stack) {
        this.stack = stack;
    }

    public void shift(int val) {
        if (val != 0) {
            setStack(stack.shift(val));
        }
    }

    public EStack getStack() {
        return stack;
    }

    public boolean hasEnoughContent(int required) {
        return stack.val - stack.element.min >= required;
    }

    public boolean hasEnoughSpace(int required) {
        return stack.element.max - stack.val >= required;
    }

    public boolean isFull() {
        return stack.val == stack.element.max;
    }

    public boolean isEmpty() {
        return stack.val == stack.element.min;
    }

    public boolean isNull() {
        return stack == EStack.NULL;
    }
}
