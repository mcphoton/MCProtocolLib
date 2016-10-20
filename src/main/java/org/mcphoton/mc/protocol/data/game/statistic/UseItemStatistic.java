package org.mcphoton.mc.protocol.data.game.statistic;

public class UseItemStatistic implements Statistic {

    private int id;

    public UseItemStatistic(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        UseItemStatistic that = (UseItemStatistic) o;

        if(id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
