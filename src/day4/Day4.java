package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {

    static char [][] grid;
    static int X_SIZE;
    static int Y_SIZE;

    public static void main(String[] args) throws IOException {
        readInput();

        task1();
        task2();
    }

    private static void task1() {
        int sum = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'X')
                    sum += numberOfChristmasFromPoint(x, y);
            }
        }

        System.out.println("Number of XMAS: " + sum);
    }

    private static void task2() {
        int sum = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'A')
                    sum += isMiddleOfX(x, y) ? 1 : 0;
            }
        }

        System.out.println("Number of x'es of MAS: " + sum);
    }

    private static int numberOfChristmasFromPoint(int x, int y) {
        int sum = 0;

        if (y + 3 < Y_SIZE && checkMAS(y + 1, x, 1, 0)) sum++;
        if (y - 3 >= 0 && checkMAS(y - 1, x, -1, 0)) sum++;
        if (x + 3 < X_SIZE && checkMAS(y, x + 1, 0, 1)) sum++;
        if (x - 3 >= 0 && checkMAS(y, x - 1, 0, -1)) sum++;
        if (y + 3 < Y_SIZE && x + 3 < X_SIZE && checkMAS(y + 1, x + 1, 1, 1)) sum++;
        if (y - 3 >= 0 && x - 3 >= 0 && checkMAS(y - 1, x - 1, -1, -1)) sum++;
        if (y + 3 < Y_SIZE && x - 3 >= 0 && checkMAS(y + 1, x - 1, 1, -1)) sum++;
        if (y - 3 >= 0 && x + 3 < X_SIZE && checkMAS(y - 1, x + 1, -1, 1)) sum++;

        return sum;
    }

    private static boolean isMiddleOfX(int x, int y) {
        if(x == 0 || x == X_SIZE - 1 || y == 0 || y == Y_SIZE - 1)
            return false;

        char topLeft = grid[y-1][x-1];
        char topRight = grid[y-1][x+1];
        char bottomLeft = grid[y+1][x-1];
        char bottomRight = grid[y+1][x+1];

        if(!((topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M')))
            return false;

        return topRight == 'M' && bottomLeft == 'S' || topRight == 'S' && bottomLeft == 'M';
    }

    private static boolean checkMAS(int y, int x, int dy, int dx) {
        return grid[y][x] == 'M' && grid[y + dy][x + dx] == 'A' && grid[y + 2 * dy][x + 2 * dx] == 'S';
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day4/input"));
        String line;
        Y_SIZE = 0;

        while (br.readLine() != null) {
            Y_SIZE++;
        }
        br.close();

        grid = new char[Y_SIZE][];

        br = new BufferedReader(new FileReader("src/day4/input"));
        int index = 0;
        while ((line = br.readLine()) != null) {
            X_SIZE = line.length();
            grid[index++] = line.toCharArray();
        }
        br.close();
    }
}
