package cinema.model;

public class Order {
    private String token;
    private Ticket ticket;
    public Order(){}
    public Order(String token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
    public Ticket getTicket() {
        return ticket;
    }
}
