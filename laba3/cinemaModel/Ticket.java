package cinemaModel;
import java.util.*;

class Ticket {
    Session session;
    Seat seat;

    Ticket(Session session, Seat seat) {
        this.session = session;
        this.seat = seat;
    }
}