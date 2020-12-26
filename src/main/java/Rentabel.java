import java.io.Serializable;

public class Rentabel implements Serializable {


    private long id;
    private int year; // год
    private long proceeds; //выручка
    private long proceedsBySells; // выручка от продаж
    private long profit; //прибыль до налогооблажения
    private long profitBySells; // прибыль от продаж
    private long capital; // капитал
    private long clearprofit; // чистая прибыль

    public Rentabel(long id, int year, long proceeds, long proceedsBySells, long profit, long profitBySells, long capital, long clearprofit) {
        this.id = id;
        this.year = year;
        this.proceeds = proceeds;
        this.proceedsBySells = proceedsBySells;
        this.profit = profit;
        this.profitBySells = profitBySells;
        this.capital = capital;
        this.clearprofit = clearprofit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getProceeds() {
        return proceeds;
    }

    public void setProceeds(long proceeds) {
        this.proceeds = proceeds;
    }

    public long getProceedsBySells() {
        return proceedsBySells;
    }

    public void setProceedsBySells(long proceedsBySells) {
        this.proceedsBySells = proceedsBySells;
    }

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getProfitBySells() {
        return profitBySells;
    }

    public void setProfitBySells(long profitBySells) {
        this.profitBySells = profitBySells;
    }

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public long getClearprofit() {
        return clearprofit;
    }

    public void setClearprofit(long clearprofit) {
        this.clearprofit = clearprofit;
    }

    @Override
    public String toString() {
        return "Rentabel{" +
                "year=" + year +
                ", proceeds=" + proceeds +
                ", proceedsBySells=" + proceedsBySells +
                ", profit=" + profit +
                ", profitBySells=" + profitBySells +
                ", capital=" + capital +
                ", clearprofit=" + clearprofit +
                '}';
    }
}