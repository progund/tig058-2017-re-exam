import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class ScanningLines {
  public static void main(String[] args) {
    File csv = new File("birthdays.txt");
    try {
      Scanner scanner = new Scanner(csv);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println("Line: " + line);
      }
    } catch (IOException ioe) {
      System.out.println("Error opening file: " + ioe.getMessage());
    }
  }
}
