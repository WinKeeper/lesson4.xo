package homework4.eristarion;

import java.util.Random;
import java.util.Scanner;

public class checkWin {
    static char[][] map;

    static final int SIZE = 5; //размер игрового поля

    static final char DOT_PLAYER = 'X';
    static final char DOT_AI = 'O';
    static final char DOT_EMPTY = '*';

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static final int WINCON = 4; // количество символов подряд для победы
    static int winConCount; //переменная в которую мы передаём количество совпадений
    static int turnsToWin; //попробую вернуть количество ходов до победы (скорее всего один)


    public static void main(String[] args) {
        prepareMap();
        printMap();

        while (true) {
            playerTurn();
            printMap();
//            turnsToWin();
//            System.out.println(turnsToWin());
            if (checkWin(DOT_PLAYER)) {
                System.out.println("Победил игрок!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
//            turnsToWin();
//            System.out.println(turnsToWin());
            if (checkWin(DOT_AI)) {
                System.out.println("Победил ИИ!");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }

        System.out.println("Игра завершена");
    }

    //   0 1 2
    // 0 x x x
    // 1 x x x
    // 2 x x x

    public static boolean checkWin(char dot) {
        for (int y = 0; y < SIZE; y++) { // сравниваем по горизонтали до заданного количества совпадений WinCon
            for (int x = 0; x < SIZE - 1; x++) {
                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x+1][y])  {
                    winConCount++;
                }
                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x+1][y]) {
                    winConCount = 0;
                }
                if (winConCount == WINCON - 1) {
                    return true;
                }
            }
        }
        for (int x = 0; x < SIZE; x++) { // сравниваем по вертикали до заданного количества совпадений в WinCon
            for (int y = 0; y < SIZE - 1; y++) {
                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x][y+1])  {
                    winConCount++;
                }
                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x][y+1]) {
                    winConCount = 0;
                }
                if (winConCount == WINCON - 1) {
                    return true;
                }
            }
        }
        for (int x = 0; x < SIZE - 1; x++) { // сравниваем по диагонали до заданного количества совпадений в WinCon
            for (int y = 0; y < SIZE - 1; y++, x++) {
                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x+1][y+1])  {
                    winConCount++;
                }
                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x+1][y+1]) {
                    winConCount = 0;
                }
                if (winConCount == WINCON - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static int turnsToWin() {
//        for (int y = 0; y < SIZE; y++) { // сравниваем по горизонтали до заданного количества совпадений WinCon
//            for (int x = 0; x < SIZE - 1; x++) {
//                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x+1][y])  {
//                    turnsToWin++;
//                }
//                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x+1][y]) {
//                    turnsToWin = 0;
//                }
//                if (winConCount == WINCON - 2) {
//                    return turnsToWin;
//                }
//            }
//        }
//        for (int x = 0; x < SIZE; x++) { // сравниваем по вертикали до заданного количества совпадений в WinCon
//            for (int y = 0; y < SIZE - 1; y++) {
//                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x][y+1])  {
//                    turnsToWin++;
//                }
//                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x][y+1]) {
//                    turnsToWin = 0;
//                }
//                if (winConCount == WINCON - 2) {
//                    return turnsToWin;
//                }
//            }
//        }
//        for (int x = 0; x < SIZE - 1; x++) { // сравниваем по диагонали до заданного количества совпадений в WinCon
//            for (int y = 0; y < SIZE - 1; y++, x++) {
//                if (map[x][y] != DOT_EMPTY && map[x][y] == map[x+1][y+1])  {
//                    turnsToWin++;
//                }
//                if (map[x][y] != DOT_EMPTY && map[x][y] != map[x+1][y+1]) {
//                    turnsToWin = 0;
//                }
//                if (winConCount == WINCON - 2) {
//                    return turnsToWin;
//                }
//            }
//        }
//        return turnsToWin;
//    }

    public static void aiTurn() {
        if (turnsToWin == WINCON - 2) { //тут должен быть перебор с ещё одним методом, проверяющим возможность постановки символа НЕ ЗА пределы поля относительно символа игрока.
            int x, y;
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellEmpty(x, y));
            map[x][y] = DOT_AI;
            System.out.printf("Ход ИИ: [%d, %d]\n", x + 1, y + 1);
        } else {
            int x, y;
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellEmpty(x, y));
            map[x][y] = DOT_AI;
            System.out.printf("Ход ИИ: [%d, %d]\n", x + 1, y + 1);
        }
    }

    public static void playerTurn() {
        int x, y;
        do {
            System.out.println("Укажите координаты хода в формате 'x y'");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellEmpty(x, y)); // >>> while (isCellEmpty(x, y) == false)
        map[x][y] = DOT_PLAYER;
    }

    public static boolean isCellEmpty(int x, int y) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int y = 0; y < SIZE; y++) {
            System.out.print((y + 1) + " ");
            for (int x = 0; x < SIZE; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void prepareMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}
