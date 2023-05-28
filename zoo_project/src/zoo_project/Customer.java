package zoo_project;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    String id;
    List<Ticket> tickets = new ArrayList<>();

    public Customer(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addTicket(Ticket t) {
        this.tickets.add(t);
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void removeLastTicket() {
        this.tickets.remove(tickets.size() - 1);
    }
}
