import java.util.Scanner;

public class ScanLine {
  public static void main(String[] args) {
    String line = "Henrik,April 13,46";
    Scanner lineScanner = new Scanner(line);
    lineScanner.useDelimiter(",");
    while (lineScanner.hasNext()) {
      System.out.println("Element: " + lineScanner.next());
    }
  }
}
