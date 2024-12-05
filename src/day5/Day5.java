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
        solver.partOne();
    }

    private void partOne() {
        List<List<String>> safeSequences = new ArrayList<>();
        int count = 0;
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
                }
            }
        });

        for (List<String> seq : safeSequences) {
            count += Integer.parseInt(seq.get(seq.size() / 2));
        }

        System.out.println(count);
    }

    private boolean checkSafety(List<String> nums) {
        for (int i = nums.size() - 1; i > 0; i--) {
            String numToCheck = nums.remove(i);
            List<String> rules = orderingRules.get(numToCheck);
            if (nums.isEmpty()) {
                return true;
            } else if (rules != null && !Collections.disjoint(nums, rules)) {
                return false;
            }
        }
        return true;
    }
}
