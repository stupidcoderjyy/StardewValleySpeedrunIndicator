package svsl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Element {
    public static final Element NULL = new Element(Id.of("null"), "null", 0, 0);
    public final Id id;
    public final int min;
    public final int max;
    public final String name;
    public final Set<Tag> tags = new HashSet<>();

    public Element(Id id, String name, int min, int max, Tag ... tags) {
        this.id = id;
        this.min = min;
        this.max = max;
        this.name = name;
        this.tags.addAll(Arrays.asList(tags));
    }

    public Element(Id id, String name, Tag ... tags) {
        this(id, name, 0, Integer.MAX_VALUE, tags);
    }

    public boolean hasTag(Tag ... tag) {
        for (Tag t : tag) {
            if (tags.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public EStack stack(int count) {
        if (count < min) {
            count = min;
        }
        if (count > max) {
            count = max;
        }
        return new EStack(this, count);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Element) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, min, max);
    }

    @Override
    public String toString() {
        return name;
    }
}