package svsl;

import java.util.Objects;

public record Identifier(String type, String name) {
    public static Identifier of(String path) {
        String[] parts = path.split(":", 2);
        String type = parts.length > 1 ? parts[0] : "common";
        String name = parts.length > 1 ? parts[1] : parts[0];
        return new Identifier(type, name);
    }

    @Override
    public int hashCode() {
        return type.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Identifier id2) {
            return id2.type.equals(type) && id2.name.equals(name);
        }
        return false;
    }

    @Override
    public String toString() {
        return type + ":" + name;
    }
}
