import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;

public class Main {
    // Лабораторная работа №2
    static Scanner in = new Scanner(System.in);

    static String TaskOne(String str) {
        System.out.print("\n1. Найти наибольшую подстроку без повторяющихся символов. ");
        String max_subline = "";

        boolean flag = true;
        int last_index = 0;

        while (flag) {
            String current_line = "";
            for (int i = last_index; i < str.length(); i++) {
                if (current_line.contains(String.valueOf(str.charAt(i)))) {
                    if (max_subline.length() < current_line.length()) {
                        max_subline = current_line;
                    }
                    last_index++;
                    break;
                }
                else {
                    current_line += str.charAt(i);
                    if (i == str.length() - 1) {
                        if (max_subline.length() < current_line.length()) {
                            max_subline = current_line;
                        }
                        flag = false;
                    }
                }
            }
        }
        return "\nОтвет: " + max_subline;
    }

    static int[] TaskTwo(int[] array1, int[] array2) {
        System.out.print("\n2. Объединить два отсортированных массива. ");
        int[] array = new int[array1.length + array2.length];

        for(int i = 0; i < array1.length; i++) {
            array[i] = array1[i];
        }

        for(int j = array1.length; j < array.length; j++) {
            array[j] = array2[j - array1.length];
        }

        Arrays.sort(array);
        System.out.print("\nОтвет: ");
        return array;
    }

    static int TaskThree(int[] array3) {
        System.out.print("\n3.  Найти максимальную сумму подмассива. ");
        int max_sum = Integer.MIN_VALUE;
        int current_sum = 0;

        for (int num : array3) {
            current_sum += num;

            if (current_sum > max_sum) {
                max_sum = current_sum;
            }

            if (current_sum < 0) {
                current_sum = 0;
            }
        }
        System.out.print("\nОтвет: ");
        return max_sum;
    }

    static int[][] TaskFour(int[][] array) {
        System.out.print("\n4. Задача: Повернуть массив на 90 градусов по часовой стрелке.");
        int rows = array.length;
        int columns = array[0].length;
        int[][] transpose = new int[columns][rows];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < columns; j++) {
                transpose[j][rows - i - 1] = array[i][j];
            }
        }
        return transpose;
    }

    static int[] TaskFive(int[] array, int target) {
        System.out.print("\n5. Найти пару элементов в массиве, сумма которых равна заданному числу. \nОтвет: ");
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array.length; j++)
                if (i != j)
                    if (array[i] + array[j] == target) {
                        return new int[]{array[i], array[j]};
                    }
        return null;
    }

    static int TaskSix(int[][] array) {
        System.out.print("\n6. Найти сумму всех элементов в двумерном массиве. \nОтвет: ");
        int sum = 0;
        for (int[] line : array)
            for (int element : line)
                sum += element;
        return sum;
    }

    static int[] TaskSeven(int[][] array) {
        System.out.print("\n7. Найти максимальный элемент в каждой строке двумерного массива. ");
        int[] answer = new int[array.length];
        for (int line = 0; line < array.length; line++) {
            int max_element = Integer.MIN_VALUE;
            for (int el = 0; el < array[line].length; el++)
                if (max_element < array[line][el])
                    max_element = array[line][el];
            answer[line] = max_element;
        }
        System.out.print("\nОтвет: ");
        return answer;
    }
static int[][] TaskEight(int[][] array) {
        System.out.println("\n8. Повернуть двумерный массив на 90 градусов против часовой стрелке. \nОтвет: \n");
        int rows = array.length;
        int collumns = array[0].length;
        int[][] answer = new int[collumns][rows];
        for (int i = 0; i < rows; i++)
            for (int j = collumns - 1; j >= 0; j--)
                answer[collumns - j - 1][i] = array[i][j];
        return answer;
    }

    public static int[] readArray() {
        System.out.print("Введите размер массива: ");
        int size = in.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }
        return array;
    }

    public static int[][] readMatrix() {
        System.out.print("Введите количество строк в матрице: ");
        int rows = in.nextInt();
        System.out.print("Введите количество столбцов в матрице: ");
        int cols = in.nextInt();
        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println("Выберите задание (1-8): ");
        int taskNumber = in.nextInt();

        switch (taskNumber) {
            case 1:
                System.out.print("Введите строку: ");
                String str = in.next();
                System.out.println(TaskOne(str));
                break;
            case 2:
                System.out.println("Введите первый отсортированный массив:");
                int[] array1 = readArray();
                System.out.println("Введите второй отсортированный массив:");
                int[] array2 = readArray();
                System.out.println(Arrays.toString(TaskTwo(array1, array2)));
                break;
            case 3:
                System.out.println("Введите массив для поиска максимальной суммы подмассива:");
                int[] array3 = readArray();
                System.out.println(TaskThree(array3));
                break;
            case 4:
                System.out.println("Введите двумерный массив для поворота:");
                int[][] array4 = readMatrix();
                int[][] rotatedArray = TaskFour(array4);
                System.out.println("\nОтвет: ");
                for (int[] rows : rotatedArray) {
                    for (int element : rows) {
                        System.out.print(element + " ");
                    }
                    System.out.println();
                }
                break;
            case 5:
                System.out.println("Введите массив для поиска пары элементов:");
                int[] array5 = readArray();
                System.out.print("Введите целевое число: ");
                int target = in.nextInt();
                System.out.println(Arrays.toString(TaskFive(array5, target)));
                break;
            case 6:
                System.out.println("Введите двумерный массив для нахождения суммы элементов:");
                int[][] array6 = readMatrix();
                System.out.println(TaskSix(array6));
                break;
            case 7:
                System.out.println("Введите двумерный массив для поиска максимальных элементов в строках:");
                int[][] array7 = readMatrix();
                System.out.println(Arrays.toString(TaskSeven(array7)));
                break;
            case 8:
                System.out.println("Введите двумерный массив для поворота:");
                int[][] array8 = readMatrix();
                int[][] rotatedArray8 = TaskEight(array8);
                for (int[] rows : rotatedArray8) {
                    for (int element : rows) {
                        System.out.print(element + " ");
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }
}