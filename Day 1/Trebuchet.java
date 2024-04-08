import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trebuchet {

  private static final java.util.Map<String, Integer> wordNums = Map.ofEntries(
      new AbstractMap.SimpleEntry<>("one", 1),
      new AbstractMap.SimpleEntry<>("two", 2),
      new AbstractMap.SimpleEntry<>("three", 3),
      new AbstractMap.SimpleEntry<>("four", 4),
      new AbstractMap.SimpleEntry<>("five", 5),
      new AbstractMap.SimpleEntry<>("six", 6),
      new AbstractMap.SimpleEntry<>("seven", 7),
      new AbstractMap.SimpleEntry<>("eight", 8),
      new AbstractMap.SimpleEntry<>("nine", 9),
      new AbstractMap.SimpleEntry<>("ten", 10)
    );

  // String contains alphanumeric where the first digit and the last digit
  // makes the calibration value
  // parses the number word or int from String
  private static int calibrationValue(String calibrationString) {
    StringBuilder sb = new StringBuilder(calibrationString);
    StringBuilder digitBuilder = new StringBuilder();

    // For Part 2 of AoC 2023 - Day 1, trebuchet
    // Loop over the string to convert the words to digits, then iterate through a new String. Use StringBuilder
    for (Map.Entry<String, Integer> entry : wordNums.entrySet()) {
      String key = entry.getKey();
      int index = sb.indexOf(key);
      while(index != -1) {
        sb.replace(index+1, index + 2, entry.getValue().toString());
        index = sb.indexOf(key);
      }
    }

    char[] chars = sb.toString().toCharArray();
    // Part 1:
    // Actually check for the digits.
    // Check from beginning:
    for (char c : chars) {
      if(Character.isDigit(c)) {
        digitBuilder.append(c);
        break;
      }
    }

    // Check from end:
    for (int i = chars.length - 1; i >= 0; i--) {
      if(Character.isDigit(chars[i])) {
        digitBuilder.append(chars[i]);
        break;
      }
    }
    return Integer.parseInt(String.valueOf(digitBuilder));
  }

  private static int calculateTotal(List<Integer> nums) {
    int total = 0;
    for(Integer num : nums) {
      total += num;
    }
    return total;
  }

  public static List<String> readLinesFromFile(String fileName) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace(); // Handle file IO exception here
    }
    return lines;
  }

  public static void main(String[] args) {
    // Read the file
    List<String> documentWords = readLinesFromFile(args[0]);
    List<Integer> nums = new ArrayList<>();
    for(String word : documentWords) {
      nums.add(calibrationValue(word));
    }
    System.out.println(calculateTotal(nums));
  }
}
