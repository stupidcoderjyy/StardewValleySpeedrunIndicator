package svsl.world;

import svsl.Id;

public enum WorldType {
    PELICAN_TOWN("鹈鹕镇"),
    OASIS("绿洲"),
    GINGER_ISLAND("姜岛"),
    ;
    public final Id id;
    public final String name;

    WorldType(String name) {
        this.id = Id.ofContainer("world.").withSuffix(name().toLowerCase());
        this.name = name;
    }
}
