package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.InputHelper;

public class Day5 {

    List<String> lines;
    Map<String, List<String>> orderingRules = new HashMap<>();

    public Day5() {
        this.lines = new InputHelper(5).getLines();
    }

    public static void main(String... args) {
        Day5 solver = new Day5();
        solver.solve();
    }

    private void solve() {
        List<List<String>> safeSequences = new ArrayList<>();
        List<List<String>> unsafeSequences = new ArrayList<>();
        int count = 0;
        int countPt2 = 0;

        this.lines.forEach(line -> {
            if (line.contains("|")) {
                String[] parts = line.split("\\|");
                if (this.orderingRules.containsKey(parts[0])) {
                    this.orderingRules.get(parts[0]).add(parts[1]);
                } else {
                    this.orderingRules.put(parts[0], new ArrayList<>(
                            Collections.singletonList(parts[1])));
                }
            } else {
                List<String> nums = new ArrayList<>(Arrays.asList(line.split(",")));
                if (!line.isEmpty() && this.checkSafety(nums)) {
                    safeSequences.add(Arrays.asList(line.split(",")));
                } else if (!line.isEmpty()) {
                    unsafeSequences.add(fixOrderAndGetMiddleNumber(line.split(",")));
                }
            }
        });

        for (List<String> seq : safeSequences) {
            count += Integer.parseInt(seq.get(seq.size() / 2));
        }
        for (List<String> seq : unsafeSequences) {
            countPt2 += Integer.parseInt(seq.get(seq.size() / 2));
        }

        System.out.println("Part one: " + count);
        System.out.println("Part two: " + countPt2);
    }

    private List<String> fixOrderAndGetMiddleNumber(String[] line) {
        Arrays.sort(line, (o1, o2) -> {
            if (orderingRules.get(o1) != null && orderingRules.get(o1).contains(o2)) {
                return -1;
            } else if (orderingRules.get(o2) != null && orderingRules.get(o2).contains(o1)) {
                return 1;
            } else {
                return 0;
            }
        });
        return new ArrayList<>(Arrays.asList(line));
    }

    private boolean checkSafety(List<String> numbers) {
        for (int i = numbers.size() - 1; i > 0; i--) {
            String numToCheck = numbers.remove(i);
            List<String> rules = orderingRules.get(numToCheck);
            if (numbers.isEmpty()) {
                return true;
            } else if (rules != null && !Collections.disjoint(numbers, rules)) {
                return false;
            }
        }
        return true;
    }
}
