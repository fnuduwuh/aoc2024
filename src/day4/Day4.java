package day4;

import tools.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    private final List<String> lines;
    Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
    public Day4() {
        lines = new InputHelper(4).getLines();
    }
    public static void main(String... args) {
        Day4 solver = new Day4();
        System.out.println("XMAS count: " + solver.partOne());
    }

    private int partOne() {
        int count = 0;

        count += checkLines(lines);
        count += checkLines(getVertical());
        count += checkLines(getDiagonal(lines));
        count += checkLines(getDiagonal(getLinesReversed(lines)));

        return count;
    }

    private List<String> getLinesReversed(List<String> linesToGet) {
        return linesToGet.stream().map(line -> new StringBuilder(line).reverse().toString()).toList();
    }

    private List<String> getVertical() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < lines.getFirst().length(); i++) {
            StringBuilder verticalString = new StringBuilder();
            for (String line : lines) {
                verticalString.append(line.charAt(i));
            }
            result.add(verticalString.toString());
        }
        return result;
    }

    private List<String> getDiagonal(List<String> linesToGet) {
        List<String> result = new ArrayList<>();
        int noOfLines = linesToGet.size();
        for (int i = 0; i < noOfLines * 2; i++) {
            StringBuilder diagonalString = new StringBuilder();
            for (int j = 0; j <= i; j++) {
                int index = i - j;
                if (index < noOfLines && j < noOfLines) {
                    diagonalString.append(linesToGet.get(index).charAt(j));
                }
            }
            result.add(diagonalString.toString());
        }
        return result;
    }

    private int checkLines(List<String> linesToCheck) {
        int count = 0;
        for (String line : linesToCheck) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                count++ ;
            }
        }
        return count;
    }
}
