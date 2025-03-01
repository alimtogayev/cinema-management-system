package cinema.model;

import cinema.model.Seat;

import java.util.List;

public class Theater {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public Theater() {}

    public Theater(int rows, int columns, List<Seat> seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
        System.out.println("Theater created");
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void deleteSeat(int row, int column) {
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i).getRow() == row && seats.get(i).getColumn() == column) {
                seats.remove(i);
                break;
            }
        }
    }

    public void addSeat(Seat returnedSeat) {
        seats.add(returnedSeat);
    }
}