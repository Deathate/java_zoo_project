package zoo_project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private Date dateObj;
    String date;
    TicketInfo tinfo;
    String path;

    public Ticket(String date, TicketInfo tinfo) throws ParseException {
        setDate(date);
        this.tinfo = tinfo;
    }

    void setDate(String time) throws ParseException {
        this.dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        this.date = getDate(this.dateObj);
    }

    public String getDate(Date d) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(d);
    }
}
