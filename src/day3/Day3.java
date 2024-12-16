package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    static String input;

    public static void main(String[] args) throws IOException {
        readInput();

        task1();
        task2();
    }

    private static void task1() {
        int sum = 0;
        String regex = "mul\\((\\d+),(\\d+)\\)";

        List<String> occurrences = findAllOccurrences(input, regex);
        for(String occurrence : occurrences) {
            occurrence = occurrence.replaceAll("mul\\(", "");
            occurrence = occurrence.replaceAll("\\)", "");

            String [] numbers = occurrence.split(",");

            sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
        }

        System.out.println("Sum of multiplications: " + sum);
    }

    private static void task2() {

        int sum = 0;
        String regex = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)";

        List<String> occurrences = findAllOccurrences(input, regex);
        boolean doFlag = true;

        for (String occurrence : occurrences) {
            if (occurrence.equals("do()")) {
                doFlag = true;
                continue;
            }

            if (occurrence.equals("don't()")) {
                doFlag = false;
                continue;
            }

            occurrence = occurrence.replaceAll("mul\\(", "");
            occurrence = occurrence.replaceAll("\\)", "");

            String[] numbers = occurrence.split(",");

            if (doFlag)
                sum += Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
        }

        System.out.println("Sum of multiplications: " + sum);
    }

    public static List<String> findAllOccurrences(String input, String regex) {
        List<String> occurrences = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            occurrences.add(matcher.group());
        }

        return occurrences;
    }

    private static void readInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/day3/input"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        input = sb.toString();
    }
}
