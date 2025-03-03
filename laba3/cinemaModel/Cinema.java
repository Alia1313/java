package cinemaModel;
import java.util.*;

class Cinema {
    String name;
    List<Hall> halls;

    Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    void addHall(Hall hall) {
        halls.add(hall);
    }
}