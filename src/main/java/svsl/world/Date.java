package svsl.world;

public class Date {
    private Season season;
    private int day;

    public Date(Season season, int day) {
        this.season = season;
        this.day = day;
    }

    public void forward() {
        if (day == 28) {
            season = Season.values()[(season.ordinal() + 1) % 4];
            day = 1;
        } else {
            day++;
        }
    }
}
