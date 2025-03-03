package cinemaModel;
import java.util.*;

class Hall {
    String name;
    int rows;
    int cols;
    List<Session> sessions;

    Hall(String name, int rows, int cols) {
        this.name = name;
        this.rows = rows;
        this.cols = cols;
        this.sessions = new ArrayList<>();
    }

    void addSession(Session session) {
        sessions.add(session);
    }
}