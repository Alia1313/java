import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        switch (name) {
            case "1":
                task1(scan);
                break;
            case "2":
                task2(scan);
                break;
            case "3":
                task3(scan);
                break;
            case "4":
                task4(scan);
                break;
            case "5":
                task5(scan);
                break;
            default:
                break;
        }
    }

    public static void task1(Scanner scan) {
        int n = scan.nextInt();
        int step = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            step++;
        }
        System.out.println(step);
    }

    public static void task2(Scanner scan) {
        int n = scan.nextInt();
        int step = 0;
        int sum = 0;
        while (n > 0) {
            int x = scan.nextInt();
            step++;
            if (step % 2 == 0) {
                sum -= x;
            } else {
                sum += x;
            }
            n -= 1;
        }
        System.out.println(sum);
    }

    public static void task3(Scanner scan) {
        int x_Treasure = scan.nextInt();
        int y_Treasure = scan.nextInt();
        int stepCount = 0;
        int y = 0;
        int x = 0;
        while (true) {
            String direction = scan.next();
            if (direction.equals("стоп")) {
                break;
            }
            int step = scan.nextInt();
            if (direction.equals("север")) {
                y += step;
            } else if (direction.equals("юг")) {
                y -= step;
            } else if (direction.equals("восток")) {
                x += step;
            } else if (direction.equals("запад")) {
                x -= step;
            }
            stepCount++;
            if (x == x_Treasure && y == y_Treasure) {
                System.out.println(stepCount);
                return;
            }
        }
        System.out.println("Сокровище не найдено.");
    }

    public static void task4(Scanner scan) {
        int k_road = scan.nextInt();
        int max = 0;
        for (int i = 0; i < k_road; i++) {
            int k_tunnel = scan.nextInt();
            int min = 100000;
            for (int j = 0; j < k_tunnel; j++) {
                int height = scan.nextInt();
                if (height < min) {
                    min = height;
                }
            }
            if (min > max) {
                max = min;
            }
        }
        System.out.println(max);
    }

    public static void task5(Scanner scan) {
        int x = scan.nextInt();
        int s = 0;
        int p = 1;
        while (x > 0) {
            s += x % 10;
            p *= x % 10;
            x /= 10;
        }
        if (s % 2 == 0 && p % 2 == 0) {
            System.out.println("число является дважды четным");
        } else {
            System.out.println("число не является дважды четным");
        }
    }
}
