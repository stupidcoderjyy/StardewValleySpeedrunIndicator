package svsl.world;

public class Date {
    private Season season;
    private int day;
    private int year;

    public Date(int year, Season season, int day) {
        this.year = year;
        this.season = season;
        this.day = day;
    }

    public void forward() {
        if (day == 28) {
            if (season == Season.Winter) {
                year++;
            }
            season = Season.values()[(season.ordinal() + 1) % 4];
            day = 1;
        } else {
            day++;
        }
    }

    public Season getSeason() {
        return season;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "第" + year + "年 " + season.name + day;
    }
}
