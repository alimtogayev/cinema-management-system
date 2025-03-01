package cinema.model;

public class Stats {
    private int income;
    private int available;
    private int purchased;

    public Stats(){}

    public Stats(int income, int available, int purchased) {
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }

    public int getAvailable() {
        return available;
    }

    public int getIncome() {
        return income;
    }

    public int getPurchased() {
        return purchased;
    }
}
