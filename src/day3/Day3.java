package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.InputHelper;

public class Day3 {

    List<String> input;

    public Day3() {
        input = new InputHelper(3).getLines();
    }

    public static void main(String... args) {
        Day3 solver = new Day3();
        String regexPt1 = "mul\\([0-9]{1,3},[0-9]{1,3}\\)";
        String regexPt2 = "mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)";
        System.out.println("total for part one is " + solver.solve(regexPt1));
        System.out.println("total for part two is " + solver.solve(regexPt2));
    }

    private int multiply(List<String> entries) {
        int total = 0;
        boolean enabled = true;
        for (String entry : entries) {
            if (entry.contains("mul") && enabled) {
                String[] splitString = entry.split(",");
                int no1 = Integer.parseInt(splitString[0].substring(splitString[0].indexOf("(") + 1));
                int no2 = Integer.parseInt(splitString[1].substring(0, splitString[1].indexOf(")")));
                total += no1 * no2;
            }
            else if (entry.equalsIgnoreCase("don't()")) {
                enabled = false;
            }
            else if (entry.equalsIgnoreCase("do()")) {
                enabled = true;
            }
        }
        return total;
    }

    private int solve(String regex) {
        List<String> validMuls = new ArrayList<>();
        for (String line : this.input) {
            Pattern regexPattern = Pattern.compile(regex);
            Matcher matcher = regexPattern.matcher(line);
            while (matcher.find())
                validMuls.add(matcher.group());
        }
        return multiply(validMuls);
    }
}
