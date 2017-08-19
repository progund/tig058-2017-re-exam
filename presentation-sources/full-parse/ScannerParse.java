import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ScannerParse {

  private static final String CSV_FILE = "birthdays.txt";
  private static final String DELIMITER = ";";

  public static void main(String[] args) {
    try {
      List<Birthday> birthdays = getBirthdays();
      System.out.println("Found " + birthdays.size() + " birthdays.");
    } catch (IOException ioe) {
      System.err.println("Couldn't read file: " + ioe.getMessage());
    }
  }

  /*
  static Birthday birthdayFromLine(String line) {
    Scanner lineScanner = new Scanner(line);
    lineScanner.useDelimiter(DELIMITER);
    String name = lineScanner.next();
    String birthday = lineScanner.next();
    int yearsOld = lineScanner.nextInt();
    return new Birthday(name, birthday, yearsOld);
  }
  */
  
  static Birthday birthdayFromLine(String line) {
    String[] elements = line.split(DELIMITER);
    String name = elements[0];
    String birthday = elements[1];
    int yearsOld = Integer.parseInt(elements[2]);
    return new Birthday(name, birthday, yearsOld);
  }
  
  static List<Birthday> getBirthdays() throws IOException {    
    Scanner fileScanner = new Scanner(new File(CSV_FILE));
    List<Birthday> birthdays = new ArrayList<>();
    while (fileScanner.hasNextLine()) {
      birthdays.add(birthdayFromLine(fileScanner.nextLine()));
    }
    return birthdays;
  }
}
