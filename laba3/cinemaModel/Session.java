package cinemaModel;
import java.util.*;

class Session {
    Movie movie;
    Date time;
    Hall hall;
    Seat[][] seats;

    Session(Movie movie, Date time, Hall hall) {
        this.movie = movie;
        this.time = time;
        this.hall = hall;
        this.seats = new Seat[hall.rows][hall.cols];
        for (int i = 0; i < hall.rows; i++) {
            for (int j = 0; j < hall.cols; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    boolean isSeatAvailable(int row, int col) {
        return !seats[row][col].isOccupied;
    }

    void bookSeat(int row, int col) {
        if (isSeatAvailable(row, col)) {
            seats[row][col].isOccupied = true;
        }
    }

    void printSeatMap() {
        for (int i = 0; i < hall.rows; i++) {
            for (int j = 0; j < hall.cols; j++) {
                System.out.print(seats[i][j].isOccupied ? "[X]" : "[ ]");
            }
            System.out.println();
        }
    }
}