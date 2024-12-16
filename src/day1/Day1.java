package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day1 {

    static ArrayList<Integer> column1 = new ArrayList<>();
    static ArrayList<Integer> column2 = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readInput();
        assert column1.size() == column2.size();

        Collections.sort(column1);
        Collections.sort(column2);

        task1();
        task2();
    }

    private static void task1() {
        int sum = 0;
        for(int i = 0; i < column1.size(); i++) {
            sum += Math.abs(column1.get(i) - column2.get(i));
        }

        System.out.println("Sum of differences: " + sum);
    }

    private static void task2() {
        HashMap<Integer, Integer> occurrencesOnColumn2 = new HashMap<>();
        for(int number : column2) {
            occurrencesOnColumn2.put(number, occurrencesOnColumn2.getOrDefault(number, 0) + 1);
        }

        int sum = 0;
        for(int number : column1) {
            Integer occurrences = occurrencesOnColumn2.get(number);
            if(occurrences == null || occurrences == 0)
                continue;

            sum += number * occurrencesOnColumn2.get(number);
        }

        System.out.println("Sum of multiplied numbers: " + sum);
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day1/input"));
        String line;

        while((line = br.readLine()) != null) {
            String[] parts = line.split(" {3}");
            column1.add(Integer.parseInt(parts[0]));
            column2.add(Integer.parseInt(parts[1]));
        }
    }
}


