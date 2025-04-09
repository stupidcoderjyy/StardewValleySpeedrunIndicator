package svsl.world;

import svsl.Container;
import svsl.Id;
import svsl.player.Player;

public class StardewValley extends Container {
    public static final StardewValley INSTANCE = new StardewValley();
    public final Player player;
    public final World pelicanTown;
    public final World oasis;
    public final World gingerIsland;
    public final Date date;

    private StardewValley() {
        super(Id.ofContainer("sv"), "星露谷");
        this.player = new Player(this);
        this.pelicanTown = World.create(this, WorldType.PELICAN_TOWN);
        this.oasis = World.create(this, WorldType.OASIS);
        this.gingerIsland = World.create(this, WorldType.GINGER_ISLAND);
        this.date = new Date(Season.Spring, 1);
    }

    @Override
    public void update() {
        super.update();
        date.forward();
    }
}
