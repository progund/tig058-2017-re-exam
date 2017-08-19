import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.File;

/* Version where we hide the Scanner inside 
 * allBirthdays(), removing the need for
 * a method readBirthday(Scanner).
 */
public class Main {

  public static final String CSV_FILE = "birthdays.txt";
  public static void main(String[] args) throws Exception {
    List<Birthday> birthdays = allBirthdays();
    List<Birthday> teens = teens(birthdays);
    printInfo(teens);
  }

  static void printInfo(List<Birthday> teens) {
    if (teens.size() == 0) {
      System.out.println("No teens.");
      return;
    }
    int numberOfTeens = teens.size();
    // Sort the teens based on yearsOld
    Collections.sort(teens, Comparator.comparing(Birthday::yearsOld));
    // Youngest teen must now be the first one
    Birthday youngestTeen = teens.get(0);
    // Oldest must be the last one - index size -1
    Birthday oldestTeen = teens.get(numberOfTeens -1);
    System.out.println("There are " + numberOfTeens + " teens.");
    System.out.println(youngestTeen.name() + " is the youngest one" +
                       " and he/she is " + youngestTeen.yearsOld() +
                       " years old.");
    System.out.println(oldestTeen.name() + " is the oldest one" +
                       " and he/she is " + oldestTeen.yearsOld() +
                       " years old.");
  }
  
  static List<Birthday> allBirthdays() throws IOException {
    List<Birthday> birthdays = new ArrayList<>();
    Scanner scanner = new Scanner(new File(CSV_FILE));
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      birthdays.add(new Birthday(line.split(";")[0],
                                 line.split(";")[1],
                                 Integer.parseInt(line.split(";")[2])));
    }
    return birthdays;
  }

  static List<Birthday> teens(List<Birthday> birthdays) {
    return birthdays
      .stream()
      .filter(b ->
              b.yearsOld() > 12 &&
              b.yearsOld() < 20)
      .collect(Collectors.toList());
  }
}
