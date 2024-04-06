import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Trebuchet {

  // String contains alphanumeric where the first digit and the last digit 
  // makes the calibration value
  private static int calibrationValue(String calibrationString) {
    char[] chars = calibrationString.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (char c : chars) {
      if(Character.isDigit(c)) {
        sb.append(c);
        break;
      }
    }
    for (int i = chars.length - 1; i >= 0; i--) {
      if(Character.isDigit(chars[i])) {
        sb.append(chars[i]);
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
