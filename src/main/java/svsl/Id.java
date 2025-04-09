package svsl;

public record Id(String type, String name) {

    public static Id of(String path) {
        String[] parts = path.split(":", 2);
        String type = parts.length > 1 ? parts[0] : "c";
        String name = parts.length > 1 ? parts[1] : parts[0];
        return new Id(type, name);
    }

    public static Id ofItem(String path) {
        return new Id("item", path);
    }

    public static Id ofPlayer(String path) {
        return new Id("player", path);
    }

    public static Id ofAction(String path) {
        return new Id("action", path);
    }

    public static Id ofTag(String path) {
        return new Id("tag", path);
    }

    public static Id ofContainer(String path) {
        return new Id("container", path);
    }

    public static Id ofRecipe(String id) {
        return new Id("recipe", id);
    }

    public Id withSuffix(String suffix) {
        return new Id(type, name + suffix);
    }

    @Override
    public int hashCode() {
        return type.hashCode() + name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Id id2) {
            return id2.type.equals(type) && id2.name.equals(name);
        }
        return false;
    }

    @Override
    public String toString() {
        return type + ":" + name;
    }
}
