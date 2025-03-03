package cinemaModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CinemaSystem {
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        initializeTestData();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Войти");
                System.out.println("2. Зарегистрироваться");
                System.out.println("3. Выйти");
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            } else {
                if (currentUser instanceof Admin) {
                    adminMenu(scanner);
                } else {
                    userMenu(scanner);
                }
            }
        }
    }

    private static void initializeTestData() {
        admins.add(new Admin("admin", "admin123"));

        users.add(new User("user", "user123"));

        Cinema cinema = new Cinema("Кинотеатр 1");
        Hall hall = new Hall("Зал 1", 5, 5);
        Movie movie = new Movie("Фильм 1", 120);
        try {
            Session session = new Session(movie, new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2025-07-01 18:00"), hall);
            hall.addSession(session);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cinema.addHall(hall);
        cinemas.add(cinema);
    }

    private static void login(Scanner scanner) {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        for (Admin admin : admins) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                currentUser = admin;
                System.out.println("Администратор вошел в систему");
                return;
            }
        }

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Пользователь вошел в систему");
                return;
            }
        }

        System.out.println("Неверный логин или пароль");
    }

    private static void register(Scanner scanner) {
        System.out.print("Введите логин: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        System.out.println("Пользователь зарегистрирован");
    }

    private static void adminMenu(Scanner scanner) {
        System.out.println("1. Добавить кинотеатр");
        System.out.println("2. Добавить зал");
        System.out.println("3. Создать сеанс");
        System.out.println("4. Выйти");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                addCinema(scanner);
                break;
            case 2:
                addHall(scanner);
                break;
            case 3:
                createSession(scanner);
                break;
            case 4:
                currentUser = null;
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private static void addCinema(Scanner scanner) {
        System.out.print("Введите название кинотеатра: ");
        String name = scanner.nextLine();
        cinemas.add(new Cinema(name));
        System.out.println("Кинотеатр добавлен");
    }

    private static void addHall(Scanner scanner) {
        System.out.print("Введите название кинотеатра: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinemaByName(cinemaName);
        if (cinema == null) {
            System.out.println("Кинотеатр не найден");
            return;
        }

        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        System.out.print("Введите количество рядов: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество мест в ряду: ");
        int cols = scanner.nextInt();
        scanner.nextLine(); 

        cinema.addHall(new Hall(hallName, rows, cols));
        System.out.println("Зал добавлен");
    }

    private static void createSession(Scanner scanner) {
        System.out.print("Введите название кинотеатра: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinemaByName(cinemaName);
        if (cinema == null) {
            System.out.println("Кинотеатр не найден");
            return;
        }

        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        Hall hall = findHallByName(cinema, hallName);
        if (hall == null) {
            System.out.println("Зал не найден");
            return;
        }

        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();
        System.out.print("Введите длительность фильма (в минутах): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Введите дату и время сеанса (yyyy-MM-dd HH:mm): ");
        String dateTime = scanner.nextLine();
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTime);
            Movie movie = new Movie(movieTitle, duration);
            Session session = new Session(movie, time, hall);
            hall.addSession(session);
            System.out.println("Сеанс создан");
        } catch (ParseException e) {
            System.out.println("Неверный формат даты и времени");
        }
    }

    private static void userMenu(Scanner scanner) {
        System.out.println("1. Купить билет");
        System.out.println("2. Найти ближайший сеанс");
        System.out.println("3. Выйти");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                buyTicket(scanner);
                break;
            case 2:
                findNearestSession(scanner);
                break;
            case 3:
                currentUser = null;
                break;
            default:
                System.out.println("Неверный выбор");
        }
    }

    private static void buyTicket(Scanner scanner) {
        System.out.print("Введите название кинотеатра: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = findCinemaByName(cinemaName);
        if (cinema == null) {
            System.out.println("Кинотеатр не найден");
            return;
        }

        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        Hall hall = findHallByName(cinema, hallName);
        if (hall == null) {
            System.out.println("Зал не найден");
            return;
        }

        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();
        Session session = findSessionByMovieTitle(hall, movieTitle);
        if (session == null) {
            System.out.println("Сеанс не найден");
            return;
        }

        System.out.println("План зала:");
        session.printSeatMap();

        System.out.print("Введите номер ряда: ");
        int row = scanner.nextInt();
        System.out.print("Введите номер места: ");
        int col = scanner.nextInt();
        scanner.nextLine(); 

        if (session.isSeatAvailable(row, col)) {
            session.bookSeat(row, col);
            System.out.println("Билет куплен");
        } else {
            System.out.println("Место уже занято");
        }
    }

    private static void findNearestSession(Scanner scanner) {
        System.out.print("Введите название фильма: ");
        String movieTitle = scanner.nextLine();
        Session nearestSession = null;
        Date now = new Date();

        for (Cinema cinema : cinemas) {
            for (Hall hall : cinema.halls) {
                for (Session session : hall.sessions) {
                    if (session.movie.title.equals(movieTitle)) {
                        if (nearestSession == null || session.time.before(nearestSession.time)) {
                            nearestSession = session;
                        }
                    }
                }
            }
        }

        if (nearestSession != null) {
            System.out.println("Ближайший сеанс: " + nearestSession.movie.title + " в " + nearestSession.time);
            System.out.println("Кинотеатр: " + findCinemaByHall(nearestSession.hall).name);
            System.out.println("Зал: " + nearestSession.hall.name);
        } else {
            System.out.println("Сеансы не найдены");
        }
    }

    private static Cinema findCinemaByName(String name) {
        for (Cinema cinema : cinemas) {
            if (cinema.name.equals(name)) {
                return cinema;
            }
        }
        return null;
    }

    private static Hall findHallByName(Cinema cinema, String name) {
        for (Hall hall : cinema.halls) {
            if (hall.name.equals(name)) {
                return hall;
            }
        }
        return null;
    }

    private static Session findSessionByMovieTitle(Hall hall, String title) {
        for (Session session : hall.sessions) {
            if (session.movie.title.equals(title)) {
                return session;
            }
        }
        return null;
    }

    private static Cinema findCinemaByHall(Hall hall) {
        for (Cinema cinema : cinemas) {
            if (cinema.halls.contains(hall)) {
                return cinema;
            }
        }
        return null;
    }
}