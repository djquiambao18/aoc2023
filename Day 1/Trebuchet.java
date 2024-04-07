import java.io.BufferedReader;
import java.io.Map;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Trebuchet {

  private static java.util.Map<String, Integer> wordNums = Map.ofEntries(
      new AbstractMap.SimpleEntry<String, Integer>("one", 1),
      new AbstractMap.SimpleEntry<String, Integer>("two", 2),
      new AbstractMap.SimpleEntry<String, Integer>("three", 3),
      new AbstractMap.SimpleEntry<String, Integer>("four", 4),
      new AbstractMap.SimpleEntry<String, Integer>("five", 5),
      new AbstractMap.SimpleEntry<String, Integer>("six", 6),
      new AbstractMap.SimpleEntry<String, Integer>("seven", 7),
      new AbstractMap.SimpleEntry<String, Integer>("eight", 8),
      new AbstractMap.SimpleEntry<String, Integer>("nine", 9),
      new AbstractMap.SimpleEntry<String, Integer>("ten", 10)
    );

  // String contains alphanumeric where the first digit and the last digit 
  // makes the calibration value
  // parses the number word or int from String
  private static int calibrationValue(String calibrationString) {
    char[] chars = calibrationString.toCharArray();
    StringBuilder sb = (StringBuilder) calibrationString;
    StringBuilder digitBuilder = new StringBuilder();

    // For Part 2 of AoC 2023 - Day 1, trebuchet
    // Loop over the string to convert the words to digits, then iterate through a new String. Use StringBuilder
    for (Map.Entry<String, Integer> entry : wordNums.entrySet()) {
      // multiple operations, can we improve?
      if(sb.contains(entry.getKey())) {
        short index = sb.indexOf(entry.getKey());
        // expensive compute time - insert and delete operations, only single-threaded
        sb.insert(index, (char) entry.getValue());
        sb.delete(index + 1, entry.getKey().length);
        
        // TODO: performance optimization, only consider the closest to the beginning or end of string
        // 1. check the indices of each matching word
        // 2. see which one is closest to beginning or end of string
        // 3. replace only those.
      }
    }

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
    return Integer.parseInt(String.valueOf(sb));
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
    List<String> documentWords = readLinesFromFile("input.txt");
    List<Integer> nums = new ArrayList<>();
    for(String word : documentWords) {
      nums.add(calibrationValue(word));
    }
    System.out.println(calculateTotal(nums));
  }
}
