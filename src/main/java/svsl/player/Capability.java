package svsl.player;

import svsl.Id;

public class Capability {
    public final Id id;
    private int exp;
    private int level;
    private int upgradeExp;

    public Capability(Id id) {
        this.id = id;
    }

    public int getExp() {
        return exp;
    }

    public void gainExp(int delta) {
        exp += delta;
        if (exp > upgradeExp) {
            exp = 0;
            upgradeExp = levelToUpgradeExp(++level);
        }
    }

    private static int levelToUpgradeExp(int level) {
        return switch (level) {
            case 1 -> 100;
            case 2 -> 380;
            case 3 -> 770;
            case 4 -> 1300;
            case 5 -> 2150;
            case 6 -> 3300;
            case 7 -> 4800;
            case 8 -> 6900;
            case 9 -> 10000;
            default -> 15000;
        };
    }
}
