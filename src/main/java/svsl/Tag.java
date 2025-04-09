package svsl;

public enum Tag {
    ITEM,
    FRUIT,
    CAN_BREW,
    SEED,
    CROP,
    FRUIT_WINE;
    public final Id id;

    Tag() {
        this.id = Id.ofTag(this.name().toLowerCase());
    }

    public static final Tag[] FRUIT_T = new Tag[]{FRUIT, CAN_BREW, CROP};
}
