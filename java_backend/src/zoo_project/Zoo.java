package zoo_project;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zoo {
    String name;
    String address;
    String phone;
    String path;
    List<Zone> zones = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<TicketInfo> ticketInfos = new ArrayList<>();
    List<Linemap> linemaps = new ArrayList<>();

    public Zoo(String name, String address, String phone, String path) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        try {
            this.path = util.saveImage(path);
        } catch (Exception e) {
            this.path = System.getProperty("user.dir") + "\\src\\zoo_project\\static\\" + path;
        }
    }

    public void addZone(List<Zone> l) {
        for (var z : l) {
            this.zones.add(z);
        }
    }

    public void addCustomer(List<Customer> l) {
        for (var z : l) {
            this.customers.add(z);
        }
    }

    public Customer getCustomer(String id) {
        Customer c = customers.stream().filter(customer -> id.equals(customer.getId())).findAny().orElse(null);
        if (c != null) {
            return c;
        } else {
            addCustomer(Arrays.asList(new Customer(id)));
            return getCustomer(id);
        }
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<Animal>();
        for (var z : zones) {
            for (var a : z.getAnimals()) {
                animals.add(a);
            }
        }
        return animals;
    }

    public void addTicketInfos(List<TicketInfo> l) {
        for (var z : l) {
            this.ticketInfos.add(z);
        }
    }

    public List<TicketInfo> getTicketInfos() {
        return ticketInfos;
    }

    public void addLinemaps(List<Linemap> l) {
        for (var z : l) {
            this.linemaps.add(z);
        }
    }

    public List<Zone> getZones() {
        return zones;
    }

    public Linemap getLinemap(String src, String dst) {
        return linemaps.stream().filter(x -> x.source.name.equals(src) && x.destination.name.equals(dst)).findAny()
                .orElse(null);
    }

    public void buyTicket(String id, String date, String tname) throws ParseException {
        TicketInfo t = ticketInfos.stream().filter(x -> x.name.equals(tname)).findAny().orElseThrow();
        getCustomer(id).addTicket(new Ticket(date, t));
    }

    public void cancelTicket(String id) throws ParseException {
        getCustomer(id).removeLastTicket();
    }

}
