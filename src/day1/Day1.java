package day1;

import tools.InputHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 {
    List<String> input;
    List<String> leftList = new ArrayList<>();
    List<String> rightList = new ArrayList<>();

    public Day1() {
        input = new InputHelper(1).getLines();
        for (String line : input) {
            leftList.add(line.split(" {3}")[0]);
            rightList.add(line.split(" {3}")[1]);
        }
    }

    public static void main(String... args) {
        Day1 solver = new Day1();
        System.out.println(solver.partOne());
        System.out.println(solver.partTwo());
    }

    public String partOne() {
        int result = 0;

        leftList.sort(Comparator.comparingInt(Integer::valueOf));
        rightList.sort(Comparator.comparingInt(Integer::valueOf));

        for (int i = 0; i < leftList.size(); i++) {
            result += Math.abs(Integer.parseInt(leftList.get(i)) - Integer.parseInt(rightList.get(i)));
        }

        return String.valueOf(result);
    }

    public String partTwo() {
        int result = 0;
        for (String valLeft : leftList) {
            int counter = 0;
            for (String valRight : rightList) {
                if (valLeft.equals(valRight)) {
                    counter++;
                }
            }
            result += Integer.parseInt(valLeft) * counter;
        }
        return String.valueOf(result);
    }
}
