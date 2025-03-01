package cinema.controller;

import cinema.exception.AlreadyPurchasedException;
import cinema.exception.ExpiredTokenException;
import cinema.exception.InvalidPasswordException;
import cinema.exception.OutOfBoundsException;
import cinema.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class SeatController {
    private Theater cinema;
    public List<Order> orders ;

    public SeatController() {
        this.cinema = getSeats();
        this.orders = new ArrayList<>();
    }

    @GetMapping("/seats")
    public Theater getSeats() {
        int rows = 9;
        int columns = 9;
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < columns; j++) {
                seats.add(new Seat(i + 1, j + 1, 10));
            }
        }
        for (int i = 4; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats.add(new Seat(i + 1, j + 1, 8));
            }
        }
        cinema = new Theater(rows, columns, seats);
        return cinema;
    }

    @PostMapping("/purchase")
    public Order purchase(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getRow()> cinema.getRows() || seat.getColumn() < 1 || seat.getColumn() > cinema.getColumns()) {
            System.out.println("Wrong input!");
            throw new OutOfBoundsException();
        }

        boolean found = false;
        for (int i = 0; i < cinema.getSeats().size(); i++) {
            if (cinema.getSeats().get(i).getRow() == seat.getRow() && cinema.getSeats().get(i).getColumn() == seat.getColumn()) {
                found = true;
                break;
            }
        }
        Order order = new Order();
        Ticket ticket = new Ticket();
        if(found){
            if(seat.getRow() < 4){
                seat.setPrice(10);
            }
            else{
                seat.setPrice(8);
            }
            String token = UUID.randomUUID().toString();
            ticket.setPrice(seat.getPrice());
            ticket.setRow(seat.getRow());
            ticket.setColumn(seat.getColumn());
            order.setTicket(ticket);
            order.setToken(token);
            orders.add(order);
            cinema.deleteSeat(seat.getRow(), seat.getColumn());
        }
        else{
            throw new AlreadyPurchasedException();
        }
        return order;
    }

    @PostMapping("/return")
    public TicketResponse returnTicket(@RequestBody TokenRequest token) {
        Ticket ticket = null;
        boolean valid = false;
        Order order = new Order();
        TicketResponse ticketResponse = new TicketResponse();
        for(int i = 0; i < orders.size(); i++){
            System.out.println(i);
            System.out.println(orders.get(i).getToken());
            if(Objects.equals(token.getToken(), orders.get(i).getToken())){
                valid = true;
                ticket = orders.get(i).getTicket();
                order = orders.get(i);
                ticketResponse.setTicket(ticket);
                orders.remove(i);
                Seat returnedSeat = new Seat(ticket.getRow(), ticket.getColumn(), ticket.getPrice());
                cinema.addSeat(returnedSeat);
                break;
            }
        }
        if(valid){
            return ticketResponse;
        }
        else{
            throw new ExpiredTokenException();
        }
    }

    @GetMapping("/stats")
    public Stats getStats(@RequestParam(name = "password", required = false) String password){
        if (password == null) {
            throw new InvalidPasswordException();
        }
        else if(password.equals("super_secret")){
            int availableSeats = cinema.getSeats().size();
            int purchasedTickets = orders.size();
            int currentIncome = 0;
            for (Order order : orders) {
                currentIncome += order.getTicket().getPrice();
            }
            return new Stats(currentIncome, availableSeats, purchasedTickets);
        }
        else{
            throw new InvalidPasswordException();
        }

    }
}