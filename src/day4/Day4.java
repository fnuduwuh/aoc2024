package day4;

import tools.InputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    private final List<String> lines;
    Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
    Pattern patternPart2 = Pattern.compile("MAS|SAM");
    public Day4() {
        lines = new InputHelper(4).getLines();
    }
    public static void main(String... args) {
        Day4 solver = new Day4();
        System.out.println("XMAS count: " + solver.partOne());
        System.out.println("XMAS count: " + solver.partTwo());
    }

    private int partTwo() {
        String[][] array = new String[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            array[i] = new String[lines.get(i).length()];
            for (int j = 0; j < lines.get(i).length(); j++) {
                array[i][j] = String.valueOf(lines.get(i).charAt(j));
            }
        }
        int counter = 0;

        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array[i].length-1; j++) {
                if (array[i][j].equalsIgnoreCase("A") && i > 0 && j > 0) {
                    String string1 = array[i-1][j-1] + array[i][j] + array[i+1][j+1];
                    String string2 = array[i+1][j-1] + array[i][j] + array[i-1][j+1];
                    if (patternPart2.matcher(string1).find() && patternPart2.matcher(string2).find()) {
                       counter++;
                    }
                }
            }
        }

        return counter;
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
