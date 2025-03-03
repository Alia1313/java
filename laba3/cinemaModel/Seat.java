package cinemaModel;
import java.util.*;

class Seat {
    int row;
    int col;
    boolean isOccupied;

    Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
    }
}