package day2;

import tools.InputHelper;

import java.util.Arrays;
import java.util.List;

public class Day2 {
    List<String> input;

    public Day2() {
        input = new InputHelper(2).getLines();
    }

    public static void main(String... args) {
        Day2 solver = new Day2();
        System.out.println(solver.partOne());
    }

    private int partOne() {
        int safeReports = 0;

        for (String line : input) {
            List<Integer> report = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
            if (checkReport(report)) safeReports++;
        }
        System.out.print("Calculated amount of SAFE reports: ");
        return safeReports;
    }

    private boolean checkReport(List<Integer> report) {
        boolean ascending = report.get(0) < report.get(1);

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            } else if (ascending && report.get(i) > report.get(i + 1)) {
                return false;
            } else if (!ascending && report.get(i) < report.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
