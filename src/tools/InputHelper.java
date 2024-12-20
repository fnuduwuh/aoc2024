package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputHelper {
    private List<String> lines;

    public InputHelper(int day) {
        try {
            lines = Files.readAllLines(Paths.get(String.format("src/day%s/input.txt", day)));
        } catch (IOException e) {
            System.out.println("Er ging iets mis met het inlezen van het bestand");
            e.printStackTrace();
        }
    }

    public String[][] get2dArray() {
        String[][] output = new String[this.lines.size()][];
        for (int i = 0; i < this.lines.size(); i++) {
            output[i] = lines.get(i).split("");
        }
        return output;
    }

    public List<String> getLines() {
        return this.lines;
    }
}
