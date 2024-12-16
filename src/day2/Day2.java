package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

    static final int minimumSaveChange = 1;
    static final int maximumSaveChange = 3;
    static ArrayList<int[]> reports = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readInput();

        task1();
        task2();
    }

    private static void task1() {
        int sum = 0;

        for (int[] report : reports) {
            sum += isSaveReport(report) ? 1 : 0;
        }

        System.out.println("Number of save reports: " + sum);
    }

    private static void task2() {
        int sum = 0;

        for (int[] report : reports) {
            sum += isSaveWithDampener(report, false) ? 1 : 0;
        }

        System.out.println("Number of save reports: " + sum);
    }

    private static boolean isSaveReport(int[] report) {
        if (report.length < 2)
            return true;

        boolean isIncreasing = report[0] < report[1];

        for (int i = 0; i < report.length - 1; i++) {
            if (isIncreasing && report[i] > report[i + 1])
                return false;

            if (!isIncreasing && report[i] < report[i + 1])
                return false;

            int difference = Math.abs(report[i] - report[i + 1]);
            if (difference < minimumSaveChange || difference > maximumSaveChange)
                return false;
        }

        return true;
    }

    private static boolean isSaveWithDampener(int[] report, boolean oneWasPassed) {
        if (report.length < 4)
            return true;

        boolean isIncreasing = decideOnIncreasing(report);

        for(int i = 0; i < report.length - 1; i++) {
            int[] newArray1 = new int[report.length - 1];
            System.arraycopy(report, 0, newArray1, 0, i);
            System.arraycopy(report, i + 1, newArray1, i, report.length - i - 1);

            int[] newArray2 = new int[report.length - 1];
            System.arraycopy(report, 0, newArray2, 0, i + 1);
            System.arraycopy(report, i + 2, newArray2, i + 1, report.length - i - 2);

            if(isIncreasing && report[i] > report[i + 1]) {
                if(oneWasPassed)
                    return false;
                return isSaveWithDampener(newArray1, true) || isSaveWithDampener(newArray2, true);
            }

            if(!isIncreasing && report[i] < report[i + 1]) {
                if(oneWasPassed)
                    return false;
                return isSaveWithDampener(newArray1, true) || isSaveWithDampener(newArray2, true);
            }

            int difference = Math.abs(report[i] - report[i + 1]);
            if(difference < minimumSaveChange || difference > maximumSaveChange) {
                if(oneWasPassed)
                    return false;
                return isSaveWithDampener(newArray1, true) || isSaveWithDampener(newArray2, true);
            }

        }

        return true;
    }

    private static boolean decideOnIncreasing(int[] report) {
        int firstDifference = report[0] - report[1];
        int secondDifference = report[1] - report[2];
        int thirdDifference = report[2] - report[3];

        int positiveCount  = (firstDifference > 0 ? 1 : 0) + (secondDifference > 0 ? 1 : 0) + (thirdDifference > 0 ? 1 : 0);
        return positiveCount < 2;
    }


private static void readInput() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("src/day2/input"));
    String line;

    while ((line = br.readLine()) != null) {
        String[] reportParts = line.split(" ");
        int[] report = new int[reportParts.length];
        for (int i = 0; i < reportParts.length; i++) {
            report[i] = Integer.parseInt(reportParts[i]);
        }
        reports.add(report);
    }
}
}
