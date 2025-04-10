package svsl;

public class EStack {
    public static final EStack NULL = new EStack(Element.NULL, 0);
    public final Element element;
    public final int val;

    public EStack(Element element, int initialCount) {
        this.element = element;
        this.val = initialCount;
    }

    public EStack(Element element) {
        this(element, 0);
    }

    public EStack shift(int val) {
        int res = Math.max(element.min, this.val + val);
        return new EStack(element, Math.min(element.max, res));
    }

    public EStack copy() {
        return new EStack(element, val);
    }

    @Override
    public String toString() {
        return this == NULL ? "null" : element + "x" + val;
    }

    public static EStack merge(EStack stack1, EStack stack2) {
        if (stack1 == NULL) {
            return stack2.copy();
        }
        if (stack2 == NULL) {
            return stack1.copy();
        }
        if (stack1.element != stack2.element) {
            return NULL;
        }
        return stack1.shift(stack2.val);
    }

    public static EStack merge(EStack stack, Element e, int val) {
        if (stack == NULL) {
            return new EStack(e, val);
        }
        if (stack.element != e) {
            return NULL;
        }
        return stack.shift(val);
    }
}
